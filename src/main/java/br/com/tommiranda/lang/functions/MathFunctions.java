package br.com.tommiranda.lang.functions;

import br.com.tommiranda.exceptions.WrongParamsException;
import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.ErrorMessages;
import br.com.tommiranda.utils.NumberOperations;
import org.apfloat.ApfloatMath;

import java.util.List;
import java.util.NoSuchElementException;

public final class MathFunctions {

    @GlobalFunction("+")
    public static Number sum(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::sum)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to + operator"));
    }

    @GlobalFunction("-")
    public static Number substract(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::subtract)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to - operator"));
    }

    @GlobalFunction("*")
    public static Number multiple(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::multiply)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to * operator"));
    }

    @GlobalFunction("/")
    public static Number divide(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::divide)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to / operator"));
    }

    @GlobalFunction("%")
    public static Number remainder(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::remainder)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to % operator"));
    }

    @GlobalFunction
    public static Number max(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::max)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to max function"));
    }

    @GlobalFunction
    public static Number min(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (Number) n)
                      .reduce(NumberOperations::min)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to min function"));
    }

    @GlobalFunction
    public static Number sqrt(List<Object> numbers) {
        if (numbers.size() != 1) {
            throw new WrongParamsException("sqrt function has 1 required param. " + numbers.size() + " + were found");
        }

        Number number = (Number) numbers.get(0);

        return NumberOperations.sqrt(number);
    }

    @GlobalFunction
    public static Number pi(List<Object> numbers) {
        if (numbers.size() > 1) {
            throw new WrongParamsException(ErrorMessages.wrongParamOptional("pi", 1, numbers.size()));
        }

        Number precision = 16d;
        if(numbers.size() == 1) {
            precision = (Number) numbers.get(0);
        }

        return NumberOperations.pi(precision);
    }
}
