package br.com.tommiranda;

import br.com.tommiranda.interpreter.ExprFormater;
import br.com.tommiranda.interpreter.Tokenizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class FunctionsTest {

    @Test
    @DisplayName("Test macro unless")
    void testMacroUnless() {
        TestUtils.evalExpr("(define-macro unless (lambda args `(if (not ,(car args)) (begin ,@(cdr args))))) ; test `");
        Object result = TestUtils.evalExpr("(unless (= 4 (+ 1 1)) 3 4)");
        Assertions.assertEquals(4L, result);
    }

    @Test
    @DisplayName("Test lambda args")
    void testLambdaArgs() {
        TestUtils.evalExpr("(define lyst (lambda items items))");
        Object result = TestUtils.evalExpr("(lyst 1 2 3 (+ 2 2))");
        Assertions.assertEquals("(1 2 3 4)", ExprFormater.format(result));
    }

    @Test
    @DisplayName("Test sum: (+ 1 2 3 4)")
    void testSimpleSum() {
        Object result = TestUtils.evalExpr("(+ 1 2 3 4)");
        Assertions.assertEquals(10L, result);
    }

    @Test
    @DisplayName("Test quote")
    void testQuote() {
        Object result = TestUtils.evalExpr("(quote (testing 1 (2.0) -3.14E159))");
        Assertions.assertEquals("(testing 1 (2.0) -3.14E159)", ExprFormater.format(result));
    }

    @Test
    @DisplayName("Test quote '")
    void testQuote2() {
        Object result = TestUtils.evalExpr("'(one 2 3)");
        Assertions.assertEquals("(one 2 3)", ExprFormater.format(result));
    }

    @Test
    @DisplayName("Test unquote-splice")
    void testUnquoteSplice() {
        TestUtils.evalExpr("(define L (list 1 2 3))");
        Object result = TestUtils.evalExpr("`(testing ,@L testing)");
        Assertions.assertEquals("(testing 1 2 3 testing)", ExprFormater.format(result));

        result = TestUtils.evalExpr("`(testing ,L testing)");
        Assertions.assertEquals("(testing (1 2 3) testing)", ExprFormater.format(result));
    }

    @Test
    @DisplayName("Test functions with define and compositions")
    void testFunctions() {
        String compose = "(define compose (lambda (f g) (lambda (x) (f (g x)))))";

        String repeat = "(define repeat (lambda (f) (compose f f)))";

        String combine = "(define combine (lambda (f)" +
                         "    (lambda (x y)" +
                         "      (if (empty? x) (quote ())" +
                         "          (f (list (car x) (car y))" +
                         "             ((combine f) (cdr x) (cdr y)))))))";

        String zip = "(define zip (combine cons))";

        String riff = "(define riff-shuffle (lambda (deck) (begin" +
                      "    (define take (lambda (n seq) (if (<= n 0) (quote ()) (cons (car seq) (take (- n 1) (cdr seq))))))" +
                      "    (define drop (lambda (n seq) (if (<= n 0) seq (drop (- n 1) (cdr seq)))))" +
                      "    (define mid (lambda (seq) (/ (length seq) 2)))" +
                      "    ((combine append) (take (mid deck) deck) (drop (mid deck) deck)))))";

        TestUtils.evalExpr(compose);
        TestUtils.evalExpr(repeat);
        TestUtils.evalExpr(combine);
        TestUtils.evalExpr(zip);
        TestUtils.evalExpr(riff);

        Object result = TestUtils.evalExpr("((repeat riff-shuffle) (list 1 2 3 4 5 6 7 8))");

        Assertions.assertEquals("(1 3 5 7 2 4 6 8)", ExprFormater.format(result));
    }

    @Test
    @DisplayName("Test div: (/ (* 3 2) (- 10 5))")
    void testDiv() {
        Object result = TestUtils.evalExpr("(/ (* 3.0 2) (- 10.0 5))");
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
    @DisplayName("Test define symbol: (define r 10)")
    void testDefineSymbol() {
        TestUtils.evalExpr("(define r 10)");
        Object result = TestUtils.evalExpr("r");
        Assertions.assertEquals(10L, result);
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
        Object bigIntValue = TestUtils.evalExpr("(type (fact 175))");

        Assertions.assertEquals(Long.class, doubleValue);
        Assertions.assertEquals(BigInteger.class, bigIntValue);
    }
}
