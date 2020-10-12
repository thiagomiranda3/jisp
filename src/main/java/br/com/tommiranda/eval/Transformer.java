package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.utils.Util;

import java.util.*;

public class Transformer {

    public Node transformTree(Node root) throws Exception {
        transformTree2(root);

        if (Util.stringOK(root.getOp())) {
            Macro macro = GlobalMacros.getMacro(root.getOp());

            if (macro != null) {
                List<Node> children = macro.exec(root.getChildNodes());

                root.setOp("null");
                root.setNodes(children);
            }
        }

        return root;
    }

    public List<Node> transformTree2(Node node) throws Exception {
        List<Node> children = node.getNodes();
        List<Node> newChildren = new LinkedList<>();

        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);

            newChildren.addAll(transformTree2(child));
        }

        node.setNodes(newChildren);

        if (Util.stringOK(node.getOp())) {
            Macro macro = GlobalMacros.getMacro(node.getOp());

            if (macro != null) {
                return macro.exec(node.getChildNodes());
            }
        }

        return Collections.singletonList(node);
    }
}
