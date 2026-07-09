package course.ch21.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Assertion Showcase")
class AssertionShowcaseTest {

    @Test
    @DisplayName("assertEquals — compare expected vs actual")
    void absoluteValueOfNegativeNumber() {
        assertEquals(5, AssertionShowcase.absoluteValue(-5));
    }

    @Test
    @DisplayName("assertEquals — positive number unchanged")
    void absoluteValueOfPositiveNumber() {
        assertEquals(7, AssertionShowcase.absoluteValue(7));
    }

    @Test
    @DisplayName("assertTrue — palindrome check")
    void racecarIsPalindrome() {
        assertTrue(AssertionShowcase.isPalindrome("racecar"));
    }

    @Test
    @DisplayName("assertFalse — non-palindrome")
    void helloIsNotPalindrome() {
        assertFalse(AssertionShowcase.isPalindrome("hello"));
    }

    @Test
    @DisplayName("assertThrows — division by zero")
    void divideByZeroThrows() {
        ArithmeticException ex = assertThrows(ArithmeticException.class,
                () -> AssertionShowcase.divide(10, 0));
        assertEquals("/ by zero", ex.getMessage());
    }

    @Test
    @DisplayName("assertAll — grouped assertions")
    void filterPositiveNumbers() {
        List<Integer> result = AssertionShowcase.filterPositive(List.of(-3, -1, 0, 2, 5));
        assertAll("filtered list",
                () -> assertEquals(2, result.size()),
                () -> assertEquals(2, result.get(0)),
                () -> assertEquals(5, result.get(1))
        );
    }

    @Test
    @DisplayName("assertFalse — null is not a palindrome")
    void nullIsNotPalindrome() {
        assertFalse(AssertionShowcase.isPalindrome(null));
    }
}
