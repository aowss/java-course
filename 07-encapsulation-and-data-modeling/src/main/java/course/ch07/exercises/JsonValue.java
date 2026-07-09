package course.ch07.exercises;

import java.util.List;

/**
 * Exercise 3 (Challenge): Sealed JSON value hierarchy with pretty printing.
 */
public sealed interface JsonValue permits JsonValue.JsonNull, JsonValue.JsonBool, JsonValue.JsonNumber,
        JsonValue.JsonString, JsonValue.JsonArray, JsonValue.JsonObject {

    /**
     * Pretty-prints this JSON value with the given indentation level.
     *
     * @param indent the current indentation level (0 = root)
     * @return the formatted JSON string
     */
    String prettyPrint(int indent);

    /**
     * Pretty-prints at the root level (indent 0).
     *
     * @return the formatted JSON string
     */
    default String prettyPrint() {
        return prettyPrint(0);
    }

    /**
     * JSON null value.
     */
    record JsonNull() implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            // TODO: implement — return "null"
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * JSON boolean value.
     *
     * @param value the boolean
     */
    record JsonBool(boolean value) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            // TODO: implement — return "true" or "false"
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * JSON number value.
     *
     * @param value the number
     */
    record JsonNumber(double value) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            // TODO: implement — format without unnecessary decimals
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * JSON string value.
     *
     * @param value the string content (without quotes)
     */
    record JsonString(String value) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            // TODO: implement — wrap in double quotes
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * JSON array value.
     *
     * @param elements the array elements
     */
    record JsonArray(List<JsonValue> elements) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            // TODO: implement — format as [elem1, elem2, ...] with indentation
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * JSON object entry.
     *
     * @param key   the property name
     * @param value the property value
     */
    record JsonEntry(String key, JsonValue value) {
    }

    /**
     * JSON object value.
     *
     * @param entries the object properties
     */
    record JsonObject(List<JsonEntry> entries) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            // TODO: implement — format as {"key": value, ...} with indentation
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * Returns the indentation string for the given level (2 spaces per level).
     *
     * @param indent the indentation level
     * @return the indentation whitespace
     */
    static String indentString(int indent) {
        return "  ".repeat(indent);
    }

    public static void main(String[] args) {
        JsonValue json = new JsonObject(List.of(
                new JsonEntry("name", new JsonString("Alice")),
                new JsonEntry("age", new JsonNumber(30)),
                new JsonEntry("active", new JsonBool(true))
        ));
        System.out.println(json.prettyPrint());
    }
}
