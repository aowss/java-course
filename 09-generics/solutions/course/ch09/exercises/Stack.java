package course.ch09.exercises;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public Stack() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void push(T element) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = (T) elements[--size];
        elements[size] = null;
        return element;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
