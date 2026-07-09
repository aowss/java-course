package course.ch12.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListOperationsTest {

    @Test
    void sortedArrayListReturnsSortedElements() {
        assertEquals(List.of(1, 3, 5, 7), ListOperations.sortedArrayList(7, 3, 5, 1));
    }

    @Test
    void sortedArrayListHandlesDuplicates() {
        assertEquals(List.of(1, 1, 2, 3), ListOperations.sortedArrayList(3, 1, 2, 1));
    }

    @Test
    void reversedReturnsReversedCopy() {
        var original = List.of("a", "b", "c");
        assertEquals(List.of("c", "b", "a"), ListOperations.reversed(original));
    }

    @Test
    void reversedDoesNotMutateOriginal() {
        var original = List.of(1, 2, 3);
        ListOperations.reversed(original);
        assertEquals(List.of(1, 2, 3), original);
    }

    @Test
    void firstAndLastReturnsCorrectElements() {
        var fl = ListOperations.firstAndLast(List.of(10, 20, 30));
        assertEquals(List.of(10, 30), fl);
    }

    @Test
    void firstAndLastSingleElement() {
        var fl = ListOperations.firstAndLast(List.of(42));
        assertEquals(List.of(42, 42), fl);
    }
}
