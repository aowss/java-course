package course.ch15.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineCounterTest {

    @TempDir
    Path tempDir;

    @Test
    void countSingleLine() throws IOException {
        Path file = tempDir.resolve("single.txt");
        Files.writeString(file, "hello world");
        LineCounter.FileStats stats = LineCounter.count(file);
        assertEquals(1, stats.lines());
        assertEquals(2, stats.words());
        assertEquals(11, stats.characters());
    }

    @Test
    void countMultipleLines() throws IOException {
        Path file = tempDir.resolve("multi.txt");
        Files.writeString(file, "one two\nthree four five\nsix\n");
        LineCounter.FileStats stats = LineCounter.count(file);
        assertEquals(3, stats.lines());
        assertEquals(6, stats.words());
        assertEquals(28, stats.characters());
    }

    @Test
    void countEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.writeString(file, "");
        LineCounter.FileStats stats = LineCounter.count(file);
        assertEquals(0, stats.lines());
        assertEquals(0, stats.words());
        assertEquals(0, stats.characters());
    }

    @Test
    void countFileWithExtraWhitespace() throws IOException {
        Path file = tempDir.resolve("spaces.txt");
        Files.writeString(file, "  hello   world  \n  foo  ");
        LineCounter.FileStats stats = LineCounter.count(file);
        assertEquals(2, stats.lines());
        assertEquals(3, stats.words());
        assertEquals(25, stats.characters());
    }

    @Test
    void countBlankLines() throws IOException {
        Path file = tempDir.resolve("blank-lines.txt");
        Files.writeString(file, "hello\n\nworld\n");
        LineCounter.FileStats stats = LineCounter.count(file);
        assertEquals(3, stats.lines());
        assertEquals(2, stats.words());
        assertEquals(13, stats.characters());
    }
}
