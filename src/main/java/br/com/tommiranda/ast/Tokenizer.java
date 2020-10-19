package br.com.tommiranda.ast;

import br.com.tommiranda.exceptions.SyntaxErrorException;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizer {

    private List<String> tokens = new ArrayList<>();
    private ArrayDeque<String> brackets = new ArrayDeque<>();

    public boolean isParseable() {
        return brackets.isEmpty();
    }

    public List<String> tokenize(String expr) {
        expr = expr.trim()
                   .replace("(", " ( ")
                   .replace("[", " [ ")
                   .replace(")", " ) ")
                   .replace("]", " ] ")
                   .replace("\"", " \" ");

        StringTokenizer tokenizer = new StringTokenizer(expr, " ", true);

        List<String> newTokens = new ArrayList<>();

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
                String lastBracket = brackets.removeLast();

                if (!lastBracket.equals("(")) {
                    throw new SyntaxErrorException("Unmatched (");
                }
            } else if (token.equals("]")) {
                String lastBracket = brackets.removeLast();

                if (!lastBracket.equals("[")) {
                    throw new SyntaxErrorException("Unmatched [");
                }
            }

            newTokens.add(token);
        }

        if(!strAcc.isEmpty()) {
            throw new SyntaxErrorException("unclosed quotes error");
        }

        this.tokens.addAll(newTokens);

        return new ArrayList<>(tokens);
    }
}
