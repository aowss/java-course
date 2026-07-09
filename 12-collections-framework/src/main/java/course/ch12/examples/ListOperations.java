package course.ch12.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Demonstrates common {@link List} operations with {@link ArrayList}.
 *
 * <p>Covers adding, removing, sorting, sublists, and the sequenced collection
 * methods introduced in Java 21 ({@code getFirst()}, {@code getLast()},
 * {@code reversed()}).
 */
public class ListOperations {

    /**
     * Creates an {@link ArrayList} from the given elements, sorts it, and returns it.
     *
     * @param elements the elements to add
     * @param <T>      the element type (must be {@link Comparable})
     * @return a sorted list
     */
    @SafeVarargs
    public static <T extends Comparable<T>> List<T> sortedArrayList(T... elements) {
        List<T> list = new ArrayList<>(List.of(elements));
        Collections.sort(list);
        return list;
    }

    /**
     * Returns a reversed copy of the given list.
     *
     * @param list the input list
     * @param <T>  the element type
     * @return a new list with elements in reverse order
     */
    public static <T> List<T> reversed(List<T> list) {
        List<T> copy = new ArrayList<>(list);
        Collections.reverse(copy);
        return copy;
    }

    /**
     * Returns the first and last elements of a list using Java 21+
     * sequenced collection methods.
     *
     * @param list the input list (must not be empty)
     * @param <T>  the element type
     * @return a two-element list: [first, last]
     */
    public static <T> List<T> firstAndLast(List<T> list) {
        return List.of(list.getFirst(), list.getLast());
    }

    public static void main(String[] args) {
        System.out.println("Sorted ArrayList: " + sortedArrayList(3, 1, 4, 1, 5, 9));

        var names = List.of("Alice", "Bob", "Charlie");
        System.out.println("Original: " + names);
        System.out.println("Reversed: " + reversed(names));

        var numbers = List.of(10, 20, 30);
        System.out.println("First and last: " + firstAndLast(numbers));
    }
}
