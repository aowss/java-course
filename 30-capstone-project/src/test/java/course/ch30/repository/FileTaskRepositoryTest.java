package course.ch30.repository;

import java.nio.file.Path;
import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import course.ch30.model.Task;
import course.ch30.model.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileTaskRepositoryTest {

    @TempDir
    Path tempDir;

    private FileTaskRepository repository;
    private Path dataFile;

    @BeforeEach
    void setUp() {
        dataFile = tempDir.resolve("tasks.txt");
        repository = new FileTaskRepository(dataFile);
    }

    @Test
    void saveAndFindById() {
        Task task = sampleTask("Write tests");
        repository.save(task);

        assertTrue(repository.findById(task.id()).isPresent());
        assertEquals(task, repository.findById(task.id()).orElseThrow());
    }

    @Test
    void findAllReturnsSavedTasks() {
        Task t1 = sampleTask("Task 1");
        Task t2 = sampleTask("Task 2");
        repository.save(t1);
        repository.save(t2);

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void findByStatusFiltersCorrectly() {
        Task pending = sampleTask("Pending");
        Task completed = new Task(
                UUID.randomUUID(), "Done", "", TaskStatus.COMPLETED, Instant.now()
        );
        repository.save(pending);
        repository.save(completed);

        assertEquals(1, repository.findByStatus(TaskStatus.PENDING).size());
        assertEquals(1, repository.findByStatus(TaskStatus.COMPLETED).size());
    }

    @Test
    void deleteByIdRemovesTask() {
        Task task = sampleTask("To delete");
        repository.save(task);

        assertTrue(repository.deleteById(task.id()));
        assertFalse(repository.findById(task.id()).isPresent());
    }

    @Test
    void deleteNonexistentReturnsFalse() {
        assertFalse(repository.deleteById(UUID.randomUUID()));
    }

    @Test
    void persistsAcrossInstances() {
        Task task = sampleTask("Persist me");
        repository.save(task);

        FileTaskRepository reloaded = new FileTaskRepository(dataFile);
        assertEquals(task, reloaded.findById(task.id()).orElseThrow());
    }

    @Test
    void handlesPipeInTitle() {
        Task task = new Task(
                UUID.randomUUID(), "a|b", "desc", TaskStatus.PENDING, Instant.parse("2025-01-01T00:00:00Z")
        );
        repository.save(task);

        FileTaskRepository reloaded = new FileTaskRepository(dataFile);
        assertEquals("a|b", reloaded.findById(task.id()).orElseThrow().title());
    }

    @Test
    void formatAndParseRoundTrip() {
        Task task = new Task(
                UUID.randomUUID(), "Test", "Description", TaskStatus.IN_PROGRESS,
                Instant.parse("2025-06-15T12:00:00Z")
        );
        String line = FileTaskRepository.formatLine(task);
        Task parsed = FileTaskRepository.parseLine(line);
        assertEquals(task, parsed);
    }

    private static Task sampleTask(String title) {
        return new Task(
                UUID.randomUUID(), title, "", TaskStatus.PENDING, Instant.parse("2025-01-01T00:00:00Z")
        );
    }
}
