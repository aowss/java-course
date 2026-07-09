package course.ch04.exercises;

/**
 * Exercise 3 (Challenge): A stopwatch that measures elapsed time.
 *
 * <p>Use {@link System#nanoTime()} to track elapsed time between
 * {@link #start()} and {@link #stop()} calls. The stopwatch supports
 * multiple start/stop cycles, accumulating the total elapsed time.
 */
public class Stopwatch {

    // TODO: declare private fields to track state and time

    /**
     * Starts the stopwatch. Throws if already running.
     *
     * @throws IllegalStateException if the stopwatch is already running
     */
    public void start() {
        // TODO: record the start time
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Stops the stopwatch and accumulates the elapsed time.
     * Throws if not currently running.
     *
     * @throws IllegalStateException if the stopwatch is not running
     */
    public void stop() {
        // TODO: calculate and accumulate elapsed time
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the total elapsed time in milliseconds.
     * If the stopwatch is currently running, includes the time since the last start.
     *
     * @return elapsed time in milliseconds
     */
    public long elapsedMillis() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Resets the stopwatch to its initial state.
     * Stops the stopwatch if it is running.
     */
    public void reset() {
        // TODO: reset all fields
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns whether the stopwatch is currently running.
     *
     * @return {@code true} if running
     */
    public boolean isRunning() {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
