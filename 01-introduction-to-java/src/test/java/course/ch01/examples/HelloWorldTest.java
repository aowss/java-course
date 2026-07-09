package course.ch01.examples;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloWorldTest {

    @Test
    void mainPrintsHelloWorld() {
        var originalOut = System.out;
        var capture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capture));
        try {
            HelloWorld.main(new String[]{});
        } finally {
            System.setOut(originalOut);
        }
        assertEquals("Hello, World!", capture.toString().trim());
    }
}
