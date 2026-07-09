package course.ch01.exercises;

/**
 * Exercise 1 (Guided): Write a greeting program.
 *
 * <p>Implement the {@link #greet(String)} method so that:
 * <ul>
 *   <li>If {@code name} is {@code null} or empty, return {@code "Hello, World!"}</li>
 *   <li>Otherwise, return {@code "Hello, <name>!"}</li>
 * </ul>
 */
public class Greeting {

    /**
     * Returns a greeting for the given name.
     *
     * @param name the name to greet, or {@code null} for the default greeting
     * @return the greeting string
     */
    public static String greet(String name) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : null;
        System.out.println(greet(name));
    }
}
