package br.com.tommiranda.ast2;

import br.com.tommiranda.exceptions.SyntaxErrorException;

import java.util.*;

public class Tokenizer {

    private Deque<String> tokens = new LinkedList<>();
    private ArrayDeque<String> brackets = new ArrayDeque<>();

    public boolean isParseable() {
        return brackets.isEmpty();
    }

    public Deque<String> tokenize(String expr) {
        expr = expr.trim()
                   .replace("(", " ( ")
                   .replace("[", " [ ")
                   .replace(")", " ) ")
                   .replace("]", " ] ")
                   .replace("\"", " \" ");

        StringTokenizer tokenizer = new StringTokenizer(expr, " ", true);

        Deque<String> newTokens = new LinkedList<>();

        StringBuilder strAcc = new StringBuilder();
        boolean readAsString = false;

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (token.equals("\"")) {
                readAsString = !readAsString;

                if (!readAsString) {
                    newTokens.add(strAcc.substring(1, strAcc.length() - 1));
                    strAcc = new StringBuilder();
                }
            } else if (readAsString) {
                strAcc.append(token);
                continue;
            } else if (token.equals(" ")) {
                continue;
            } else if (token.equals("(") || token.equals("[")) {
                brackets.add(token);
            } else if (token.equals(")")) {
                if (brackets.isEmpty() || !brackets.removeLast().equals("(")) {
                    throw new SyntaxErrorException("Unmatched delimiter (");
                }
            } else if (token.equals("]")) {
                if (brackets.isEmpty() || !brackets.removeLast().equals("[")) {
                    throw new SyntaxErrorException("Unmatched delimiter [");
                }
            }

            newTokens.add(token);
        }

        if(!strAcc.isEmpty()) {
            throw new SyntaxErrorException("Unclosed quotes error");
        }

        this.tokens.addAll(newTokens);

        return new LinkedList<>(tokens);
    }

    public Deque<String> getTokens() {
        return tokens;
    }

    public void clean() {
        tokens.clear();
        brackets.clear();
    }
}
