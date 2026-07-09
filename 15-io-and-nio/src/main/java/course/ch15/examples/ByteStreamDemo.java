package course.ch15.examples;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Demonstrates byte-oriented I/O and contrasts it with character-oriented I/O.
 *
 * <p>Byte streams ({@link InputStream} / {@link OutputStream}) work with raw bytes
 * and are suitable for binary data such as images, audio, or serialised objects.
 * Character streams ({@link java.io.Reader} / {@link java.io.Writer}) handle text
 * with proper character-encoding support.
 *
 * <p>This class uses both the classic stream API and the NIO.2 convenience methods:
 * <ul>
 *   <li>{@link Files#write(Path, byte[], java.nio.file.OpenOption...)} — write bytes</li>
 *   <li>{@link Files#readAllBytes(Path)} — read all bytes</li>
 *   <li>{@link Files#newOutputStream(Path, java.nio.file.OpenOption...)} — obtain an {@code OutputStream}</li>
 *   <li>{@link Files#newInputStream(Path, java.nio.file.OpenOption...)} — obtain an {@code InputStream}</li>
 * </ul>
 *
 * <pre>{@code
 * byte[] data = {72, 101, 108, 108, 111};   // "Hello" in ASCII
 * Files.write(path, data);
 * byte[] read = Files.readAllBytes(path);
 * }</pre>
 */
public class ByteStreamDemo {

    /**
     * Writes {@code data} to the given file using {@link Files#write(Path, byte[], java.nio.file.OpenOption...)}.
     */
    public static void writeBytes(Path file, byte[] data) throws IOException {
        Files.write(file, data);
    }

    /**
     * Reads the entire contents of {@code file} as a byte array.
     */
    public static byte[] readBytes(Path file) throws IOException {
        return Files.readAllBytes(file);
    }

    /**
     * Writes {@code data} to the given file using a classic {@link OutputStream}.
     */
    public static void writeBytesWithStream(Path file, byte[] data) throws IOException {
        try (OutputStream out = Files.newOutputStream(file)) {
            out.write(data);
        }
    }

    /**
     * Reads the entire contents of {@code file} using a classic {@link InputStream}.
     */
    public static byte[] readBytesWithStream(Path file) throws IOException {
        try (InputStream in = Files.newInputStream(file)) {
            return in.readAllBytes();
        }
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of("bytes-demo.bin");

        byte[] data = {72, 101, 108, 108, 111, 33};  // "Hello!" in ASCII
        writeBytes(file, data);
        System.out.println("Wrote " + data.length + " bytes to " + file);

        byte[] read = readBytes(file);
        System.out.println("Read back: " + new String(read));

        byte[] moreData = {0x00, 0x0F, (byte) 0xFF, 0x42};
        writeBytesWithStream(file, moreData);
        byte[] readBack = readBytesWithStream(file);
        System.out.println("Stream round-trip — wrote " + moreData.length
                + " bytes, read " + readBack.length + " bytes");

        Files.deleteIfExists(file);
    }
}
