package br.com.tommiranda;

import br.com.tommiranda.exceptions.SymbolUndefinedException;
import br.com.tommiranda.interpreter.Env;
import br.com.tommiranda.interpreter.Evaluator;
import br.com.tommiranda.interpreter.Parser;
import br.com.tommiranda.interpreter.Tokenizer;
import br.com.tommiranda.lang.Global;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Deque;

public class FunctionsTest {

    Tokenizer tokenizer = new Tokenizer();
    Env env = Global.getGlobalEnv();

    @BeforeEach
    void init() {
        tokenizer.clean();
    }

    @Test
    @DisplayName("Test sum: (+ 1 2 3 4)")
    void testSimpleSum() {
        tokenizer.tokenize("(+ 1 2 3 4)");

        Deque<String> tokens = tokenizer.getTokens();
        Object expr = Parser.parse(tokens);
        Object result = Evaluator.eval(expr, env);

        Assertions.assertEquals(10.0d, result);
    }

    @Test
    @DisplayName("Test div: (/ (* 3 2) (- 10 5))")
    void testDiv() {
        tokenizer.tokenize("(/ (* 3 2) (- 10 5))");

        Deque<String> tokens = tokenizer.getTokens();
        Object expr = Parser.parse(tokens);
        Object result = Evaluator.eval(expr, env);

        Assertions.assertEquals(1.2d, result);
    }

    @Test
    @DisplayName("Test not parseable")
    void testNotParseable() {
        tokenizer.tokenize("(+");

        Assertions.assertFalse(tokenizer.isParseable());
    }

    @Test
    @DisplayName("Test symbol undefined")
    void testSymbolUndefined() {
        tokenizer.tokenize("(hello)");

        Deque<String> tokens = tokenizer.getTokens();
        Object expr = Parser.parse(tokens);

        Assertions.assertThrows(SymbolUndefinedException.class, () -> Evaluator.eval(expr, env));
    }

    @Test
    @DisplayName("Test define symbol: (define r 10)")
    void testDefineSymbol() {
        Evaluator.eval(Parser.parse(tokenizer.tokenize("(define r 10)")), env);

        tokenizer.clean();

        Object result = Evaluator.eval(Parser.parse(tokenizer.tokenize("r")), env);

        Assertions.assertEquals(10d, result);
    }

    @Test
    @DisplayName("Test function definition: (define circle-area (lambda (r) (* (pi) (* r r))))")
    void testFunctionCircleArea() {
        Evaluator.eval(Parser.parse(tokenizer.tokenize("(define circle-area (lambda (r) (* (pi) (* r r))))")), env);

        tokenizer.clean();

        Object result = Evaluator.eval(Parser.parse(tokenizer.tokenize("(circle-area (+ 5 5))")), env);

        Assertions.assertEquals("314.15926535897927", result.toString());
    }

    // (define fact (lambda (n) (if (<= n 1) 1 (* n (fact (- n 1))))))
    @Test
    @DisplayName("Test factorial type")
    void testFactorialType() {
        Evaluator.eval(Parser.parse(tokenizer.tokenize("(define fact (lambda (n) (if (<= n 1) 1 (* n (fact (- n 1))))))")), env);

        tokenizer.clean();

        Object doubleValue = Evaluator.eval(Parser.parse(tokenizer.tokenize("(type (fact 10))")), env);

        tokenizer.clean();

        Object bigDecimalValue = Evaluator.eval(Parser.parse(tokenizer.tokenize("(type (fact 175))")), env);

        Assertions.assertEquals(Double.class, doubleValue);
        Assertions.assertEquals(BigDecimal.class, bigDecimalValue);
    }
}
