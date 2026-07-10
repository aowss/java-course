package course.bridge.loganalyzer.parse;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import course.bridge.loganalyzer.model.LogEntry;
import course.bridge.loganalyzer.model.LogLevel;

/**
 * Parses lines in the format {@code yyyy-MM-ddTHH:mm:ss LEVEL message}.
 */
public final class LogParser {

    private static final Pattern LINE = Pattern.compile(
            "^(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2})\\s+(\\w+)\\s+(.*)$");

    public Optional<LogEntry> parseLine(String line) {
        if (line == null || line.isBlank()) {
            return Optional.empty();
        }
        Matcher matcher = LINE.matcher(line.trim());
        if (!matcher.matches()) {
            return Optional.empty();
        }
        try {
            LocalDateTime timestamp = LocalDateTime.parse(matcher.group(1));
            LogLevel level = LogLevel.valueOf(matcher.group(2));
            String message = matcher.group(3);
            return Optional.of(new LogEntry(timestamp, level, message));
        } catch (DateTimeParseException | IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
