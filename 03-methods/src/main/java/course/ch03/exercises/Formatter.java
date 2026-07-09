package course.ch03.exercises;

/**
 * Exercise 3 (Challenge): Overloaded formatting methods.
 *
 * <p>Each overload produces a different formatted string:
 * <ul>
 *   <li>{@code format("count", 42)} → {@code "count: 42"}</li>
 *   <li>{@code format("price", 9.99)} → {@code "price: 9.99"} (2 decimal places)</li>
 *   <li>{@code format("tags", "a", "b", "c")} → {@code "tags: [a, b, c]"}</li>
 * </ul>
 */
public class Formatter {

    /**
     * Formats a label with an integer value.
     *
     * @param label the label
     * @param value the integer value
     * @return formatted string in the form "label: value"
     */
    public static String format(String label, int value) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Formats a label with a double value, rounded to 2 decimal places.
     *
     * @param label the label
     * @param value the double value
     * @return formatted string in the form "label: X.XX"
     */
    public static String format(String label, double value) {
        // TODO: implement — hint: use String.format("%.2f", value)
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Formats a label with a list of string values.
     *
     * @param label  the label
     * @param values the values to list
     * @return formatted string in the form "label: [v1, v2, v3]"
     */
    public static String format(String label, String... values) {
        // TODO: implement — handle empty varargs as "label: []"
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
