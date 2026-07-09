package course.ch16.exercises;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Exercise 1 (Guided): Count lines, words, and characters in a file using the Files API.
 */
public class LineCounter {

    /**
     * Holds the statistics for a single file.
     *
     * @param lines      the number of lines
     * @param words      the number of whitespace-delimited words
     * @param characters the number of characters
     */
    public record FileStats(long lines, long words, long characters) {}

    /**
     * Reads {@code file} and counts its lines, words, and characters.
     *
     * <p>Words are whitespace-delimited tokens as defined by
     * {@link String#split(String)} with the pattern {@code "\\s+"}.
     *
     * @param file the path to the file to analyse
     * @return a {@link FileStats} record with the counts
     * @throws IOException if the file cannot be read
     */
    public static FileStats count(Path file) throws IOException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
