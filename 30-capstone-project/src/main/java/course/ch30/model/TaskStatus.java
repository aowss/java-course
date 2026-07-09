package course.ch30.model;

/**
 * Lifecycle status of a task.
 */
public enum TaskStatus {
    /** Task has been created but not started. */
    PENDING,
    /** Task is actively being worked on. */
    IN_PROGRESS,
    /** Task has been finished. */
    COMPLETED,
    /** Task was cancelled and will not be completed. */
    CANCELLED
}
