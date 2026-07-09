package course.ch08.examples;

/**
 * Demonstrates the {@code throws} clause for checked and unchecked exceptions.
 *
 * <p>The compiler requires checked exceptions to be declared or caught. Unchecked
 * exceptions ({@link RuntimeException} subclasses) do <em>not</em> require a
 * {@code throws} declaration — but you may still declare them to document the
 * contract for callers (Effective Java, Item 74).
 */
public class ThrowsClauseDemo {

    /**
     * Parses a positive integer from text.
     *
     * <p>Declares both a checked exception ({@link ParseException}) and an
     * unchecked one ({@link IllegalArgumentException}). The unchecked declaration
     * is optional but documents that non-positive values are rejected.
     *
     * @param text the string to parse
     * @return the parsed positive integer
     * @throws ParseException           if {@code text} is not a valid integer
     * @throws IllegalArgumentException if the parsed value is not positive
     */
    public static int parsePositive(String text) throws ParseException, IllegalArgumentException {
        int value;
        try {
            value = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new ParseException("Not an integer: " + text, e);
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Value must be positive, was: " + value);
        }
        return value;
    }

    /**
     * Opens a resource identified by name.
     *
     * <p>Only the checked exception appears in {@code throws} because the compiler
     * enforces it. {@link IllegalArgumentException} is thrown but not declared —
     * callers are not forced to catch it.
     *
     * @param name the resource name
     * @return a confirmation message
     * @throws ResourceNotFoundException if no resource matches {@code name}
     */
    public static String openResource(String name) throws ResourceNotFoundException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (!name.startsWith("res://")) {
            throw new ResourceNotFoundException(name);
        }
        return "Opened " + name;
    }

    /**
     * A checked exception for parse failures.
     */
    public static class ParseException extends Exception {
        public ParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * A checked exception for missing resources.
     */
    public static class ResourceNotFoundException extends Exception {
        public ResourceNotFoundException(String name) {
            super("Resource not found: " + name);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(parsePositive("42"));
            System.out.println(openResource("res://config"));
        } catch (ParseException | ResourceNotFoundException e) {
            System.out.println("Checked: " + e.getMessage());
        }
    }
}
