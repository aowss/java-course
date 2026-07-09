package course.ch06.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Demonstrates default methods and private helper methods in interfaces.
 *
 * <p>The default {@link #sort(List)} method provides reusable sorting logic
 * while delegating comparison to the abstract {@link #compareTo(Object)} method.
 */
public interface Sortable<T extends Sortable<T>> extends Comparator<T> {

    /**
     * Compares this object with the given object for ordering.
     *
     * @param other the object to compare
     * @return a negative, zero, or positive integer
     */
    int compareTo(T other);

    @Override
    default int compare(T first, T second) {
        return comparePair(first, second);
    }

    /**
     * Sorts a mutable copy of the given list using this object's comparison logic.
     *
     * @param items the items to sort
     * @return a new sorted list
     */
    default List<T> sort(List<T> items) {
        List<T> copy = new ArrayList<>(items);
        copy.sort(this);
        return copy;
    }

    /**
     * Returns the smaller of two values according to this sort order.
     *
     * @param first  the first value
     * @param second the second value
     * @return the smaller value
     */
    default T min(T first, T second) {
        return compare(first, second) <= 0 ? first : second;
    }

    /**
     * Private helper that delegates to {@link #compareTo(Object)} on the first value.
     */
    private int comparePair(T first, T second) {
        if (first == second) {
            return 0;
        }
        return first.compareTo(second);
    }

    /**
     * A sortable integer wrapper.
     *
     * @param value the integer value
     */
    record SortableInt(int value) implements Sortable<SortableInt> {

        @Override
        public int compareTo(SortableInt other) {
            return Integer.compare(value, other.value);
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    /**
     * A sortable string wrapper that compares by length, then alphabetically.
     *
     * @param text the string value
     */
    record SortableString(String text) implements Sortable<SortableString> {

        @Override
        public int compareTo(SortableString other) {
            int byLength = Integer.compare(text.length(), other.text.length());
            if (byLength != 0) {
                return byLength;
            }
            return text.compareTo(other.text);
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static void main(String[] args) {
        Sortable<SortableInt> byValue = new SortableInt(0);
        var numbers = List.of(new SortableInt(42), new SortableInt(7), new SortableInt(19));
        System.out.println("Sorted ints: " + byValue.sort(numbers));

        Sortable<SortableString> byLength = new SortableString("");
        var words = List.of(new SortableString("banana"), new SortableString("fig"), new SortableString("apple"));
        System.out.println("Sorted strings: " + byLength.sort(words));
    }
}
