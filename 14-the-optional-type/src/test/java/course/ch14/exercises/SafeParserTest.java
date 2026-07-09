package course.ch14.exercises;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SafeParserTest {

    @Test
    void parseIntReturnsValueForValidInput() {
        assertEquals(Optional.of(42), SafeParser.parseInt("42"));
        assertEquals(Optional.of(-7), SafeParser.parseInt(" -7 "));
    }

    @Test
    void parseIntReturnsEmptyForInvalidInput() {
        assertTrue(SafeParser.parseInt("abc").isEmpty());
        assertTrue(SafeParser.parseInt(null).isEmpty());
        assertTrue(SafeParser.parseInt("  ").isEmpty());
    }

    @Test
    void parseDoubleReturnsValueForValidInput() {
        assertEquals(Optional.of(3.14), SafeParser.parseDouble("3.14"));
    }

    @Test
    void parseDoubleReturnsEmptyForInvalidInput() {
        assertTrue(SafeParser.parseDouble("not-a-number").isEmpty());
    }

    @Test
    void parseBooleanAcceptsTrueAndFalse() {
        assertEquals(Optional.of(true), SafeParser.parseBoolean("true"));
        assertEquals(Optional.of(false), SafeParser.parseBoolean("FALSE"));
        assertTrue(SafeParser.parseBoolean("yes").isEmpty());
    }

    @Test
    void firstValidIntReturnsFirstParseableValue() {
        assertEquals(Optional.of(7), SafeParser.firstValidInt("x", "y", "7", "9"));
        assertTrue(SafeParser.firstValidInt("a", "b").isEmpty());
    }
}
