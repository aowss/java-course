package course.ch16.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    private final int numberOfPhilosophers;
    private final ReentrantLock[] forks;
    private final int[] mealsEaten;

    public class Philosopher implements Runnable {

        private final int id;
        private int rounds;

        public Philosopher(int id, int rounds) {
            this.id = id;
            this.rounds = rounds;
        }

        @Override
        public void run() {
            ReentrantLock leftFork = forks[id];
            ReentrantLock rightFork = forks[(id + 1) % numberOfPhilosophers];

            while (rounds > 0) {
                if (leftFork.tryLock()) {
                    try {
                        if (rightFork.tryLock()) {
                            try {
                                mealsEaten[id]++;
                                rounds--;
                            } finally {
                                rightFork.unlock();
                            }
                        }
                    } finally {
                        leftFork.unlock();
                    }
                }
                Thread.yield();
            }
        }
    }

    public DiningPhilosophers(int numberOfPhilosophers) {
        this.numberOfPhilosophers = numberOfPhilosophers;
        this.forks = new ReentrantLock[numberOfPhilosophers];
        this.mealsEaten = new int[numberOfPhilosophers];
        for (int i = 0; i < numberOfPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    public void startDining(int rounds) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfPhilosophers; i++) {
            Thread t = new Thread(new Philosopher(i, rounds), "Philosopher-" + i);
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    public int getTotalMealsEaten() {
        int total = 0;
        for (int meals : mealsEaten) {
            total += meals;
        }
        return total;
    }
}
