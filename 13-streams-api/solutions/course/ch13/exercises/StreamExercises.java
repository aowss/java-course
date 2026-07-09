package course.ch13.exercises;

import java.util.List;

public class StreamExercises {

    public static List<Integer> evensOnly(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
    }

    public static List<Integer> squareAll(List<Integer> numbers) {
        return numbers.stream()
                .map(n -> n * n)
                .toList();
    }

    public static int sumPositives(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n > 0)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public static double average(List<Integer> numbers) {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }

    public static void main(String[] args) {
        var numbers = List.of(-2, 3, 4, -1, 5, 6);
        System.out.println("Evens:    " + evensOnly(numbers));
        System.out.println("Squared:  " + squareAll(numbers));
        System.out.println("Pos sum:  " + sumPositives(numbers));
        System.out.println("Average:  " + average(numbers));
    }
}
