package br.com.tommiranda.eval;

import br.com.tommiranda.ast.Node;

import java.util.List;

public interface Macro {

    List<Node> exec(List<Node> nodes) throws Exception;
}
