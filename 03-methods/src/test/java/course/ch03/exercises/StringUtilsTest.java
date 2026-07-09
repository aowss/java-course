package course.ch03.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {

    // --- reverse ---

    @Test
    void reverseWord() {
        assertEquals("olleh", StringUtils.reverse("hello"));
    }

    @Test
    void reverseEmpty() {
        assertEquals("", StringUtils.reverse(""));
    }

    @Test
    void reverseSingleChar() {
        assertEquals("a", StringUtils.reverse("a"));
    }

    @Test
    void reversePalindrome() {
        assertEquals("racecar", StringUtils.reverse("racecar"));
    }

    // --- isPalindrome ---

    @Test
    void isPalindromeTrue() {
        assertTrue(StringUtils.isPalindrome("racecar"));
    }

    @Test
    void isPalindromeCaseInsensitive() {
        assertTrue(StringUtils.isPalindrome("Madam"));
    }

    @Test
    void isPalindromeFalse() {
        assertFalse(StringUtils.isPalindrome("hello"));
    }

    @Test
    void isPalindromeEmpty() {
        assertTrue(StringUtils.isPalindrome(""));
    }

    @Test
    void isPalindromeSingleChar() {
        assertTrue(StringUtils.isPalindrome("x"));
    }

    // --- countOccurrences ---

    @Test
    void countOccurrencesMultiple() {
        assertEquals(3, StringUtils.countOccurrences("banana", 'a'));
    }

    @Test
    void countOccurrencesNone() {
        assertEquals(0, StringUtils.countOccurrences("hello", 'z'));
    }

    @Test
    void countOccurrencesEmpty() {
        assertEquals(0, StringUtils.countOccurrences("", 'a'));
    }

    @Test
    void countOccurrencesAllSame() {
        assertEquals(4, StringUtils.countOccurrences("aaaa", 'a'));
    }
}
