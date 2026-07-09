package course.ch20.exercises;

import java.util.List;

/**
 * Exercise 1 (Guided): Parse a simplified POM XML string.
 *
 * <p>Given a string containing a simplified POM XML with a
 * {@code <dependencies>} section, extract each dependency as a
 * {@link Dependency} record.
 *
 * <p>Example input:
 * <pre>{@code
 * <dependencies>
 *     <dependency>
 *         <groupId>org.junit.jupiter</groupId>
 *         <artifactId>junit-jupiter</artifactId>
 *         <version>5.11.0</version>
 *         <scope>test</scope>
 *     </dependency>
 * </dependencies>
 * }</pre>
 *
 * <p>If a {@code <scope>} element is absent, default to {@code "compile"}.
 */
public class DependencyAnalyzer {

    /**
     * Represents a Maven dependency.
     *
     * @param groupId    the group ID
     * @param artifactId the artifact ID
     * @param version    the version
     * @param scope      the dependency scope (compile, test, provided, runtime)
     */
    public record Dependency(String groupId, String artifactId, String version, String scope) {}

    /**
     * Parses a simplified POM XML string and extracts dependencies.
     *
     * @param pomXml the XML string containing a {@code <dependencies>} block
     * @return a list of parsed dependencies
     * @throws IllegalArgumentException if pomXml is null
     */
    public static List<Dependency> parse(String pomXml) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
