package course.ch14.exercises;

import java.util.concurrent.Callable;

/**
 * Exercise 2 (Practice): Execute a task with retry logic.
 *
 * <p>If the task fails, retry up to {@code maxRetries} times. If all attempts fail,
 * throw a {@link RuntimeException} with the last exception as cause and all earlier
 * exceptions as suppressed.
 *
 * <pre>{@code
 * var executor = new RetryExecutor(3);
 * String result = executor.execute(() -> fetchData());
 * int attempts = executor.getAttemptCount();
 * }</pre>
 */
public class RetryExecutor {

    /**
     * Creates a retry executor with the given maximum number of retries.
     *
     * @param maxRetries the maximum number of retries (0 means execute once with no retries)
     * @throws IllegalArgumentException if {@code maxRetries} is negative
     */
    public RetryExecutor(int maxRetries) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Executes the given task, retrying on failure up to the configured maximum.
     *
     * <p>If all attempts fail, throws a {@link RuntimeException} whose cause is the
     * exception from the last attempt. All earlier exceptions are added as suppressed
     * exceptions on that {@code RuntimeException}.
     *
     * @param task the task to execute
     * @param <T>  the return type of the task
     * @return the result of the first successful execution
     * @throws RuntimeException if all attempts fail
     */
    public <T> T execute(Callable<T> task) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the number of attempts made during the last call to {@link #execute}.
     *
     * @return the attempt count
     */
    public int getAttemptCount() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
