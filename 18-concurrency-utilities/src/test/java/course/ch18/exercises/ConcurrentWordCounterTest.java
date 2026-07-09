package course.ch18.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(10)
class ConcurrentWordCounterTest {

    @TempDir
    Path tempDir;

    @Test
    void countSingleFile() throws IOException {
        Path file = tempDir.resolve("a.txt");
        Files.writeString(file, "hello world hello");
        Map<String, Long> counts = ConcurrentWordCounter.countWords(List.of(file));
        assertEquals(2L, counts.get("hello"));
        assertEquals(1L, counts.get("world"));
    }

    @Test
    void countMultipleFiles() throws IOException {
        Path f1 = tempDir.resolve("f1.txt");
        Path f2 = tempDir.resolve("f2.txt");
        Files.writeString(f1, "hello world");
        Files.writeString(f2, "hello java");
        Map<String, Long> counts = ConcurrentWordCounter.countWords(List.of(f1, f2));
        assertEquals(2L, counts.get("hello"));
        assertEquals(1L, counts.get("world"));
        assertEquals(1L, counts.get("java"));
    }

    @Test
    void countIsCaseInsensitive() throws IOException {
        Path file = tempDir.resolve("mixed.txt");
        Files.writeString(file, "Hello HELLO hello");
        Map<String, Long> counts = ConcurrentWordCounter.countWords(List.of(file));
        assertEquals(3L, counts.get("hello"));
    }

    @Test
    void countEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.writeString(file, "");
        Map<String, Long> counts = ConcurrentWordCounter.countWords(List.of(file));
        assertTrue(counts.isEmpty());
    }

    @Test
    void countSplitsOnNonLetterCharacters() throws IOException {
        Path file = tempDir.resolve("special.txt");
        Files.writeString(file, "hello-world hello_world hello.world");
        Map<String, Long> counts = ConcurrentWordCounter.countWords(List.of(file));
        assertEquals(3L, counts.get("hello"));
        assertEquals(3L, counts.get("world"));
    }

    @Test
    void countEmptyList() {
        Map<String, Long> counts = ConcurrentWordCounter.countWords(List.of());
        assertTrue(counts.isEmpty());
    }
}
