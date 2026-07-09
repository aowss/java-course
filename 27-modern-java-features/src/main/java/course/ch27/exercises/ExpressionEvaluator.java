package course.ch27.exercises;

/**
 * Exercise 2 (Practice): Evaluate arithmetic expressions using sealed types.
 *
 * <p>Model expressions as a sealed hierarchy and evaluate them recursively with
 * pattern matching switch.
 *
 * <pre>{@code
 * Expr expr = new Add(new Literal(2), new Mul(new Literal(3), new Literal(4)));
 * evaluate(expr); // 14.0
 * }</pre>
 */
public final class ExpressionEvaluator {

    private ExpressionEvaluator() {
    }

    /**
     * Sealed root type for arithmetic expressions.
     */
    public sealed interface Expr permits Literal, Add, Mul, Neg {
    }

    /**
     * A numeric literal.
     *
     * @param value the constant value
     */
    public record Literal(double value) implements Expr {
    }

    /**
     * Addition of two sub-expressions.
     *
     * @param left  the left operand
     * @param right the right operand
     */
    public record Add(Expr left, Expr right) implements Expr {
    }

    /**
     * Multiplication of two sub-expressions.
     *
     * @param left  the left operand
     * @param right the right operand
     */
    public record Mul(Expr left, Expr right) implements Expr {
    }

    /**
     * Unary negation.
     *
     * @param expr the sub-expression to negate
     */
    public record Neg(Expr expr) implements Expr {
    }

    /**
     * Recursively evaluates the given expression tree.
     *
     * @param expr the expression to evaluate
     * @return the numeric result
     */
    public static double evaluate(Expr expr) {
        // TODO: use pattern matching switch on Expr
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
