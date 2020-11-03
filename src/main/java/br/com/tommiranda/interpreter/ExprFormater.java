package br.com.tommiranda.interpreter;

import java.math.BigDecimal;
import java.util.List;

public class ExprFormater {

    public static String format(Object result) {
        if (result == null) {
            return null;
        }

        if (result instanceof List) {
            String[] expr = ((List<Object>) result).stream()
                                                   .map(ExprFormater::toStringAtom).toArray(String[]::new);

            return "(" + String.join(" ", expr) + ")";
        }

        return toStringAtom(result);
    }

    private static String toStringAtom(Object obj) {
        if(obj instanceof String) {
            return "\"" + obj + "\"";
        }

        return obj.toString();
    }
}
