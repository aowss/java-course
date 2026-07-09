package course.ch11.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates generic methods and wildcard usage.
 *
 * <p>Includes bounded type parameters ({@code T extends Comparable<T>}),
 * unbounded wildcards ({@code ?}), upper-bounded wildcards
 * ({@code ? extends Number}), and lower-bounded wildcards
 * ({@code ? super Integer}).
 */
public class GenericUtils {

    /**
     * Returns the greater of two comparable values.
     *
     * @param a   the first value
     * @param b   the second value
     * @param <T> a type that implements {@link Comparable}
     * @return the greater value, or {@code a} if they are equal
     */
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    /**
     * Swaps two elements in an array.
     *
     * @param array the array
     * @param i     the index of the first element
     * @param j     the index of the second element
     * @param <T>   the array element type
     */
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Prints all elements in a list using an unbounded wildcard.
     *
     * @param list the list to print
     */
    public static void printAll(List<?> list) {
        for (Object item : list) {
            System.out.println("  " + item);
        }
    }

    /**
     * Sums all numbers in a list using an upper-bounded wildcard.
     *
     * @param numbers a list of any {@link Number} subtype
     * @return the sum as a {@code double}
     */
    public static double sumOfNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

    /**
     * Adds integers to a list using a lower-bounded wildcard.
     *
     * @param list the target list (accepts {@code Integer} or any supertype)
     * @param from the start of the range (inclusive)
     * @param to   the end of the range (exclusive)
     */
    public static void addIntegers(List<? super Integer> list, int from, int to) {
        for (int i = from; i < to; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) {
        System.out.println("max(3, 7) = " + max(3, 7));
        System.out.println("max(\"apple\", \"banana\") = " + max("apple", "banana"));

        String[] names = {"Alice", "Bob", "Charlie"};
        swap(names, 0, 2);
        System.out.println("After swap: " + java.util.Arrays.toString(names));

        System.out.println("\nprintAll:");
        printAll(List.of(1, "two", 3.0));

        System.out.println("Sum of [1, 2.5, 3]: " + sumOfNumbers(List.of(1, 2.5, 3)));
    }
}
