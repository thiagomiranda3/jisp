package br.com.tommiranda.interpreter;

import java.util.List;

public class Truth {

    public static boolean isTrue(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Boolean) {
            return obj.equals(true);
        }

        if (obj instanceof List) {
            return !((List) obj).isEmpty();
        }

        if (obj instanceof String) {
            return !((String) obj).isEmpty();
        }

        return true;
    }
}
