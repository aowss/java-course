package course.ch12.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TerminalOpsTest {

    @Test
    void sum() {
        assertEquals(15, TerminalOps.sum(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    void product() {
        assertEquals(120, TerminalOps.product(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    void productOfEmptyIsOne() {
        assertEquals(1, TerminalOps.product(List.of()));
    }

    @Test
    void longestWord() {
        assertEquals("streams", TerminalOps.longestWord(List.of("java", "streams", "api")).orElse(""));
    }

    @Test
    void countStartingWith() {
        assertEquals(2, TerminalOps.countStartingWith(List.of("Java", "javascript", "python"), "java"));
    }

    @Test
    void firstEven() {
        assertEquals(4, TerminalOps.firstEven(List.of(1, 3, 4, 6)).orElse(-1));
    }

    @Test
    void join() {
        assertEquals("a, b, c", TerminalOps.join(List.of("a", "b", "c"), ", "));
    }

    @Test
    void anyNegative() {
        assertTrue(TerminalOps.anyNegative(List.of(1, -2, 3)));
        assertFalse(TerminalOps.anyNegative(List.of(1, 2, 3)));
    }
}
