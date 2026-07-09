package course.ch09.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringImmutabilityTest {

    @Test
    void demonstrateImmutabilityPreservesOriginal() {
        var result = StringImmutability.demonstrateImmutability("hello");
        assertEquals("hello", result[0]);
        assertEquals("HELLO", result[1]);
    }

    @Test
    void internedLiteralsShareReference() {
        String a = "Java";
        String b = "Java";
        assertTrue(StringImmutability.areSameReference(a, b));
    }

    @Test
    void newStringDoesNotShareReference() {
        String a = "Java";
        String b = new String("Java");
        assertFalse(StringImmutability.areSameReference(a, b));
    }

    @Test
    void lengthReturnsCorrectValue() {
        assertEquals(5, StringImmutability.length("Hello"));
        assertEquals(0, StringImmutability.length(""));
    }

    @Test
    void charAtReturnsCorrectCharacter() {
        assertEquals('e', StringImmutability.charAt("Hello", 1));
    }

    @Test
    void containsSubstringFindsMatch() {
        assertTrue(StringImmutability.containsSubstring("Hello, World", "World"));
        assertFalse(StringImmutability.containsSubstring("Hello, World", "xyz"));
    }
}
