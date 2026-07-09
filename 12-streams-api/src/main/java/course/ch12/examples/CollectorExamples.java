package course.ch12.examples;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Demonstrates {@link Collectors} for grouping, partitioning, and aggregation.
 */
public class CollectorExamples {

    /**
     * Groups words by their length.
     *
     * @param words the input words
     * @return a map from length to list of words
     */
    public static Map<Integer, List<String>> groupByLength(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(String::length));
    }

    /**
     * Partitions numbers into even and odd buckets.
     *
     * @param numbers the input numbers
     * @return a map with keys {@code true} (even) and {@code false} (odd)
     */
    public static Map<Boolean, List<Integer>> partitionByEven(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }

    /**
     * Counts word frequencies (case-insensitive).
     *
     * @param words the input words
     * @return a map from lowercase word to count
     */
    public static Map<String, Long> wordFrequencies(List<String> words) {
        return words.stream()
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    /**
     * Joins words into a comma-separated string.
     *
     * @param words the words to join
     * @return the joined string
     */
    public static String joinComma(List<String> words) {
        return words.stream()
                .collect(Collectors.joining(", "));
    }

    /**
     * Returns summary statistics for a list of integers.
     *
     * @param numbers the input numbers
     * @return summary statistics (count, sum, min, max, average)
     */
    public static java.util.IntSummaryStatistics stats(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();
    }

    /**
     * Collects words into an unmodifiable set.
     *
     * @param words the input words
     * @return an unmodifiable set of unique words
     */
    public static Set<String> toUnmodifiableSet(List<String> words) {
        return words.stream()
                .collect(Collectors.toUnmodifiableSet());
    }

    public static void main(String[] args) {
        var words = List.of("java", "go", "kotlin", "rust");
        System.out.println("By length:    " + groupByLength(words));
        System.out.println("Frequencies:  " + wordFrequencies(List.of("Java", "java", "Go", "java")));

        var numbers = List.of(1, 2, 3, 4, 5, 6);
        System.out.println("Partition:    " + partitionByEven(numbers));
        System.out.println("Stats:        " + stats(numbers));
    }
}
