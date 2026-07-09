package course.ch14.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates try-with-resources and the {@link AutoCloseable} interface.
 *
 * <p>A try-with-resources statement ensures that each resource is closed at the end of the
 * statement, even if an exception is thrown. This eliminates the need for explicit
 * {@code finally} blocks for resource cleanup.
 *
 * <pre>{@code
 * try (var resource = new SimpleResource("demo")) {
 *     resource.use();
 * }
 * // resource.close() is called automatically
 * }</pre>
 */
public class TryWithResources {

    /**
     * A simple resource that tracks whether it has been closed.
     * Implements {@link AutoCloseable} so it can be used in try-with-resources.
     */
    public static class SimpleResource implements AutoCloseable {

        private final String name;
        private boolean closed;
        private final List<String> log;

        public SimpleResource(String name, List<String> log) {
            this.name = name;
            this.closed = false;
            this.log = log;
            log.add("opened: " + name);
        }

        public void use() {
            if (closed) {
                throw new IllegalStateException("Resource " + name + " is already closed");
            }
            log.add("used: " + name);
        }

        public boolean isClosed() {
            return closed;
        }

        @Override
        public void close() {
            closed = true;
            log.add("closed: " + name);
        }

        @Override
        public String toString() {
            return "SimpleResource[" + name + ", closed=" + closed + "]";
        }
    }

    /**
     * A resource whose {@code close()} method throws an exception.
     */
    public static class FailingResource implements AutoCloseable {

        private final String name;
        private final List<String> log;

        public FailingResource(String name, List<String> log) {
            this.name = name;
            this.log = log;
            log.add("opened: " + name);
        }

        public void use() {
            log.add("used: " + name);
        }

        @Override
        public void close() {
            log.add("close-failed: " + name);
            throw new RuntimeException("Failed to close " + name);
        }
    }

    /**
     * Uses a resource inside try-with-resources. The resource is automatically closed.
     *
     * @param log a list to record operations
     */
    public static void useAndClose(List<String> log) {
        try (var resource = new SimpleResource("A", log)) {
            resource.use();
        }
    }

    /**
     * Demonstrates that the resource is closed even when an exception is thrown during use.
     *
     * @param log a list to record operations
     * @throws RuntimeException always, after the resource is closed
     */
    public static void closeOnException(List<String> log) {
        try (var resource = new SimpleResource("B", log)) {
            resource.use();
            throw new RuntimeException("something went wrong");
        }
    }

    /**
     * Demonstrates multiple resources in a single try-with-resources.
     * Resources are closed in <strong>reverse</strong> order of creation.
     *
     * @param log a list to record operations
     */
    public static void multipleResources(List<String> log) {
        try (var first = new SimpleResource("first", log);
             var second = new SimpleResource("second", log)) {
            first.use();
            second.use();
        }
    }

    /**
     * Demonstrates suppressed exceptions: when both the body and {@code close()} throw,
     * the close exception is added as a suppressed exception on the body exception.
     *
     * @param log a list to record operations
     * @throws RuntimeException the exception from the body, with the close exception suppressed
     */
    public static void suppressedExceptions(List<String> log) {
        try (var resource = new FailingResource("C", log)) {
            resource.use();
            throw new RuntimeException("body exception");
        }
    }

    public static void main(String[] args) {
        List<String> log = new ArrayList<>();

        System.out.println("--- useAndClose ---");
        useAndClose(log);
        log.forEach(System.out::println);

        log.clear();
        System.out.println("\n--- closeOnException ---");
        try {
            closeOnException(log);
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        }
        log.forEach(System.out::println);

        log.clear();
        System.out.println("\n--- multipleResources ---");
        multipleResources(log);
        log.forEach(System.out::println);

        log.clear();
        System.out.println("\n--- suppressedExceptions ---");
        try {
            suppressedExceptions(log);
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
            for (Throwable s : e.getSuppressed()) {
                System.out.println("  Suppressed: " + s.getMessage());
            }
        }
        log.forEach(System.out::println);
    }
}
