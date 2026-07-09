package course.ch09.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {

    @Test
    void constructorSetsValues() {
        var pair = new Pair<>("key", 42);
        assertEquals("key", pair.getFirst());
        assertEquals(42, pair.getSecond());
    }

    @Test
    void ofFactoryCreatesCorrectPair() {
        var pair = Pair.of("a", "b");
        assertEquals("a", pair.getFirst());
        assertEquals("b", pair.getSecond());
    }

    @Test
    void toStringFormatsCorrectly() {
        var pair = Pair.of("x", 1);
        assertEquals("(x, 1)", pair.toString());
    }

    @Test
    void pairWithDifferentTypes() {
        var pair = Pair.of(3.14, true);
        assertEquals(3.14, pair.getFirst());
        assertEquals(true, pair.getSecond());
    }
}
