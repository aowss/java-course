package course.ch23.examples;

import java.util.function.Supplier;

/**
 * Demonstrates commonly used built-in Java annotations.
 *
 * <p>Covers {@link Override}, {@link Deprecated}, {@link SuppressWarnings},
 * and {@link FunctionalInterface}.
 */
public class BuiltInAnnotations {

    /**
     * Returns a short description of each built-in annotation covered in this chapter.
     *
     * @return a multi-line summary
     */
    public static String describeBuiltInAnnotations() {
        return """
                @Override — marks a method that overrides a superclass method
                @Deprecated — marks an API element scheduled for removal
                @SuppressWarnings — suppresses compiler warnings for a scope
                @FunctionalInterface — marks an interface with exactly one abstract method""";
    }

    /**
     * Checks whether the given class is annotated with {@link Deprecated}.
     *
     * @param type the class to inspect
     * @return {@code true} if the class has {@code @Deprecated}
     */
    public static boolean isDeprecated(Class<?> type) {
        return type.isAnnotationPresent(Deprecated.class);
    }

    /**
     * Checks whether the given class is annotated with {@link FunctionalInterface}.
     *
     * @param type the class to inspect
     * @return {@code true} if the class has {@code @FunctionalInterface}
     */
    public static boolean hasFunctionalInterfaceAnnotation(Class<?> type) {
        return type.isAnnotationPresent(FunctionalInterface.class);
    }

    /**
     * A legacy greeting method kept for backward compatibility.
     *
     * @param name the name to greet
     * @return a greeting string
     * @deprecated Use {@link #greet(String)} instead.
     */
    @Deprecated(since = "1.0", forRemoval = true)
    public static String legacyGreet(String name) {
        return "Hi, " + name;
    }

    /**
     * Returns a modern greeting for the given name.
     *
     * @param name the name to greet
     * @return a greeting string
     */
    public static String greet(String name) {
        return "Hello, " + name;
    }

    /**
     * A functional interface used to demonstrate {@code @FunctionalInterface}.
     */
    @FunctionalInterface
    public interface Greeter extends Supplier<String> {
        /**
         * @return a greeting message
         */
        @Override
        String get();
    }

    /**
     * Creates a {@link Greeter} that returns the given message.
     *
     * @param message the greeting message
     * @return a greeter instance
     */
    public static Greeter createGreeter(String message) {
        return () -> message;
    }

    /**
     * A deprecated API type kept for demonstration purposes.
     */
    @Deprecated(since = "1.0", forRemoval = true)
    public static final class LegacyApi {
        private LegacyApi() {
        }
    }

    public static void main(String[] args) {
        System.out.println(describeBuiltInAnnotations());
        System.out.println();
        System.out.println("Greeter says: " + createGreeter("Welcome to Java!").get());
        System.out.println("Is LegacyApi deprecated? " + isDeprecated(LegacyApi.class));
    }
}
