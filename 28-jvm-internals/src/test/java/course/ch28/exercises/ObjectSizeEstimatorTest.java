package course.ch28.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectSizeEstimatorTest {

    @Test
    void estimateNullReturnsZero() {
        assertEquals(0, ObjectSizeEstimator.estimate(null));
    }

    @Test
    void estimateInteger() {
        long size = ObjectSizeEstimator.estimate(42);
        assertEquals(ObjectSizeEstimator.OBJECT_HEADER_BYTES + 4, size);
    }

    @Test
    void estimateString() {
        long size = ObjectSizeEstimator.estimateString("hello");
        assertEquals(ObjectSizeEstimator.OBJECT_HEADER_BYTES
                + ObjectSizeEstimator.ARRAY_HEADER_BYTES
                + 10
                + ObjectSizeEstimator.REFERENCE_BYTES, size);
    }

    @Test
    void estimateIntArray() {
        int[] array = new int[100];
        long size = ObjectSizeEstimator.estimatePrimitiveArray(array, 4);
        assertEquals(ObjectSizeEstimator.ARRAY_HEADER_BYTES + 400L, size);
    }

    @Test
    void estimateIntArrayViaEstimate() {
        long size = ObjectSizeEstimator.estimate(new int[50]);
        assertTrue(size >= ObjectSizeEstimator.ARRAY_HEADER_BYTES + 200);
    }
}
