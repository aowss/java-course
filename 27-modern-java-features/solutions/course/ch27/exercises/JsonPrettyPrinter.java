package course.ch27.exercises;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class JsonPrettyPrinter {

    private JsonPrettyPrinter() {
    }

    public sealed interface JsonValue permits JsonNull, JsonBool, JsonNumber, JsonString, JsonArray, JsonObject {
    }

    public record JsonNull() implements JsonValue {
    }

    public record JsonBool(boolean value) implements JsonValue {
    }

    public record JsonNumber(double value) implements JsonValue {
    }

    public record JsonString(String value) implements JsonValue {
    }

    public record JsonArray(List<JsonValue> elements) implements JsonValue {
    }

    public record JsonObject(Map<String, JsonValue> fields) implements JsonValue {
    }

    public static String prettyPrint(JsonValue value) {
        return prettyPrint(value, 0);
    }

    private static String prettyPrint(JsonValue value, int indent) {
        String pad = "  ".repeat(indent);
        String childPad = "  ".repeat(indent + 1);

        return switch (value) {
            case JsonNull _ -> "null";
            case JsonBool b -> String.valueOf(b.value());
            case JsonNumber n -> stripTrailingZero(n.value());
            case JsonString s -> "\"" + s.value() + "\"";
            case JsonArray a -> formatArray(a.elements(), indent);
            case JsonObject o -> formatObject(o.fields(), indent, pad, childPad);
        };
    }

    private static String formatArray(List<JsonValue> elements, int indent) {
        if (elements.isEmpty()) {
            return "[]";
        }
        String childPad = "  ".repeat(indent + 1);
        String body = elements.stream()
                .map(element -> childPad + prettyPrint(element, indent + 1))
                .collect(Collectors.joining(",\n"));
        return "[\n" + body + "\n" + "  ".repeat(indent) + "]";
    }

    private static String formatObject(Map<String, JsonValue> fields, int indent, String pad, String childPad) {
        if (fields.isEmpty()) {
            return "{}";
        }
        String body = fields.entrySet().stream()
                .map(entry -> childPad + "\"" + entry.getKey() + "\": "
                        + prettyPrint(entry.getValue(), indent + 1))
                .collect(Collectors.joining(",\n"));
        return "{\n" + body + "\n" + pad + "}";
    }

    private static String stripTrailingZero(double value) {
        if (value == (long) value) {
            return (long) value + ".0";
        }
        return String.valueOf(value);
    }
}
