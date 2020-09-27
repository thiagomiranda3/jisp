package br.com.tommiranda.ast;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String op;
    private Value value;
    private List<Node> nodes = new ArrayList<>();

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

    public Value getResult() {
        return value;
    }

    public void setResult(Value value) {
        this.value = value;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
}
