package course.ch04.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterTest {

    @BeforeEach
    void resetTotal() {
        Counter.resetTotalCount();
    }

    @Test
    void incrementIncreasesCount() {
        var counter = new Counter();
        counter.increment();
        counter.increment();
        assertEquals(2, counter.getCount());
    }

    @Test
    void totalCountTracksAllInstances() {
        var c1 = new Counter();
        var c2 = new Counter();
        c1.increment();
        c1.increment();
        c2.increment();
        assertEquals(3, Counter.getTotalCount());
    }

    @Test
    void instanceCountsAreIndependent() {
        var c1 = new Counter();
        var c2 = new Counter();
        c1.increment();
        c1.increment();
        c2.increment();
        assertEquals(2, c1.getCount());
        assertEquals(1, c2.getCount());
    }

    @Test
    void resetTotalCountClearsStaticCounter() {
        var counter = new Counter();
        counter.increment();
        Counter.resetTotalCount();
        assertEquals(0, Counter.getTotalCount());
    }
}
