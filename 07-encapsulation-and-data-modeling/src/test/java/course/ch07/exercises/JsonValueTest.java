package course.ch07.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonValueTest {

    @Test
    void jsonNullPrettyPrint() {
        assertEquals("null", new JsonValue.JsonNull().prettyPrint());
    }

    @Test
    void jsonBoolPrettyPrint() {
        assertEquals("true", new JsonValue.JsonBool(true).prettyPrint());
        assertEquals("false", new JsonValue.JsonBool(false).prettyPrint());
    }

    @Test
    void jsonNumberPrettyPrint() {
        assertEquals("42", new JsonValue.JsonNumber(42).prettyPrint());
        assertEquals("3.14", new JsonValue.JsonNumber(3.14).prettyPrint());
    }

    @Test
    void jsonStringPrettyPrint() {
        assertEquals("\"hello\"", new JsonValue.JsonString("hello").prettyPrint());
    }

    @Test
    void jsonArrayPrettyPrint() {
        JsonValue array = new JsonValue.JsonArray(List.of(
                new JsonValue.JsonNumber(1),
                new JsonValue.JsonString("two")
        ));
        String result = array.prettyPrint();
        assertTrue(result.startsWith("["));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("\"two\""));
        assertTrue(result.endsWith("]"));
    }

    @Test
    void jsonObjectPrettyPrint() {
        JsonValue obj = new JsonValue.JsonObject(List.of(
                new JsonValue.JsonEntry("name", new JsonValue.JsonString("Alice")),
                new JsonValue.JsonEntry("age", new JsonValue.JsonNumber(30))
        ));
        String result = obj.prettyPrint();
        assertTrue(result.startsWith("{"));
        assertTrue(result.contains("\"name\""));
        assertTrue(result.contains("\"Alice\""));
        assertTrue(result.contains("\"age\""));
        assertTrue(result.contains("30"));
        assertTrue(result.endsWith("}"));
    }

    @Test
    void indentString() {
        assertEquals("", JsonValue.indentString(0));
        assertEquals("  ", JsonValue.indentString(1));
        assertEquals("    ", JsonValue.indentString(2));
    }
}
