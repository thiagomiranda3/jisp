package br.com.tommiranda;

import br.com.tommiranda.exceptions.DefineMacroError;
import br.com.tommiranda.exceptions.SymbolUndefined;
import br.com.tommiranda.exceptions.SyntaxError;
import br.com.tommiranda.exceptions.WrongArguments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

    @Test
    @DisplayName("Test symbol undefined")
    void testSymbolUndefined() {
        Assertions.assertThrows(SymbolUndefined.class, () -> TestUtils.evalExpr("(hello)"));
    }

    @Test
    @DisplayName("Test WrongArguments")
    void testWrongArguments() {
        Assertions.assertThrows(WrongArguments.class, () -> TestUtils.evalExpr("(set! x)"));
        Assertions.assertThrows(WrongArguments.class, () -> TestUtils.evalExpr("(quote 1 2)"));
        Assertions.assertThrows(WrongArguments.class, () -> TestUtils.evalExpr("(if 1 2 3 4)"));
        Assertions.assertThrows(WrongArguments.class, () -> TestUtils.evalExpr("(lambda (x))"));
    }

    @Test
    @DisplayName("Test SyntaxError")
    void testSyntaxError() {
        Assertions.assertThrows(SyntaxError.class, () -> TestUtils.evalExpr("(define 3 4)"));
        Assertions.assertThrows(SyntaxError.class, () -> TestUtils.evalExpr("(lambda 3 3)"));
        Assertions.assertThrows(SyntaxError.class, () -> TestUtils.evalExpr("`,@L"));
    }

    @Test
    @DisplayName("Test DefineMacroError")
    void testDefineMacroError() {
        Assertions.assertThrows(DefineMacroError.class, () -> TestUtils.evalExpr("(if (= 1 2) (define-macro a 'a) (define-macro a 'b))"));
    }
}
