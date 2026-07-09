package course.ch27.exercises;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonPrettyPrinterTest {

    @Test
    void prettyPrintNull() {
        assertEquals("null", JsonPrettyPrinter.prettyPrint(new JsonPrettyPrinter.JsonNull()));
    }

    @Test
    void prettyPrintBool() {
        assertEquals("true", JsonPrettyPrinter.prettyPrint(new JsonPrettyPrinter.JsonBool(true)));
    }

    @Test
    void prettyPrintNumber() {
        assertEquals("42.0", JsonPrettyPrinter.prettyPrint(new JsonPrettyPrinter.JsonNumber(42)));
    }

    @Test
    void prettyPrintString() {
        assertEquals("\"Ada\"", JsonPrettyPrinter.prettyPrint(new JsonPrettyPrinter.JsonString("Ada")));
    }

    @Test
    void prettyPrintArray() {
        JsonPrettyPrinter.JsonValue value = new JsonPrettyPrinter.JsonArray(List.of(
                new JsonPrettyPrinter.JsonNumber(1),
                new JsonPrettyPrinter.JsonString("x")
        ));
        assertEquals("""
                [
                  1.0,
                  "x"
                ]""", JsonPrettyPrinter.prettyPrint(value));
    }

    @Test
    void prettyPrintObject() {
        Map<String, JsonPrettyPrinter.JsonValue> fields = new LinkedHashMap<>();
        fields.put("name", new JsonPrettyPrinter.JsonString("Ada"));
        fields.put("active", new JsonPrettyPrinter.JsonBool(true));
        JsonPrettyPrinter.JsonValue value = new JsonPrettyPrinter.JsonObject(fields);
        assertEquals("""
                {
                  "name": "Ada",
                  "active": true
                }""", JsonPrettyPrinter.prettyPrint(value));
    }

    @Test
    void prettyPrintNested() {
        Map<String, JsonPrettyPrinter.JsonValue> inner = new LinkedHashMap<>();
        inner.put("x", new JsonPrettyPrinter.JsonNumber(1));
        JsonPrettyPrinter.JsonValue value = new JsonPrettyPrinter.JsonObject(
                Map.of("data", new JsonPrettyPrinter.JsonObject(inner))
        );
        assertEquals("""
                {
                  "data": {
                    "x": 1.0
                  }
                }""", JsonPrettyPrinter.prettyPrint(value));
    }
}
