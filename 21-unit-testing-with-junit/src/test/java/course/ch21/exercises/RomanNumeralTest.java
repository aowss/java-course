package course.ch21.exercises;

import course.ch21.examples.RomanNumeral;

import org.junit.jupiter.api.DisplayName;

/**
 * Exercise 2 (Practice): Write parameterized tests for {@link RomanNumeral}.
 *
 * <p>Use {@code @ParameterizedTest} with appropriate sources to test:
 * <ul>
 *   <li>{@code toRoman} — convert integers to Roman numerals (test at least 8 input/output pairs)</li>
 *   <li>{@code fromRoman} — convert Roman numerals back to integers</li>
 *   <li>Roundtrip: {@code fromRoman(toRoman(n)) == n}</li>
 *   <li>Edge cases: out-of-range values throw {@code IllegalArgumentException}</li>
 * </ul>
 *
 * <p>Suggested annotations to use: {@code @CsvSource}, {@code @ValueSource}, {@code @MethodSource}.
 */
@DisplayName("Roman Numeral Parameterized Tests")
class RomanNumeralTest {

    // TODO: Write a @ParameterizedTest with @CsvSource testing toRoman()
    //       Pairs: 1→"I", 4→"IV", 9→"IX", 14→"XIV", 42→"XLII",
    //              99→"XCIX", 1994→"MCMXCIV", 3999→"MMMCMXCIX"

    // TODO: Write a @ParameterizedTest with @CsvSource testing fromRoman()
    //       Pairs: "I"→1, "IV"→4, "IX"→9, "XIV"→14, "XLII"→42,
    //              "XCIX"→99, "MCMXCIV"→1994, "MMMCMXCIX"→3999

    // TODO: Write a @ParameterizedTest with @ValueSource(ints = {...}) testing roundtrip
    //       For each value n, verify fromRoman(toRoman(n)) == n

    // TODO: Write a @Test verifying that toRoman(0) throws IllegalArgumentException
    // TODO: Write a @Test verifying that toRoman(4000) throws IllegalArgumentException
    // TODO: Write a @Test verifying that fromRoman("") throws IllegalArgumentException
}
