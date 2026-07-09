package course.ch15.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LineCounter {

    public record FileStats(long lines, long words, long characters) {}

    public static FileStats count(Path file) throws IOException {
        String content = Files.readString(file);
        long characters = content.length();

        List<String> lines = Files.readAllLines(file);
        long lineCount = lines.size();

        long wordCount = 0;
        for (String line : lines) {
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                wordCount += trimmed.split("\\s+").length;
            }
        }

        return new FileStats(lineCount, wordCount, characters);
    }
}
