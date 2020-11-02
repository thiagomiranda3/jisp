package br.com.tommiranda.interpreter;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.*;

public class Parser {

    private static final Map<String, Symbol> aliases = createAliases();

    private static Map<String, Symbol> createAliases() {
        Map<String, Symbol> aliases = new HashMap<>();

        aliases.put("'", new Symbol("quote"));
        aliases.put("`", new Symbol("quasiquote"));
        aliases.put(",", new Symbol("unquote"));
        aliases.put(",@", new Symbol("unquote-splicing"));

        return aliases;
    }

    public static Object parse(Deque<String> tokens) {
        boolean readAsString = false;
        List<Object> ast = new ArrayList<>();

        while (!tokens.isEmpty()) {
            String token = tokens.pop();

            if (token.equals("(")) {
                ast.add(parse(tokens));
            } else if (token.equals(")")) {
                return ast;
            } else if (token.equals("\"")) {
                readAsString = !readAsString;
            } else if (readAsString) {
                ast.add(token);
            } else if (NumberUtils.isCreatable(token)) {
                try {
                    Double value = Double.valueOf(token);

                    if (Double.isInfinite(value) || Double.isNaN(value)) {
                        ast.add(new BigDecimal(token));
                    } else {
                        ast.add(value);
                    }
                } catch (NumberFormatException | ArithmeticException e) {
                    ast.add(new BigDecimal(token));
                }
            } else if (token.equals("true") || token.equals("false")) {
                ast.add(Boolean.valueOf(token));
            } else if (token.equals("null")) {
                ast.add(null);
            } else if (aliases.containsKey(token)) {
                return Arrays.asList(aliases.get(token), parse(tokens));
            } else {
                ast.add(new Symbol(token));
            }
        }

        return ast.get(0);
    }
}
