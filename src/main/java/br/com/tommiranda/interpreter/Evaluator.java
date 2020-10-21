package br.com.tommiranda.interpreter;

import br.com.tommiranda.lang.Func;
import br.com.tommiranda.exceptions.WrongParamsException;

import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {

    // [{"name":"if"},[{"name":">"},[{"name":"*"},11,11],120],[{"name":"*"},7,6],{"name":"oops"}]
    public static Object eval(Object val, Env env) {
        if (val instanceof Symbol) {
            return env.getSymbolValue(((Symbol) val).getName());
        } else if (!(val instanceof List)) {
            return val;
        }

        List<Object> expr = (List) val;

        Symbol op = (Symbol) expr.get(0);
        List<Object> args = expr.subList(1, expr.size());

        if (op.equals(new Symbol("if"))) {
            Object test = args.get(0);
            Object then = args.get(1);
            Object otherwise = args.get(2);


            return eval(test, env).equals(true) ? eval(then, env) : eval(otherwise, env);
        } else if (op.equals(new Symbol("define"))) {
            Symbol symbol = (Symbol) args.get(0);
            Object body = args.get(1);
            env.addSymbol(symbol.getName(), eval(body, env));
        } else if (op.equals(new Symbol("set!"))) {
            Symbol symbol = (Symbol) args.get(0);
            env.findEnv(symbol.getName()).put(symbol.getName(), eval(args.get(1), env));
        } else if (op.equals(new Symbol("lambda"))) {
            List<Symbol> params = (List<Symbol>) args.get(0);
            Object body = args.get(1);

            return (Func) (values) -> {
                if (params.size() != values.size()) {
                    throw new WrongParamsException(values.size() + " params passed to function, but only " + params.size() + " allowed");
                }

                Env funcEnv = new Env(params, values);
                funcEnv.setOuterEnv(env);

                return eval(body, funcEnv);
            };
        } else {
            Func func = (Func) env.getSymbolValue(op.getName());
            List<Object> params = args.stream()
                                      .map(e -> eval(e, env))
                                      .collect(Collectors.toList());
            return func.exec(params);
        }

        return null;
    }
}
