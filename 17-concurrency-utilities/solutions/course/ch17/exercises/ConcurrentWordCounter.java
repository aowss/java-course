package course.ch17.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentWordCounter {

    public static Map<String, Long> countWords(List<Path> files) {
        ConcurrentHashMap<String, Long> counts = new ConcurrentHashMap<>();

        if (files.isEmpty()) {
            return counts;
        }

        ExecutorService executor = Executors.newFixedThreadPool(
                Math.min(files.size(), Runtime.getRuntime().availableProcessors()));
        try {
            for (Path file : files) {
                executor.submit(() -> {
                    try {
                        String content = Files.readString(file);
                        String[] words = content.split("[^a-zA-Z]+");
                        for (String word : words) {
                            if (!word.isEmpty()) {
                                counts.merge(word.toLowerCase(), 1L, Long::sum);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to read " + file, e);
                    }
                });
            }
        } finally {
            executor.shutdown();
            try {
                executor.awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        return counts;
    }
}
