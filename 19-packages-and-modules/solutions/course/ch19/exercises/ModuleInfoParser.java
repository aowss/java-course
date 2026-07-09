package course.ch19.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModuleInfoParser {

    public record ModuleInfo(String moduleName, List<String> requires, List<String> exports) {}

    private static final Pattern MODULE_PATTERN = Pattern.compile("module\\s+(\\S+)\\s*\\{");
    private static final Pattern REQUIRES_PATTERN = Pattern.compile("requires\\s+(?:transitive\\s+)?(\\S+)\\s*;");
    private static final Pattern EXPORTS_PATTERN = Pattern.compile("exports\\s+(\\S+)\\s*;");

    public static ModuleInfo parse(String source) {
        if (source == null) {
            throw new IllegalArgumentException("Source must not be null");
        }

        Matcher moduleMatcher = MODULE_PATTERN.matcher(source);
        if (!moduleMatcher.find()) {
            throw new IllegalArgumentException("No valid module declaration found");
        }
        String moduleName = moduleMatcher.group(1);

        List<String> requires = new ArrayList<>();
        Matcher reqMatcher = REQUIRES_PATTERN.matcher(source);
        while (reqMatcher.find()) {
            requires.add(reqMatcher.group(1));
        }

        List<String> exports = new ArrayList<>();
        Matcher expMatcher = EXPORTS_PATTERN.matcher(source);
        while (expMatcher.find()) {
            exports.add(expMatcher.group(1));
        }

        return new ModuleInfo(moduleName, List.copyOf(requires), List.copyOf(exports));
    }
}
