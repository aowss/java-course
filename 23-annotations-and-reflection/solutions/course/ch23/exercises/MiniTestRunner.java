package course.ch23.exercises;

import course.ch23.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MiniTestRunner {

    public static TestResult runAll(Object testInstance) {
        if (testInstance == null) {
            throw new IllegalArgumentException("Test instance must not be null");
        }
        int passed = 0;
        int failed = 0;
        List<String> failures = new ArrayList<>();

        for (Method method : testInstance.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Test.class)) {
                continue;
            }
            if (method.getParameterCount() != 0) {
                continue;
            }
            method.setAccessible(true);
            try {
                method.invoke(testInstance);
                passed++;
            } catch (ReflectiveOperationException e) {
                failed++;
                Throwable cause = e.getCause() != null ? e.getCause() : e;
                failures.add(method.getName() + ": " + cause.getMessage());
            }
        }
        return new TestResult(passed, failed, List.copyOf(failures));
    }

    public record TestResult(int passed, int failed, List<String> failures) {
    }

    public static void main(String[] args) {
        TestResult result = runAll(new SampleTests());
        System.out.println("Passed: " + result.passed());
        System.out.println("Failed: " + result.failed());
        result.failures().forEach(System.out::println);
    }

    static class SampleTests {

        @Test
        void alwaysPasses() {
        }

        @Test
        void alwaysFails() {
            throw new AssertionError("boom");
        }
    }
}
