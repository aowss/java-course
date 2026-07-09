package course.ch14.examples;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Demonstrates terminal stream operations: forEach, reduce, collect, count, min/max, findFirst.
 */
public class TerminalOps {

    /**
     * Sums all integers in the list.
     *
     * @param numbers the numbers to sum
     * @return the total
     */
    public static int sum(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    /**
     * Returns the product of all integers using {@code reduce}.
     *
     * @param numbers the numbers to multiply
     * @return the product (1 for empty list)
     */
    public static int product(List<Integer> numbers) {
        return numbers.stream()
                .reduce(1, (a, b) -> a * b);
    }

    /**
     * Returns the longest string in the list.
     *
     * @param words the input words
     * @return the longest word, or empty if the list is empty
     */
    public static Optional<String> longestWord(List<String> words) {
        return words.stream()
                .max(java.util.Comparator.comparingInt(String::length));
    }

    /**
     * Counts words that start with the given prefix (case-insensitive).
     *
     * @param words  the input words
     * @param prefix the prefix to match
     * @return the count
     */
    public static long countStartingWith(List<String> words, String prefix) {
        return words.stream()
                .filter(w -> w.toLowerCase().startsWith(prefix.toLowerCase()))
                .count();
    }

    /**
     * Returns the first even number in the list.
     *
     * @param numbers the input numbers
     * @return the first even number, or empty
     */
    public static OptionalInt firstEven(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .filter(n -> n % 2 == 0)
                .findFirst();
    }

    /**
     * Joins strings with the given delimiter.
     *
     * @param words     the words to join
     * @param delimiter the delimiter
     * @return the joined string
     */
    public static String join(List<String> words, String delimiter) {
        return words.stream()
                .collect(java.util.stream.Collectors.joining(delimiter));
    }

    /**
     * Returns whether any number in the list is negative.
     *
     * @param numbers the numbers to check
     * @return {@code true} if any number is negative
     */
    public static boolean anyNegative(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(n -> n < 0);
    }

    public static void main(String[] args) {
        var numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("Sum:     " + sum(numbers));
        System.out.println("Product: " + product(numbers));

        var words = List.of("java", "streams", "api");
        System.out.println("Longest: " + longestWord(words).orElse("none"));
        System.out.println("Joined:  " + join(words, ", "));
    }
}
