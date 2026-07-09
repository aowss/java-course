package course.ch27.exercises;

import java.util.List;
import java.util.Map;

/**
 * Exercise 3 (Challenge): Pretty-print JSON values modeled as sealed types.
 *
 * <p>Implement {@link #prettyPrint(JsonValue)} to produce indented JSON with 2-space
 * indentation per nesting level.
 *
 * <pre>{@code
 * JsonValue value = new JsonObject(Map.of("name", new JsonString("Ada")));
 * prettyPrint(value);
 * // {
 * //   "name": "Ada"
 * // }
 * }</pre>
 */
public final class JsonPrettyPrinter {

    private JsonPrettyPrinter() {
    }

    /**
     * Sealed root type for JSON values.
     */
    public sealed interface JsonValue permits JsonNull, JsonBool, JsonNumber, JsonString, JsonArray, JsonObject {
    }

    /** JSON null. */
    public record JsonNull() implements JsonValue {
    }

    /**
     * JSON boolean.
     *
     * @param value the boolean value
     */
    public record JsonBool(boolean value) implements JsonValue {
    }

    /**
     * JSON number.
     *
     * @param value the numeric value
     */
    public record JsonNumber(double value) implements JsonValue {
    }

    /**
     * JSON string.
     *
     * @param value the string content (no escaping required for this exercise)
     */
    public record JsonString(String value) implements JsonValue {
    }

    /**
     * JSON array.
     *
     * @param elements the ordered elements
     */
    public record JsonArray(List<JsonValue> elements) implements JsonValue {
    }

    /**
     * JSON object.
     *
     * @param fields the field map (insertion order preserved)
     */
    public record JsonObject(Map<String, JsonValue> fields) implements JsonValue {
    }

    /**
     * Pretty-prints a JSON value with 2-space indentation.
     *
     * @param value the JSON value to format
     * @return the formatted JSON string (no trailing newline)
     */
    public static String prettyPrint(JsonValue value) {
        // TODO: use pattern matching switch and recursive formatting
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
