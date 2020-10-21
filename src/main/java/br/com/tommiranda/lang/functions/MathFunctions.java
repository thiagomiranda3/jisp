package br.com.tommiranda.lang.functions;

import br.com.tommiranda.lang.GlobalFunction;
import br.com.tommiranda.utils.NumberOperations;

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
}
