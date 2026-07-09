package course.ch14.examples;

import java.util.List;

/**
 * Demonstrates intermediate stream operations: filter, map, flatMap, distinct, sorted, limit.
 */
public class IntermediateOps {

    /**
     * Filters strings to those longer than the given minimum length.
     *
     * @param words     the input words
     * @param minLength the minimum length
     * @return the filtered words
     */
    public static List<String> filterByLength(List<String> words, int minLength) {
        return words.stream()
                .filter(w -> w.length() >= minLength)
                .toList();
    }

    /**
     * Converts strings to uppercase.
     *
     * @param words the input words
     * @return the uppercased words
     */
    public static List<String> toUpperCase(List<String> words) {
        return words.stream()
                .map(String::toUpperCase)
                .toList();
    }

    /**
     * Splits each string into characters and flattens into a single stream.
     *
     * @param words the input words
     * @return a flat list of characters as strings
     */
    public static List<String> flatMapToChars(List<String> words) {
        return words.stream()
                .flatMap(w -> w.chars().mapToObj(c -> String.valueOf((char) c)))
                .toList();
    }

    /**
     * Returns distinct words sorted alphabetically.
     *
     * @param words the input words (may contain duplicates)
     * @return sorted unique words
     */
    public static List<String> distinctSorted(List<String> words) {
        return words.stream()
                .distinct()
                .sorted()
                .toList();
    }

    /**
     * Returns the first {@code n} even numbers from the stream of integers.
     *
     * @param numbers the input numbers
     * @param n       how many even numbers to take
     * @return the first n even numbers
     */
    public static List<Integer> firstNEvens(List<Integer> numbers, int n) {
        return numbers.stream()
                .filter(n2 -> n2 % 2 == 0)
                .limit(n)
                .toList();
    }

    /**
     * Pipelines filter → map → sorted on a stream of integers.
     *
     * @param numbers the input numbers
     * @return positive numbers doubled and sorted
     */
    public static List<Integer> positiveDoubledSorted(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n > 0)
                .map(n -> n * 2)
                .sorted()
                .toList();
    }

    public static void main(String[] args) {
        var words = List.of("java", "go", "kotlin", "rust", "java");
        System.out.println("Filter:   " + filterByLength(words, 4));
        System.out.println("Upper:    " + toUpperCase(words));
        System.out.println("Distinct: " + distinctSorted(words));

        var numbers = List.of(-3, 2, 4, 6, 1, 8, 10, 3);
        System.out.println("Evens:    " + firstNEvens(numbers, 3));
        System.out.println("Pipeline: " + positiveDoubledSorted(numbers));
    }
}
