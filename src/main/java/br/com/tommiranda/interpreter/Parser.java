package br.com.tommiranda.interpreter;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Parser {

    private static final Map<String, Symbol> aliases = createAliases();
    private static Deque<String> tokens;

    private static Map<String, Symbol> createAliases() {
        Map<String, Symbol> aliases = new HashMap<>();

        aliases.put("'", new Symbol("quote"));
        aliases.put("`", new Symbol("quasiquote"));
        aliases.put(",", new Symbol("unquote"));
        aliases.put(",@", new Symbol("unquote-splicing"));

        return aliases;
    }

    public static Object parse(Deque<String> tokens) {
        Parser.tokens = tokens;
        return parse(tokens.pop());
    }

    public static Object parse(String token) {
        if (token.equals("(")) {
            List<Object> ast = new ArrayList<>();

            while (!tokens.isEmpty()) {
                token = tokens.pop();

                if (token.equals(")")) {
                    return ast;
                }

                ast.add(parse(token));
            }
        } else if (aliases.containsKey(token)) {
            return Arrays.asList(aliases.get(token), parse(tokens));
        }

        return atom(token);
    }

    public static Object atom(String token) {
        if (token.equals("true") || token.equals("false")) {
            return Boolean.valueOf(token);
        } else if (token.startsWith("\"")) {
            return token.substring(1, token.length() - 1);
        } else if (token.startsWith("0x")) {
            return Long.valueOf(token.substring(2), 16);
        } else if (token.startsWith("0b")) {
            return Long.valueOf(token.substring(2), 2);
        } else if (NumberUtils.isDigits(token)) {
            try {
                return Long.valueOf(token);
            } catch (NumberFormatException | ArithmeticException e) {
                return new BigInteger(token);
            }
        } else if (NumberUtils.isCreatable(token)) {
            try {
                Double value = Double.valueOf(token);

                if (Double.isInfinite(value) || Double.isNaN(value)) {
                    return new BigDecimal(token);
                } else {
                    return value;
                }
            } catch (NumberFormatException | ArithmeticException e) {
                return new BigDecimal(token);
            }
        } else if (token.equals("null")) {
            return null;
        } else {
            return new Symbol(token);
        }
    }
}
