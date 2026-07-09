package course.ch16.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Demonstrates the NIO.2 {@link Files} API for reading and writing text files.
 *
 * <p>Key operations shown:
 * <ul>
 *   <li>{@link Files#writeString(Path, CharSequence, java.nio.file.OpenOption...)} —
 *       write a {@code String} to a file in one call</li>
 *   <li>{@link Files#readString(Path)} — read an entire file into a {@code String}</li>
 *   <li>{@link Files#readAllLines(Path)} — read all lines into a {@code List<String>}</li>
 *   <li>Appending to an existing file with {@link StandardOpenOption#APPEND}</li>
 * </ul>
 *
 * <pre>{@code
 * Path path = Path.of("example.txt");
 * Files.writeString(path, "Hello, NIO.2!");
 * String content = Files.readString(path);
 * }</pre>
 */
public class FileReadWrite {

    /**
     * Writes {@code content} to the given file, creating it if it does not exist.
     */
    public static void writeText(Path file, String content) throws IOException {
        Files.writeString(file, content);
    }

    /**
     * Reads the entire contents of {@code file} as a single {@code String}.
     */
    public static String readText(Path file) throws IOException {
        return Files.readString(file);
    }

    /**
     * Appends {@code content} to the given file, creating it if it does not exist.
     */
    public static void appendText(Path file, String content) throws IOException {
        Files.writeString(file, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    /**
     * Reads all lines from {@code file} into a list.
     */
    public static List<String> readLines(Path file) throws IOException {
        return Files.readAllLines(file);
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of("demo-output.txt");

        writeText(file, "Line 1\nLine 2\n");
        System.out.println("Written to " + file);

        String content = readText(file);
        System.out.println("Content:\n" + content);

        appendText(file, "Line 3\n");
        System.out.println("After append:");
        List<String> lines = readLines(file);
        for (int i = 0; i < lines.size(); i++) {
            System.out.println("  [" + i + "] " + lines.get(i));
        }

        Files.deleteIfExists(file);
    }
}
