package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.utils.Util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Evaluator {

    public void assignValuesToSymbols(Node node, Map<Symbol, Object> env) {
        for (Node child : Util.safeList(node.getNodes())) {
            assignValuesToSymbols(child, env);
        }

        Object value = node.getValue();

        if (!(value instanceof Symbol)) {
            return;
        }

        Symbol symbol = (Symbol) value;

        Object symbolValue = env.get(symbol);

        if (symbolValue != null) {
            symbol.setValue(symbolValue);
        }
    }

    public Object eval(Node node) {
        if (Util.isNullOrEmpty(node.getOp())) {
            return node.getValue();
        }

        Func func = Globals.getFunc(node.getOp());

        List<Object> params = node.getNodes().stream()
                                  .map(this::eval)
                                  .map(this::evaluateIfSymbol)
                                  .collect(Collectors.toList());

        return func.exec(params);

    }

    private Object evaluateIfSymbol(Object value) {
        if (value instanceof Symbol) {
            Symbol symbol = (Symbol) value;

            if (symbol.getValue() != null) {
                return symbol.getValue();
            }

            return Globals.getSymbolValue(symbol.getName());
        }

        return value;
    }
}
