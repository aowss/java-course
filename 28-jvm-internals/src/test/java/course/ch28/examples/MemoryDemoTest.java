package course.ch28.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MemoryDemoTest {

    @Test
    void captureSnapshotReturnsPositiveValues() {
        MemoryDemo.MemorySnapshot snap = MemoryDemo.captureSnapshot();
        assertTrue(snap.totalHeapBytes() > 0);
        assertTrue(snap.maxHeapBytes() > 0);
        assertTrue(snap.freeHeapBytes() >= 0);
        assertTrue(snap.usedHeapBytes() >= 0);
    }

    @Test
    void usedHeapIsTotalMinusFree() {
        MemoryDemo.MemorySnapshot snap = MemoryDemo.captureSnapshot();
        assertEquals(snap.totalHeapBytes() - snap.freeHeapBytes(), snap.usedHeapBytes());
    }

    @Test
    void allocateReturnsArrayOfRequestedSize() {
        byte[] data = MemoryDemo.allocate(1024);
        assertEquals(1024, data.length);
    }

    @Test
    void gcAndCompareReturnsTwoSnapshots() {
        MemoryDemo.MemorySnapshot[] snapshots = MemoryDemo.gcAndCompare();
        assertEquals(2, snapshots.length);
        assertNotNull(snapshots[0]);
        assertNotNull(snapshots[1]);
    }
}
