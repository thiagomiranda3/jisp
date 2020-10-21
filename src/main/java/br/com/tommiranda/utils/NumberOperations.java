package br.com.tommiranda.utils;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class NumberOperations {

    public static Number sum(Number a, Number b) {
        try {
            if (a instanceof Double && b instanceof Double) {
                double result = a.doubleValue() + b.doubleValue();

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
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
        } catch (ArithmeticException e) {
        }

        return new BigDecimal(a.toString()).add(new BigDecimal(b.toString()));
    }

    public static Number subtract(Number a, Number b) {
        try {
            if (a instanceof Double && b instanceof Double) {
                double result = a.doubleValue() - b.doubleValue();

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
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
        } catch (ArithmeticException e) {
        }

        return new BigDecimal(a.toString()).subtract(new BigDecimal(b.toString()));
    }

    public static Number multiply(Number a, Number b) {
        try {
            if (a instanceof Double && b instanceof Double) {
                double result = a.doubleValue() * b.doubleValue();

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
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
        } catch (ArithmeticException e) {
        }

        return new BigDecimal(a.toString()).multiply(new BigDecimal(b.toString()));
    }

    public static Number divide(Number a, Number b) {
        if (a instanceof Double && b instanceof Double) {
            double result = a.doubleValue() / b.doubleValue();

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                throw new ArithmeticException("double overflow");
            }

            return result;
        }

        if (a instanceof BigDecimal && b instanceof BigDecimal) {
            return ((BigDecimal) a).divide((BigDecimal) b, 16, RoundingMode.HALF_EVEN);
        }

        if (a instanceof BigDecimal) {
            return ((BigDecimal) a).divide(new BigDecimal(b.toString()), 16, RoundingMode.HALF_EVEN);
        }

        if (b instanceof BigDecimal) {
            return new BigDecimal(a.toString()).divide((BigDecimal) b, 16, RoundingMode.HALF_EVEN);
        }

        return new BigDecimal(a.toString()).divide(new BigDecimal(b.toString()), 16, RoundingMode.HALF_EVEN);
    }

    public static Number remainder(Number a, Number b) {
        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() % b.doubleValue();
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
        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() >= b.doubleValue() ? a : b;
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
        if (a instanceof Double && b instanceof Double) {
            return a.doubleValue() <= b.doubleValue() ? a : b;
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

    public static Number sqrt(Number a) {
        if (a instanceof Double) {
            double result = Math.sqrt(a.doubleValue());

            if (Double.isInfinite(result) || Double.isNaN(result)) {
                throw new ArithmeticException("double overflow");
            }

            return result;
        }

        return ((BigDecimal) a).sqrt(new MathContext(16));
    }

    public static Number pi(Number a) {
        long precision = (long) a.doubleValue();

        Apfloat result = ApfloatMath.pi(precision);

        if (precision > 16) {
            return new BigDecimal(result.toString(true));
        }

        return result.doubleValue();
    }
}
