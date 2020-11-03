package br.com.tommiranda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MathFunctionsTest {

    @Test
    @DisplayName("Test sum")
    void testSum() {
        Object result = TestUtils.evalExpr("(+ 1 2 3 4)");
        Assertions.assertEquals(10L, result);

        result = TestUtils.evalExpr("(+ 1.0 2)");
        Assertions.assertEquals(3.0, result);

        result = TestUtils.evalExpr("(+ 3 (bigdec 2.5))");
        Assertions.assertEquals(new BigDecimal("5.5"), result);

        result = TestUtils.evalExpr("(+ 3 (bigint 10))");
        Assertions.assertEquals(new BigInteger("13"), result);
    }

    @Test
    @DisplayName("Test substract")
    void testSubstract() {
        Object result = TestUtils.evalExpr("(- 1 2 3 4)");
        Assertions.assertEquals(-8L, result);

        result = TestUtils.evalExpr("(- 1.0 2)");
        Assertions.assertEquals(-1.0, result);

        result = TestUtils.evalExpr("(- 3 (bigdec 2.5))");
        Assertions.assertEquals(new BigDecimal("0.5"), result);

        result = TestUtils.evalExpr("(- 3 (bigint 10))");
        Assertions.assertEquals(new BigInteger("-7"), result);
    }

    @Test
    @DisplayName("Test multiply")
    void testMultiply() {
        Object result = TestUtils.evalExpr("(* 1 2 3 4)");
        Assertions.assertEquals(24L, result);

        result = TestUtils.evalExpr("(* 1.0 2)");
        Assertions.assertEquals(2.0, result);

        result = TestUtils.evalExpr("(* 3 (bigdec 2.5))");
        Assertions.assertEquals(new BigDecimal("7.5"), result);

        result = TestUtils.evalExpr("(* 3 (bigint 10))");
        Assertions.assertEquals(new BigInteger("30"), result);
    }

    @Test
    @DisplayName("Test divide")
    void testDivide() {
        Object result = TestUtils.evalExpr("(/ 1 2 3 4)");
        Assertions.assertEquals(0.041666666666666664, result);

        result = TestUtils.evalExpr("(/ 1.0 2)");
        Assertions.assertEquals(0.5, result);

        result = TestUtils.evalExpr("(/ 3 (bigdec 2.5))");
        Assertions.assertEquals(new BigDecimal("1.2"), result);

        result = TestUtils.evalExpr("(/ 1 (bigdec 3))");
        Assertions.assertEquals(new BigDecimal("0.333333333333333333"), result);

        result = TestUtils.evalExpr("(/ 3 (bigint 10))");
        Assertions.assertEquals(new BigDecimal("0.3"), result);
    }
}
