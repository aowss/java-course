package course.ch18.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AsyncPipelineTest {

    @Test
    void fetchConvertsToUpperCase() {
        assertEquals("HELLO", AsyncPipeline.fetch("hello"));
    }

    @Test
    void transformReversesString() {
        assertEquals("dlrow", AsyncPipeline.transform("world"));
    }

    @Test
    void validateReturnsValidInput() {
        assertEquals("test", AsyncPipeline.validate("test"));
    }

    @Test
    void validateThrowsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> AsyncPipeline.validate(null));
    }

    @Test
    void validateThrowsOnEmpty() {
        assertThrows(IllegalArgumentException.class, () -> AsyncPipeline.validate(""));
    }

    @Test
    void formatWrapsBrackets() {
        assertEquals("[test]", AsyncPipeline.format("test"));
    }

    @Test
    void processChainsFetchTransformValidateFormat() {
        String result = AsyncPipeline.process("hello").join();
        assertEquals("[OLLEH]", result);
    }

    @Test
    void processWithDifferentInput() {
        String result = AsyncPipeline.process("world").join();
        assertEquals("[DLROW]", result);
    }

    @Test
    void processWithErrorHandlingReturnsResultForValidInput() {
        String result = AsyncPipeline.processWithErrorHandling("test").join();
        assertEquals("[TSET]", result);
    }

    @Test
    void processWithErrorHandlingReturnsFallbackForNull() {
        String result = AsyncPipeline.processWithErrorHandling(null).join();
        assertEquals("[ERROR]", result);
    }

    @Test
    void processWithErrorHandlingReturnsFallbackForEmpty() {
        String result = AsyncPipeline.processWithErrorHandling("").join();
        assertEquals("[ERROR]", result);
    }
}
