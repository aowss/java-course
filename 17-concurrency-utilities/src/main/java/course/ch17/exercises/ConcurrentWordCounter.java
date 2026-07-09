package course.ch17.exercises;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Exercise 3 (Challenge): Count word frequencies across multiple files concurrently
 * using a thread pool and {@code ConcurrentHashMap}.
 *
 * <p>Words are defined as sequences of letters (split on non-letter characters).
 * Counting is case-insensitive.
 */
public class ConcurrentWordCounter {

    /**
     * Reads all files concurrently using a thread pool, counts word frequencies
     * (case-insensitive), and returns the combined map of word → count.
     *
     * <p>Uses {@code ConcurrentHashMap} with {@code merge()} or {@code compute()}
     * to safely aggregate counts from multiple threads.
     *
     * @param files the list of file paths to process
     * @return a map of lowercase word to occurrence count
     */
    public static Map<String, Long> countWords(List<Path> files) {
        // TODO: create a fixed thread pool
        // TODO: submit a task for each file that reads and counts words
        // TODO: use ConcurrentHashMap.merge() to combine counts safely
        // TODO: shut down the executor and return the result
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
