package course.ch10.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Demonstrates common {@link Map} operations with {@link HashMap} and {@link TreeMap}.
 *
 * <p>Covers put, get, containsKey, iteration, {@code computeIfAbsent()}, and
 * {@code merge()}.
 */
public class MapOperations {

    /**
     * Counts the frequency of each character in the given string.
     *
     * @param text the input string
     * @return a map of character to frequency
     */
    public static Map<Character, Integer> charFrequency(String text) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.merge(c, 1, Integer::sum);
        }
        return freq;
    }

    /**
     * Inverts a map, swapping keys and values.
     *
     * <p>If multiple keys map to the same value, only one is kept.
     *
     * @param map the original map
     * @param <K> the key type
     * @param <V> the value type
     * @return a new map with keys and values swapped
     */
    public static <K, V> Map<V, K> invert(Map<K, V> map) {
        Map<V, K> inverted = new HashMap<>();
        for (var entry : map.entrySet()) {
            inverted.put(entry.getValue(), entry.getKey());
        }
        return inverted;
    }

    /**
     * Groups words by their first letter using {@link Map#computeIfAbsent}.
     *
     * @param words the words to group
     * @return a sorted map of first-letter to list of words
     */
    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        Map<Character, List<String>> groups = new TreeMap<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                groups.computeIfAbsent(Character.toLowerCase(word.charAt(0)),
                        k -> new ArrayList<>()).add(word);
            }
        }
        return groups;
    }

    public static void main(String[] args) {
        System.out.println("Character frequency:");
        System.out.println("  'hello' → " + new TreeMap<>(charFrequency("hello")));

        System.out.println("\nInvert map:");
        var original = Map.of("a", 1, "b", 2, "c", 3);
        System.out.println("  " + new TreeMap<>(original) + " → " + new TreeMap<>(invert(original)));

        System.out.println("\nGroup by first letter:");
        var words = List.of("apple", "banana", "avocado", "blueberry", "cherry");
        groupByFirstLetter(words).forEach((letter, group) ->
                System.out.println("  " + letter + " → " + group));
    }
}
