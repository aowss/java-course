package course.ch04.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InitializationOrderTest {

    @Test
    void staticInitializersRunBeforeInstanceInitializersAndConstructor() {
        List<String> log = InitializationOrder.demonstrate();

        assertEquals(
                List.of(
                        "static field initializer",
                        "static block",
                        "instance field initializer",
                        "instance block",
                        "constructor",
                        "after constructor: Ada"
                ),
                log
        );
    }
}
