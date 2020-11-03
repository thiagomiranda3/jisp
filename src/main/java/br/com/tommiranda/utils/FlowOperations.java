package br.com.tommiranda.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FlowOperations {

    public static boolean lessThenOrEqual(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            return a.longValue() <= b.longValue();
        }

        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() <= b.doubleValue();
        }

        if (a instanceof Long && b instanceof Double) {
            return a.longValue() <= b.doubleValue();
        }

        if (a instanceof Double && b instanceof Long) {
            return a.doubleValue() <= b.longValue();
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).compareTo(new BigInteger(b.toString())) <= 0;
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).compareTo(new BigDecimal(b.toString())) <= 0;
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).compareTo((BigDecimal) b) <= 0;
        }

        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString())) <= 0;
    }

    public static boolean greaterThenOrEqual(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            return a.longValue() >= b.longValue();
        }

        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() >= b.doubleValue();
        }

        if (a instanceof Long && b instanceof Double) {
            return a.longValue() >= b.doubleValue();
        }

        if (a instanceof Double && b instanceof Long) {
            return a.doubleValue() >= b.longValue();
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).compareTo(new BigInteger(b.toString())) >= 0;
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).compareTo(new BigDecimal(b.toString())) >= 0;
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).compareTo((BigDecimal) b) >= 0;
        }

        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString())) >= 0;
    }

    public static boolean lessThen(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            return a.longValue() < b.longValue();
        }

        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() < b.doubleValue();
        }

        if (a instanceof Long && b instanceof Double) {
            return a.longValue() < b.doubleValue();
        }

        if (a instanceof Double && b instanceof Long) {
            return a.doubleValue() < b.longValue();
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).compareTo(new BigInteger(b.toString())) < 0;
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).compareTo(new BigDecimal(b.toString())) < 0;
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).compareTo((BigDecimal) b) < 0;
        }

        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString())) < 0;
    }

    public static boolean greaterThen(Number a, Number b) {

        if (a instanceof Long && b instanceof Long) {
            return a.longValue() > b.longValue();
        }

        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() > b.doubleValue();
        }

        if (a instanceof Long && b instanceof Double) {
            return a.longValue() > b.doubleValue();
        }

        if (a instanceof Double && b instanceof Long) {
            return a.doubleValue() > b.longValue();
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).compareTo(new BigInteger(b.toString())) > 0;
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).compareTo(new BigDecimal(b.toString())) > 0;
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).compareTo((BigDecimal) b) > 0;
        }

        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString())) > 0;
    }

    public static boolean equals(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            return a.longValue() == b.longValue();
        }

        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() == b.doubleValue();
        }

        if (a instanceof Long && b instanceof Double) {
            return a.longValue() == b.doubleValue();
        }

        if (a instanceof Double && b instanceof Long) {
            return a.doubleValue() == b.longValue();
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return a.equals(new BigInteger(b.toString()));
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).stripTrailingZeros().equals(new BigDecimal(b.toString()).stripTrailingZeros());
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).stripTrailingZeros().equals(((BigDecimal) b).stripTrailingZeros());
        }

        return new BigDecimal(a.toString()).stripTrailingZeros().equals(new BigDecimal(b.toString()).stripTrailingZeros());
    }
}
