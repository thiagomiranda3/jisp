package br.com.tommiranda.interpreter;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class ExprFormater {

    private static final Gson gson = new Gson();

    public static String format(Object val) {
        if (val == null) {
            return null;
        } else if (val instanceof String) {
            return "\"" + val + "\"";
        } else if (val instanceof Map) {
            return gson.toJson(val);
        } else if (val instanceof List) {
            String[] expr = ((List<Object>) val).stream()
                                                .map(ExprFormater::format).toArray(String[]::new);

            return "(" + String.join(" ", expr) + ")";
        }

        return val.toString();
    }
}
