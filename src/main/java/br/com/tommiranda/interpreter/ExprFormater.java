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
        if(obj instanceof Double) {
            Double doubleValue = (Double) obj;

            if(doubleValue % 1 == 0) {
                return String.valueOf(doubleValue.longValue());
            }
        } else if(obj instanceof BigDecimal) {
            BigDecimal bigDec = (BigDecimal) obj;

            try {
                return bigDec.toBigIntegerExact().toString();
            } catch (Exception e) {
            }
        } else if(obj instanceof String) {
            return "\"" + obj + "\"";
        }

        return obj.toString();
    }
}
