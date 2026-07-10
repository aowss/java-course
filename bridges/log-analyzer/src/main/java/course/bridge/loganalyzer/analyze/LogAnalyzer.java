package course.bridge.loganalyzer.analyze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import course.bridge.loganalyzer.model.LogEntry;
import course.bridge.loganalyzer.model.LogLevel;
import course.bridge.loganalyzer.parse.LogParser;

/**
 * Reads log files and produces stream-based summaries.
 */
public final class LogAnalyzer {

    private final LogParser parser = new LogParser();

    public List<LogEntry> readEntries(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(parser::parseLine)
                    .flatMap(Optional::stream)
                    .toList();
        }
    }

    public Map<LogLevel, Long> countByLevel(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(parser::parseLine)
                    .flatMap(Optional::stream)
                    .collect(Collectors.groupingBy(LogEntry::level, Collectors.counting()));
        }
    }

    public List<LogEntry> errorsOnly(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(parser::parseLine)
                    .flatMap(Optional::stream)
                    .filter(entry -> entry.level() == LogLevel.ERROR)
                    .toList();
        }
    }

    public List<String> topMessages(Path file, int limit) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines
                    .map(parser::parseLine)
                    .flatMap(Optional::stream)
                    .collect(Collectors.groupingBy(LogEntry::message, Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(limit)
                    .map(Map.Entry::getKey)
                    .toList();
        }
    }
}
