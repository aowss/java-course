package course.ch02.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayStatsTest {

    @Test
    void minOfSortedArray() {
        assertEquals(1, ArrayStats.min(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void minOfUnsortedArray() {
        assertEquals(-3, ArrayStats.min(new int[]{5, -3, 2, 8, 1}));
    }

    @Test
    void minOfSingleElement() {
        assertEquals(42, ArrayStats.min(new int[]{42}));
    }

    @Test
    void maxOfArray() {
        assertEquals(8, ArrayStats.max(new int[]{5, -3, 2, 8, 1}));
    }

    @Test
    void maxOfNegatives() {
        assertEquals(-1, ArrayStats.max(new int[]{-5, -3, -1, -8}));
    }

    @Test
    void sumOfArray() {
        assertEquals(15L, ArrayStats.sum(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void sumHandlesLargeValues() {
        assertEquals(4_294_967_294L,
                ArrayStats.sum(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}));
    }

    @Test
    void averageOfArray() {
        assertEquals(3.0, ArrayStats.average(new int[]{1, 2, 3, 4, 5}), 0.001);
    }

    @Test
    void averageOfSingleElement() {
        assertEquals(7.0, ArrayStats.average(new int[]{7}), 0.001);
    }

    @Test
    void minThrowsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.min(null));
    }

    @Test
    void maxThrowsOnEmpty() {
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.max(new int[]{}));
    }

    @Test
    void sumThrowsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.sum(null));
    }

    @Test
    void averageThrowsOnEmpty() {
        assertThrows(IllegalArgumentException.class, () -> ArrayStats.average(new int[]{}));
    }
}
