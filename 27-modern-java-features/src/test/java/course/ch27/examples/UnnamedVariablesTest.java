package course.ch27.examples;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnnamedVariablesTest {

    @Test
    void countElementsReturnsSize() {
        assertEquals(3, UnnamedVariables.countElements(List.of("a", "b", "c")));
    }

    @Test
    void countElementsEmptyList() {
        assertEquals(0, UnnamedVariables.countElements(List.of()));
    }

    @Test
    void sumValuesIgnoresKeys() {
        assertEquals(30, UnnamedVariables.sumValues(Map.of("x", 10, "y", 20)));
    }

    @Test
    void firstOfExtractsFirstComponent() {
        assertEquals(7, UnnamedVariables.firstOf(new UnnamedVariables.Pair(7, 99)));
    }

    @Test
    void runQuietlyReturnsTrueOnSuccess() {
        assertTrue(UnnamedVariables.runQuietly(() -> {}));
    }

    @Test
    void runQuietlyReturnsFalseOnException() {
        assertFalse(UnnamedVariables.runQuietly(() -> {
            throw new IllegalStateException("boom");
        }));
    }
}
