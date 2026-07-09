package course.ch14.exercises;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextAnalyzer {

    public static List<String> words(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return Arrays.stream(text.trim().split("\\s+"))
                .map(String::toLowerCase)
                .filter(w -> !w.isEmpty())
                .toList();
    }

    public static long wordCount(String text) {
        return words(text).size();
    }

    public static double averageWordLength(String text) {
        return words(text).stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);
    }

    public static Map<String, Long> wordFrequencies(String text) {
        return words(text).stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
    }

    public static String longestWord(String text) {
        return words(text).stream()
                .max(java.util.Comparator.comparingInt(String::length))
                .orElse("");
    }

    public static void main(String[] args) {
        String sample = "The quick brown fox jumps over the lazy dog";
        System.out.println("Words:      " + words(sample));
        System.out.println("Count:      " + wordCount(sample));
        System.out.println("Avg length: " + averageWordLength(sample));
        System.out.println("Frequencies:" + wordFrequencies(sample));
        System.out.println("Longest:    " + longestWord(sample));
    }
}
