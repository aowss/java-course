package course.ch10.exercises;

/**
 * Exercise 2 (Practice): A queue that rejects duplicate elements.
 *
 * <p>Implement a FIFO queue that combines {@link java.util.Queue} and
 * {@link java.util.Set} semantics: elements are processed in insertion
 * order, but duplicate elements are rejected.
 *
 * @param <T> the type of elements in this queue
 */
public class UniqueQueue<T> {

    /**
     * Creates an empty unique queue.
     */
    public UniqueQueue() {
        // TODO: initialize internal data structures
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Adds an element to the back of the queue if it is not already present.
     *
     * @param element the element to add
     * @return {@code true} if the element was added, {@code false} if it was a duplicate
     */
    public boolean offer(T element) {
        // TODO: implement — add only if not already in the queue
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the front element, or {@code null} if the queue is empty
     */
    public T poll() {
        // TODO: implement — remove from front, also remove from the set
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the element at the front without removing it.
     *
     * @return the front element, or {@code null} if the queue is empty
     */
    public T peek() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the size
     */
    public int size() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code true} if this queue contains the given element.
     *
     * @param element the element to check
     * @return {@code true} if present
     */
    public boolean contains(T element) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
