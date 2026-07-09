package course.ch14.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates various ways to create streams in Java.
 */
public class StreamCreation {

    /**
     * Creates a stream from a collection.
     *
     * @param items the source list
     * @param <T>   the element type
     * @return a stream of the list elements
     */
    public static <T> Stream<T> fromCollection(List<T> items) {
        return items.stream();
    }

    /**
     * Creates a stream from a varargs array.
     *
     * @param items the source array
     * @param <T>   the element type
     * @return a stream of the array elements
     */
    @SafeVarargs
    public static <T> Stream<T> fromArray(T... items) {
        return Arrays.stream(items);
    }

    /**
     * Creates an infinite stream of integers starting at {@code from}.
     *
     * @param from the starting value (inclusive)
     * @return an infinite stream of consecutive integers
     */
    public static Stream<Integer> infiniteFrom(int from) {
        return Stream.iterate(from, n -> n + 1);
    }

    /**
     * Creates a stream of integers in the range [start, end).
     *
     * @param start the start value (inclusive)
     * @param end   the end value (exclusive)
     * @return a stream of integers
     */
    public static IntStream range(int start, int end) {
        return IntStream.range(start, end);
    }

    /**
     * Creates a stream by splitting a string on whitespace.
     *
     * @param text the input text
     * @return a stream of words
     */
    public static Stream<String> wordsFrom(String text) {
        return Arrays.stream(text.trim().split("\\s+"));
    }

    /**
     * Creates a stream from a generator function, limited to {@code count} elements.
     *
     * @param seed   the initial value
     * @param next   the next-value function
     * @param count  the number of elements
     * @param <T>    the element type
     * @return a finite stream
     */
    public static <T> Stream<T> generate(T seed, java.util.function.UnaryOperator<T> next, int count) {
        return Stream.iterate(seed, next).limit(count);
    }

    public static void main(String[] args) {
        System.out.println("From list:  " + fromCollection(List.of("a", "b", "c")).toList());
        System.out.println("From array: " + fromArray(1, 2, 3).toList());
        System.out.println("Range:      " + range(1, 6).boxed().toList());
        System.out.println("Words:      " + wordsFrom("hello world java").toList());
        System.out.println("Generated:  " + generate(1, n -> n * 2, 5).toList());
    }
}
