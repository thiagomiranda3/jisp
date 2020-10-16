package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.utils.Util;

import java.util.Map;

public class Evaluator {

    public void assignValuesToSymbols(Node node, Map<Symbol, Object> symbolParams) throws Exception {
        for (Node child : Util.safeList(node.getNodes())) {
            assignValuesToSymbols(child, symbolParams);
        }

        Object value = node.getValue();

        if (!(value instanceof Symbol)) {
            return;
        }

        Symbol symbol = (Symbol) value;

        Object symbolValue = symbolParams.get(symbol);

        if (symbolValue != null) {
            symbol.setValue(symbolValue);
        }
    }

    public Object evaluateTree(Node node) throws Exception {
        for (Node child : Util.safeList(node.getNodes())) {
            evaluateTree(child);
        }

        if (Util.stringOK(node.getOp())) {
            Func func = Globals.getFunc(node.getOp());

            Object value = func.exec(node.getEvaluatedChildren());

            node.setOp(null);
            node.setNodes(null);
            node.setValue(value);
        }

        return node.getValue();
    }
}
