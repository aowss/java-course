package course.ch15.examples;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionalCreationTest {

    @Test
    void ofNullableWithValue() {
        Optional<String> opt = OptionalCreation.ofNullable("hello");
        assertTrue(opt.isPresent());
        assertEquals("hello", opt.get());
    }

    @Test
    void ofNullableWithNull() {
        assertTrue(OptionalCreation.ofNullable(null).isEmpty());
    }

    @Test
    void emptyIsEmpty() {
        assertTrue(OptionalCreation.empty().isEmpty());
    }

    @Test
    void orElseReturnsValueOrDefault() {
        assertEquals("hi", OptionalCreation.orElse(Optional.of("hi"), "default"));
        assertEquals("default", OptionalCreation.orElse(Optional.empty(), "default"));
    }

    @Test
    void orElseThrowThrowsWhenEmpty() {
        assertEquals("hi", OptionalCreation.orElseThrow(Optional.of("hi")));
        assertThrows(NoSuchElementException.class, () -> OptionalCreation.orElseThrow(Optional.empty()));
    }

    @Test
    void hasContentRejectsBlank() {
        assertTrue(OptionalCreation.hasContent(Optional.of("hello")));
        assertFalse(OptionalCreation.hasContent(Optional.of("  ")));
        assertFalse(OptionalCreation.hasContent(Optional.empty()));
    }

    @Test
    void firstPresentSkipsNullAndBlank() {
        assertEquals("Bob", OptionalCreation.firstPresent(null, "", "Bob", "Alice").orElse(""));
    }
}
