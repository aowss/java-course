package course.ch03.exercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MathUtilsTest {

    // --- clamp ---

    @Test
    void clampValueInRange() {
        assertEquals(5, MathUtils.clamp(5, 0, 10));
    }

    @Test
    void clampValueBelowMin() {
        assertEquals(0, MathUtils.clamp(-5, 0, 10));
    }

    @Test
    void clampValueAboveMax() {
        assertEquals(10, MathUtils.clamp(15, 0, 10));
    }

    @Test
    void clampValueAtBoundaries() {
        assertEquals(0, MathUtils.clamp(0, 0, 10));
        assertEquals(10, MathUtils.clamp(10, 0, 10));
    }

    @Test
    void clampThrowsWhenMinGreaterThanMax() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.clamp(5, 10, 0));
    }

    // --- isPrime ---

    @ParameterizedTest
    @CsvSource({"2", "3", "5", "7", "11", "13", "97"})
    void isPrimeTrue(int n) {
        assertTrue(MathUtils.isPrime(n));
    }

    @ParameterizedTest
    @CsvSource({"0", "1", "4", "9", "15", "100"})
    void isPrimeFalse(int n) {
        assertFalse(MathUtils.isPrime(n));
    }

    @Test
    void isPrimeNegative() {
        assertFalse(MathUtils.isPrime(-7));
    }

    // --- factorial ---

    @Test
    void factorialOfZero() {
        assertEquals(1L, MathUtils.factorial(0));
    }

    @Test
    void factorialOfOne() {
        assertEquals(1L, MathUtils.factorial(1));
    }

    @Test
    void factorialOfFive() {
        assertEquals(120L, MathUtils.factorial(5));
    }

    @Test
    void factorialOfTen() {
        assertEquals(3_628_800L, MathUtils.factorial(10));
    }

    @Test
    void factorialOfTwenty() {
        assertEquals(2_432_902_008_176_640_000L, MathUtils.factorial(20));
    }

    @Test
    void factorialNegativeThrows() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.factorial(-1));
    }
}
