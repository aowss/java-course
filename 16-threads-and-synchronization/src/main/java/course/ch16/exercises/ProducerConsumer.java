package course.ch16.exercises;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Exercise 2 (Practice): Implement a bounded buffer using {@code wait()} and {@code notifyAll()}.
 *
 * <p>This is the classic producer–consumer pattern. Producers add items to the buffer
 * (blocking if full) and consumers remove items (blocking if empty).
 *
 * @param <T> the type of elements in the buffer
 */
public class ProducerConsumer<T> {

    private final Queue<T> buffer = new LinkedList<>();
    private final int capacity;

    /**
     * Creates a bounded buffer with the given capacity.
     *
     * @param capacity the maximum number of items the buffer can hold
     * @throws IllegalArgumentException if {@code capacity} is not positive
     */
    public ProducerConsumer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive, was: " + capacity);
        }
        this.capacity = capacity;
    }

    /**
     * Adds an item to the buffer, blocking if the buffer is full.
     *
     * @param item the item to add
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public void produce(T item) throws InterruptedException {
        // TODO: use synchronized, wait(), and notifyAll()
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Removes and returns an item from the buffer, blocking if the buffer is empty.
     *
     * @return the next item
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public T consume() throws InterruptedException {
        // TODO: use synchronized, wait(), and notifyAll()
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the current number of items in the buffer.
     *
     * @return the buffer size
     */
    public int size() {
        // TODO: make this thread-safe
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
