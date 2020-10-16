package br.com.tommiranda.lang.macros;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.eval.Func;
import br.com.tommiranda.eval.Globals;
import br.com.tommiranda.eval.Symbol;
import br.com.tommiranda.lang.GlobalMacro;

import java.util.Collections;
import java.util.List;

public class CoreMacros {

    // (define x 1)
    // (define y (+ x 1))
    // (define mul (lambda [x, y] (* x y)))
    @GlobalMacro
    public static List<Node> define(List<Node> nodes) {
        if (nodes.size() != 2) {
            throw new IllegalArgumentException("define needs to receive a symbol and an expression");
        }

        Node nodeSymbol = nodes.get(0);
        Node nodeExpr = nodes.get(1);

        if (nodeSymbol.getValue() == null || !(nodeSymbol.getValue() instanceof Symbol)) {
            throw new IllegalArgumentException("define needs to receive a symbol as first param");
        }

        if (nodeExpr.getValue() == null) {
            throw new IllegalArgumentException("define needs to receive a value or a lambda function as second param");
        }

        Symbol defineName = (Symbol) nodeSymbol.getValue();

        if (nodeExpr.getValue() instanceof Func) {
            Globals.addFunction(defineName.getName(), (Func) nodeExpr.getValue());
        } else {
            Globals.addSymbol(defineName.getName(), nodeExpr.getValue());
        }

        return null;
    }

    @GlobalMacro
    public static List<Node> undefine(List<Node> nodes) {
        if (nodes.size() != 1) {
            throw new IllegalArgumentException("undefine can only receive one symbol as param");
        }

        Node nodeSymbol = nodes.get(0);

        if (nodeSymbol.getValue() == null || !(nodeSymbol.getValue() instanceof Symbol)) {
            throw new IllegalArgumentException("define needs to receive a symbol as param");
        }

        Symbol defineName = (Symbol) nodeSymbol.getValue();

        boolean undefined = Globals.removeSymbol(defineName.getName()) ||
                            Globals.removeFunction(defineName.getName());

        Node node = new Node(Boolean.toString(undefined), null);

        return Collections.singletonList(node);
    }
}
