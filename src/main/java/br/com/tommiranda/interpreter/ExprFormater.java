package br.com.tommiranda.interpreter;

import java.util.List;

public class ExprFormater {

    public static String format(Object val) {
        if (val == null) {
            return null;
        } else if (val instanceof String) {
            return "\"" + val + "\"";
        } else if (val instanceof List) {
            String[] expr = ((List<Object>) val).stream()
                                                   .map(ExprFormater::format).toArray(String[]::new);

            return "(" + String.join(" ", expr) + ")";
        }

        return val.toString();
    }
}
