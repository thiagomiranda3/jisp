package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;

import java.util.List;

public class FlowFunctions {

    @GlobalFunction("<=")
    public static Object lessThenOrEqual(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired("<=", 2, numbers.size()));
        }

        Comparable a = (Comparable) numbers.get(0);
        Comparable b = (Comparable) numbers.get(1);

        int test = a.compareTo(b);

        return test <= 0;
    }

    @GlobalFunction(">=")
    public static Object greaterThenOrEqual(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired(">=", 2, numbers.size()));
        }

        Comparable a = (Comparable) numbers.get(0);
        Comparable b = (Comparable) numbers.get(1);

        int test = a.compareTo(b);

        return test >= 0;
    }

    @GlobalFunction("<")
    public static Object lessThen(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired("<", 2, numbers.size()));
        }

        Comparable a = (Comparable) numbers.get(0);
        Comparable b = (Comparable) numbers.get(1);

        int test = a.compareTo(b);

        return test < 0;
    }

    @GlobalFunction(">")
    public static Object greaterThen(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired(">", 2, numbers.size()));
        }

        Comparable a = (Comparable) numbers.get(0);
        Comparable b = (Comparable) numbers.get(1);

        int test = a.compareTo(b);

        return test > 0;
    }

    @GlobalFunction("=")
    public static Object equals(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongParamsException(ErrorMessages.wrongParamRequired(">", 2, numbers.size()));
        }

        Object a = numbers.get(0);
        Object b = numbers.get(1);

        return a.equals(b);
    }
}
