package br.com.tommiranda.utils;

import org.apache.commons.lang3.ClassUtils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class FlowOperations {

    public static boolean lessThenOrEqual(Number a, Number b) {
        try {
            if (ClassUtils.isPrimitiveOrWrapper(a.getClass()) && ClassUtils.isPrimitiveOrWrapper(b.getClass())) {
                Comparable ca = (Comparable) a;
                Comparable cb = (Comparable) b;

                return ca.compareTo(cb) <= 0;
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
        } catch (ArithmeticException e) {
        }

        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString())) <= 0;
    }

    public static boolean greaterThenOrEqual(Number a, Number b) {
        try {
            if (ClassUtils.isPrimitiveOrWrapper(a.getClass()) && ClassUtils.isPrimitiveOrWrapper(b.getClass())) {
                Comparable ca = (Comparable) a;
                Comparable cb = (Comparable) b;

                return ca.compareTo(cb) >= 0;
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
        } catch (ArithmeticException e) {
        }

        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString())) >= 0;
    }

    public static boolean lessThen(Number a, Number b) {
        try {
            if (ClassUtils.isPrimitiveOrWrapper(a.getClass()) && ClassUtils.isPrimitiveOrWrapper(b.getClass())) {
                Comparable ca = (Comparable) a;
                Comparable cb = (Comparable) b;

                return ca.compareTo(cb) < 0;
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
        } catch (ArithmeticException e) {
        }

        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString())) < 0;
    }

    public static boolean greaterThen(Number a, Number b) {
        try {
            if (ClassUtils.isPrimitiveOrWrapper(a.getClass()) && ClassUtils.isPrimitiveOrWrapper(b.getClass())) {
                Comparable ca = (Comparable) a;
                Comparable cb = (Comparable) b;

                return ca.compareTo(cb) > 0;
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
        } catch (ArithmeticException e) {
        }

        return new BigDecimal(a.toString()).compareTo(new BigDecimal(b.toString())) > 0;
    }
}
