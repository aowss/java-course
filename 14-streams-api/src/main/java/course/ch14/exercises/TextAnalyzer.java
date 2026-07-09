package course.ch14.exercises;

import java.util.List;
import java.util.Map;

/**
 * Exercise 2 (Practice): Text analysis with streams.
 */
public class TextAnalyzer {

    /**
     * Splits text into words (whitespace-separated, lowercased).
     *
     * @param text the input text
     * @return the list of words
     */
    public static List<String> words(String text) {
        // TODO: implement — split on whitespace, lowercase, filter empty
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Counts the total number of words.
     *
     * @param text the input text
     * @return the word count (0 for null or blank)
     */
    public static long wordCount(String text) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the average word length, or 0 for empty text.
     *
     * @param text the input text
     * @return the average word length
     */
    public static double averageWordLength(String text) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns word frequencies as a map (lowercase word → count).
     *
     * @param text the input text
     * @return the frequency map
     */
    public static Map<String, Long> wordFrequencies(String text) {
        // TODO: implement — use Collectors.groupingBy with counting
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the longest word (case-insensitive comparison by length).
     *
     * @param text the input text
     * @return the longest word in lowercase, or empty string if no words
     */
    public static String longestWord(String text) {
        // TODO: implement — use max comparator on length
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        String sample = "The quick brown fox jumps over the lazy dog";
        System.out.println("Words:      " + words(sample));
        System.out.println("Count:      " + wordCount(sample));
        System.out.println("Avg length: " + averageWordLength(sample));
        System.out.println("Frequencies:" + wordFrequencies(sample));
        System.out.println("Longest:    " + longestWord(sample));
    }
}
