package course.ch16.exercises;

public class SafeCounter {

    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized void decrement() {
        value--;
    }

    public synchronized int get() {
        return value;
    }
}
