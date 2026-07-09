package course.ch13.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamExercisesTest {

    @Test
    void evensOnly() {
        assertEquals(List.of(-2, 4, 6), StreamExercises.evensOnly(List.of(-2, 3, 4, -1, 5, 6)));
    }

    @Test
    void squareAll() {
        assertEquals(List.of(1, 4, 9), StreamExercises.squareAll(List.of(1, 2, 3)));
    }

    @Test
    void sumPositives() {
        assertEquals(18, StreamExercises.sumPositives(List.of(-2, 3, 4, -1, 5, 6)));
    }

    @Test
    void sumPositivesEmpty() {
        assertEquals(0, StreamExercises.sumPositives(List.of(-1, -2)));
    }

    @Test
    void average() {
        assertEquals(2.5, StreamExercises.average(List.of(1, 2, 3, 4)), 0.001);
    }

    @Test
    void averageEmpty() {
        assertEquals(0, StreamExercises.average(List.of()));
    }
}
