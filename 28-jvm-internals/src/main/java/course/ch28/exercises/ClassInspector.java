package course.ch28.exercises;

/**
 * Exercise 2 (Practice): Inspect class metadata via reflection.
 *
 * <p>Implement methods that extract structural information from a {@link Class} object.
 *
 * <pre>{@code
 * String info = ClassInspector.describe(String.class);
 * // "class java.lang.String extends java.lang.Object [final]"
 * }</pre>
 */
public final class ClassInspector {

    private ClassInspector() {
    }

    /**
     * Returns a one-line description of the class.
     *
     * <p>Format: {@code "<kind> <name> extends <super> [<modifiers>]"} where
     * {@code kind} is {@code "interface"} or {@code "class"}, and modifiers
     * include {@code final}, {@code abstract}, and/or {@code sealed} when applicable.
     *
     * @param clazz the class to describe
     * @return a formatted description
     */
    public static String describe(Class<?> clazz) {
        // TODO: build description from clazz metadata
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the number of declared fields in the class (including private).
     *
     * @param clazz the class to inspect
     * @return the field count
     */
    public static int declaredFieldCount(Class<?> clazz) {
        // TODO: use clazz.getDeclaredFields().length
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns {@code true} if the class is a record.
     *
     * @param clazz the class to check
     * @return {@code true} for record classes
     */
    public static boolean isRecord(Class<?> clazz) {
        // TODO: use clazz.isRecord()
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
