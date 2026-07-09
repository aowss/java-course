package course.ch13.examples;

import java.util.Optional;
import java.util.function.Function;

/**
 * Demonstrates Optional chaining with {@code map}, {@code flatMap}, and {@code filter}.
 */
public class OptionalChaining {

    /**
     * Parses a string to an integer, returning empty on failure.
     *
     * @param text the text to parse
     * @return an Optional containing the parsed integer
     */
    public static Optional<Integer> parseInt(String text) {
        if (text == null || text.isBlank()) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.parseInt(text.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * Doubles the value inside the Optional.
     *
     * @param optional the optional integer
     * @return the doubled value, or empty
     */
    public static Optional<Integer> doubleValue(Optional<Integer> optional) {
        return optional.map(n -> n * 2);
    }

    /**
     * Converts an Optional integer to its string representation.
     *
     * @param optional the optional integer
     * @return the string form, or empty
     */
    public static Optional<String> toString(Optional<Integer> optional) {
        return optional.map(String::valueOf);
    }

    /**
     * Chains parse → filter (positive) → double using flatMap and map.
     *
     * @param text the text to parse
     * @return doubled positive integer, or empty
     */
    public static Optional<Integer> parsePositiveAndDouble(String text) {
        return parseInt(text)
                .filter(n -> n > 0)
                .map(n -> n * 2);
    }

    /**
     * Applies a transformation that itself returns an Optional (flatMap).
     *
     * @param optional the source optional
     * @param mapper   the flat-mapping function
     * @param <T>      the source type
     * @param <R>      the result type
     * @return the flattened result
     */
    public static <T, R> Optional<R> chain(Optional<T> optional, Function<T, Optional<R>> mapper) {
        return optional.flatMap(mapper);
    }

    /**
     * Returns a formatted label if the value is present.
     *
     * @param optional the optional name
     * @return formatted greeting or a default message
     */
    public static String greet(Optional<String> optional) {
        return optional
                .filter(name -> !name.isBlank())
                .map(name -> "Hello, " + name + "!")
                .orElse("Hello, Guest!");
    }

    public static void main(String[] args) {
        System.out.println("Parse:    " + parseInt("42"));
        System.out.println("Double:   " + doubleValue(parseInt("21")));
        System.out.println("Chain:    " + parsePositiveAndDouble("7"));
        System.out.println("Greet:    " + greet(Optional.of("Alice")));
        System.out.println("Greet ∅:  " + greet(Optional.empty()));
    }
}
