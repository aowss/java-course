package course.ch01.exercises;

/**
 * Exercise 2 (Practice): Build a system information reporter.
 *
 * <p>Implement the {@link #report()} method so that it returns a string
 * in the following format:
 * <pre>
 *   Java <version> on <os.name> (<os.arch>)
 * </pre>
 *
 * <p>For example: {@code "Java 25 on Mac OS X (aarch64)"}
 */
public class SystemReporter {

    /**
     * Returns a formatted string describing the current Java and OS environment.
     *
     * @return the system report string
     */
    public static String report() {
        // TODO: implement this method using System.getProperty()
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        System.out.println(report());
    }
}
