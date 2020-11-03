package br.com.tommiranda.utils;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.apache.commons.lang3.ClassUtils;
import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class NumberOperations {

    public static Number sum(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            try {
                return Math.addExact(a.longValue(), b.longValue());
            } catch (ArithmeticException e) {
                return new BigInteger(a.toString()).add(new BigInteger(b.toString()));
            }
        }

        if (a instanceof Double && b instanceof Double) {
            double result = a.doubleValue() + b.doubleValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).add(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof Long && b instanceof Double) {
            double result = a.longValue() + b.doubleValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).add(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof Double && b instanceof Long) {
            double result = a.doubleValue() + b.longValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).add(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).add((BigInteger) b);
        }

        if (a instanceof BigInteger && b instanceof Long) {
            return ((BigInteger) a).add(new BigInteger(b.toString()));
        }

        if (a instanceof Long && b instanceof BigInteger) {
            return new BigInteger(a.toString()).add((BigInteger) b);
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return ((BigDecimal) a).add((BigDecimal) b);
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).add(new BigDecimal(b.toString()));
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).add((BigDecimal) b);
        }

        return new BigDecimal(a.toString()).add(new BigDecimal(b.toString()));
    }

    public static Number subtract(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            try {
                return Math.subtractExact(a.longValue(), b.longValue());
            } catch (ArithmeticException e) {
                return new BigInteger(a.toString()).subtract(new BigInteger(b.toString()));
            }
        }

        if (a instanceof Double && b instanceof Double) {
            double result = a.doubleValue() - b.doubleValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).subtract(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof Long && b instanceof Double) {
            double result = a.longValue() - b.doubleValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).subtract(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof Double && b instanceof Long) {
            double result = a.doubleValue() - b.longValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).subtract(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).subtract((BigInteger) b);
        }

        if (a instanceof BigInteger && b instanceof Long) {
            return ((BigInteger) a).subtract(new BigInteger(b.toString()));
        }

        if (a instanceof Long && b instanceof BigInteger) {
            return new BigInteger(a.toString()).subtract((BigInteger) b);
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return ((BigDecimal) a).subtract((BigDecimal) b);
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).subtract(new BigDecimal(b.toString()));
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).subtract((BigDecimal) b);
        }

        return new BigDecimal(a.toString()).subtract(new BigDecimal(b.toString()));
    }

    public static Number multiply(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            try {
                return Math.multiplyExact(a.longValue(), b.longValue());
            } catch (ArithmeticException e) {
                return new BigInteger(a.toString()).multiply(new BigInteger(b.toString()));
            }
        }

        if (a instanceof Double && b instanceof Double) {
            double result = a.doubleValue() * b.doubleValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).multiply(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof Long && b instanceof Double) {
            double result = a.longValue() * b.doubleValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).multiply(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof Double && b instanceof Long) {
            double result = a.doubleValue() * b.longValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).multiply(new BigDecimal(b.toString()));
            }

            return result;
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).multiply((BigInteger) b);
        }

        if (a instanceof BigInteger && b instanceof Long) {
            return ((BigInteger) a).multiply(new BigInteger(b.toString()));
        }

        if (a instanceof Long && b instanceof BigInteger) {
            return new BigInteger(a.toString()).multiply((BigInteger) b);
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return ((BigDecimal) a).multiply((BigDecimal) b);
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).multiply(new BigDecimal(b.toString()));
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).multiply((BigDecimal) b);
        }

        return new BigDecimal(a.toString()).multiply(new BigDecimal(b.toString()));
    }

    public static Number divide(Number a, Number b) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass()) && ClassUtils.isPrimitiveOrWrapper(b.getClass())) {
            double result = a.doubleValue() / b.doubleValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return new BigDecimal(a.toString()).divide(new BigDecimal(b.toString()), 18, RoundingMode.HALF_EVEN).stripTrailingZeros();
            }

            return result;
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return ((BigDecimal) a).divide((BigDecimal) b, 18, RoundingMode.HALF_EVEN).stripTrailingZeros();
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).divide(new BigDecimal(b.toString()), 18, RoundingMode.HALF_EVEN).stripTrailingZeros();
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).divide((BigDecimal) b, 18, RoundingMode.HALF_EVEN).stripTrailingZeros();
        }

        return new BigDecimal(a.toString()).divide(new BigDecimal(b.toString()), 18, RoundingMode.HALF_EVEN).stripTrailingZeros();
    }

    public static Number remainder(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            return a.longValue() % b.longValue();
        }

        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() % b.doubleValue();
        }

        if (a instanceof Long && b instanceof Double) {
            return a.longValue() % b.doubleValue();
        }

        if (a instanceof Double && b instanceof Long) {
            return a.doubleValue() % b.longValue();
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).remainder((BigInteger) b);
        }

        if (a instanceof BigInteger && b instanceof Long) {
            return ((BigInteger) a).remainder(new BigInteger(b.toString()));
        }

        if (a instanceof Long && b instanceof BigInteger) {
            return new BigInteger(a.toString()).remainder((BigInteger) b);
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return ((BigDecimal) a).remainder((BigDecimal) b);
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).remainder(new BigDecimal(b.toString()));
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).remainder((BigDecimal) b);
        }

        return new BigDecimal(a.toString()).remainder(new BigDecimal(b.toString()));
    }

    public static Number max(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            return a.longValue() >= b.longValue() ? a : b;
        }

        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() >= b.doubleValue() ? a : b;
        }

        if (a instanceof Long && b instanceof Double) {
            return a.longValue() >= b.doubleValue() ? a : b;
        }

        if (a instanceof Double && b instanceof Long) {
            return a.doubleValue() >= b.longValue() ? a : b;
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).max((BigInteger) b);
        }

        if (a instanceof BigInteger && b instanceof Long) {
            return ((BigInteger) a).max(new BigInteger(b.toString()));
        }

        if (a instanceof Long && b instanceof BigInteger) {
            return new BigInteger(a.toString()).max((BigInteger) b);
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return ((BigDecimal) a).max((BigDecimal) b);
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).max(new BigDecimal(b.toString()));
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).max((BigDecimal) b);
        }

        return new BigDecimal(a.toString()).max(new BigDecimal(b.toString()));
    }

    public static Number min(Number a, Number b) {
        if (a instanceof Long && b instanceof Long) {
            return a.longValue() <= b.longValue() ? a : b;
        }

        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() <= b.doubleValue() ? a : b;
        }

        if (a instanceof Long && b instanceof Double) {
            return a.longValue() <= b.doubleValue() ? a : b;
        }

        if (a instanceof Double && b instanceof Long) {
            return a.doubleValue() <= b.longValue() ? a : b;
        }

        if (a instanceof BigInteger && b instanceof BigInteger) {
            return ((BigInteger) a).min((BigInteger) b);
        }

        if (a instanceof BigInteger && b instanceof Long) {
            return ((BigInteger) a).min(new BigInteger(b.toString()));
        }

        if (a instanceof Long && b instanceof BigInteger) {
            return new BigInteger(a.toString()).min((BigInteger) b);
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return ((BigDecimal) a).min((BigDecimal) b);
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).min(new BigDecimal(b.toString()));
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).min((BigDecimal) b);
        }

        return new BigDecimal(a.toString()).min(new BigDecimal(b.toString()));
    }

    public static Number pow(Number a, Number pow) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass()) && ClassUtils.isPrimitiveOrWrapper(pow.getClass())) {
            double result = Math.pow(a.doubleValue(), pow.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.pow(new BigDecimal(a.toString()), new BigDecimal(pow.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.pow(new BigDecimal(a.toString()), new BigDecimal(pow.toString()), new MathContext(16));
    }

    public static Number sqrt(Number a) {
        if ((ClassUtils.isPrimitiveOrWrapper(a.getClass()))) {
            double result = Math.sqrt(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.sqrt(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        if (a instanceof BigDecimal) {
            return BigDecimalMath.sqrt((BigDecimal) a, new MathContext(16));
        }

        return BigDecimalMath.sqrt(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number pi(Number a) {
        long precision = (long) a.doubleValue();

        Apfloat result = ApfloatMath.pi(precision);

        if (precision > 16) {
            return BigDecimalMath.toBigDecimal(result.toString(true));
        }

        return result.doubleValue();
    }

    public static Number abs(Number a) {
        if (a instanceof Double) {
            return Math.abs(a.doubleValue());
        }

        if (a instanceof Long) {
            return Math.abs(a.longValue());
        }

        if (a instanceof BigInteger) {
            return ((BigInteger) a).abs();
        }

        return ((BigDecimal) a).abs();
    }

    public static Number sin(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.sin(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.sin(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.sin(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number cos(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.cos(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.cos(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.cos(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number tan(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.tan(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.tan(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.tan(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number asin(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.asin(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.asin(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.asin(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number acos(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.acos(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.acos(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.acos(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number atan(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.atan(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.atan(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.atan(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number atan2(Number a, Number b) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass()) && ClassUtils.isPrimitiveOrWrapper(b.getClass())) {
            double result = Math.atan2(a.doubleValue(), b.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.atan2(new BigDecimal(a.toString()), new BigDecimal(b.toString()), new MathContext(16));
            }

            return result;
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return BigDecimalMath.atan2((BigDecimal) a, (BigDecimal) b, new MathContext(16));
        }

        if (a instanceof BigDecimal) {
            return BigDecimalMath.atan2((BigDecimal) a, new BigDecimal(b.toString()), new MathContext(16));
        }

        if (b instanceof BigDecimal) {
            return BigDecimalMath.atan2(new BigDecimal(a.toString()), (BigDecimal) b, new MathContext(16));
        }

        return BigDecimalMath.atan2(new BigDecimal(a.toString()), new BigDecimal(b.toString()), new MathContext(16));
    }

    public static Number sinh(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.sinh(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.sinh(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.sinh(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number cosh(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.cosh(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.cosh(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.cosh(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number tanh(Number a) {
        if (ClassUtils.isPrimitiveOrWrapper(a.getClass())) {
            double result = Math.tanh(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                return BigDecimalMath.tanh(new BigDecimal(a.toString()), new MathContext(16));
            }

            return result;
        }

        return BigDecimalMath.tanh(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number asinh(Number a) {
        if (a instanceof BigDecimal) {
            return BigDecimalMath.asinh((BigDecimal) a, new MathContext(16));
        }

        return BigDecimalMath.asinh(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number acosh(Number a) {
        if (a instanceof BigDecimal) {
            return BigDecimalMath.acosh((BigDecimal) a, new MathContext(16));
        }

        return BigDecimalMath.acosh(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number atanh(Number a) {
        if (a instanceof BigDecimal) {
            return BigDecimalMath.atanh((BigDecimal) a, new MathContext(16));
        }

        return BigDecimalMath.atanh(new BigDecimal(a.toString()), new MathContext(16));
    }
}
