package br.com.tommiranda.lang.macros;

import br.com.tommiranda.ast.Node;
import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.GlobalMacro;
import br.com.tommiranda.utils.Util;

import java.util.List;

public class ListMacros {

    @GlobalMacro
    public static List<Node> apply(List<Node> nodes) {
        if(nodes.size() != 1) {
            throw new WrongParamsException("apply needs only one list as a param");
        }

        Node node = nodes.get(0);

        if(Util.isNullOrEmpty(node.getOp()) || !node.getOp().equals("list")) {
            throw new WrongParamsException("apply needs to receive a list as a param");
        }

        return node.getNodes();
    }
}
