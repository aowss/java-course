package course.ch13.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PipelineTest {

    @Test
    void singleStep() {
        var result = new Pipeline<String>()
                .addStep(String::toUpperCase)
                .execute("hello");
        assertEquals("HELLO", result);
    }

    @Test
    void multipleSteps() {
        var result = new Pipeline<String>()
                .addStep(String::trim)
                .addStep(String::toUpperCase)
                .execute("  hello  ");
        assertEquals("HELLO", result);
    }

    @Test
    void noSteps() {
        var result = new Pipeline<String>()
                .execute("hello");
        assertEquals("hello", result);
    }

    @Test
    void integerPipeline() {
        var result = new Pipeline<Integer>()
                .addStep(n -> n * 2)
                .addStep(n -> n + 10)
                .addStep(n -> n * 3)
                .execute(5);
        assertEquals(60, result);
    }

    @Test
    void stepsApplyInOrder() {
        var result = new Pipeline<String>()
                .addStep(s -> s + "B")
                .addStep(s -> s + "C")
                .addStep(s -> s + "D")
                .execute("A");
        assertEquals("ABCD", result);
    }
}
