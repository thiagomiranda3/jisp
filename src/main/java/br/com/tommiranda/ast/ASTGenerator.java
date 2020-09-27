package br.com.tommiranda.ast;

import br.com.tommiranda.utils.Util;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

public class ASTGenerator {

    private Node root;

    private int countExp = 0;

    public Node createAST(String exp) {
        if (Util.isNullOrEmpty(exp)) {
            System.out.println((String) null);
        }

        char firstChar = exp.charAt(countExp++);

        if (firstChar != '(') {
            throw new IllegalStateException("Expression need to be initialized with (");
        }

        root = createNode(exp);
        return root;
    }

    // (+
    // (hello)
    // (+ 1 2 3 4)
    // (/ (* 3 2) (- 10 5))
    // (* 2 3 (- 5 2))
    private Node createNode(String exp) {
        while (!isEOF(exp)) {
            Node node = new Node();

            String op = getOp(exp);
            node.setOp(op);

            char c = exp.charAt(countExp);

            if (c == ')') {
                return node;
            }

            countExp++;

            while (!isEOF(exp)) {
                node.getNodes().add(getValue(exp));

                c = exp.charAt(countExp);

                if (c == ')') {
                    return node;
                }

                countExp++;
            }
        }

        throw new IllegalStateException("A ) is missing");
    }

    // (+ 1 2 3 4)
    private String getOp(String exp) {
        StringBuilder op = new StringBuilder();

        while (!isEOF(exp)) {
            char c = exp.charAt(countExp);

            if (c == ' ' || c == ')') {
                return op.toString();
            }

            if (!OpValidator.isValid(c)) {
                throw new IllegalStateException(c + " is not a valid op character");
            }

            op.append(c);
            countExp++;
        }

        throw new IllegalStateException("A ) is missing");
    }

    private Node getValue(String exp) {
        StringBuilder value = new StringBuilder();

        while (!isEOF(exp)) {
            char c = exp.charAt(countExp);

            if (c == '(') {
                countExp++;

                Node node = createNode(exp);

                countExp++;

                return node;
            }

            c = exp.charAt(countExp);

            if (c == ' ' || c == ')') {
                String strValue = value.toString();

                if (NumberUtils.isCreatable(strValue)) {
                    return new Node(new Value(new BigDecimal(strValue)));
                }

                return new Node(new Value(strValue));
            }

            value.append(c);
            countExp++;
        }

        throw new IllegalStateException("A ) is missing");
    }

    private boolean isEOF(String exp) {
        return exp.length() == countExp;
    }
}
