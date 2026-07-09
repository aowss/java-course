package course.ch11.exercises;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class UniqueQueue<T> {

    private final Queue<T> queue = new ArrayDeque<>();
    private final Set<T> seen = new HashSet<>();

    public UniqueQueue() {
    }

    public boolean offer(T element) {
        if (seen.contains(element)) {
            return false;
        }
        seen.add(element);
        queue.offer(element);
        return true;
    }

    public T poll() {
        T element = queue.poll();
        if (element != null) {
            seen.remove(element);
        }
        return element;
    }

    public T peek() {
        return queue.peek();
    }

    public int size() {
        return queue.size();
    }

    public boolean contains(T element) {
        return seen.contains(element);
    }
}
