package course.ch14.exercises;

import java.util.Optional;

public class SafeParser {

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

    public static Optional<Double> parseDouble(String text) {
        if (text == null || text.isBlank()) {
            return Optional.empty();
        }
        try {
            return Optional.of(Double.parseDouble(text.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static Optional<Boolean> parseBoolean(String text) {
        if (text == null || text.isBlank()) {
            return Optional.empty();
        }
        String trimmed = text.trim().toLowerCase();
        if ("true".equals(trimmed)) {
            return Optional.of(true);
        }
        if ("false".equals(trimmed)) {
            return Optional.of(false);
        }
        return Optional.empty();
    }

    public static Optional<Integer> firstValidInt(String... texts) {
        for (String text : texts) {
            Optional<Integer> parsed = parseInt(text);
            if (parsed.isPresent()) {
                return parsed;
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        System.out.println("Int:   " + parseInt("42"));
        System.out.println("Bad:   " + parseInt("abc"));
        System.out.println("First: " + firstValidInt("x", "y", "7"));
    }
}
