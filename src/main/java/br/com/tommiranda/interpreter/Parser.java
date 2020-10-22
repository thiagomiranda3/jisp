package br.com.tommiranda.interpreter;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Parser {

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
                        throw new ArithmeticException("double overflow");
                    }

                    ast.add(value);
                } catch (NumberFormatException | ArithmeticException e) {
                    ast.add(new BigDecimal(token));
                }
            } else if (token.equals("true") || token.equals("false")) {
                ast.add(Boolean.valueOf(token));
            } else if (token.equals("null")) {
                ast.add(null);
            } else {
                ast.add(new Symbol(token));
            }
        }

        return ast.get(0);
    }
}
