package course.ch14.examples;

import java.util.List;
import java.util.Optional;

/**
 * Demonstrates common {@link Optional} anti-patterns and their correct alternatives.
 */
public class OptionalAntiPatterns {

    /**
     * Anti-pattern: using {@code Optional.get()} without checking.
     * Correct: use {@code orElse}, {@code orElseThrow}, or {@code ifPresent}.
     *
     * @param optional the optional value
     * @return the value safely
     */
    public static String safeUnwrap(Optional<String> optional) {
        return optional.orElse("default");
    }

    /**
     * Anti-pattern: returning {@code null} from methods that should return Optional.
     * Correct: always return {@link Optional#empty()} instead of null.
     *
     * @param value the input value
     * @return an Optional, never null
     */
    public static Optional<String> neverReturnNull(String value) {
        return Optional.ofNullable(value);
    }

    /**
     * Anti-pattern: using Optional as a field type.
     * Correct: use {@code null} for absent fields; Optional is for return types.
     *
     * @param name the user name (may be null)
     * @return a display name
     */
    public static String displayName(String name) {
        return name != null ? name : "Anonymous";
    }

    /**
     * Anti-pattern: wrapping collections in Optional.
     * Correct: return an empty collection instead of {@code Optional.empty()}.
     *
     * @param items the optional list
     * @return the list or an empty list
     */
    public static List<String> itemsOrEmpty(Optional<List<String>> items) {
        return items.orElse(List.of());
    }

    /**
     * Anti-pattern: using {@code isPresent()} + {@code get()} instead of functional methods.
     * Correct: use {@code map}, {@code filter}, {@code orElse}.
     *
     * @param optional the optional email
     * @return the domain part, or empty string
     */
    public static String extractDomain(Optional<String> optional) {
        return optional
                .filter(email -> email.contains("@"))
                .map(email -> email.substring(email.indexOf('@') + 1))
                .orElse("");
    }

    /**
     * Anti-pattern: creating {@code Optional.of()} with a potentially null value.
     * Correct: use {@code Optional.ofNullable()}.
     *
     * @param value the value (may be null)
     * @return a safe Optional
     */
    public static Optional<String> safeOf(String value) {
        return Optional.ofNullable(value);
    }

    public static void main(String[] args) {
        System.out.println("Safe:     " + safeUnwrap(Optional.empty()));
        System.out.println("Domain:   " + extractDomain(Optional.of("alice@example.com")));
        System.out.println("Display:  " + displayName(null));
    }
}
