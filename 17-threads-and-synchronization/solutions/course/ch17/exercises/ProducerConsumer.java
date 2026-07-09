package course.ch17.exercises;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer<T> {

    private final Queue<T> buffer = new LinkedList<>();
    private final int capacity;

    public ProducerConsumer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive, was: " + capacity);
        }
        this.capacity = capacity;
    }

    public synchronized void produce(T item) throws InterruptedException {
        while (buffer.size() == capacity) {
            wait();
        }
        buffer.add(item);
        notifyAll();
    }

    public synchronized T consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        T item = buffer.poll();
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return buffer.size();
    }
}
