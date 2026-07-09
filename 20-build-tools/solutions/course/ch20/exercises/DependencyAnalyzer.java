package course.ch20.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DependencyAnalyzer {

    public record Dependency(String groupId, String artifactId, String version, String scope) {}

    private static final Pattern DEP_BLOCK = Pattern.compile(
            "<dependency>\\s*(.*?)\\s*</dependency>", Pattern.DOTALL);
    private static final Pattern GROUP_ID = Pattern.compile("<groupId>\\s*(.*?)\\s*</groupId>");
    private static final Pattern ARTIFACT_ID = Pattern.compile("<artifactId>\\s*(.*?)\\s*</artifactId>");
    private static final Pattern VERSION = Pattern.compile("<version>\\s*(.*?)\\s*</version>");
    private static final Pattern SCOPE = Pattern.compile("<scope>\\s*(.*?)\\s*</scope>");

    public static List<Dependency> parse(String pomXml) {
        if (pomXml == null) {
            throw new IllegalArgumentException("POM XML must not be null");
        }

        List<Dependency> result = new ArrayList<>();
        Matcher depMatcher = DEP_BLOCK.matcher(pomXml);

        while (depMatcher.find()) {
            String block = depMatcher.group(1);
            String groupId = extractRequired(GROUP_ID, block, "groupId");
            String artifactId = extractRequired(ARTIFACT_ID, block, "artifactId");
            String version = extractRequired(VERSION, block, "version");
            String scope = extractOptional(SCOPE, block, "compile");
            result.add(new Dependency(groupId, artifactId, version, scope));
        }

        return result;
    }

    private static String extractRequired(Pattern pattern, String block, String fieldName) {
        Matcher m = pattern.matcher(block);
        if (!m.find()) {
            throw new IllegalArgumentException("Missing <" + fieldName + "> in dependency block");
        }
        return m.group(1);
    }

    private static String extractOptional(Pattern pattern, String block, String defaultValue) {
        Matcher m = pattern.matcher(block);
        return m.find() ? m.group(1) : defaultValue;
    }
}
