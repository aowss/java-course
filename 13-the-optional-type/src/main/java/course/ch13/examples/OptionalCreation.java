package course.ch13.examples;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Demonstrates creating and unwrapping {@link Optional} values.
 */
public class OptionalCreation {

    /**
     * Wraps a nullable value in an {@link Optional}.
     *
     * @param value the value (may be null)
     * @return an Optional containing the value, or empty if null
     */
    public static Optional<String> ofNullable(String value) {
        return Optional.ofNullable(value);
    }

    /**
     * Returns an empty Optional.
     *
     * @return an empty Optional
     */
    public static Optional<String> empty() {
        return Optional.empty();
    }

    /**
     * Safely unwraps an Optional, returning the default if empty.
     *
     * @param optional     the optional value
     * @param defaultValue the fallback
     * @return the value or the default
     */
    public static String orElse(Optional<String> optional, String defaultValue) {
        return optional.orElse(defaultValue);
    }

    /**
     * Unwraps an Optional, throwing if empty.
     *
     * @param optional the optional value
     * @return the contained value
     * @throws NoSuchElementException if empty
     */
    public static String orElseThrow(Optional<String> optional) {
        return optional.orElseThrow();
    }

    /**
     * Returns the value only if present, otherwise {@code null}.
     *
     * @param optional the optional value
     * @return the value or null
     */
    public static String orElseNull(Optional<String> optional) {
        return optional.orElse(null);
    }

    /**
     * Checks whether the Optional contains a non-empty string.
     *
     * @param optional the optional value
     * @return {@code true} if a non-blank value is present
     */
    public static boolean hasContent(Optional<String> optional) {
        return optional.filter(s -> !s.isBlank()).isPresent();
    }

    /**
     * Finds the first non-null, non-blank string from the arguments.
     *
     * @param values the candidate values
     * @return the first present value, or empty
     */
    public static Optional<String> firstPresent(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Optional<String> name = ofNullable("Alice");
        System.out.println("Present:  " + name.isPresent());
        System.out.println("Value:    " + orElse(name, "Guest"));
        System.out.println("Empty:    " + orElse(empty(), "Guest"));
        System.out.println("First:    " + firstPresent(null, "", "Bob", "Alice"));
    }
}
