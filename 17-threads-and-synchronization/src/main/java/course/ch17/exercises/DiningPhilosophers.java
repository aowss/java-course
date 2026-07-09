package course.ch17.exercises;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Exercise 3 (Challenge): Solve the dining philosophers problem without deadlock or starvation.
 *
 * <p>Use {@link ReentrantLock} with {@link ReentrantLock#tryLock()} to prevent deadlock.
 * Each philosopher must eat the requested number of rounds, and no philosopher should starve
 * (every philosopher must eat at least once).
 */
public class DiningPhilosophers {

    private final int numberOfPhilosophers;
    private final ReentrantLock[] forks;
    private final int[] mealsEaten;

    /**
     * A philosopher who alternates between thinking and eating.
     *
     * <p>To eat, a philosopher must acquire both the left and right fork (locks).
     */
    public class Philosopher implements Runnable {

        private final int id;
        private int rounds;

        /**
         * Creates a philosopher.
         *
         * @param id     the philosopher's index (0-based)
         * @param rounds the number of meals this philosopher must eat
         */
        public Philosopher(int id, int rounds) {
            this.id = id;
            this.rounds = rounds;
        }

        @Override
        public void run() {
            // TODO: implement — acquire forks using tryLock(), eat, release forks
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * Creates the dining table.
     *
     * @param numberOfPhilosophers the number of philosophers (and forks)
     */
    public DiningPhilosophers(int numberOfPhilosophers) {
        this.numberOfPhilosophers = numberOfPhilosophers;
        this.forks = new ReentrantLock[numberOfPhilosophers];
        this.mealsEaten = new int[numberOfPhilosophers];
        for (int i = 0; i < numberOfPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    /**
     * Starts all philosophers and waits for each to eat the given number of rounds.
     *
     * @param rounds the number of meals each philosopher must eat
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public void startDining(int rounds) throws InterruptedException {
        // TODO: create and start philosopher threads, then join them
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the total number of meals eaten across all philosophers.
     *
     * @return total meals eaten
     */
    public int getTotalMealsEaten() {
        // TODO: sum mealsEaten array
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
