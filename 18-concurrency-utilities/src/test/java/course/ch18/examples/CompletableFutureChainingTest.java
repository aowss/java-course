package course.ch18.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompletableFutureChainingTest {

    @Test
    void chainWithThenApply() {
        String result = CompletableFutureChaining.chainWithThenApply("hello").join();
        assertEquals("HELLO:5", result);
    }

    @Test
    void chainWithThenApplyEmptyString() {
        String result = CompletableFutureChaining.chainWithThenApply("").join();
        assertEquals(":0", result);
    }

    @Test
    void chainWithThenCompose() {
        String result = CompletableFutureChaining.chainWithThenCompose("hello").join();
        assertEquals("HELLO!", result);
    }

    @Test
    void combineResults() {
        String result = CompletableFutureChaining.combineResults("hello", "world").join();
        assertEquals("5-dlrow", result);
    }

    @Test
    void withErrorHandlingValidInput() {
        String result = CompletableFutureChaining.withErrorHandling("hello").join();
        assertEquals("HELLO", result);
    }

    @Test
    void withErrorHandlingNullInput() {
        String result = CompletableFutureChaining.withErrorHandling(null).join();
        assertEquals("FALLBACK", result);
    }

    @Test
    void withErrorHandlingEmptyInput() {
        String result = CompletableFutureChaining.withErrorHandling("").join();
        assertEquals("FALLBACK", result);
    }
}
