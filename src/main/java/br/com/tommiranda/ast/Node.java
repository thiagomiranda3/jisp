package br.com.tommiranda.ast;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

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

    public List<Node> getNodes() {
        if (nodes == null) {
            nodes = new LinkedList<>();
        }

        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Object> getChildValues() {
        return getNodes().stream()
                         .map(Node::getValue)
                         .collect(Collectors.toList());
    }

    public List<Node> getChildNodes() {
        return new LinkedList<>(getNodes());
    }
}
