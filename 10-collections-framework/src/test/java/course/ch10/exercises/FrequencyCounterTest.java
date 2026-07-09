package course.ch10.exercises;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrequencyCounterTest {

    // --- countFrequencies ---

    @Test
    void countFrequenciesSimpleText() {
        var freq = FrequencyCounter.countFrequencies("the cat sat on the mat");
        assertEquals(2, freq.get("the"));
        assertEquals(1, freq.get("cat"));
        assertEquals(1, freq.get("sat"));
        assertEquals(1, freq.get("on"));
        assertEquals(1, freq.get("mat"));
    }

    @Test
    void countFrequenciesIsCaseInsensitive() {
        var freq = FrequencyCounter.countFrequencies("Java java JAVA");
        assertEquals(3, freq.get("java"));
    }

    @Test
    void countFrequenciesReturnsEmptyMapForNull() {
        assertTrue(FrequencyCounter.countFrequencies(null).isEmpty());
    }

    @Test
    void countFrequenciesReturnsEmptyMapForBlank() {
        assertTrue(FrequencyCounter.countFrequencies("   ").isEmpty());
    }

    // --- sortedByFrequency ---

    @Test
    void sortedByFrequencyHighestFirst() {
        var sorted = FrequencyCounter.sortedByFrequency("the cat sat on the mat the cat");
        assertEquals("the", sorted.get(0).getKey());
        assertEquals(3, sorted.get(0).getValue());
        assertEquals("cat", sorted.get(1).getKey());
        assertEquals(2, sorted.get(1).getValue());
    }

    @Test
    void sortedByFrequencyReturnsEmptyForNull() {
        assertTrue(FrequencyCounter.sortedByFrequency(null).isEmpty());
    }
}
