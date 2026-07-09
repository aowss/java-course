package course.ch12.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapOperationsTest {

    @Test
    void charFrequencyCountsCorrectly() {
        var freq = MapOperations.charFrequency("hello");
        assertEquals(1, freq.get('h'));
        assertEquals(1, freq.get('e'));
        assertEquals(2, freq.get('l'));
        assertEquals(1, freq.get('o'));
    }

    @Test
    void charFrequencyEmptyString() {
        var freq = MapOperations.charFrequency("");
        assertTrue(freq.isEmpty());
    }

    @Test
    void invertSwapsKeysAndValues() {
        var original = Map.of("a", 1, "b", 2);
        var inverted = MapOperations.invert(original);
        assertEquals("a", inverted.get(1));
        assertEquals("b", inverted.get(2));
    }

    @Test
    void groupByFirstLetterGroupsCorrectly() {
        var words = List.of("apple", "avocado", "banana");
        var groups = MapOperations.groupByFirstLetter(words);
        assertEquals(List.of("apple", "avocado"), groups.get('a'));
        assertEquals(List.of("banana"), groups.get('b'));
    }

    @Test
    void groupByFirstLetterSkipsEmptyStrings() {
        var words = List.of("", "apple");
        var groups = MapOperations.groupByFirstLetter(words);
        assertEquals(1, groups.size());
        assertEquals(List.of("apple"), groups.get('a'));
    }
}
