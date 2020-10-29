package br.com.tommiranda.interpreter;

import br.com.tommiranda.exceptions.WrongArgumentsException;
import br.com.tommiranda.lang.Func;
import br.com.tommiranda.lang.Global;
import br.com.tommiranda.lang.Procedure;
import br.com.tommiranda.utils.ErrorMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {

    public static Object eval(Object val, Env env) {
        while (true) {
            if (val == null) {
                return null;
            }

            if (val instanceof Symbol) {
                return env.getSymbolValue(((Symbol) val).getName());
            } else if (!(val instanceof List)) {
                return val;
            }

            List<Object> expr = (List<Object>) val;
            Object op = expr.get(0);
            List<Object> args = expr.subList(1, expr.size());

            if (op.equals(new Symbol("quote"))) {
                return args.get(0);
            } else if (op.equals(new Symbol("if"))) {
                Object test = args.get(0);
                Object then = args.get(1);
                Object otherwise = args.get(2);

                val = Truth.isTrue(eval(test, env)) ? then : otherwise;
            } else if (op.equals(new Symbol("define"))) {
                Symbol symbol = (Symbol) args.get(0);
                Object body = args.get(1);
                env.addSymbol(symbol.getName(), eval(body, env));
                return null;
            } else if (op.equals(new Symbol("set!"))) {
                Symbol symbol = (Symbol) args.get(0);
                env.findEnv(symbol.getName()).put(symbol.getName(), eval(args.get(1), env));
                return null;
            } else if (op.equals(new Symbol("lambda"))) {
                List<Symbol> params = ((List<Symbol>) args.get(0)).stream().map(a -> (Symbol) a).collect(Collectors.toList());
                Object body = args.get(1);

                return new Procedure(params, body, env);
            } else {
                Object func = eval(op, env);

                List<Object> arguments = new ArrayList<>();
                for (Object arg : args) {
                    arguments.add(eval(arg, env));
                }

                if (func instanceof Procedure) {
                    Procedure proc = (Procedure) func;
                    val = proc.getBody();
                    env = new Env(proc.getParams(), arguments, proc.getEnv());
                } else {
                    return ((Func) func).exec(arguments);
                }
            }
        }
    }

    public static Object expand(Object val, boolean toplevel) {
        if (!(val instanceof List)) {
            return val;
        }

        List<Object> expr = (List<Object>) val;
        if (expr.isEmpty()) {
            return null;
        }

        Object op = expr.get(0);
        List<Object> args = expr.subList(1, expr.size());

        if (op.equals(new Symbol("quote"))) {
            requireSize("quote", args, 1);
            return val;
        } else if (op.equals(new Symbol("if"))) {
            // (if t c) => (if t c null)
            if (args.size() == 2) {
                args.add(null);
            }

            requireSize("if", args, 3);
            return expr.stream()
                       .map(e -> expand(e, false))
                       .collect(Collectors.toList());
        } else if (op.equals(new Symbol("set!"))) {
            requireSize("set!", args, 2);
            return Arrays.asList(op, args.get(0), expand(args.get(1), false));
        } else if (op.equals(new Symbol("define")) || op.equals(new Symbol("define-macro"))) {
            requireAtLeast(op.toString(), args, 2);
            Object v = args.get(0);
            List<Object> body = args.subList(1, args.size());

            //(define (f args) body) => (define f (lambda (args) body))
            if (v instanceof List) {
                List<Object> lbdDef = (List<Object>) v;
                Object f = lbdDef.get(0);
                List<Object> params = lbdDef.subList(1, lbdDef.size());

                List<Object> lambda = new ArrayList<>();
                lambda.add(new Symbol("lambda"));
                lambda.add(params);
                lambda.addAll(body);
                return expand(Arrays.asList(op, f, lambda), false);
            } else {
                requireSize(op.toString(), args, 2);
                requireSymbol(op.toString(), v);
                Object exp = expand(args.get(1), false);

                if (op.equals(new Symbol("define-macro"))) {
                    if (!toplevel) {
                        throw new WrongArgumentsException("define-macro only allowed at top level");
                    }
                    Object func = eval(exp, Global.getEnv());
                    requireFunc(func);
                    Global.addMacro((Symbol) v, (Func) func);
                    return null;
                }

                return Arrays.asList(new Symbol("define"), v, exp);
            }
        } else if (op.equals(new Symbol("begin"))) {
            // (begin) => None
            if (expr.size() == 1) {
                return null;
            }

            return expr.stream()
                       .map(e -> expand(e, toplevel))
                       .collect(Collectors.toList());
        } else if (op.equals(new Symbol("lambda"))) {
            // (lambda (x) e1 e2) => (lambda (x) (begin e1 e2))
            requireAtLeast(op.toString(), args, 2);
            requireList(op.toString(), args.get(0));
            List<Object> params = (List<Object>) args.get(0);
            List<Object> body = args.subList(1, args.size());
            requireSymbol(op.toString(), params);
            Object exp = body.size() == 1 ? body.get(0) : Arrays.asList(new Symbol("begin"), body);
            return Arrays.asList(new Symbol("lambda"), params, expand(exp, false));
        } else if (op.equals(new Symbol("quasiquote"))) {
            requireSize(op.toString(), args, 1);
            return expandQuasiquote(args.get(0));
        } else if (op instanceof Symbol && Global.getMacro((Symbol) op) != null) {
            return expand(Global.getMacro((Symbol) op).exec(args), toplevel);
        } else {
            return expr.stream()
                       .map(e -> expand(e, false))
                       .collect(Collectors.toList());
        }
    }

    public static Object expandQuasiquote(Object val) {
        if (!isPair(val)) {
            return Arrays.asList(new Symbol("quote"), val);
        }

        if (val.equals(new Symbol("unquote-splicing"))) {
            throw new WrongArgumentsException("can't splice here");
        }

        List<Object> expr = (List<Object>) val;

        if (expr.get(0).equals(new Symbol("unquote"))) {
            requireSize("unquote", expr, 2);
            return expr.get(1);
        } else if (isPair(expr.get(0))) {
            List<Object> list = (List<Object>) expr.get(0);

            if (list.get(0).equals(new Symbol("unquote-splicing"))) {
                requireSize(expr.get(0).toString(), list, 2);
                return Arrays.asList(new Symbol("append"),
                                     list.get(1),
                                     expandQuasiquote(expr.subList(1, expr.size())));
            }
        }

        return Arrays.asList(new Symbol("cons"),
                             expandQuasiquote(expr.get(0)),
                             expandQuasiquote(expr.subList(1, expr.size())));
    }

    public static boolean isPair(Object val) {
        if (val instanceof List) {
            List<Object> list = (List<Object>) val;
            return !list.isEmpty();
        }

        return false;
    }

    public static void requireSize(String op, List<Object> list, int size) {
        if (list.size() > size) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired(op, size, list.size()));
        }
    }

    public static void requireAtLeast(String op, List<Object> list, int size) {
        if (list.size() < size) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamAtLeast(op, size, list.size()));
        }
    }

    public static void requireList(String op, Object obj) {
        if (!(obj instanceof List)) {
            throw new WrongArgumentsException(op + " contains illegal argument list");
        }
    }

    public static void requireSymbol(String op, Object sym) {
        if (sym instanceof List) {
            List<Object> list = (List<Object>) sym;

            for (Object obj : list) {
                requireSymbol(op, obj);
            }
        } else if (!(sym instanceof Symbol)) {
            throw new WrongArgumentsException(sym.toString() + " in " + op + " is not a Symbol");
        }
    }

    public static void requireFunc(Object func) {
        if (func instanceof Func) {
            return;
        }

        throw new WrongArgumentsException("macro must be a procedure");
    }
}
