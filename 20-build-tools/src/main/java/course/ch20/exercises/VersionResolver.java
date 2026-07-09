package course.ch20.exercises;

/**
 * Exercise 2 (Practice): Semantic version comparison and range matching.
 *
 * <p>Implement comparison of semantic versions (major.minor.patch) and
 * checking whether a version falls within a range expression.
 *
 * <p>Range format: {@code ">=1.2.0,<2.0.0"} — comma-separated constraints.
 * Each constraint is an operator ({@code >=}, {@code <=}, {@code >}, {@code <}, {@code =})
 * followed by a version string.
 */
public class VersionResolver {

    /**
     * Represents a semantic version with major, minor, and patch components.
     *
     * @param major the major version
     * @param minor the minor version
     * @param patch the patch version
     */
    public record SemVer(int major, int minor, int patch) implements Comparable<SemVer> {

        /**
         * Parses a version string like "1.2.3" into a {@link SemVer}.
         *
         * @param version the version string
         * @return the parsed version
         * @throws IllegalArgumentException if the format is invalid
         */
        public static SemVer parse(String version) {
            // TODO: implement this method
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public int compareTo(SemVer other) {
            // TODO: implement this method
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public String toString() {
            return major + "." + minor + "." + patch;
        }
    }

    /**
     * Checks if a version satisfies a range expression.
     *
     * @param version the version to check
     * @param range   the range expression (e.g., {@code ">=1.2.0,<2.0.0"})
     * @return {@code true} if the version satisfies all constraints in the range
     */
    public static boolean satisfies(SemVer version, String range) {
        // TODO: implement this method
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
