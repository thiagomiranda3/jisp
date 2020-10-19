package br.com.tommiranda.ast;

import br.com.tommiranda.eval.Globals;
import br.com.tommiranda.eval.Symbol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node implements Serializable {

    private String op;
    private Object value;
    private List<Node> nodes;

    public Node() {
    }

    public Node(String op, Object value) {
        this.op = op;
        this.value = value;
    }

    public Node(Object value) {
        this.value = value;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getEvaluatedValue() {
        if (value instanceof Symbol) {
            Symbol symbol = (Symbol) value;

            if (symbol.getValue() != null) {
                return symbol.getValue();
            }

            return Globals.getSymbolValue(symbol.getName());
        }

        return value;
    }

    public List<Node> getNodes() {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }

        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Object> getChildrenValues() {
        return getNodes().stream()
                         .map(Node::getValue)
                         .collect(Collectors.toList());
    }

    public List<Object> getEvaluatedChildren() {
        return getNodes().stream()
                         .map(Node::getEvaluatedValue)
                         .collect(Collectors.toList());
    }

    public List<Node> getChildNodes() {
        return new ArrayList<>(getNodes());
    }
}
