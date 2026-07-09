package course.ch15.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates walking a directory tree using the NIO.2 {@link Files#walk(Path, java.nio.file.FileVisitOption...)} API.
 *
 * <p>This class provides static utility methods that:
 * <ul>
 *   <li>List all regular files under a directory (recursively)</li>
 *   <li>Filter files by extension</li>
 *   <li>Calculate the total size of all files in a directory tree</li>
 * </ul>
 *
 * <pre>{@code
 * List<Path> javaFiles = DirectoryWalker.findByExtension(Path.of("src"), ".java");
 * long totalBytes = DirectoryWalker.totalSize(Path.of("src"));
 * }</pre>
 */
public class DirectoryWalker {

    /**
     * Returns all regular files under {@code directory}, recursively.
     *
     * @param directory the root directory to walk
     * @return a list of paths to regular files, sorted by natural order
     */
    public static List<Path> listFiles(Path directory) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream
                    .filter(Files::isRegularFile)
                    .sorted()
                    .toList();
        }
    }

    /**
     * Returns all regular files under {@code directory} whose names end with {@code extension}.
     *
     * @param directory the root directory to walk
     * @param extension the file extension to match, including the dot (e.g., {@code ".java"})
     * @return a list of matching paths, sorted by natural order
     */
    public static List<Path> findByExtension(Path directory, String extension) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(extension))
                    .sorted()
                    .toList();
        }
    }

    /**
     * Calculates the total size in bytes of all regular files under {@code directory}.
     *
     * @param directory the root directory to walk
     * @return total size in bytes
     */
    public static long totalSize(Path directory) throws IOException {
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream
                    .filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            return 0L;
                        }
                    })
                    .sum();
        }
    }

    public static void main(String[] args) throws IOException {
        Path dir = Path.of("src");
        if (!Files.isDirectory(dir)) {
            System.out.println("Run from the chapter root so that 'src/' is visible.");
            return;
        }

        List<Path> allFiles = listFiles(dir);
        System.out.println("All files under src/ (" + allFiles.size() + "):");
        allFiles.forEach(p -> System.out.println("  " + p));

        List<Path> javaFiles = findByExtension(dir, ".java");
        System.out.println("\n.java files (" + javaFiles.size() + "):");
        javaFiles.forEach(p -> System.out.println("  " + p));

        long bytes = totalSize(dir);
        System.out.println("\nTotal size: " + bytes + " bytes");
    }
}
