package course.ch30.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import course.ch30.model.Task;
import course.ch30.model.TaskStatus;

/**
 * File-backed task repository that persists tasks as pipe-delimited lines.
 *
 * <p>Format per line: {@code id|title|description|status|createdAt}
 *
 * <p>Title and description are escaped by replacing {@code |} with {@code \|}.
 */
public final class FileTaskRepository implements TaskRepository {

    private static final String DELIMITER = "|";
    private static final String ESCAPED_DELIMITER = "\\|";

    private final Path filePath;
    private final Map<UUID, Task> tasks = new LinkedHashMap<>();

    /**
     * Creates a repository backed by the given file.
     *
     * @param filePath the path to the data file
     */
    public FileTaskRepository(Path filePath) {
        this.filePath = filePath;
        loadFromFile();
    }

    @Override
    public void save(Task task) {
        tasks.put(task.id(), task);
        persistToFile();
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> findAll() {
        return List.copyOf(tasks.values());
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        return tasks.values().stream()
                .filter(task -> task.status() == status)
                .toList();
    }

    @Override
    public boolean deleteById(UUID id) {
        Task removed = tasks.remove(id);
        if (removed != null) {
            persistToFile();
            return true;
        }
        return false;
    }

    private void loadFromFile() {
        if (!Files.exists(filePath)) {
            return;
        }
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.isBlank()) {
                    continue;
                }
                Task task = parseLine(line);
                tasks.put(task.id(), task);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load tasks from " + filePath, e);
        }
    }

    private void persistToFile() {
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            List<String> lines = new ArrayList<>();
            for (Task task : tasks.values()) {
                lines.add(formatLine(task));
            }
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to persist tasks to " + filePath, e);
        }
    }

    static String formatLine(Task task) {
        return task.id() + DELIMITER
                + escape(task.title()) + DELIMITER
                + escape(task.description()) + DELIMITER
                + task.status().name() + DELIMITER
                + task.createdAt().toString();
    }

    static Task parseLine(String line) {
        String[] parts = splitEscaped(line);
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid task line: " + line);
        }
        return new Task(
                UUID.fromString(parts[0]),
                unescape(parts[1]),
                unescape(parts[2]),
                TaskStatus.valueOf(parts[3]),
                Instant.parse(parts[4])
        );
    }

    private static String[] splitEscaped(String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean escaping = false;

        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (escaping) {
                current.append(ch);
                escaping = false;
            } else if (ch == '\\' && i + 1 < line.length() && line.charAt(i + 1) == '|') {
                escaping = true;
            } else if (ch == '|') {
                parts.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(ch);
            }
        }
        parts.add(current.toString());
        return parts.toArray(new String[0]);
    }

    private static String escape(String value) {
        return value.replace(DELIMITER, ESCAPED_DELIMITER);
    }

    private static String unescape(String value) {
        return value.replace(ESCAPED_DELIMITER, DELIMITER);
    }
}
