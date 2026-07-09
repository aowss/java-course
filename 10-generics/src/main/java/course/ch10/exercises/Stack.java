package course.ch10.exercises;

/**
 * Exercise 1 (Guided): A generic stack backed by an array.
 *
 * <p>Implement a last-in-first-out (LIFO) stack with the following operations:
 * <ul>
 *   <li>{@link #push(Object)} — add an element to the top</li>
 *   <li>{@link #pop()} — remove and return the top element</li>
 *   <li>{@link #peek()} — return the top element without removing it</li>
 *   <li>{@link #isEmpty()} — check if the stack is empty</li>
 *   <li>{@link #size()} — return the number of elements</li>
 * </ul>
 *
 * <p>Use an internal {@code Object[]} array. When the array is full, resize it
 * to double its current capacity.
 *
 * @param <T> the type of elements in this stack
 */
public class Stack<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Creates an empty stack with the default initial capacity.
     */
    public Stack() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Pushes an element onto the top of this stack.
     *
     * @param element the element to push
     */
    public void push(T element) {
        // TODO: implement — add element at index size, resize array if full
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Removes and returns the top element.
     *
     * @return the top element
     * @throws java.util.EmptyStackException if the stack is empty
     */
    public T pop() {
        // TODO: implement — return element at size-1, decrement size
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the top element without removing it.
     *
     * @return the top element
     * @throws java.util.EmptyStackException if the stack is empty
     */
    public T peek() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code true} if this stack contains no elements.
     *
     * @return {@code true} if empty
     */
    public boolean isEmpty() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the size
     */
    public int size() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
