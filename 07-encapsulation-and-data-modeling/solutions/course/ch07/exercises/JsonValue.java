package course.ch07.exercises;

import java.util.List;
import java.util.stream.Collectors;

public sealed interface JsonValue permits JsonValue.JsonNull, JsonValue.JsonBool, JsonValue.JsonNumber,
        JsonValue.JsonString, JsonValue.JsonArray, JsonValue.JsonObject {

    String prettyPrint(int indent);

    default String prettyPrint() {
        return prettyPrint(0);
    }

    record JsonNull() implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            return "null";
        }
    }

    record JsonBool(boolean value) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            return Boolean.toString(value);
        }
    }

    record JsonNumber(double value) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            if (value == Math.rint(value)) {
                return Long.toString((long) value);
            }
            return Double.toString(value);
        }
    }

    record JsonString(String value) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            return "\"" + value + "\"";
        }
    }

    record JsonArray(List<JsonValue> elements) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            if (elements.isEmpty()) {
                return "[]";
            }
            String inner = elements.stream()
                    .map(e -> indentString(indent + 1) + e.prettyPrint(indent + 1))
                    .collect(Collectors.joining(",\n"));
            return "[\n" + inner + "\n" + indentString(indent) + "]";
        }
    }

    record JsonEntry(String key, JsonValue value) {
    }

    record JsonObject(List<JsonEntry> entries) implements JsonValue {
        @Override
        public String prettyPrint(int indent) {
            if (entries.isEmpty()) {
                return "{}";
            }
            String inner = entries.stream()
                    .map(e -> indentString(indent + 1) + "\"" + e.key() + "\": "
                            + e.value().prettyPrint(indent + 1))
                    .collect(Collectors.joining(",\n"));
            return "{\n" + inner + "\n" + indentString(indent) + "}";
        }
    }

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
