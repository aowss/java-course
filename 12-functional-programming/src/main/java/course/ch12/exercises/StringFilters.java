package course.ch12.exercises;

import java.util.List;
import java.util.function.Predicate;

/**
 * Exercise 1 (Guided): Use {@link Predicate} to filter lists of strings.
 *
 * <p>Implement each factory method to return a {@link Predicate} that tests
 * a single condition, and implement {@link #filter(List, Predicate)} to apply
 * a predicate to a list.
 */
public class StringFilters {

    /**
     * Returns a predicate that tests whether a string is non-null and non-empty.
     *
     * @return a predicate that accepts non-null, non-empty strings
     */
    public static Predicate<String> nonEmpty() {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a predicate that tests whether a string starts with the given prefix.
     *
     * @param prefix the prefix to check for
     * @return a predicate that accepts strings starting with {@code prefix}
     */
    public static Predicate<String> startsWith(String prefix) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns a predicate that tests whether a string is longer than {@code n} characters.
     *
     * @param n the minimum length (exclusive)
     * @return a predicate that accepts strings with length greater than {@code n}
     */
    public static Predicate<String> longerThan(int n) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Filters the list, keeping only elements that satisfy the predicate.
     *
     * @param items     the list to filter
     * @param predicate the condition to test
     * @return a new list containing only matching elements
     */
    public static List<String> filter(List<String> items, Predicate<String> predicate) {
        // TODO: implement using streams
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
