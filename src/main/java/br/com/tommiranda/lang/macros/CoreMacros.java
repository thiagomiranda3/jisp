package br.com.tommiranda.lang.macros;

import br.com.tommiranda.interpreter.Evaluator;
import br.com.tommiranda.interpreter.ExprFormater;
import br.com.tommiranda.interpreter.Symbol;
import br.com.tommiranda.lang.GlobalMacro;
import br.com.tommiranda.utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.tommiranda.utils.ParamsValidator.*;

public class CoreMacros {

    /*
    def cons(x, y): return [x]+y

    def let(*args):
        args = list(args)
        x = cons(_let, args)
        require(x, len(args)>1)
        bindings, body = args[0], args[1:]
        require(x, all(isa(b, list) and len(b)==2 and isa(b[0], Symbol)
                       for b in bindings), "illegal binding list")
        vars, vals = zip(*bindings)
        return [[_lambda, list(vars)]+map(expand, body)] + map(expand, vals)
     */
    // (let ((a 1) (b 2)) (+ a b)) => 3
    @GlobalMacro
    public static Object let(List<Object> args) {
        List<Object> x = new ArrayList<>();
        x.add(new Symbol("let"));
        x.addAll(args);

        requireAtLeast("let", args, 2);

        List<Object> bindings = (List<Object>) args.get(0);
        List<Object> body = args.subList(1, args.size());

        List<Object> params = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        for (Object binding : Util.safeList(bindings)) {
            requireList(ExprFormater.format(x), binding);

            List<Object> lb = (List<Object>) binding;

            requireSize(ExprFormater.format(x), lb, 2);
            requireSymbol(ExprFormater.format(x), lb.get(0));

            params.add(lb.get(0));
            values.add(lb.get(1));
        }

        List<Object> lambdaFunction = new ArrayList<>();
        lambdaFunction.add(new Symbol("lambda"));
        lambdaFunction.add(params);
        lambdaFunction.addAll(body.stream()
                                  .map(b -> Evaluator.expand(b, false))
                                  .collect(Collectors.toList()));

        List<Object> executableLambda = new ArrayList<>();
        executableLambda.add(lambdaFunction);

        executableLambda.addAll(values.stream()
                                      .map(v -> Evaluator.expand(v, false))
                                      .collect(Collectors.toList()));

        return executableLambda;
    }
}
