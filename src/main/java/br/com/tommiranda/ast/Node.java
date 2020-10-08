package br.com.tommiranda.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node {

    private String op;
    private Value value;
    private List<Node> nodes;

    public Node() {
    }

    public Node(Value value) {
        this.value = value;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
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

    public List<Value> getChildValues() {
        return getNodes().stream()
                         .map(Node::getValue)
                         .collect(Collectors.toList());
    }
}
