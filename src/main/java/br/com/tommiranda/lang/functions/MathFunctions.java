package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongArgumentsException;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;
import br.com.tommiranda.utils.NumberOperations;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

public final class MathFunctions {

    @GlobalFunction("+")
    public static Number sum(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::sum)
                      .orElseThrow(() -> new NoSuchElementException("No param passed to + operator"));
    }

    @GlobalFunction("-")
    public static Number substract(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::subtract)
                      .orElseThrow(() -> new NoSuchElementException("No param passed to - operator"));
    }

    @GlobalFunction("*")
    public static Number multiple(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::multiply)
                      .orElseThrow(() -> new NoSuchElementException("No param passed to * operator"));
    }

    @GlobalFunction("/")
    public static Number divide(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::divide)
                      .orElseThrow(() -> new NoSuchElementException("No param passed to / operator"));
    }

    @GlobalFunction("%")
    public static Number remainder(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::remainder)
                      .orElseThrow(() -> new NoSuchElementException("No param passed to % operator"));
    }

    @GlobalFunction
    public static Number max(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::max)
                      .orElseThrow(() -> new NoSuchElementException("No param passed to max function"));
    }

    @GlobalFunction
    public static Number min(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::min)
                      .orElseThrow(() -> new NoSuchElementException("No param passed to min function"));
    }

    @GlobalFunction
    public static Number bigdec(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("bigdec", 1, numbers.size()));
        }

        return new BigDecimal(numbers.get(0).toString());
    }

    @GlobalFunction
    public static Number pow(List<Object> numbers) {
        if (numbers.size() != 2) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("pow", 2, numbers.size()));
        }

        Number number = (Number) numbers.get(0);
        Number pow = (Number) numbers.get(1);

        return NumberOperations.pow(number, pow);
    }

    @GlobalFunction
    public static Number sqrt(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("sqrt", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.sqrt(number);
    }

    @GlobalFunction
    public static Number abs(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("abs", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.abs(number);
    }

    @GlobalFunction
    public static Number pi(List<Object> numbers) {
        if (numbers.size() > 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamOptional("pi", 1, numbers.size()));
        }

        Number precision = 16d;
        if (numbers.size() == 1) {
            precision = (Number) numbers.get(0);
        }

        return NumberOperations.pi(precision);
    }

    @GlobalFunction
    public static Number sin(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("sin", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.sin(number);
    }

    @GlobalFunction
    public static Number cos(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("cos", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.cos(number);
    }

    @GlobalFunction
    public static Number tan(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("tan", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.tan(number);
    }

    @GlobalFunction
    public static Number asin(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("asin", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.asin(number);
    }

    @GlobalFunction
    public static Number acos(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("acos", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.acos(number);
    }

    @GlobalFunction
    public static Number atan(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("atan", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.atan(number);
    }

    @GlobalFunction
    public static Number atan2(List<Object> numbers) {
        if (numbers.size() != 2) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("atan2", 2, numbers.size()));
        }

        Number a = (Number) numbers.get(0);
        Number b = (Number) numbers.get(1);

        return NumberOperations.atan2(a, b);
    }

    @GlobalFunction
    public static Number sinh(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("sinh", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.sinh(number);
    }

    @GlobalFunction
    public static Number cosh(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("cosh", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.cosh(number);
    }

    @GlobalFunction
    public static Number tanh(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("tanh", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.tanh(number);
    }

    @GlobalFunction
    public static Number asinh(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("asinh", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.asinh(number);
    }

    @GlobalFunction
    public static Number acosh(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("acosh", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.acosh(number);
    }

    @GlobalFunction
    public static Number atanh(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongArgumentsException(ErrorMessages.wrongParamRequired("atanh", 1, numbers.size()));
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.atanh(number);
    }
}
