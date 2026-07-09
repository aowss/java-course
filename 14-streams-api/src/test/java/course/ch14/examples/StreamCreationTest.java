package course.ch14.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamCreationTest {

    @Test
    void fromCollection() {
        assertEquals(List.of("a", "b"), StreamCreation.fromCollection(List.of("a", "b")).toList());
    }

    @Test
    void fromArray() {
        assertEquals(List.of(1, 2, 3), StreamCreation.fromArray(1, 2, 3).toList());
    }

    @Test
    void range() {
        assertEquals(List.of(1, 2, 3, 4), StreamCreation.range(1, 5).boxed().toList());
    }

    @Test
    void wordsFrom() {
        assertEquals(List.of("hello", "world"), StreamCreation.wordsFrom("  hello   world ").toList());
    }

    @Test
    void generate() {
        assertEquals(List.of(1, 2, 4, 8, 16), StreamCreation.generate(1, n -> n * 2, 5).toList());
    }

    @Test
    void infiniteFromFirstFive() {
        assertEquals(List.of(10, 11, 12), StreamCreation.infiniteFrom(10).limit(3).toList());
    }
}
