package course.ch11.exercises;

import java.util.List;

/**
 * Exercise 3 (Challenge): A generic list that maintains sorted order.
 *
 * <p>Implement a list that keeps its elements in natural (ascending) order.
 * New elements are inserted at the correct position so the list is always sorted.
 *
 * <p>The {@link #contains(Comparable)} method should use binary search for efficiency.
 *
 * @param <T> the element type, which must be {@link Comparable}
 */
public class SortedList<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Creates an empty sorted list.
     */
    public SortedList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Adds an element in sorted order.
     *
     * @param element the element to add
     */
    public void add(T element) {
        // TODO: implement — find insertion point, shift elements right, insert
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the element at the given index.
     *
     * @param index the zero-based index
     * @return the element at the index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code true} if this list contains the given element, using binary search.
     *
     * @param element the element to search for
     * @return {@code true} if found
     */
    public boolean contains(T element) {
        // TODO: implement — use binary search for O(log n) lookup
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the size
     */
    public int size() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns an unmodifiable {@link List} view of the elements.
     *
     * @return the elements as a list
     */
    public List<T> toList() {
        // TODO: implement — copy elements into a new list and return unmodifiable
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
