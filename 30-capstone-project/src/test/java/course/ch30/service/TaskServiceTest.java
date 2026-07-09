package course.ch30.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import course.ch30.model.Task;
import course.ch30.model.TaskStatus;
import course.ch30.repository.TaskRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    private InMemoryTaskRepository repository;
    private TaskService service;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
        service = new TaskService(repository);
    }

    @Test
    void createTaskPersistsAndReturnsPending() {
        Task task = service.createTask("Buy groceries", "Milk and eggs");
        assertEquals("Buy groceries", task.title());
        assertEquals("Milk and eggs", task.description());
        assertEquals(TaskStatus.PENDING, task.status());
        assertEquals(1, service.taskCount());
    }

    @Test
    void listTasksReturnsAll() {
        service.createTask("Task 1", "");
        service.createTask("Task 2", "");
        assertEquals(2, service.listTasks().size());
    }

    @Test
    void listByStatusFilters() {
        Task t1 = service.createTask("Pending task", "");
        Task t2 = service.createTask("Another", "");
        service.updateStatus(t2.id(), TaskStatus.COMPLETED);

        assertEquals(1, service.listByStatus(TaskStatus.PENDING).size());
        assertEquals(1, service.listByStatus(TaskStatus.COMPLETED).size());
        assertEquals(t1.id(), service.listByStatus(TaskStatus.PENDING).getFirst().id());
    }

    @Test
    void getTaskFindsById() {
        Task created = service.createTask("Find me", "");
        Optional<Task> found = service.getTask(created.id());
        assertTrue(found.isPresent());
        assertEquals(created, found.orElseThrow());
    }

    @Test
    void updateStatusChangesTask() {
        Task task = service.createTask("Update me", "");
        Task updated = service.updateStatus(task.id(), TaskStatus.IN_PROGRESS);
        assertEquals(TaskStatus.IN_PROGRESS, updated.status());
        assertEquals(TaskStatus.IN_PROGRESS,
                service.getTask(task.id()).orElseThrow().status());
    }

    @Test
    void updateStatusThrowsForMissingTask() {
        assertThrows(IllegalArgumentException.class,
                () -> service.updateStatus(UUID.randomUUID(), TaskStatus.COMPLETED));
    }

    @Test
    void deleteTaskRemovesFromRepository() {
        Task task = service.createTask("Delete me", "");
        assertTrue(service.deleteTask(task.id()));
        assertFalse(service.getTask(task.id()).isPresent());
    }

    @Test
    void deleteNonexistentReturnsFalse() {
        assertFalse(service.deleteTask(UUID.randomUUID()));
    }

    /**
     * In-memory repository for unit testing the service layer.
     */
    static final class InMemoryTaskRepository implements TaskRepository {

        private final Map<UUID, Task> tasks = new HashMap<>();

        @Override
        public void save(Task task) {
            tasks.put(task.id(), task);
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
            List<Task> result = new ArrayList<>();
            for (Task task : tasks.values()) {
                if (task.status() == status) {
                    result.add(task);
                }
            }
            return result;
        }

        @Override
        public boolean deleteById(UUID id) {
            return tasks.remove(id) != null;
        }
    }
}
