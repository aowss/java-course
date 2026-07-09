package course.ch04.examples;

/**
 * Demonstrates static (class-level) vs. instance (object-level) fields.
 *
 * <p>Each {@code Counter} tracks its own count, while a shared static field
 * tracks the total number of increments across all instances.
 */
public class Counter {

    private static int totalCount;

    private int count;

    public void increment() {
        count++;
        totalCount++;
    }

    public int getCount() {
        return count;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static void resetTotalCount() {
        totalCount = 0;
    }

    @Override
    public String toString() {
        return "Counter{count=%d, totalCount=%d}".formatted(count, totalCount);
    }

    public static void main(String[] args) {
        var c1 = new Counter();
        var c2 = new Counter();

        c1.increment();
        c1.increment();
        c2.increment();

        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("Total increments across all counters: " + Counter.getTotalCount());
    }
}
