package course.ch13.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaBasicsTest {

    @Test
    void sortByLength() {
        var input = List.of("banana", "apple", "cherry", "date", "fig");
        var result = LambdaBasics.sortByLength(input);
        assertEquals(List.of("fig", "date", "apple", "banana", "cherry"), result);
    }

    @Test
    void sortByLengthThenAlpha() {
        var input = List.of("cherry", "banana", "anchor");
        var result = LambdaBasics.sortByLengthThenAlpha(input);
        assertEquals(List.of("anchor", "banana", "cherry"), result);
    }

    @Test
    void sortByLengthThenAlphaMixedLengths() {
        var input = List.of("banana", "apple", "cherry", "date", "fig");
        var result = LambdaBasics.sortByLengthThenAlpha(input);
        assertEquals(List.of("fig", "date", "apple", "banana", "cherry"), result);
    }

    @Test
    void greetWithPrefix() {
        assertEquals("Hi Alice!", LambdaBasics.greetWithPrefix("Hi", "Alice"));
        assertEquals("Hello Bob!", LambdaBasics.greetWithPrefix("Hello", "Bob"));
    }
}
