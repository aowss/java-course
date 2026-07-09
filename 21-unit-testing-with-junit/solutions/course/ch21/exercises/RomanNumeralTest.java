package course.ch21.exercises;

import course.ch21.examples.RomanNumeral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Roman Numeral Parameterized Tests")
class RomanNumeralTest {

    @ParameterizedTest(name = "{0} → \"{1}\"")
    @CsvSource({
            "1,    I",
            "4,    IV",
            "9,    IX",
            "14,   XIV",
            "42,   XLII",
            "99,   XCIX",
            "1994, MCMXCIV",
            "3999, MMMCMXCIX"
    })
    @DisplayName("toRoman converts integer to Roman numeral")
    void toRoman(int input, String expected) {
        assertEquals(expected, RomanNumeral.toRoman(input));
    }

    @ParameterizedTest(name = "\"{0}\" → {1}")
    @CsvSource({
            "I,         1",
            "IV,        4",
            "IX,        9",
            "XIV,       14",
            "XLII,      42",
            "XCIX,      99",
            "MCMXCIV,   1994",
            "MMMCMXCIX, 3999"
    })
    @DisplayName("fromRoman converts Roman numeral to integer")
    void fromRoman(String input, int expected) {
        assertEquals(expected, RomanNumeral.fromRoman(input));
    }

    @ParameterizedTest(name = "roundtrip for {0}")
    @ValueSource(ints = {1, 5, 10, 50, 100, 500, 1000, 2024, 3999})
    @DisplayName("roundtrip: fromRoman(toRoman(n)) == n")
    void roundtrip(int n) {
        assertEquals(n, RomanNumeral.fromRoman(RomanNumeral.toRoman(n)));
    }

    @Test
    @DisplayName("toRoman(0) throws IllegalArgumentException")
    void toRomanZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(0));
    }

    @Test
    @DisplayName("toRoman(4000) throws IllegalArgumentException")
    void toRomanAboveRangeThrows() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.toRoman(4000));
    }

    @Test
    @DisplayName("fromRoman(\"\") throws IllegalArgumentException")
    void fromRomanEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.fromRoman(""));
    }
}
