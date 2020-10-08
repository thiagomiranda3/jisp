package br.com.tommiranda.lang;

import br.com.tommiranda.ast.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;

public final class MathFunctions {

    @GlobalFunction("+")
    public static Value sum(List<Value> numbers) {
        return numbers.stream()
                      .map(Value::getAsNumber)
                      .reduce(BigDecimal::add)
                      .map(Value::new)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to + operator"));
    }

    @GlobalFunction("-")
    public static Value substract(List<Value> numbers) {
        return numbers.stream()
                      .map(Value::getAsNumber)
                      .reduce(BigDecimal::subtract)
                      .map(Value::new)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to - operator"));
    }

    @GlobalFunction("*")
    public static Value multiple(List<Value> numbers) {
        return numbers.stream()
                      .map(Value::getAsNumber)
                      .reduce(BigDecimal::multiply)
                      .map(Value::new)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to * operator"));
    }

    @GlobalFunction("/")
    public static Value divide(List<Value> numbers) {
        return numbers.stream()
                      .map(Value::getAsNumber)
                      .reduce((a, b) -> a.divide(b, 8, RoundingMode.HALF_EVEN))
                      .map(Value::new)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to / operator"));
    }

    @GlobalFunction("%")
    public static Value remainder(List<Value> numbers) {
        return numbers.stream()
                      .map(Value::getAsNumber)
                      .reduce(BigDecimal::remainder)
                      .map(Value::new)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to % operator"));
    }

    @GlobalFunction
    public static Value max(List<Value> numbers) {
        return numbers.stream()
                      .map(Value::getAsNumber)
                      .reduce(BigDecimal::max)
                      .map(Value::new)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to max function"));
    }

    @GlobalFunction
    public static Value min(List<Value> numbers) {
        return numbers.stream()
                      .map(Value::getAsNumber)
                      .reduce(BigDecimal::min)
                      .map(Value::new)
                      .orElseThrow(() -> new NoSuchElementException("No params passed to min function"));
    }
}
