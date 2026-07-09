package course.ch10.examples;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Demonstrates {@link Queue} and {@link Deque} operations.
 *
 * <p>Covers FIFO queues with {@link LinkedList} and double-ended queues
 * with {@link ArrayDeque}.
 */
public class QueueDemo {

    /**
     * Processes a queue by polling elements one at a time.
     *
     * @param queue the queue to process
     * @param <T>   the element type
     * @return a string of all elements joined by " → "
     */
    public static <T> String processQueue(Queue<T> queue) {
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            if (!sb.isEmpty()) {
                sb.append(" \u2192 ");
            }
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * Reverses elements using a deque as a stack (LIFO).
     *
     * @param items the items to reverse
     * @param <T>   the element type
     * @return a new list with elements in reverse order
     */
    public static <T> List<T> reverseWithDeque(List<T> items) {
        Deque<T> stack = new ArrayDeque<>();
        for (T item : items) {
            stack.push(item);
        }
        List<T> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");
        System.out.println("Queue processing: " + processQueue(queue));

        var items = List.of("A", "B", "C", "D");
        System.out.println("Original: " + items);
        System.out.println("Reversed: " + reverseWithDeque(items));
    }
}
