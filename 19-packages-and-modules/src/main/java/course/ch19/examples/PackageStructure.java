package course.ch19.examples;

import java.util.List;
import java.util.Map;

/**
 * Demonstrates proper Java package naming and organization conventions.
 *
 * <p>Java packages follow a reverse-domain naming convention and organize
 * classes by functional area. This class provides utilities to illustrate
 * and validate package naming rules.
 */
public class PackageStructure {

    /**
     * Standard top-level package categories used in a typical Java project.
     */
    public static final List<String> STANDARD_LAYERS = List.of(
            "model", "service", "repository", "controller", "util", "config"
    );

    /**
     * Returns a fully qualified package name following reverse-domain convention.
     *
     * @param domain the organization domain (e.g., "example.com")
     * @param project the project name
     * @param layer the functional layer (e.g., "model", "service")
     * @return the fully qualified package name
     * @throws IllegalArgumentException if any argument is null or blank
     */
    public static String buildPackageName(String domain, String project, String layer) {
        if (domain == null || domain.isBlank()) {
            throw new IllegalArgumentException("Domain must not be null or blank");
        }
        if (project == null || project.isBlank()) {
            throw new IllegalArgumentException("Project must not be null or blank");
        }
        if (layer == null || layer.isBlank()) {
            throw new IllegalArgumentException("Layer must not be null or blank");
        }

        String[] domainParts = domain.split("\\.");
        var sb = new StringBuilder();
        for (int i = domainParts.length - 1; i >= 0; i--) {
            sb.append(domainParts[i].toLowerCase());
            sb.append('.');
        }
        sb.append(project.toLowerCase());
        sb.append('.');
        sb.append(layer.toLowerCase());
        return sb.toString();
    }

    /**
     * Checks whether a package name follows Java naming conventions.
     *
     * <p>Valid package names consist of lowercase identifiers separated by dots.
     * Each segment must start with a letter or underscore and contain only
     * letters, digits, and underscores.
     *
     * @param packageName the package name to validate
     * @return {@code true} if the name is valid
     */
    public static boolean isValidPackageName(String packageName) {
        if (packageName == null || packageName.isEmpty()) {
            return false;
        }
        String[] segments = packageName.split("\\.", -1);
        for (String segment : segments) {
            if (segment.isEmpty()) {
                return false;
            }
            if (!Character.isJavaIdentifierStart(segment.charAt(0))) {
                return false;
            }
            for (int i = 1; i < segment.length(); i++) {
                if (!Character.isJavaIdentifierPart(segment.charAt(i))) {
                    return false;
                }
            }
            if (!segment.equals(segment.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Suggests a package layout for a given project based on standard layers.
     *
     * @param basePackage the base package (e.g., "com.example.myapp")
     * @return a map of layer name to full package path
     */
    public static Map<String, String> suggestLayout(String basePackage) {
        if (basePackage == null || basePackage.isBlank()) {
            throw new IllegalArgumentException("Base package must not be null or blank");
        }
        var layout = new java.util.LinkedHashMap<String, String>();
        for (String layer : STANDARD_LAYERS) {
            layout.put(layer, basePackage + "." + layer);
        }
        return layout;
    }

    public static void main(String[] args) {
        System.out.println("=== Package Structure Demo ===");
        System.out.println();

        String pkg = buildPackageName("example.com", "onlinestore", "model");
        System.out.println("Built package name: " + pkg);
        System.out.println("Valid? " + isValidPackageName(pkg));
        System.out.println();

        System.out.println("Suggested layout for 'com.example.onlinestore':");
        suggestLayout("com.example.onlinestore")
                .forEach((layer, fullPkg) -> System.out.println("  " + layer + " → " + fullPkg));
    }
}
