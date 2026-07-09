package course.ch19.examples;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class VirtualThreadBasicsTest {

    @Test
    void startAndJoinRunsTask() throws InterruptedException {
        AtomicBoolean ran = new AtomicBoolean(false);
        Thread vt = VirtualThreadBasics.startAndJoin(() -> ran.set(true));
        assertTrue(ran.get());
        assertTrue(vt.isVirtual());
    }

    @Test
    void startAndJoinReturnsTerminatedThread() throws InterruptedException {
        Thread vt = VirtualThreadBasics.startAndJoin(() -> {});
        assertEquals(Thread.State.TERMINATED, vt.getState());
    }

    @Test
    void startVirtualThreadIsVirtual() throws InterruptedException {
        Thread vt = VirtualThreadBasics.startVirtualThread(() -> {});
        assertTrue(vt.isVirtual());
    }

    @Test
    void createNamedFactoryProducesVirtualThreads() throws InterruptedException {
        ThreadFactory factory = VirtualThreadBasics.createNamedFactory("test-");
        Thread t = factory.newThread(() -> {});
        t.start();
        t.join();
        assertTrue(t.isVirtual());
        assertTrue(t.getName().startsWith("test-"));
    }

    @Test
    void virtualThreadsAreDaemon() throws InterruptedException {
        assertTrue(VirtualThreadBasics.virtualThreadsAreDaemon());
    }

    @Test
    void factoryCreatesSequentiallyNamedThreads() throws InterruptedException {
        ThreadFactory factory = VirtualThreadBasics.createNamedFactory("seq-");
        Thread t0 = factory.newThread(() -> {});
        Thread t1 = factory.newThread(() -> {});
        assertTrue(t0.getName().startsWith("seq-"));
        assertTrue(t1.getName().startsWith("seq-"));
    }
}
