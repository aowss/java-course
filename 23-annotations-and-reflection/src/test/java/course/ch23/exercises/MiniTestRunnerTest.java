package course.ch23.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MiniTestRunnerTest {

    static class MixedTests {

        @course.ch23.annotations.Test
        void passes() {
        }

        @course.ch23.annotations.Test
        void fails() {
            throw new IllegalStateException("expected failure");
        }

        void notATest() {
            throw new AssertionError("should not run");
        }
    }

    @Test
    void runAllCountsPassesAndFailures() {
        MiniTestRunner.TestResult result = MiniTestRunner.runAll(new MixedTests());
        assertEquals(1, result.passed());
        assertEquals(1, result.failed());
    }

    @Test
    void runAllRecordsFailureMessages() {
        MiniTestRunner.TestResult result = MiniTestRunner.runAll(new MixedTests());
        assertEquals(1, result.failures().size());
        assertTrue(result.failures().getFirst().contains("fails"));
    }

    @Test
    void runAllIgnoresUnannotatedMethods() {
        MiniTestRunner.TestResult result = MiniTestRunner.runAll(new MixedTests());
        assertEquals(2, result.passed() + result.failed());
    }

    @Test
    void runAllRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> MiniTestRunner.runAll(null));
    }

    static class AllPassingTests {

        @course.ch23.annotations.Test
        void first() {
        }

        @course.ch23.annotations.Test
        void second() {
        }
    }

    @Test
    void runAllWithAllPassingTests() {
        MiniTestRunner.TestResult result = MiniTestRunner.runAll(new AllPassingTests());
        assertEquals(2, result.passed());
        assertEquals(0, result.failed());
    }
}
