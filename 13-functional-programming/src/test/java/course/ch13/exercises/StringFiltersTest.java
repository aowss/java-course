package course.ch13.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringFiltersTest {

    @Test
    void nonEmptyAcceptsNonEmptyString() {
        assertTrue(StringFilters.nonEmpty().test("hello"));
    }

    @Test
    void nonEmptyRejectsEmpty() {
        assertFalse(StringFilters.nonEmpty().test(""));
    }

    @Test
    void nonEmptyRejectsNull() {
        assertFalse(StringFilters.nonEmpty().test(null));
    }

    @Test
    void startsWithMatches() {
        assertTrue(StringFilters.startsWith("He").test("Hello"));
    }

    @Test
    void startsWithRejectsNonMatch() {
        assertFalse(StringFilters.startsWith("He").test("World"));
    }

    @Test
    void startsWithRejectsNull() {
        assertFalse(StringFilters.startsWith("He").test(null));
    }

    @Test
    void longerThanAcceptsLongerString() {
        assertTrue(StringFilters.longerThan(3).test("hello"));
    }

    @Test
    void longerThanRejectsShorterString() {
        assertFalse(StringFilters.longerThan(3).test("hi"));
    }

    @Test
    void longerThanRejectsExactLength() {
        assertFalse(StringFilters.longerThan(3).test("abc"));
    }

    @Test
    void longerThanRejectsNull() {
        assertFalse(StringFilters.longerThan(3).test(null));
    }

    @Test
    void filterWithNonEmpty() {
        var input = List.of("hello", "", "world", "");
        var result = StringFilters.filter(input, StringFilters.nonEmpty());
        assertEquals(List.of("hello", "world"), result);
    }

    @Test
    void filterWithStartsWith() {
        var input = List.of("Hello", "Hi", "World", "Help");
        var result = StringFilters.filter(input, StringFilters.startsWith("He"));
        assertEquals(List.of("Hello", "Help"), result);
    }

    @Test
    void filterWithLongerThan() {
        var input = List.of("a", "bb", "ccc", "dddd");
        var result = StringFilters.filter(input, StringFilters.longerThan(2));
        assertEquals(List.of("ccc", "dddd"), result);
    }

    @Test
    void filterWithCombinedPredicates() {
        var input = List.of("Hello", "Hi", "Help", "World", "Heap");
        var predicate = StringFilters.startsWith("He").and(StringFilters.longerThan(3));
        var result = StringFilters.filter(input, predicate);
        assertEquals(List.of("Hello", "Help", "Heap"), result);
    }
}
