package course.ch01.examples;

/**
 * Demonstrates how to query JVM and system properties at runtime.
 *
 * <p>Run this to see what the JVM knows about itself and the host OS.
 */
public class JvmInfo {

    public static void main(String[] args) {
        System.out.println("Java version:    " + System.getProperty("java.version"));
        System.out.println("Java vendor:     " + System.getProperty("java.vendor"));
        System.out.println("JVM name:        " + System.getProperty("java.vm.name"));
        System.out.println("OS name:         " + System.getProperty("os.name"));
        System.out.println("OS architecture: " + System.getProperty("os.arch"));
        System.out.println("User directory:  " + System.getProperty("user.dir"));
    }
}
