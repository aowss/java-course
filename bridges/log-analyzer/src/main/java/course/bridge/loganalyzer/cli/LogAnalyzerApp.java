package course.bridge.loganalyzer.cli;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import course.bridge.loganalyzer.analyze.LogAnalyzer;
import course.bridge.loganalyzer.model.LogEntry;
import course.bridge.loganalyzer.model.LogLevel;

/**
 * Prints log summaries for a file path passed on the command line.
 */
public final class LogAnalyzerApp {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: LogAnalyzerApp <log-file>");
            System.exit(1);
        }

        Path file = Path.of(args[0]);
        LogAnalyzer analyzer = new LogAnalyzer();

        Map<LogLevel, Long> counts = analyzer.countByLevel(file);
        System.out.println("Counts by level:");
        counts.forEach((level, count) -> System.out.println("  " + level + ": " + count));

        List<LogEntry> errors = analyzer.errorsOnly(file);
        System.out.println("\nErrors (" + errors.size() + "):");
        errors.forEach(entry -> System.out.println("  " + entry.timestamp() + " " + entry.message()));

        List<String> top = analyzer.topMessages(file, 3);
        System.out.println("\nTop messages:");
        top.forEach(message -> System.out.println("  " + message));
    }
}
