package course.ch13.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MethodReferenceDemoTest {

    @Test
    void parseWithReference() {
        assertEquals(42, MethodReferenceDemo.parseWithReference("42"));
        assertEquals(-7, MethodReferenceDemo.parseWithReference("-7"));
    }

    @Test
    void startsWithPrefix() {
        assertTrue(MethodReferenceDemo.startsWithPrefix("Hello", "He"));
        assertFalse(MethodReferenceDemo.startsWithPrefix("Hello", "Wo"));
    }

    @Test
    void toUpperAll() {
        var result = MethodReferenceDemo.toUpperAll(List.of("hello", "world"));
        assertEquals(List.of("HELLO", "WORLD"), result);
    }

    @Test
    void toUpperAllEmpty() {
        assertEquals(List.of(), MethodReferenceDemo.toUpperAll(List.of()));
    }

    @Test
    void createBuilder() {
        var sb = MethodReferenceDemo.createBuilder("hello");
        assertEquals("hello", sb.toString());
    }
}
