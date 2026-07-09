package course.ch17.exercises;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Timeout(10)
class SafeCounterTest {

    @Test
    void incrementOnce() {
        var counter = new SafeCounter();
        counter.increment();
        assertEquals(1, counter.get());
    }

    @Test
    void decrementOnce() {
        var counter = new SafeCounter();
        counter.increment();
        counter.decrement();
        assertEquals(0, counter.get());
    }

    @Test
    void concurrentIncrements() throws InterruptedException {
        var counter = new SafeCounter();
        int threadCount = 10;
        int incrementsPerThread = 10_000;
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    counter.increment();
                }
            });
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        assertEquals(threadCount * incrementsPerThread, counter.get());
    }

    @Test
    void concurrentIncrementsAndDecrements() throws InterruptedException {
        var counter = new SafeCounter();
        int count = 10_000;

        Thread incThread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                counter.increment();
            }
        });
        Thread decThread = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                counter.decrement();
            }
        });

        incThread.start();
        decThread.start();
        incThread.join();
        decThread.join();

        assertEquals(0, counter.get());
    }
}
