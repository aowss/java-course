package course.ch08.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegexBasicsTest {

    @Test
    void fullMatchReturnsTrueForMatchingPattern() {
        assertTrue(RegexBasics.fullMatch("abc123", "[a-z]+\\d+"));
    }

    @Test
    void fullMatchReturnsFalseForNonMatchingPattern() {
        assertFalse(RegexBasics.fullMatch("abc", "\\d+"));
    }

    @Test
    void findAllReturnsAllMatches() {
        var matches = RegexBasics.findAll("one 2 three 4", "\\d+");
        assertEquals(List.of("2", "4"), matches);
    }

    @Test
    void findAllReturnsEmptyListWhenNoMatch() {
        var matches = RegexBasics.findAll("hello", "\\d+");
        assertEquals(List.of(), matches);
    }

    @Test
    void replaceAllReplacesMatches() {
        assertEquals("h-ll-", RegexBasics.replaceAll("hello", "[aeiou]", "-"));
    }

    @Test
    void extractFirstGroupReturnsGroup() {
        assertEquals("555", RegexBasics.extractFirstGroup("(555) 123", "\\((\\d+)\\)"));
    }

    @Test
    void extractFirstGroupReturnsNullWhenNoMatch() {
        assertNull(RegexBasics.extractFirstGroup("no match", "\\((\\d+)\\)"));
    }
}
