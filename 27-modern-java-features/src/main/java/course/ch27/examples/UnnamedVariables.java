package course.ch27.examples;

import java.util.List;
import java.util.Map;

/**
 * Demonstrates unnamed variables ({@code _}) for intentionally unused bindings.
 *
 * <p>Unnamed variables clarify intent: the value exists for side effects or structural
 * requirements, but the program does not read it. They appear in enhanced {@code for}
 * loops, {@code catch} clauses, lambda parameters, and record patterns.
 *
 * <pre>{@code
 * int count = UnnamedVariables.countElements(List.of("a", "b", "c"));
 * // 3
 * }</pre>
 */
public final class UnnamedVariables {

    private UnnamedVariables() {
    }

    /**
     * Counts elements without binding the loop variable.
     *
     * @param items the list to count
     * @return the number of elements
     */
    public static int countElements(List<String> items) {
        int count = 0;
        for (String _ : items) {
            count++;
        }
        return count;
    }

    /**
     * Sums map values, ignoring each key.
     *
     * @param scores a map of identifiers to scores
     * @return the sum of all values
     */
    public static int sumValues(Map<String, Integer> scores) {
        int total = 0;
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String _ = entry.getKey();
            total += entry.getValue();
        }
        return total;
    }

    /**
     * Returns the first component of a pair record, discarding the second.
     *
     * @param pair a pair of integers
     * @return the first element
     */
    public static int firstOf(Pair pair) {
        return switch (pair) {
            case Pair(int first, int _) -> first;
        };
    }

    /**
     * Executes a runnable and returns whether it completed without throwing.
     *
     * @param task the task to run
     * @return {@code true} if no exception was thrown
     */
    public static boolean runQuietly(Runnable task) {
        try {
            task.run();
            return true;
        } catch (RuntimeException _) {
            return false;
        }
    }

    /**
     * A simple pair record for demonstrating record patterns with unnamed variables.
     *
     * @param first  the first value
     * @param second the second value
     */
    public record Pair(int first, int second) {
    }

    public static void main(String[] args) {
        System.out.println("countElements = " + countElements(List.of("x", "y", "z")));
        System.out.println("sumValues = " + sumValues(Map.of("a", 10, "b", 20)));
        System.out.println("firstOf = " + firstOf(new Pair(7, 99)));
        System.out.println("runQuietly = " + runQuietly(() -> System.out.println("  task ran")));
    }
}
