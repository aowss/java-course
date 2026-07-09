package course.ch10.examples;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Demonstrates {@link Set} operations with {@link HashSet} and {@link TreeSet}.
 *
 * <p>Covers uniqueness, iteration order, and set-theoretic operations
 * (union, intersection, difference).
 */
public class SetOperations {

    /**
     * Returns the union of two sets.
     *
     * @param a   the first set
     * @param b   the second set
     * @param <T> the element type
     * @return a new set containing all elements from both sets
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    /**
     * Returns the intersection of two sets.
     *
     * @param a   the first set
     * @param b   the second set
     * @param <T> the element type
     * @return a new set containing only elements present in both sets
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    /**
     * Returns the difference (elements in {@code a} but not in {@code b}).
     *
     * @param a   the first set
     * @param b   the second set
     * @param <T> the element type
     * @return a new set containing elements only in {@code a}
     */
    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }

    public static void main(String[] args) {
        Set<String> a = Set.of("apple", "banana", "cherry");
        Set<String> b = Set.of("banana", "date", "cherry");

        System.out.println("Set A:         " + new TreeSet<>(a));
        System.out.println("Set B:         " + new TreeSet<>(b));
        System.out.println("Union:         " + new TreeSet<>(union(a, b)));
        System.out.println("Intersection:  " + new TreeSet<>(intersection(a, b)));
        System.out.println("Difference:    " + new TreeSet<>(difference(a, b)));
    }
}
