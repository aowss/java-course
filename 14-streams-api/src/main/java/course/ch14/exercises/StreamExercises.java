package course.ch14.exercises;

import java.util.List;

/**
 * Exercise 1 (Guided): Basic stream operations — filter, map, sum.
 */
public class StreamExercises {

    /**
     * Returns only the even numbers from the list.
     *
     * @param numbers the input numbers
     * @return the even numbers in encounter order
     */
    public static List<Integer> evensOnly(List<Integer> numbers) {
        // TODO: implement — filter even numbers
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Squares each number in the list.
     *
     * @param numbers the input numbers
     * @return the squared values
     */
    public static List<Integer> squareAll(List<Integer> numbers) {
        // TODO: implement — map each number to n * n
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the sum of all positive numbers.
     *
     * @param numbers the input numbers
     * @return the sum of positive values (0 if none)
     */
    public static int sumPositives(List<Integer> numbers) {
        // TODO: implement — filter positives and sum
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the average of the numbers, or 0 if the list is empty.
     *
     * @param numbers the input numbers
     * @return the arithmetic mean
     */
    public static double average(List<Integer> numbers) {
        // TODO: implement — use mapToInt and average
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        var numbers = List.of(-2, 3, 4, -1, 5, 6);
        System.out.println("Evens:    " + evensOnly(numbers));
        System.out.println("Squared:  " + squareAll(numbers));
        System.out.println("Pos sum:  " + sumPositives(numbers));
        System.out.println("Average:  " + average(numbers));
    }
}
