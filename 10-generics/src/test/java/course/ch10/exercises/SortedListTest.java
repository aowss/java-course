package course.ch10.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortedListTest {

    @Test
    void addMaintainsSortedOrder() {
        var list = new SortedList<Integer>();
        list.add(3);
        list.add(1);
        list.add(2);
        assertEquals(List.of(1, 2, 3), list.toList());
    }

    @Test
    void addWithDuplicates() {
        var list = new SortedList<String>();
        list.add("banana");
        list.add("apple");
        list.add("banana");
        assertEquals(List.of("apple", "banana", "banana"), list.toList());
    }

    @Test
    void getReturnsElementAtIndex() {
        var list = new SortedList<Integer>();
        list.add(5);
        list.add(1);
        list.add(3);
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    void getThrowsOnInvalidIndex() {
        var list = new SortedList<Integer>();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    void containsUsesBinarySearch() {
        var list = new SortedList<Integer>();
        for (int i = 0; i < 100; i++) {
            list.add(i * 2);
        }
        assertTrue(list.contains(50));
        assertFalse(list.contains(51));
    }

    @Test
    void containsOnEmptyList() {
        var list = new SortedList<String>();
        assertFalse(list.contains("anything"));
    }

    @Test
    void sizeReturnsElementCount() {
        var list = new SortedList<String>();
        assertEquals(0, list.size());
        list.add("a");
        list.add("b");
        assertEquals(2, list.size());
    }

    @Test
    void toListReturnsCorrectElements() {
        var list = new SortedList<Integer>();
        list.add(3);
        list.add(1);
        assertEquals(List.of(1, 3), list.toList());
    }
}
