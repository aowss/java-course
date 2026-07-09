package course.ch11.exercises;

import java.util.List;
import java.util.Map;

/**
 * Exercise 1 (Guided): Word frequency counter.
 *
 * <p>Implement the methods below to count word frequencies in a text string
 * using a {@link java.util.HashMap}. Words are separated by whitespace and
 * compared case-insensitively.
 */
public class FrequencyCounter {

    /**
     * Counts the frequency of each word in the text (case-insensitive).
     *
     * <p>Returns an empty map if the text is {@code null} or blank.
     *
     * @param text the input text
     * @return a map of lowercase word to its frequency count
     */
    public static Map<String, Integer> countFrequencies(String text) {
        // TODO: implement — split on whitespace, build a HashMap of word counts
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns word-frequency entries sorted by frequency in descending order.
     *
     * <p>If two words have the same frequency, their relative order is unspecified.
     *
     * @param text the input text
     * @return a list of entries sorted by frequency (highest first)
     */
    public static List<Map.Entry<String, Integer>> sortedByFrequency(String text) {
        // TODO: implement — hint: get entries from countFrequencies, sort by value descending
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        String sample = "the cat sat on the mat the cat";
        System.out.println("Frequencies: " + countFrequencies(sample));
        System.out.println("Sorted:      " + sortedByFrequency(sample));
    }
}
