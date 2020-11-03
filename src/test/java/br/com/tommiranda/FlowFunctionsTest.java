package br.com.tommiranda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FlowFunctionsTest {

    @Test
    @DisplayName("Test lessThenOrEqual")
    void lessThenOrEqual() {
        Object result = TestUtils.evalExpr("(<= 1 2)");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(<= 5 3.232)");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(<= 5 (bigdec 3.232))");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(<= 5.2 (bigint 10))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(<= 5 5.00)");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(<= 5 (bigdec 5))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(<= 5.0 (bigint 5))");
        Assertions.assertEquals(true, result);
    }

    @Test
    @DisplayName("Test greaterThenOrEqual")
    void greaterThenOrEqual() {
        Object result = TestUtils.evalExpr("(>= 1 2)");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(>= 5 3.232)");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(>= 5 (bigdec 3.232))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(>= 5.2 (bigint 10))");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(>= 5 5.00)");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(>= 5 (bigdec 5))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(>= 5.0 (bigint 5))");
        Assertions.assertEquals(true, result);
    }

    @Test
    @DisplayName("Test lessThen")
    void lessThen() {
        Object result = TestUtils.evalExpr("(< 1 2)");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(< 5 3.232)");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(< 5 (bigdec 3.232))");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(< 5.2 (bigint 10))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(< 5 5.00)");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(< 5 (bigdec 5))");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(< 5.0 (bigint 5))");
        Assertions.assertEquals(false, result);
    }

    @Test
    @DisplayName("Test greaterThen")
    void greaterThen() {
        Object result = TestUtils.evalExpr("(> 1 2)");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(> 5 3.232)");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(> 5 (bigdec 3.232))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(> 5.2 (bigint 10))");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(> 5 5.00)");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(> 5 (bigdec 5))");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(> 5.0 (bigint 5))");
        Assertions.assertEquals(false, result);
    }

    @Test
    @DisplayName("Test equals")
    void equals() {
        Object result = TestUtils.evalExpr("(= 1 1)");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(= 1 1.0)");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(= (bigdec 1.000) (bigdec 1.0))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(= (bigint 5) (bigdec 5.000))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(= (bigint 5) (bigdec 5.000000000000001))");
        Assertions.assertEquals(false, result);

        result = TestUtils.evalExpr("(= 5.0 (bigint 5))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(= (bigint 10) (bigint 10))");
        Assertions.assertEquals(true, result);

        result = TestUtils.evalExpr("(= 7 7.000000001)");
        Assertions.assertEquals(false, result);
    }
}
