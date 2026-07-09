package course.ch06.exercises;

/**
 * Exercise 2 (Practice): Generic validation with a functional interface.
 *
 * <p>Implement {@link Validator} and factory methods for common validation rules.
 */
@FunctionalInterface
public interface Validator<T> {

    /**
     * Validates the given value.
     *
     * @param value the value to validate
     * @return {@code true} if valid, {@code false} otherwise
     */
    boolean validate(T value);

    /**
     * Returns a human-readable error message for an invalid value.
     *
     * @param value the invalid value
     * @return the error message
     */
    default String errorMessage(T value) {
        return "Invalid value: " + value;
    }

    /**
     * Creates a validator that rejects {@code null} values.
     *
     * @param <T> the value type
     * @return a non-null validator
     */
    static <T> Validator<T> nonNull() {
        // TODO: implement — return false for null, true otherwise
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Creates a validator that checks strings are not blank.
     *
     * @return a non-blank string validator
     */
    static Validator<String> nonBlank() {
        // TODO: implement — reject null and blank strings
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Creates a validator that checks a string's length is within bounds (inclusive).
     *
     * @param min minimum length
     * @param max maximum length
     * @return a length-range validator
     */
    static Validator<String> lengthBetween(int min, int max) {
        // TODO: implement — check length is between min and max
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Combines this validator with another — both must pass.
     *
     * @param other the other validator
     * @return a combined validator
     */
    default Validator<T> and(Validator<T> other) {
        // TODO: implement — both validators must return true
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        Validator<String> username = nonBlank().and(lengthBetween(3, 20));
        System.out.println("Valid 'alice': " + username.validate("alice"));
        System.out.println("Valid '':      " + username.validate(""));
    }
}
