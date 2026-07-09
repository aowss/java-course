package course.ch05.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExprTest {

    @Test
    void numEval() {
        assertEquals(42.0, new Num(42).eval(), 0.001);
    }

    @Test
    void addEval() {
        var expr = new Add(new Num(2), new Num(3));
        assertEquals(5.0, expr.eval(), 0.001);
    }

    @Test
    void mulEval() {
        var expr = new Mul(new Num(4), new Num(5));
        assertEquals(20.0, expr.eval(), 0.001);
    }

    @Test
    void nestedExpression() {
        var expr = new Mul(new Add(new Num(2), new Num(3)), new Num(4));
        assertEquals(20.0, expr.eval(), 0.001);
    }

    @Test
    void deeplyNestedExpression() {
        var expr = new Mul(
                new Add(new Num(1), new Num(2)),
                new Add(new Num(3), new Num(4))
        );
        assertEquals(21.0, expr.eval(), 0.001);
    }

    @Test
    void numToString() {
        assertEquals("42.0", new Num(42).toString());
    }

    @Test
    void addToString() {
        var expr = new Add(new Num(2), new Num(3));
        assertEquals("(2.0 + 3.0)", expr.toString());
    }

    @Test
    void mulToString() {
        var expr = new Mul(new Num(4), new Num(5));
        assertEquals("(4.0 * 5.0)", expr.toString());
    }

    @Test
    void nestedToString() {
        var expr = new Mul(new Add(new Num(2), new Num(3)), new Num(4));
        assertEquals("((2.0 + 3.0) * 4.0)", expr.toString());
    }
}
