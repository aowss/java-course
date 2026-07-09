package course.ch16.exercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(30)
class DiningPhilosophersTest {

    @Test
    void allPhilosophersEat() throws InterruptedException {
        int count = 5;
        int rounds = 3;
        var table = new DiningPhilosophers(count);
        table.startDining(rounds);
        assertEquals(count * rounds, table.getTotalMealsEaten());
    }

    @Test
    void twoPhilosophers() throws InterruptedException {
        var table = new DiningPhilosophers(2);
        table.startDining(5);
        assertEquals(10, table.getTotalMealsEaten());
    }

    @Test
    void singlePhilosopher() throws InterruptedException {
        var table = new DiningPhilosophers(1);
        table.startDining(3);
        assertEquals(3, table.getTotalMealsEaten());
    }

    @Test
    void noStarvation() throws InterruptedException {
        int count = 5;
        int rounds = 10;
        var table = new DiningPhilosophers(count);
        table.startDining(rounds);
        assertTrue(table.getTotalMealsEaten() > 0);
        assertEquals(count * rounds, table.getTotalMealsEaten());
    }
}
