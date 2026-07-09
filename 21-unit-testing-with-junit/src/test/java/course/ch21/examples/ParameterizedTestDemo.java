package course.ch21.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Parameterized Test Demo")
class ParameterizedTestDemo {

    @ParameterizedTest(name = "{0}°C = {1}°F")
    @CsvSource({
            "0,    32.0",
            "100,  212.0",
            "-40,  -40.0",
            "37,   98.6"
    })
    @DisplayName("@CsvSource — Celsius to Fahrenheit")
    void celsiusToFahrenheit(double celsius, double expectedFahrenheit) {
        assertEquals(expectedFahrenheit, TemperatureConverter.toFahrenheit(celsius), 0.1);
    }

    @ParameterizedTest(name = "\"{0}\" is a palindrome")
    @ValueSource(strings = {"racecar", "madam", "level", "kayak"})
    @DisplayName("@ValueSource — palindrome strings")
    void palindromeStrings(String input) {
        assertTrue(AssertionShowcase.isPalindrome(input));
    }

    record AbsTestCase(int input, int expected) {}

    static Stream<AbsTestCase> absoluteValueCases() {
        return Stream.of(
                new AbsTestCase(-5, 5),
                new AbsTestCase(0, 0),
                new AbsTestCase(3, 3),
                new AbsTestCase(-100, 100)
        );
    }

    @ParameterizedTest(name = "abs({0}) = {1}")
    @MethodSource("absoluteValueCases")
    @DisplayName("@MethodSource — absolute value")
    void absoluteValue(AbsTestCase tc) {
        assertEquals(tc.expected(), AssertionShowcase.absoluteValue(tc.input()));
    }
}
