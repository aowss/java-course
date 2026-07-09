package course.ch30.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * An immutable task with a unique identifier, title, optional description,
 * status, and creation timestamp.
 *
 * @param id          unique task identifier
 * @param title       short task title (non-blank)
 * @param description optional longer description (may be empty)
 * @param status      current lifecycle status
 * @param createdAt   UTC creation timestamp
 */
public record Task(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        Instant createdAt
) {

    /**
     * Compact constructor validating required fields.
     */
    public Task {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(status, "status must not be null");
        Objects.requireNonNull(createdAt, "createdAt must not be null");
        if (title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
    }

    /**
     * Creates a new pending task with a generated ID and current timestamp.
     *
     * @param title       the task title
     * @param description the task description (may be empty)
     * @return a new pending task
     */
    public static Task create(String title, String description) {
        return new Task(
                UUID.randomUUID(),
                title,
                description == null ? "" : description,
                TaskStatus.PENDING,
                Instant.now()
        );
    }

    /**
     * Returns a copy of this task with an updated status.
     *
     * @param newStatus the new status
     * @return a task with the updated status
     */
    public Task withStatus(TaskStatus newStatus) {
        return new Task(id, title, description, newStatus, createdAt);
    }
}
