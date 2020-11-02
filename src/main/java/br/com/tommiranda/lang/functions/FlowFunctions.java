package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongArguments;
import br.com.tommiranda.interpreter.Truth;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;
import br.com.tommiranda.utils.FlowOperations;

import java.util.List;

public class FlowFunctions {

    @GlobalFunction("<=")
    public static Object lessThenOrEqual(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("<=", 2, numbers.size()));
        }

        return FlowOperations.lessThenOrEqual((Number) numbers.get(0), (Number) numbers.get(1));
    }

    @GlobalFunction(">=")
    public static Object greaterThenOrEqual(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired(">=", 2, numbers.size()));
        }

        return FlowOperations.greaterThenOrEqual((Number) numbers.get(0), (Number) numbers.get(1));
    }

    @GlobalFunction("<")
    public static Object lessThen(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("<", 2, numbers.size()));
        }

        return FlowOperations.lessThen((Number) numbers.get(0), (Number) numbers.get(1));
    }

    @GlobalFunction(">")
    public static Object greaterThen(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired(">", 2, numbers.size()));
        }

        return FlowOperations.greaterThen((Number) numbers.get(0), (Number) numbers.get(1));
    }

    @GlobalFunction("=")
    public static Object equals(List<Object> numbers) {
        if(numbers.size() != 2) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired(">", 2, numbers.size()));
        }

        Object a = numbers.get(0);
        Object b = numbers.get(1);

        return a.equals(b);
    }

    @GlobalFunction("test")
    public static Object test(List<Object> numbers) {
        if(numbers.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("test", 1, numbers.size()));
        }

        return Truth.isTrue(numbers.get(0));
    }

    @GlobalFunction("not")
    public static Object not(List<Object> numbers) {
        if(numbers.size() != 1) {
            throw new WrongArguments(ErrorMessages.wrongParamRequired("not", 1, numbers.size()));
        }

        return !Truth.isTrue(numbers.get(0));
    }
}
