package course.ch05.exercises;

/**
 * Exercise 3 (Challenge): An addition node in an expression tree.
 *
 * <p>{@link #eval()} returns {@code left.eval() + right.eval()}.
 * {@link #toString()} returns {@code "(left + right)"}.
 */
public class Add extends Expr {

    // TODO: declare private final fields 'left' and 'right' (both Expr)

    /**
     * Creates an addition expression.
     *
     * @param left  the left operand
     * @param right the right operand
     */
    public Add(Expr left, Expr right) {
        // TODO: assign the fields
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public double eval() {
        // TODO: return left.eval() + right.eval()
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String toString() {
        // TODO: return "(" + left + " + " + right + ")"
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
