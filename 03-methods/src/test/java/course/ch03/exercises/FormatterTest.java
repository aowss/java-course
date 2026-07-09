package course.ch03.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FormatterTest {

    @Test
    void formatInt() {
        assertEquals("count: 42", Formatter.format("count", 42));
    }

    @Test
    void formatIntZero() {
        assertEquals("value: 0", Formatter.format("value", 0));
    }

    @Test
    void formatIntNegative() {
        assertEquals("temp: -5", Formatter.format("temp", -5));
    }

    @Test
    void formatDouble() {
        assertEquals("price: 9.99", Formatter.format("price", 9.99));
    }

    @Test
    void formatDoubleRounding() {
        assertEquals("pi: 3.14", Formatter.format("pi", 3.14159));
    }

    @Test
    void formatDoubleWholeNumber() {
        assertEquals("rate: 5.00", Formatter.format("rate", 5.0));
    }

    @Test
    void formatVarargsSingle() {
        assertEquals("tag: [java]", Formatter.format("tag", "java"));
    }

    @Test
    void formatVarargsMultiple() {
        assertEquals("tags: [a, b, c]", Formatter.format("tags", "a", "b", "c"));
    }

    @Test
    void formatVarargsEmpty() {
        assertEquals("tags: []", Formatter.format("tags"));
    }
}
