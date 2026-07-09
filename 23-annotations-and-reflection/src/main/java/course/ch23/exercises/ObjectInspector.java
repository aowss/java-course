package course.ch23.exercises;

import java.util.List;

/**
 * Exercise 1 (Guided): Inspect objects using reflection.
 *
 * <p>Implement methods that use the reflection API to describe an object's
 * runtime type, declared fields, and declared methods.
 */
public class ObjectInspector {

    /**
     * Returns a multi-line summary of the object's class, fields, and methods.
     *
     * <p>Format:
     * <pre>
     * Class: SimpleName
     * Fields: field1, field2
     * Methods: method1, method2
     * </pre>
     *
     * <p>Field and method names must be sorted alphabetically. Synthetic members
     * are excluded.
     *
     * @param target the object to inspect
     * @return a formatted summary
     * @throws IllegalArgumentException if {@code target} is {@code null}
     */
    public static String inspect(Object target) {
        // TODO: implement using Class, Field, and Method reflection APIs
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the declared field names of the target object's class.
     *
     * @param target the object to inspect
     * @return sorted field names (excluding synthetic fields)
     * @throws IllegalArgumentException if {@code target} is {@code null}
     */
    public static List<String> getFieldNames(Object target) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the declared method names of the target object's class.
     *
     * @param target the object to inspect
     * @return sorted distinct method names (excluding synthetic methods)
     * @throws IllegalArgumentException if {@code target} is {@code null}
     */
    public static List<String> getMethodNames(Object target) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        System.out.println(inspect("Hello, reflection!"));
    }
}
