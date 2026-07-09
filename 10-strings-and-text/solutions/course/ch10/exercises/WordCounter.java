package course.ch10.exercises;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class WordCounter {

    public static int countWords(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }
        return text.trim().split("\\s+").length;
    }

    public static int countUniqueWords(String text) {
        if (text == null || text.isBlank()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        Set<String> unique = Arrays.stream(words)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        return unique.size();
    }

    public static String mostFrequentWord(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }
        String[] words = text.trim().split("\\s+");
        Map<String, Integer> freq = new HashMap<>();
        for (String word : words) {
            String lower = word.toLowerCase();
            freq.merge(lower, 1, Integer::sum);
        }
        return freq.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static void main(String[] args) {
        String sample = "the cat sat on the mat the cat";
        System.out.println("Total words:   " + countWords(sample));
        System.out.println("Unique words:  " + countUniqueWords(sample));
        System.out.println("Most frequent: " + mostFrequentWord(sample));
    }
}
