package course.ch12.examples;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetOperationsTest {

    @Test
    void unionCombinesBothSets() {
        var a = Set.of(1, 2, 3);
        var b = Set.of(3, 4, 5);
        assertEquals(Set.of(1, 2, 3, 4, 5), SetOperations.union(a, b));
    }

    @Test
    void intersectionReturnsCommonElements() {
        var a = Set.of(1, 2, 3);
        var b = Set.of(2, 3, 4);
        assertEquals(Set.of(2, 3), SetOperations.intersection(a, b));
    }

    @Test
    void intersectionReturnsEmptyWhenNoOverlap() {
        var a = Set.of(1, 2);
        var b = Set.of(3, 4);
        assertEquals(Set.of(), SetOperations.intersection(a, b));
    }

    @Test
    void differenceReturnsElementsOnlyInFirst() {
        var a = Set.of(1, 2, 3);
        var b = Set.of(2, 3, 4);
        assertEquals(Set.of(1), SetOperations.difference(a, b));
    }

    @Test
    void differenceReturnsEmptyWhenSubset() {
        var a = Set.of(1, 2);
        var b = Set.of(1, 2, 3);
        assertEquals(Set.of(), SetOperations.difference(a, b));
    }
}
