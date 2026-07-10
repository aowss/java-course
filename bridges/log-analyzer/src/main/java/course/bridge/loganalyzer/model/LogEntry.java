package course.bridge.loganalyzer.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A single parsed log line.
 */
public record LogEntry(LocalDateTime timestamp, LogLevel level, String message) {

    public LogEntry {
        Objects.requireNonNull(timestamp, "timestamp must not be null");
        Objects.requireNonNull(level, "level must not be null");
        Objects.requireNonNull(message, "message must not be null");
    }
}
