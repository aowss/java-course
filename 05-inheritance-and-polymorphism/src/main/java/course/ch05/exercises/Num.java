package course.ch05.exercises;

/**
 * Exercise 3 (Challenge): A numeric literal in an expression tree.
 *
 * <p>{@link #eval()} returns the stored value.
 * {@link #toString()} returns the value as a string (e.g. {@code "42.0"}).
 */
public class Num extends Expr {

    // TODO: declare a private final field 'value' (double)

    /**
     * Creates a numeric expression with the given value.
     *
     * @param value the numeric value
     */
    public Num(double value) {
        // TODO: assign the field
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public double eval() {
        // TODO: return the value
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String toString() {
        // TODO: return String.valueOf(value)
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
