package br.com.tommiranda.lang.functions;

import br.com.tommiranda.lang.GlobalFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;

public final class MathFunctions {

    @GlobalFunction("+")
    public static BigDecimal sum(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (BigDecimal) n)
                      .reduce(BigDecimal::add)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to + operator"));
    }

    @GlobalFunction("-")
    public static BigDecimal substract(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (BigDecimal) n)
                      .reduce(BigDecimal::subtract)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to - operator"));
    }

    @GlobalFunction("*")
    public static BigDecimal multiple(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (BigDecimal) n)
                      .reduce(BigDecimal::multiply)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to * operator"));
    }

    @GlobalFunction("/")
    public static BigDecimal divide(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (BigDecimal) n)
                      .reduce((a, b) -> a.divide(b, 8, RoundingMode.HALF_EVEN))
                      .orElseThrow(() -> new NoSuchElementException("No params passed to / operator"));
    }

    @GlobalFunction("%")
    public static BigDecimal remainder(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (BigDecimal) n)
                      .reduce(BigDecimal::remainder)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to % operator"));
    }

    @GlobalFunction
    public static BigDecimal max(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (BigDecimal) n)
                      .reduce(BigDecimal::max)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to max function"));
    }

    @GlobalFunction
    public static BigDecimal min(List<Object> numbers) {
        return numbers.stream()
                      .map(n -> (BigDecimal) n)
                      .reduce(BigDecimal::min)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to min function"));
    }
}
