package course.ch15.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileSearchTest {

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        Files.writeString(tempDir.resolve("App.java"), "class App {}");
        Files.writeString(tempDir.resolve("readme.txt"), "readme");
        Path sub = tempDir.resolve("pkg");
        Files.createDirectory(sub);
        Files.writeString(sub.resolve("Util.java"), "class Util {}");
        Files.writeString(sub.resolve("notes.md"), "# notes");
    }

    @Test
    void searchJavaFiles() throws IOException {
        List<Path> results = FileSearch.search(tempDir, "*.java");
        assertEquals(2, results.size());
        assertTrue(results.stream()
                .allMatch(p -> p.getFileName().toString().endsWith(".java")));
    }

    @Test
    void searchTxtFiles() throws IOException {
        List<Path> results = FileSearch.search(tempDir, "*.txt");
        assertEquals(1, results.size());
        assertEquals("readme.txt", results.get(0).getFileName().toString());
    }

    @Test
    void searchNoMatches() throws IOException {
        List<Path> results = FileSearch.search(tempDir, "*.xml");
        assertTrue(results.isEmpty());
    }

    @Test
    void searchResultsAreSorted() throws IOException {
        List<Path> results = FileSearch.search(tempDir, "*.java");
        List<Path> sorted = results.stream().sorted().toList();
        assertEquals(sorted, results);
    }

    @Test
    void searchAllFiles() throws IOException {
        List<Path> results = FileSearch.search(tempDir, "*");
        assertEquals(4, results.size());
    }
}
