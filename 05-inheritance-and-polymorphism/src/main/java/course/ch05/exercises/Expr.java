package course.ch05.exercises;

/**
 * Exercise 3 (Challenge): Abstract base for an arithmetic expression tree.
 *
 * <p>Concrete subclasses ({@link Num}, {@link Add}, {@link Mul}) represent
 * numbers and operations. Call {@link #eval()} to compute the result.
 */
public abstract class Expr {

    /**
     * Evaluates this expression and returns its numeric value.
     *
     * @return the result of evaluating the expression
     */
    public abstract double eval();

    /**
     * Returns a parenthesized string representation of this expression.
     */
    @Override
    public abstract String toString();
}
