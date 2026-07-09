package course.ch06.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortableTest {

    @Test
    void sortIntsAscending() {
        Sortable<Sortable.SortableInt> sorter = new Sortable.SortableInt(0);
        var input = List.of(new Sortable.SortableInt(42), new Sortable.SortableInt(7), new Sortable.SortableInt(19));
        var sorted = sorter.sort(input);
        assertEquals(List.of(7, 19, 42), sorted.stream().map(Sortable.SortableInt::value).toList());
    }

    @Test
    void sortStringsByLengthThenAlpha() {
        Sortable<Sortable.SortableString> sorter = new Sortable.SortableString("");
        var input = List.of(
                new Sortable.SortableString("banana"),
                new Sortable.SortableString("fig"),
                new Sortable.SortableString("apple")
        );
        var sorted = sorter.sort(input);
        assertEquals(List.of("fig", "apple", "banana"), sorted.stream().map(Sortable.SortableString::text).toList());
    }

    @Test
    void minReturnsSmallerValue() {
        Sortable<Sortable.SortableInt> sorter = new Sortable.SortableInt(0);
        var a = new Sortable.SortableInt(10);
        var b = new Sortable.SortableInt(3);
        assertEquals(3, sorter.min(a, b).value());
    }

    @Test
    void compareReturnsZeroForEqualValues() {
        Sortable<Sortable.SortableInt> sorter = new Sortable.SortableInt(0);
        var a = new Sortable.SortableInt(5);
        var b = new Sortable.SortableInt(5);
        assertEquals(0, sorter.compare(a, b));
    }
}
