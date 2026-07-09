package course.ch16.examples;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ByteStreamDemoTest {

    @TempDir
    Path tempDir;

    @Test
    void writeAndReadBytes() throws IOException {
        Path file = tempDir.resolve("data.bin");
        byte[] data = {1, 2, 3, 4, 5};
        ByteStreamDemo.writeBytes(file, data);
        assertArrayEquals(data, ByteStreamDemo.readBytes(file));
    }

    @Test
    void writeAndReadEmptyBytes() throws IOException {
        Path file = tempDir.resolve("empty.bin");
        byte[] data = {};
        ByteStreamDemo.writeBytes(file, data);
        assertArrayEquals(data, ByteStreamDemo.readBytes(file));
    }

    @Test
    void writeAndReadBytesWithStream() throws IOException {
        Path file = tempDir.resolve("stream.bin");
        byte[] data = {10, 20, 30, 40};
        ByteStreamDemo.writeBytesWithStream(file, data);
        assertArrayEquals(data, ByteStreamDemo.readBytesWithStream(file));
    }

    @Test
    void nioAndStreamMethodsAreInterchangeable() throws IOException {
        Path file = tempDir.resolve("interop.bin");
        byte[] data = {72, 101, 108, 108, 111};
        ByteStreamDemo.writeBytes(file, data);
        assertArrayEquals(data, ByteStreamDemo.readBytesWithStream(file));
    }

    @Test
    void roundTripPreservesTextAsBytes() throws IOException {
        Path file = tempDir.resolve("text.bin");
        String text = "Hello, World!";
        byte[] data = text.getBytes();
        ByteStreamDemo.writeBytesWithStream(file, data);
        byte[] read = ByteStreamDemo.readBytes(file);
        assertEquals(text, new String(read));
    }
}
