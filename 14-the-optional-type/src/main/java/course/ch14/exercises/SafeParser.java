package course.ch14.exercises;

import java.util.Optional;

/**
 * Exercise 1 (Guided): Safe parsing with Optional.
 */
public class SafeParser {

    /**
     * Parses a string to an integer, returning empty on null, blank, or invalid input.
     *
     * @param text the text to parse
     * @return the parsed integer, or empty
     */
    public static Optional<Integer> parseInt(String text) {
        // TODO: implement — handle null, blank, and NumberFormatException
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Parses a string to a double, returning empty on invalid input.
     *
     * @param text the text to parse
     * @return the parsed double, or empty
     */
    public static Optional<Double> parseDouble(String text) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Parses a string to a boolean ("true"/"false", case-insensitive).
     *
     * @param text the text to parse
     * @return the parsed boolean, or empty for invalid input
     */
    public static Optional<Boolean> parseBoolean(String text) {
        // TODO: implement — only accept "true" or "false"
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the first successfully parsed integer from the given strings.
     *
     * @param texts the candidate strings
     * @return the first valid integer, or empty
     */
    public static Optional<Integer> firstValidInt(String... texts) {
        // TODO: implement — try each string in order
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        System.out.println("Int:   " + parseInt("42"));
        System.out.println("Bad:   " + parseInt("abc"));
        System.out.println("First: " + firstValidInt("x", "y", "7"));
    }
}
