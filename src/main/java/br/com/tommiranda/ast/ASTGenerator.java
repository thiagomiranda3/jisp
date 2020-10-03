package br.com.tommiranda.ast;

import br.com.tommiranda.utils.Util;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Scanner;

public class ASTGenerator {

    private Node root;
    private String code;
    private Scanner scanner = new Scanner(System.in);

    private int countExp = 0;

    public void waitNewCode() {
        code = scanner.nextLine();

        code = " " + code;

        countExp = 0;
    }

    public Node createAST() {
        waitNewCode();

        if (Util.isNullOrEmpty(code)) {
            System.out.println((String) null);
        }

        ignoreSpaces();

        char firstChar = code.charAt(countExp++);

        if (firstChar != '(') {
            throw new IllegalStateException("Expression need to be initialized with (");
        }

        root = createNode();

        return root;
    }

    // (+
    // (hello)
    // (+ 1 2 3 4)
    // (/ (* 3 2) (- 10 5))
    // (* 2 3 (- 5 2))
    private Node createNode() {
        while (true) {
            continueIfEOS();

            Node node = new Node();

            node.setOp(getOp());

            char c = code.charAt(countExp);

            if (c == ')') {
                return node;
            }

            while (true) {
                ignoreSpaces();
                continueIfEOS();

                c = code.charAt(countExp);

                if (c == ')') {
                    return node;
                }

                node.getNodes().add(getValue());
            }
        }
    }

    // (+ 1 2 3 4)
    private String getOp() {
        StringBuilder op = new StringBuilder();

        ignoreSpaces();

        while (true) {
            continueIfEOS();

            char c = code.charAt(countExp);

            if (ignoreSpaces() || c == ')') {
                return op.toString();
            }

            if (!OpValidator.isValid(c)) {
                throw new IllegalStateException(c + " is not a valid op character");
            }

            op.append(c);
            countExp++;
        }
    }

    private Node getValue() {
        StringBuilder value = new StringBuilder();

        while (true) {
            continueIfEOS();

            char c = code.charAt(countExp);

            if (c == '(') {
                countExp++;

                Node node = createNode();

                countExp++;

                return node;
            }

            c = code.charAt(countExp);

            if (ignoreSpaces() || c == ')') {
                String strValue = value.toString();

                if (NumberUtils.isCreatable(strValue)) {
                    return new Node(new Value(new BigDecimal(strValue)));
                }

                return new Node(new Value(strValue));
            }

            value.append(c);
            countExp++;
        }
    }

    public boolean ignoreSpaces() {
        continueIfEOS();
        char c = code.charAt(countExp);

        if (c != ' ' && c != '\t' && c != '\n') {
            return false;
        }

        while (true) {
            continueIfEOS();

            c = code.charAt(countExp);

            if (c != ' ' && c != '\t' && c != '\n') {
                return true;
            }

            countExp++;
        }
    }

    private void continueIfEOS() {
        if (countExp >= code.length()) {
            waitNewCode();
        }
    }
}
