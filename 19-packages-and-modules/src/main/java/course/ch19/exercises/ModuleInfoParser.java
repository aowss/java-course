package course.ch19.exercises;

import java.util.List;

/**
 * Exercise 2 (Practice): Parse a {@code module-info.java} source string.
 *
 * <p>Given a string containing the text of a {@code module-info.java} file,
 * extract the module name, all {@code requires} directives, and all
 * {@code exports} directives.
 *
 * <p>Example input:
 * <pre>
 * module com.example.app {
 *     requires java.sql;
 *     requires java.logging;
 *     exports com.example.app.api;
 *     exports com.example.app.model;
 * }
 * </pre>
 */
public class ModuleInfoParser {

    /**
     * Holds the parsed contents of a module-info.java file.
     *
     * @param moduleName the module name
     * @param requires   the list of required modules
     * @param exports    the list of exported packages
     */
    public record ModuleInfo(String moduleName, List<String> requires, List<String> exports) {}

    /**
     * Parses a module-info.java source string and extracts its components.
     *
     * @param source the full text of a module-info.java file
     * @return a {@link ModuleInfo} with the extracted data
     * @throws IllegalArgumentException if the source is null or does not contain a valid module declaration
     */
    public static ModuleInfo parse(String source) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
