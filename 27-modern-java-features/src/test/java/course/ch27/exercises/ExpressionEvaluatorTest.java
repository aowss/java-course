package course.ch27.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpressionEvaluatorTest {

    @Test
    void evaluateLiteral() {
        assertEquals(42.0, ExpressionEvaluator.evaluate(new ExpressionEvaluator.Literal(42)));
    }

    @Test
    void evaluateAdd() {
        ExpressionEvaluator.Expr expr = new ExpressionEvaluator.Add(
                new ExpressionEvaluator.Literal(2),
                new ExpressionEvaluator.Literal(3)
        );
        assertEquals(5.0, ExpressionEvaluator.evaluate(expr));
    }

    @Test
    void evaluateMul() {
        ExpressionEvaluator.Expr expr = new ExpressionEvaluator.Mul(
                new ExpressionEvaluator.Literal(3),
                new ExpressionEvaluator.Literal(4)
        );
        assertEquals(12.0, ExpressionEvaluator.evaluate(expr));
    }

    @Test
    void evaluateNeg() {
        assertEquals(-7.0, ExpressionEvaluator.evaluate(
                new ExpressionEvaluator.Neg(new ExpressionEvaluator.Literal(7))));
    }

    @Test
    void evaluateNestedExpression() {
        ExpressionEvaluator.Expr expr = new ExpressionEvaluator.Add(
                new ExpressionEvaluator.Literal(2),
                new ExpressionEvaluator.Mul(
                        new ExpressionEvaluator.Literal(3),
                        new ExpressionEvaluator.Literal(4)
                )
        );
        assertEquals(14.0, ExpressionEvaluator.evaluate(expr));
    }
}
