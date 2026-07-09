package course.ch30.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import course.ch30.model.Task;
import course.ch30.model.TaskStatus;

/**
 * Persistence layer for tasks.
 */
public interface TaskRepository {

    /**
     * Saves a task (insert or update).
     *
     * @param task the task to save
     */
    void save(Task task);

    /**
     * Finds a task by its unique identifier.
     *
     * @param id the task ID
     * @return the task, if present
     */
    Optional<Task> findById(UUID id);

    /**
     * Returns all stored tasks.
     *
     * @return an unmodifiable list of tasks
     */
    List<Task> findAll();

    /**
     * Returns tasks matching the given status.
     *
     * @param status the status to filter by
     * @return matching tasks
     */
    List<Task> findByStatus(TaskStatus status);

    /**
     * Deletes a task by ID.
     *
     * @param id the task ID
     * @return {@code true} if a task was deleted
     */
    boolean deleteById(UUID id);
}
