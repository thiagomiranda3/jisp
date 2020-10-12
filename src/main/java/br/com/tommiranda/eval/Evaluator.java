package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.ast.Value;
import br.com.tommiranda.utils.Util;

public class Evaluator {

    public Value evaluateTree(Node node) throws Exception {
        for (Node child : Util.safeList(node.getNodes())) {
            evaluateTree(child);
        }

        if (Util.stringOK(node.getOp())) {
            Func func = GlobalFunctions.getFunc(node.getOp());

            Value value = func.exec(node.getChildValues());

            node.setOp(null);
            node.setNodes(null);
            node.setValue(value);
        }

        return node.getValue();
    }
}
