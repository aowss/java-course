package course.ch16.exercises;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Exercise 2 (Practice): Recursively search a directory for files matching a glob pattern.
 */
public class FileSearch {

    /**
     * Returns all regular files under {@code directory} whose names match the given
     * {@code glob} pattern (e.g., {@code "*.java"}).
     *
     * <p>The results are sorted by their natural {@link Path} order.
     *
     * @param directory the root directory to search
     * @param glob      a glob pattern such as {@code "*.java"} or {@code "*.txt"}
     * @return a sorted list of matching paths
     * @throws IOException if the directory cannot be walked
     */
    public static List<Path> search(Path directory, String glob) throws IOException {
        // TODO: implement using Files.walk() and/or PathMatcher
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
