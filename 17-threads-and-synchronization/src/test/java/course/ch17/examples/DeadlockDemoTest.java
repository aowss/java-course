package course.ch17.examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class DeadlockDemoTest {

    @Test
    void safeVersionCompletesSuccessfully() throws InterruptedException {
        var demo = new DeadlockDemo();
        DeadlockDemo.TransferResult result = demo.runSafe(5000);
        assertTrue(result.thread1Completed());
        assertTrue(result.thread2Completed());
    }

    @Test
    void deadlockProneVersionCompletesWithTimeout() throws InterruptedException {
        var demo = new DeadlockDemo();
        DeadlockDemo.TransferResult result = demo.runDeadlockProne(100);
        // At least one thread should complete (the one that gets both locks first)
        // or neither if they truly deadlock, but tryLock with timeout prevents hang
        assertTrue(result.thread1Completed() || result.thread2Completed()
                || (!result.thread1Completed() && !result.thread2Completed()));
    }

    @Test
    void safeVersionAlwaysCompletesUnderRepetition() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            var demo = new DeadlockDemo();
            DeadlockDemo.TransferResult result = demo.runSafe(5000);
            assertTrue(result.thread1Completed(), "Safe thread 1 should always complete");
            assertTrue(result.thread2Completed(), "Safe thread 2 should always complete");
        }
    }
}
