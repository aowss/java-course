package course.ch03.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VarargsDemoTest {

    @Test
    void sumNoArgs() {
        assertEquals(0, VarargsDemo.sum());
    }

    @Test
    void sumSingleArg() {
        assertEquals(5, VarargsDemo.sum(5));
    }

    @Test
    void sumMultipleArgs() {
        assertEquals(6, VarargsDemo.sum(1, 2, 3));
    }

    @Test
    void joinWithComma() {
        assertEquals("a, b, c", VarargsDemo.joinWithSeparator(", ", "a", "b", "c"));
    }

    @Test
    void joinSingleElement() {
        assertEquals("only", VarargsDemo.joinWithSeparator(", ", "only"));
    }

    @Test
    void joinEmpty() {
        assertEquals("", VarargsDemo.joinWithSeparator(", "));
    }
}
