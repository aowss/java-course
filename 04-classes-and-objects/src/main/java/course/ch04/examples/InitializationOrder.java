package course.ch04.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates initialization order within a single class: static fields, static blocks,
 * instance blocks, field initializers, and the constructor.
 *
 * <p>Run {@link #demonstrate()} and inspect the log to see the exact sequence.
 */
public final class InitializationOrder {

    private InitializationOrder() {
    }

    /**
     * Creates a {@link Demo} instance and returns the initialization log.
     *
     * @return ordered log of initialization events
     */
    public static List<String> demonstrate() {
        Log.clear();
        Demo demo = new Demo("Ada");
        Log.add("after constructor: " + demo.name());
        return Log.snapshot();
    }

    static final class Log {

        private static final List<String> EVENTS = new ArrayList<>();

        private Log() {
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

    static final class Demo {

        static final String STATIC_FIELD = initStaticField();

        static {
            Log.add("static block");
        }

        private static String initStaticField() {
            Log.add("static field initializer");
            return "ready";
        }

        private final int id = initIdField();
        private final String name;

        {
            Log.add("instance block");
        }

        private int initIdField() {
            Log.add("instance field initializer");
            return 1;
        }

        Demo(String name) {
            Log.add("constructor");
            this.name = name;
        }

        String name() {
            return name;
        }
    }

    public static void main(String[] args) {
        demonstrate().forEach(System.out::println);
    }
}
