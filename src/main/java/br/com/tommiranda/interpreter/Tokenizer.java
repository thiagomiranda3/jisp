package br.com.tommiranda.interpreter;

import br.com.tommiranda.exceptions.SyntaxError;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tokenizer {

    private boolean parseable = false;
    private Deque<String> tokens = new LinkedList<>();
    private ArrayDeque<String> brackets = new ArrayDeque<>();

    public boolean isParseable() {
        return parseable;
    }

    public Deque<String> tokenize(String expr) {
        Pattern compile = Pattern.compile(" |\\(|\\)");

        String[] tokens = compile.split(expr);

        expr = expr.trim()
                   .replace("(", " ( ")
                   .replace("[", " [ ")
                   .replace(")", " ) ")
                   .replace("]", " ] ")
                   .replace(";", " ; ")
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
            } else if(token.equals(";")) {
                break;
            }
            else if (token.equals("(") || token.equals("[")) {
                brackets.add(token);
            } else if (token.equals(")")) {
                if (brackets.isEmpty() || !brackets.removeLast().equals("(")) {
                    throw new SyntaxError("Unmatched delimiter (");
                }
            } else if (token.equals("]")) {
                if (brackets.isEmpty() || !brackets.removeLast().equals("[")) {
                    throw new SyntaxError("Unmatched delimiter [");
                }
            }

            newTokens.add(token);
        }

        if (!strAcc.isEmpty()) {
            throw new SyntaxError("Unclosed quotes error");
        }

        parseable = brackets.isEmpty() && !newTokens.isEmpty();

        this.tokens.addAll(newTokens);

        LinkedList<String> regexTokens = new LinkedList<>(Arrays.asList(tokens));

        return new LinkedList<>(this.tokens);
    }

    public Deque<String> getTokens() {
        return tokens;
    }

    public void clean() {
        tokens.clear();
        brackets.clear();
    }
}
