package br.com.tommiranda.ast2;

import br.com.tommiranda.eval.Func;
import br.com.tommiranda.eval.Symbol;

import java.util.List;
import java.util.stream.Collectors;

public class Evaluator {

    // [{"name":"if"},[{"name":">"},[{"name":"*"},11,11],120],[{"name":"*"},7,6],{"name":"oops"}]
    public static Object eval(Object val, Environment env) {
        if (val instanceof Symbol) {
            return env.getSymbolValue(((Symbol) val).getName());
        } else if (!(val instanceof List)) {
            return val;
        }

        List<Object> expr = (List) val;

        if (expr.get(0).equals(new Symbol("if"))) {
            Object test = expr.get(1);
            Object then = expr.get(2);
            Object otherwise = expr.get(3);

            return eval(test, env).equals(true) ? eval(then, env) : eval(otherwise, env);
        } else if (expr.get(0).equals(new Symbol("define"))) {
            Symbol symbol = (Symbol) expr.get(1);
            Object body = expr.get(2);
            env.addSymbol(symbol.getName(), eval(body, env));
            return null;
        } else {
            Symbol symbol = (Symbol) expr.get(0);
            Func func = (Func) env.getSymbolValue(symbol.getName());
            List<Object> params = expr.subList(1, expr.size()).stream()
                                      .map(e -> eval(e, env))
                                      .collect(Collectors.toList());
            return func.exec(params);
        }
    }
}
