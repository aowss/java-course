package course.ch14.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntermediateOpsTest {

    @Test
    void filterByLength() {
        var words = List.of("java", "go", "kotlin", "c");
        assertEquals(List.of("java", "kotlin"), IntermediateOps.filterByLength(words, 4));
    }

    @Test
    void toUpperCase() {
        assertEquals(List.of("JAVA", "GO"), IntermediateOps.toUpperCase(List.of("java", "go")));
    }

    @Test
    void flatMapToChars() {
        assertEquals(List.of("a", "b"), IntermediateOps.flatMapToChars(List.of("ab")));
    }

    @Test
    void distinctSorted() {
        assertEquals(List.of("a", "b", "c"), IntermediateOps.distinctSorted(List.of("c", "a", "b", "a")));
    }

    @Test
    void firstNEvens() {
        assertEquals(List.of(2, 4, 6), IntermediateOps.firstNEvens(List.of(1, 2, 3, 4, 5, 6, 8), 3));
    }

    @Test
    void positiveDoubledSorted() {
        assertEquals(List.of(2, 4, 6), IntermediateOps.positiveDoubledSorted(List.of(-1, 1, 3, 2)));
    }
}
