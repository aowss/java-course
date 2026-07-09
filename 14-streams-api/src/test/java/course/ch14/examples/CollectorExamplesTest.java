package course.ch14.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CollectorExamplesTest {

    @Test
    void groupByLength() {
        Map<Integer, List<String>> grouped = CollectorExamples.groupByLength(List.of("go", "java", "rust"));
        assertEquals(List.of("go"), grouped.get(2));
        assertEquals(List.of("java", "rust"), grouped.get(4));
    }

    @Test
    void partitionByEven() {
        Map<Boolean, List<Integer>> parts = CollectorExamples.partitionByEven(List.of(1, 2, 3, 4));
        assertEquals(List.of(2, 4), parts.get(true));
        assertEquals(List.of(1, 3), parts.get(false));
    }

    @Test
    void wordFrequencies() {
        Map<String, Long> freq = CollectorExamples.wordFrequencies(List.of("Java", "java", "Go"));
        assertEquals(2L, freq.get("java"));
        assertEquals(1L, freq.get("go"));
    }

    @Test
    void joinComma() {
        assertEquals("a, b, c", CollectorExamples.joinComma(List.of("a", "b", "c")));
    }

    @Test
    void stats() {
        var stats = CollectorExamples.stats(List.of(1, 2, 3, 4, 5));
        assertEquals(5, stats.getCount());
        assertEquals(15, stats.getSum());
        assertEquals(1, stats.getMin());
        assertEquals(5, stats.getMax());
        assertEquals(3.0, stats.getAverage(), 0.001);
    }

    @Test
    void toUnmodifiableSet() {
        Set<String> set = CollectorExamples.toUnmodifiableSet(List.of("a", "b", "a"));
        assertEquals(Set.of("a", "b"), set);
        assertThrows(UnsupportedOperationException.class, () -> set.add("c"));
    }
}
