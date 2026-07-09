package course.ch16.exercises;

/**
 * Exercise 1 (Guided): Implement a thread-safe counter using {@code synchronized}.
 *
 * <p>All methods must be safe for concurrent access from multiple threads.
 */
public class SafeCounter {

    private int value;

    /**
     * Atomically increments the counter by one.
     */
    public void increment() {
        // TODO: make this thread-safe
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Atomically decrements the counter by one.
     */
    public void decrement() {
        // TODO: make this thread-safe
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the current counter value.
     *
     * @return the current value
     */
    public int get() {
        // TODO: make this thread-safe
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
