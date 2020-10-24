package br.com.tommiranda.utils;

import ch.obermuhlner.math.big.BigDecimalMath;
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
        try {
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
        } catch (ArithmeticException e) {
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

    public static Number pow(Number a, Number pow) {
        try {
            if (a instanceof Double) {
                double result = Math.pow(a.doubleValue(), pow.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.pow((BigDecimal) a, new BigDecimal(pow.toString()), new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.pow(new BigDecimal(a.toString()), new BigDecimal(pow.toString()), new MathContext(16));
    }

    public static Number sqrt(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.sqrt(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return ((BigDecimal) a).sqrt(new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return new BigDecimal(a.toString()).sqrt(new MathContext(16));
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

        return ((BigDecimal) a).abs();
    }

    public static Number sin(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.sin(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.sin((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.sin(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number cos(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.cos(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.cos((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.cos(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number tan(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.tan(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.tan((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.tan(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number asin(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.asin(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.asin((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.asin(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number acos(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.acos(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.acos((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.acos(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number atan(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.atan(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.atan((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.atan(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number atan2(Number a, Number b) {
        try {
            if (a instanceof Double && b instanceof Double) {
                double result = Math.atan2(a.doubleValue(), b.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
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
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.atan2(new BigDecimal(a.toString()), new BigDecimal(b.toString()), new MathContext(16));
    }

    public static Number sinh(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.sinh(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.sinh((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.sinh(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number cosh(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.cosh(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.cosh((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
        }

        return BigDecimalMath.cosh(new BigDecimal(a.toString()), new MathContext(16));
    }

    public static Number tanh(Number a) {
        try {
            if (a instanceof Double) {
                double result = Math.tanh(a.doubleValue());

                if (Double.isInfinite(result) || Double.isNaN(result)) {
                    throw new ArithmeticException("double overflow");
                }

                return result;
            }

            if (a instanceof BigDecimal) {
                return BigDecimalMath.tanh((BigDecimal) a, new MathContext(16));
            }
        } catch (ArithmeticException e) {
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
