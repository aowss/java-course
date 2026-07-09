package course.ch16.exercises;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.stream.Stream;

public class FileSearch {

    public static List<Path> search(Path directory, String glob) throws IOException {
        FileSystem fs = directory.getFileSystem();
        PathMatcher matcher = fs.getPathMatcher("glob:" + glob);
        try (Stream<Path> stream = Files.walk(directory)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(p -> matcher.matches(p.getFileName()))
                    .sorted()
                    .toList();
        }
    }
}
