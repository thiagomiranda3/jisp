package br.com.tommiranda.interpreter;

import br.com.tommiranda.lang.Func;
import br.com.tommiranda.lang.Procedure;

import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {

    public static Object eval(Object val, Env env) {
        if (val == null) {
            return null;
        }

        if (val instanceof Symbol) {
            return env.getSymbolValue(((Symbol) val).getName());
        } else if (!(val instanceof List)) {
            return val;
        }

        List<Object> expr = (List) val;
        if (expr.isEmpty()) {
            return null;
        }

        Object op = expr.get(0);
        List<Object> args = expr.subList(1, expr.size());

        if (op.equals(new Symbol("quote"))) {
            return args.get(0);
        } else if (op.equals(new Symbol("if"))) {
            Object test = args.get(0);
            Object then = args.get(1);
            Object otherwise = args.get(2);

            return Truth.isTrue(eval(test, env)) ? eval(then, env) : eval(otherwise, env);
        } else if (op.equals(new Symbol("define"))) {
            Symbol symbol = (Symbol) args.get(0);
            Object body = args.get(1);
            env.addSymbol(symbol.getName(), eval(body, env));
        } else if (op.equals(new Symbol("set!"))) {
            Symbol symbol = (Symbol) args.get(0);
            env.findEnv(symbol.getName()).put(symbol.getName(), eval(args.get(1), env));
        } else if (op.equals(new Symbol("lambda"))) {
            List<Symbol> params = ((List<Symbol>) args.get(0)).stream().map(a -> (Symbol) a).collect(Collectors.toList());
            Object body = args.get(1);

            return new Procedure(params, body, env);
        } else {
            Func func = (Func) eval(op, env);
            List<Object> arguments = args.stream()
                                         .map(e -> eval(e, env))
                                         .collect(Collectors.toList());
            return func.exec(arguments);
        }

        return null;
    }
}
