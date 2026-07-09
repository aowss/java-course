package course.ch28.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates class loading order: static field initializers, static blocks,
 * instance initializers, and constructors.
 *
 * <p>When a class is first used, the JVM loads it and runs static initializers
 * in source order. Creating an instance runs instance initializers and the constructor.
 *
 * <pre>{@code
 * List<String> log = ClassLoadingOrder.loadAndInstantiate();
 * // ["static field: Parent", "static block: Parent", ...]
 * }</pre>
 */
public final class ClassLoadingOrder {

    private ClassLoadingOrder() {
    }

    /**
     * Loads {@link Child} and creates an instance, returning the initialization log.
     *
     * @return ordered log of initialization events
     */
    public static List<String> loadAndInstantiate() {
        InitializationLog.clear();
        Child child = new Child("Ada");
        InitializationLog.add("instance created: " + child.name());
        return InitializationLog.snapshot();
    }

    /**
     * Thread-safe initialization event log used by the demo classes.
     */
    static final class InitializationLog {

        private static final List<String> EVENTS = new ArrayList<>();

        private InitializationLog() {
        }

        static void add(String event) {
            EVENTS.add(event);
        }

        static void clear() {
            EVENTS.clear();
        }

        static List<String> snapshot() {
            return List.copyOf(EVENTS);
        }
    }

    static class Parent {

        static final String STATIC_FIELD = initStaticField();

        static {
            InitializationLog.add("static block: Parent");
        }

        private static String initStaticField() {
            InitializationLog.add("static field: Parent");
            return "parent-static";
        }

        {
            InitializationLog.add("instance block: Parent");
        }

        Parent() {
            InitializationLog.add("constructor: Parent");
        }
    }

    static final class Child extends Parent {

        static final String CHILD_FIELD = initChildField();

        static {
            InitializationLog.add("static block: Child");
        }

        private static String initChildField() {
            InitializationLog.add("static field: Child");
            return "child-static";
        }

        private final String name;

        {
            InitializationLog.add("instance block: Child");
        }

        Child(String name) {
            InitializationLog.add("constructor: Child");
            this.name = name;
        }

        String name() {
            return name;
        }
    }

    public static void main(String[] args) {
        loadAndInstantiate().forEach(System.out::println);
    }
}
