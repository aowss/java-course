package course.ch16.examples;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class ThreadCreationTest {

    @Test
    void runWithSubclassCreatesCorrectNumberOfThreads() throws InterruptedException {
        List<String> names = ThreadCreation.runWithSubclass(3);
        assertEquals(3, names.size());
        assertTrue(names.stream().allMatch(n -> n.startsWith("Subclass-")));
    }

    @Test
    void runWithRunnableCreatesCorrectNumberOfThreads() throws InterruptedException {
        List<String> names = ThreadCreation.runWithRunnable(4);
        assertEquals(4, names.size());
        assertTrue(names.stream().allMatch(n -> n.startsWith("Runnable-")));
    }

    @Test
    void runWithPlatformThreadCreatesCorrectNumberOfThreads() throws InterruptedException {
        List<String> names = ThreadCreation.runWithPlatformThread(2);
        assertEquals(2, names.size());
        assertTrue(names.stream().allMatch(n -> n.startsWith("Platform-")));
    }

    @Test
    void runWithZeroThreadsReturnsEmptyList() throws InterruptedException {
        assertEquals(List.of(), ThreadCreation.runWithSubclass(0));
        assertEquals(List.of(), ThreadCreation.runWithRunnable(0));
        assertEquals(List.of(), ThreadCreation.runWithPlatformThread(0));
    }
}
