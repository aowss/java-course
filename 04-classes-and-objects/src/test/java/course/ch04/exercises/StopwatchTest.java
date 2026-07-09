package course.ch04.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StopwatchTest {

    @Test
    void initialElapsedIsZero() {
        var sw = new Stopwatch();
        assertEquals(0, sw.elapsedMillis());
    }

    @Test
    void isRunningAfterStart() {
        var sw = new Stopwatch();
        sw.start();
        assertTrue(sw.isRunning());
    }

    @Test
    void isNotRunningAfterStop() {
        var sw = new Stopwatch();
        sw.start();
        sw.stop();
        assertFalse(sw.isRunning());
    }

    @Test
    void elapsedTimeAfterSleep() throws InterruptedException {
        var sw = new Stopwatch();
        sw.start();
        Thread.sleep(50);
        sw.stop();
        assertTrue(sw.elapsedMillis() >= 30,
                "Expected at least 30ms, got " + sw.elapsedMillis());
    }

    @Test
    void multipleStartStopAccumulates() throws InterruptedException {
        var sw = new Stopwatch();
        sw.start();
        Thread.sleep(50);
        sw.stop();
        var first = sw.elapsedMillis();
        sw.start();
        Thread.sleep(50);
        sw.stop();
        assertTrue(sw.elapsedMillis() > first,
                "Time should accumulate across start/stop cycles");
    }

    @Test
    void startWhileRunningThrows() {
        var sw = new Stopwatch();
        sw.start();
        assertThrows(IllegalStateException.class, sw::start);
    }

    @Test
    void stopWhileNotRunningThrows() {
        var sw = new Stopwatch();
        assertThrows(IllegalStateException.class, sw::stop);
    }

    @Test
    void resetClearsElapsedTime() throws InterruptedException {
        var sw = new Stopwatch();
        sw.start();
        Thread.sleep(50);
        sw.stop();
        sw.reset();
        assertEquals(0, sw.elapsedMillis());
        assertFalse(sw.isRunning());
    }

    @Test
    void resetStopsRunningWatch() {
        var sw = new Stopwatch();
        sw.start();
        sw.reset();
        assertFalse(sw.isRunning());
        assertEquals(0, sw.elapsedMillis());
    }
}
