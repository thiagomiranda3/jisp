package br.com.tommiranda;

import br.com.tommiranda.exceptions.SymbolUndefined;
import br.com.tommiranda.interpreter.Tokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class FunctionsTest {

    @Test
    @DisplayName("Test sum: (+ 1 2 3 4)")
    void testSimpleSum() {
        Object result = TestUtils.evalExpr("(+ 1 2 3 4)");
        Assertions.assertEquals(10.0d, result);
    }

    @Test
    @DisplayName("Test div: (/ (* 3 2) (- 10 5))")
    void testDiv() {
        Object result = TestUtils.evalExpr("(/ (* 3 2) (- 10 5))");
        Assertions.assertEquals(1.2d, result);
    }

    @Test
    @DisplayName("Test not parseable")
    void testNotParseable() {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.tokenize("(+");
        Assertions.assertFalse(tokenizer.isParseable());
    }

    @Test
    @DisplayName("Test symbol undefined")
    void testSymbolUndefined() {
        Assertions.assertThrows(SymbolUndefined.class, () -> TestUtils.evalExpr("(hello)"));
    }

    @Test
    @DisplayName("Test define symbol: (define r 10)")
    void testDefineSymbol() {
        TestUtils.evalExpr("(define r 10)");
        Object result = TestUtils.evalExpr("r");
        Assertions.assertEquals(10d, result);
    }

    @Test
    @DisplayName("Test function definition: (define circle-area (lambda (r) (* (pi) (* r r))))")
    void testFunctionCircleArea() {
        TestUtils.evalExpr("(define circle-area (lambda (r) (* (pi) (* r r))))");
        Object result = TestUtils.evalExpr("(circle-area (+ 5 5))");
        Assertions.assertEquals("314.15926535897927", result.toString());
    }

    @Test
    @DisplayName("Test factorial type")
    void testFactorialType() {
        TestUtils.evalExpr("(define fact (lambda (n) (if (<= n 1) 1 (* n (fact (- n 1))))))");

        Object doubleValue = TestUtils.evalExpr("(type (fact 10))");
        Object bigDecimalValue = TestUtils.evalExpr("(type (fact 175))");

        Assertions.assertEquals(Double.class, doubleValue);
        Assertions.assertEquals(BigDecimal.class, bigDecimalValue);
    }
}
