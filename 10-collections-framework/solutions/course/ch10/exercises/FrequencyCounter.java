package course.ch10.exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyCounter {

    public static Map<String, Integer> countFrequencies(String text) {
        Map<String, Integer> frequencies = new HashMap<>();
        if (text == null || text.isBlank()) {
            return frequencies;
        }
        for (String word : text.split("\\s+")) {
            String key = word.toLowerCase();
            frequencies.merge(key, 1, Integer::sum);
        }
        return frequencies;
    }

    public static List<Map.Entry<String, Integer>> sortedByFrequency(String text) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(countFrequencies(text).entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        return entries;
    }
}
