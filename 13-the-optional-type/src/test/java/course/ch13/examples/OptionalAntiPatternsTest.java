package course.ch13.examples;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionalAntiPatternsTest {

    @Test
    void safeUnwrapUsesOrElse() {
        assertEquals("default", OptionalAntiPatterns.safeUnwrap(Optional.empty()));
        assertEquals("hi", OptionalAntiPatterns.safeUnwrap(Optional.of("hi")));
    }

    @Test
    void neverReturnNull() {
        Optional<String> result = OptionalAntiPatterns.neverReturnNull(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void displayNameHandlesNull() {
        assertEquals("Anonymous", OptionalAntiPatterns.displayName(null));
        assertEquals("Alice", OptionalAntiPatterns.displayName("Alice"));
    }

    @Test
    void itemsOrEmptyReturnsEmptyList() {
        assertEquals(List.of(), OptionalAntiPatterns.itemsOrEmpty(Optional.empty()));
    }

    @Test
    void extractDomain() {
        assertEquals("example.com", OptionalAntiPatterns.extractDomain(Optional.of("alice@example.com")));
        assertEquals("", OptionalAntiPatterns.extractDomain(Optional.of("noemail")));
    }

    @Test
    void safeOfHandlesNull() {
        assertTrue(OptionalAntiPatterns.safeOf(null).isEmpty());
        assertEquals("hi", OptionalAntiPatterns.safeOf("hi").orElse(""));
    }
}
