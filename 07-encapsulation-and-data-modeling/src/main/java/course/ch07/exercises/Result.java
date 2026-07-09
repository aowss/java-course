package course.ch07.exercises;

/**
 * Exercise 2 (Practice): Sealed {@code Result} type for success/failure outcomes.
 *
 * @param <T> the success value type
 */
public sealed interface Result<T> permits Result.Success, Result.Failure {

    /**
     * A successful result wrapping a value.
     *
     * @param value the success value
     * @param <T>   the value type
     */
    record Success<T>(T value) implements Result<T> {
    }

    /**
     * A failed result wrapping an error message.
     *
     * @param message the error message
     * @param <T>     the value type (unused)
     */
    record Failure<T>(String message) implements Result<T> {
    }

    /**
     * Creates a successful result.
     *
     * @param value the value
     * @param <T>   the value type
     * @return a success result
     */
    static <T> Result<T> ok(T value) {
        return new Success<>(value);
    }

    /**
     * Creates a failed result.
     *
     * @param message the error message
     * @param <T>     the value type
     * @return a failure result
     */
    static <T> Result<T> fail(String message) {
        return new Failure<>(message);
    }

    /**
     * Returns the value if successful, or the given default if failed.
     *
     * @param defaultValue the value to return on failure
     * @return the success value or the default
     */
    default T getOrElse(T defaultValue) {
        // TODO: implement — use pattern matching switch
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Transforms the success value, leaving failures unchanged.
     *
     * @param mapper the transformation function
     * @param <R>    the result type
     * @return a new result with the mapped value
     */
    default <R> Result<R> map(java.util.function.Function<T, R> mapper) {
        // TODO: implement — map Success values, propagate Failure
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a human-readable description of this result.
     *
     * @return a description string
     */
    default String describe() {
        // TODO: implement — "Success: value" or "Failure: message"
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        Result<Integer> result = Result.ok(42);
        System.out.println(result.describe());
        System.out.println("Value: " + result.getOrElse(0));
    }
}
