package course.ch19.examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(30)
class VirtualVsPlatformThreadsTest {

    @Test
    void runVirtualThreadsCompletesWithinReasonableTime() {
        long elapsed = VirtualVsPlatformThreads.runVirtualThreads(100, 10);
        assertTrue(elapsed < 10_000, "100 virtual threads sleeping 10ms should finish quickly");
    }

    @Test
    void runPlatformThreadsCompletes() {
        long elapsed = VirtualVsPlatformThreads.runPlatformThreads(10, 10);
        assertTrue(elapsed < 10_000);
    }

    @Test
    void runWithExecutorCompletesAllTasks() {
        int completed = VirtualVsPlatformThreads.runWithExecutor(100);
        assertEquals(100, completed);
    }

    @Test
    void runWithExecutorSingleTask() {
        int completed = VirtualVsPlatformThreads.runWithExecutor(1);
        assertEquals(1, completed);
    }

    @Test
    void runVirtualThreadsScalesToThousands() {
        long elapsed = VirtualVsPlatformThreads.runVirtualThreads(10_000, 1);
        assertTrue(elapsed < 30_000, "10k virtual threads should scale well");
    }
}
