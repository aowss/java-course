package course.ch10.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedList<T extends Comparable<T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public SortedList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public void add(T element) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        int index = 0;
        while (index < size && ((T) elements[index]).compareTo(element) < 0) {
            index++;
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    @SuppressWarnings("unchecked")
    public boolean contains(T element) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = ((T) elements[mid]).compareTo(element);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public List<T> toList() {
        List<T> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add((T) elements[i]);
        }
        return Collections.unmodifiableList(result);
    }
}
