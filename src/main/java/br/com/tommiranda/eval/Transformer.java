package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.utils.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transformer {

    public Node transformTree(Node root) throws Exception {
        List<Node> nodes = transformTree2(root);

        Macro macro = GlobalMacros.getMacro(root.getOp());
        if (macro != null) {
            root.setOp("pass");
            root.setNodes(nodes);
        }

        return root;
    }

    public List<Node> transformTree2(Node node) throws Exception {
        List<Node> children = node.getNodes();
        List<Node> newChildren = new ArrayList<>();

        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);

            List<Node> newNodes = transformTree2(child);
            if (newNodes != null) {
                newChildren.addAll(newNodes);
            }
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
