package course.ch08.exercises;

/**
 * Exercise 1 (Guided): Word frequency analysis.
 *
 * <p>Implement the methods below to analyze word frequency in a text string.
 * Words are separated by whitespace and should be compared case-insensitively.
 */
public class WordCounter {

    /**
     * Counts the total number of words in the text.
     *
     * <p>Words are separated by whitespace. An empty or {@code null} string
     * has zero words.
     *
     * @param text the input text
     * @return the number of words
     */
    public static int countWords(String text) {
        // TODO: implement — split on whitespace and count non-empty tokens
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Counts the number of unique words in the text (case-insensitive).
     *
     * @param text the input text
     * @return the number of distinct words
     */
    public static int countUniqueWords(String text) {
        // TODO: implement — hint: use a Set with lowercase words
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the most frequently occurring word (case-insensitive).
     *
     * <p>If there is a tie, return any one of the most frequent words.
     * Returns {@code null} if the text is {@code null} or empty.
     *
     * @param text the input text
     * @return the most frequent word in lowercase, or {@code null}
     */
    public static String mostFrequentWord(String text) {
        // TODO: implement — hint: build a frequency map and find the max entry
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        String sample = "the cat sat on the mat the cat";
        System.out.println("Total words:   " + countWords(sample));
        System.out.println("Unique words:  " + countUniqueWords(sample));
        System.out.println("Most frequent: " + mostFrequentWord(sample));
    }
}
