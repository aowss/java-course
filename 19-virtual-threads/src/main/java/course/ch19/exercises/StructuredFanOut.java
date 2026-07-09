package course.ch19.exercises;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Exercise 3 (Challenge): Query multiple services and return first success or collect all failures.
 *
 * <p>This exercise simulates the "fan-out" pattern where multiple concurrent operations
 * are launched and you either take the first successful result or aggregate all failures.
 * Uses standard virtual thread APIs.
 *
 * <pre>{@code
 * List<Callable<String>> tasks = List.of(() -> "fast", () -> { throw new Exception(); });
 * String first = StructuredFanOut.firstSuccess(tasks);  // "fast"
 * }</pre>
 */
public class StructuredFanOut {

    /**
     * Runs all tasks on virtual threads and returns the first successful result.
     *
     * <p>If all tasks fail, throws a {@link RuntimeException} whose suppressed exceptions
     * contain all the individual failures.
     *
     * @param tasks the tasks to execute
     * @param <T>   the result type
     * @return the first successful result
     * @throws RuntimeException if all tasks fail
     */
    public static <T> T firstSuccess(List<Callable<T>> tasks) {
        // TODO: launch all tasks on virtual threads
        // TODO: return the first successful result
        // TODO: if all fail, throw RuntimeException with suppressed exceptions
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Runs all tasks on virtual threads and collects all successful results within the timeout.
     *
     * @param tasks   the tasks to execute
     * @param timeout the maximum time to wait for results
     * @param <T>     the result type
     * @return a list of successful results (may be empty if all fail or time out)
     */
    public static <T> List<T> allSuccesses(List<Callable<T>> tasks, Duration timeout) {
        // TODO: launch all tasks on virtual threads
        // TODO: collect results that complete within the timeout
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
