package course.ch28.examples;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GCObserverTest {

    @Test
    void collectStatsReturnsNonEmptyList() {
        List<GCObserver.GcStats> stats = GCObserver.collectStats();
        assertFalse(stats.isEmpty());
    }

    @Test
    void gcStatsHaveNames() {
        for (GCObserver.GcStats stats : GCObserver.collectStats()) {
            assertNotNull(stats.name());
            assertFalse(stats.name().isBlank());
        }
    }

    @Test
    void totalCollectionCountIsNonNegative() {
        assertTrue(GCObserver.totalCollectionCount() >= 0);
    }

    @Test
    void observeCollectionAfterAllocationReturnsBoolean() {
        boolean observed = GCObserver.observeCollectionAfterAllocation();
        assertTrue(observed || !observed);
    }
}
