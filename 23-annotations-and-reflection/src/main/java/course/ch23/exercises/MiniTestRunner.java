package course.ch23.exercises;

import java.util.List;

/**
 * Exercise 3 (Challenge): A minimal test runner driven by a custom {@code @Test} annotation.
 *
 * <p>Scans the given test instance for zero-argument methods annotated with
 * {@link course.ch23.annotations.Test}, invokes each one, and reports results.
 * A test passes when it completes without throwing an exception.
 */
public class MiniTestRunner {

    /**
     * Runs all {@code @Test} methods on the given instance.
     *
     * @param testInstance the object containing test methods
     * @return aggregated pass/fail results
     * @throws IllegalArgumentException if {@code testInstance} is {@code null}
     */
    public static TestResult runAll(Object testInstance) {
        // TODO: implement using reflection and course.ch23.annotations.Test
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Aggregated results from a test run.
     *
     * @param passed   number of passing tests
     * @param failed   number of failing tests
     * @param failures descriptions of failed tests (method name and exception message)
     */
    public record TestResult(int passed, int failed, List<String> failures) {
    }

    public static void main(String[] args) {
        TestResult result = runAll(new SampleTests());
        System.out.println("Passed: " + result.passed());
        System.out.println("Failed: " + result.failed());
        result.failures().forEach(System.out::println);
    }

    /**
     * Sample test class used in demos.
     */
    static class SampleTests {

        @course.ch23.annotations.Test
        void alwaysPasses() {
        }

        @course.ch23.annotations.Test
        void alwaysFails() {
            throw new AssertionError("boom");
        }
    }
}
