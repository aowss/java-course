package course.ch16.examples;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileReadWriteTest {

    @TempDir
    Path tempDir;

    @Test
    void writeAndReadText() throws IOException {
        Path file = tempDir.resolve("test.txt");
        FileReadWrite.writeText(file, "Hello, NIO!");
        assertEquals("Hello, NIO!", FileReadWrite.readText(file));
    }

    @Test
    void writeCreatesFile() throws IOException {
        Path file = tempDir.resolve("new-file.txt");
        FileReadWrite.writeText(file, "content");
        assertTrue(file.toFile().exists());
    }

    @Test
    void appendAddsToExistingContent() throws IOException {
        Path file = tempDir.resolve("append.txt");
        FileReadWrite.writeText(file, "first");
        FileReadWrite.appendText(file, " second");
        assertEquals("first second", FileReadWrite.readText(file));
    }

    @Test
    void appendCreatesFileIfMissing() throws IOException {
        Path file = tempDir.resolve("no-such-file.txt");
        FileReadWrite.appendText(file, "created");
        assertEquals("created", FileReadWrite.readText(file));
    }

    @Test
    void readLinesReturnsList() throws IOException {
        Path file = tempDir.resolve("lines.txt");
        FileReadWrite.writeText(file, "alpha\nbeta\ngamma");
        List<String> lines = FileReadWrite.readLines(file);
        assertEquals(List.of("alpha", "beta", "gamma"), lines);
    }

    @Test
    void readLinesEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty.txt");
        FileReadWrite.writeText(file, "");
        List<String> lines = FileReadWrite.readLines(file);
        assertTrue(lines.isEmpty());
    }
}
