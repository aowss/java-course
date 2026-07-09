package course.ch01.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingTest {

    @Test
    void greetWithName() {
        assertEquals("Hello, Alice!", Greeting.greet("Alice"));
    }

    @Test
    void greetWithNull() {
        assertEquals("Hello, World!", Greeting.greet(null));
    }

    @Test
    void greetWithEmptyString() {
        assertEquals("Hello, World!", Greeting.greet(""));
    }

    @Test
    void greetWithDifferentName() {
        assertEquals("Hello, Bob!", Greeting.greet("Bob"));
    }
}
