package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.utils.Util;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Transformer {

//    public Node transformTree(Node node) throws Exception {
//        for (Node child : Util.safeList(node.getNodes())) {
//            transformTree(child);
//        }
//
//        if (Util.stringOK(node.getOp())) {
//            Macro macro = GlobalMacros.getMacro(node.getOp());
//
//            if (macro != null) {
//                List<Node> nodes = macro.exec(node.getChildNodes());
//            }
//        }
//
//        return node;
//    }

    public Node transformTree(Node root) throws Exception {
        transformTree2(root);

        return root;
    }

    public List<Node> transformTree2(Node node) throws Exception {
        List<Node> children = node.getNodes();

        for(int i = 0; i < children.size(); i++) {
            Node child = children.get(i);

            children.remove(i);
            children.addAll(i, transformTree2(child));
        }

        if (Util.stringOK(node.getOp())) {
            Macro macro = GlobalMacros.getMacro(node.getOp());

            if (macro != null) {
                return macro.exec(node.getChildNodes());
            }
        }

        return Collections.singletonList(node);
    }
}
