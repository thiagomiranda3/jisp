package br.com.tommiranda.lang.macros;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.eval.Evaluator;
import br.com.tommiranda.eval.Func;
import br.com.tommiranda.eval.Environment;
import br.com.tommiranda.eval.Symbol;
import br.com.tommiranda.exceptions.DuplicateParamsException;
import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.GlobalMacro;
import br.com.tommiranda.utils.Util;

import java.util.*;

public class CoreMacros {

    // (define x 1)
    // (define y (+ x 1))
    // (define mul (lambda [x, y] (* x y)))
    @GlobalMacro
    public static List<Node> define(List<Node> nodes) {
        if (nodes.size() != 2) {
            throw new WrongParamsException("define needs to receive a symbol and an expression");
        }

        Node nodeSymbol = nodes.get(0);
        Node nodeExpr = nodes.get(1);

        if (nodeSymbol.getValue() == null || !(nodeSymbol.getValue() instanceof Symbol)) {
            throw new WrongParamsException("define needs to receive a symbol as first param");
        }

        if (nodeExpr.getValue() == null) {
            throw new WrongParamsException("define needs to receive a value or a lambda function as second param");
        }

        Symbol defineName = (Symbol) nodeSymbol.getValue();

        if (nodeExpr.getValue() instanceof Func) {
            Environment.addSymbol(defineName.getName(), (Func) nodeExpr.getValue());
        } else {
            Environment.addSymbol(defineName.getName(), nodeExpr.getValue());
        }

        return null;
    }

    @GlobalMacro
    public static List<Node> undefine(List<Node> nodes) {
        if (nodes.size() != 1) {
            throw new WrongParamsException("undefine can only receive one symbol as param");
        }

        Node nodeSymbol = nodes.get(0);

        if (nodeSymbol.getValue() == null || !(nodeSymbol.getValue() instanceof Symbol)) {
            throw new WrongParamsException("undefine needs to receive a symbol as param");
        }

        Symbol defineName = (Symbol) nodeSymbol.getValue();

        boolean undefined = Environment.removeSymbol(defineName.getName());

        Node node = new Node(Boolean.toString(undefined), null);

        return Collections.singletonList(node);
    }

    // (lambda [x, y] (* x y))
    // (lambda (list x y) (* x y))
    // (define mul (lambda (list x y) (* x y)))
    @GlobalMacro
    public static List<Node> lambda(List<Node> nodes) {
        if (nodes.size() != 2) {
            throw new WrongParamsException("lambda functions need to receive a list and an expression");
        }

        Node nodeList = nodes.get(0);
        Node nodeExpr = nodes.get(1);

        if (Util.isNullOrEmpty(nodeList.getOp()) || !nodeList.getOp().equals("list")) {
            throw new WrongParamsException("lambda needs to receive a list as the first param");
        }

        if (Util.isNullOrEmpty(nodeExpr.getOp())) {
            throw new WrongParamsException("lambda needs to receive an expression as the second param");
        }

        List<Symbol> symbolParams = new ArrayList<>();
        Set<Symbol> uniqueSymbols = new LinkedHashSet<>();

        for (Object param : Util.safeList(nodeList.getChildrenValues())) {
            if (!(param instanceof Symbol)) {
                throw new WrongParamsException("All itens in the list params need to be a symbol");
            }

            if (!uniqueSymbols.add((Symbol) param)) {
                throw new DuplicateParamsException("lambda function cannot receive duplicate params");
            }

            symbolParams.add((Symbol) param);
        }

        Func func = (params) -> {
            if (symbolParams.size() != params.size()) {
                throw new WrongParamsException(params.size() + " params passed to function, but only " + symbolParams.size() + " allowed");
            }

            Map<Symbol, Object> env = Util.createMapFromIterables(symbolParams,
                                                                         params,
                                                                         LinkedHashMap::new);

            new Evaluator().assignValuesToSymbols(nodeExpr, env);

            Object result = new Evaluator().eval(nodeExpr);

            return result;
        };

        return Collections.singletonList(new Node(func));
    }
}
