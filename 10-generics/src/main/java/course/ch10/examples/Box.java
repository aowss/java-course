package course.ch10.examples;

/**
 * A simple generic container that holds a single value.
 *
 * <p>Demonstrates the basic syntax for declaring a generic class with a
 * type parameter {@code T}.
 *
 * @param <T> the type of the value stored in this box
 */
public class Box<T> {

    private T value;

    /**
     * Creates an empty box.
     */
    public Box() {
        this.value = null;
    }

    /**
     * Creates a box containing the given value.
     *
     * @param value the initial value
     */
    public Box(T value) {
        this.value = value;
    }

    /**
     * Returns the value in this box.
     *
     * @return the stored value, or {@code null} if empty
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value in this box.
     *
     * @param value the value to store
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Returns {@code true} if this box contains no value.
     *
     * @return {@code true} if the stored value is {@code null}
     */
    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public String toString() {
        return "Box[" + value + "]";
    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>("Hello");
        System.out.println(stringBox);
        System.out.println("Empty? " + stringBox.isEmpty());

        Box<Integer> intBox = new Box<>();
        System.out.println(intBox);
        System.out.println("Empty? " + intBox.isEmpty());
        intBox.setValue(42);
        System.out.println(intBox);
    }
}
