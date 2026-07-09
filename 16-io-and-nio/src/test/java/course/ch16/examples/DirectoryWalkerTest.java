package course.ch16.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DirectoryWalkerTest {

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        Files.writeString(tempDir.resolve("a.txt"), "aaa");
        Files.writeString(tempDir.resolve("b.java"), "bbb");
        Path sub = tempDir.resolve("sub");
        Files.createDirectory(sub);
        Files.writeString(sub.resolve("c.txt"), "ccc");
        Files.writeString(sub.resolve("d.java"), "ddd");
    }

    @Test
    void listFilesReturnsAllFiles() throws IOException {
        List<Path> files = DirectoryWalker.listFiles(tempDir);
        assertEquals(4, files.size());
    }

    @Test
    void listFilesExcludesDirectories() throws IOException {
        List<Path> files = DirectoryWalker.listFiles(tempDir);
        assertTrue(files.stream().allMatch(Files::isRegularFile));
    }

    @Test
    void listFilesIsSorted() throws IOException {
        List<Path> files = DirectoryWalker.listFiles(tempDir);
        List<Path> sorted = files.stream().sorted().toList();
        assertEquals(sorted, files);
    }

    @Test
    void findByExtensionJava() throws IOException {
        List<Path> javaFiles = DirectoryWalker.findByExtension(tempDir, ".java");
        assertEquals(2, javaFiles.size());
        assertTrue(javaFiles.stream()
                .allMatch(p -> p.getFileName().toString().endsWith(".java")));
    }

    @Test
    void findByExtensionTxt() throws IOException {
        List<Path> txtFiles = DirectoryWalker.findByExtension(tempDir, ".txt");
        assertEquals(2, txtFiles.size());
    }

    @Test
    void findByExtensionNoMatches() throws IOException {
        List<Path> xmlFiles = DirectoryWalker.findByExtension(tempDir, ".xml");
        assertTrue(xmlFiles.isEmpty());
    }

    @Test
    void totalSizeIsPositive() throws IOException {
        long size = DirectoryWalker.totalSize(tempDir);
        assertTrue(size > 0);
    }

    @Test
    void totalSizeEqualsIndividualSizes() throws IOException {
        long expected = Files.size(tempDir.resolve("a.txt"))
                + Files.size(tempDir.resolve("b.java"))
                + Files.size(tempDir.resolve("sub/c.txt"))
                + Files.size(tempDir.resolve("sub/d.java"));
        assertEquals(expected, DirectoryWalker.totalSize(tempDir));
    }
}
