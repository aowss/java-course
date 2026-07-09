package course.ch30.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import course.ch30.model.Task;
import course.ch30.model.TaskStatus;
import course.ch30.repository.TaskRepository;

/**
 * Business logic for task management.
 */
public final class TaskService {

    private final TaskRepository repository;

    /**
     * Creates a service backed by the given repository.
     *
     * @param repository the task repository
     */
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates and persists a new pending task.
     *
     * @param title       the task title
     * @param description the task description (may be empty)
     * @return the created task
     */
    public Task createTask(String title, String description) {
        Task task = Task.create(title, description);
        repository.save(task);
        return task;
    }

    /**
     * Returns all tasks.
     *
     * @return all stored tasks
     */
    public List<Task> listTasks() {
        return repository.findAll();
    }

    /**
     * Returns tasks filtered by status.
     *
     * @param status the status to filter by
     * @return matching tasks
     */
    public List<Task> listByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }

    /**
     * Finds a task by ID.
     *
     * @param id the task ID
     * @return the task, if found
     */
    public Optional<Task> getTask(UUID id) {
        return repository.findById(id);
    }

    /**
     * Updates a task's status.
     *
     * @param id        the task ID
     * @param newStatus the new status
     * @return the updated task
     * @throws IllegalArgumentException if the task is not found
     */
    public Task updateStatus(UUID id, TaskStatus newStatus) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
        Task updated = task.withStatus(newStatus);
        repository.save(updated);
        return updated;
    }

    /**
     * Deletes a task by ID.
     *
     * @param id the task ID
     * @return {@code true} if the task was deleted
     */
    public boolean deleteTask(UUID id) {
        return repository.deleteById(id);
    }

    /**
     * Returns a count of tasks by status.
     *
     * @return total number of tasks
     */
    public int taskCount() {
        return repository.findAll().size();
    }
}
