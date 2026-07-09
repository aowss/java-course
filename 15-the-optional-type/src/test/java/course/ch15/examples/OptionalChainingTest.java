package course.ch15.examples;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionalChainingTest {

    @Test
    void parseIntValid() {
        assertEquals(42, OptionalChaining.parseInt("42").orElse(0));
    }

    @Test
    void parseIntInvalid() {
        assertTrue(OptionalChaining.parseInt("abc").isEmpty());
        assertTrue(OptionalChaining.parseInt(null).isEmpty());
    }

    @Test
    void doubleValue() {
        assertEquals(42, OptionalChaining.doubleValue(Optional.of(21)).orElse(0));
    }

    @Test
    void toStringConversion() {
        assertEquals("42", OptionalChaining.toString(Optional.of(42)).orElse(""));
    }

    @Test
    void parsePositiveAndDouble() {
        assertEquals(14, OptionalChaining.parsePositiveAndDouble("7").orElse(0));
        assertTrue(OptionalChaining.parsePositiveAndDouble("-3").isEmpty());
    }

    @Test
    void greetWithName() {
        assertEquals("Hello, Alice!", OptionalChaining.greet(Optional.of("Alice")));
    }

    @Test
    void greetDefault() {
        assertEquals("Hello, Guest!", OptionalChaining.greet(Optional.empty()));
    }

    @Test
    void chainFlatMaps() {
        Optional<Integer> result = OptionalChaining.chain(
                Optional.of("5"),
                OptionalChaining::parseInt
        );
        assertEquals(5, result.orElse(0));
    }
}
