package course.ch16.exercises;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvParserTest {

    @TempDir
    Path tempDir;

    @Test
    void parseSimpleCsv() throws IOException {
        Path file = tempDir.resolve("data.csv");
        Files.writeString(file, "name,age,city\nAlice,30,Paris\nBob,25,London\n");
        List<Map<String, String>> rows = CsvParser.parse(file);

        assertEquals(2, rows.size());
        assertEquals("Alice", rows.get(0).get("name"));
        assertEquals("30", rows.get(0).get("age"));
        assertEquals("Paris", rows.get(0).get("city"));
        assertEquals("Bob", rows.get(1).get("name"));
    }

    @Test
    void parseSingleRow() throws IOException {
        Path file = tempDir.resolve("single.csv");
        Files.writeString(file, "key,value\nfoo,bar\n");
        List<Map<String, String>> rows = CsvParser.parse(file);

        assertEquals(1, rows.size());
        assertEquals("foo", rows.get(0).get("key"));
        assertEquals("bar", rows.get(0).get("value"));
    }

    @Test
    void parseHeaderOnly() throws IOException {
        Path file = tempDir.resolve("header-only.csv");
        Files.writeString(file, "name,age\n");
        List<Map<String, String>> rows = CsvParser.parse(file);

        assertTrue(rows.isEmpty());
    }

    @Test
    void parseWithCustomDelimiter() throws IOException {
        Path file = tempDir.resolve("semicolon.csv");
        Files.writeString(file, "name;age\nAlice;30\n");
        List<Map<String, String>> rows = CsvParser.parse(file, ';');

        assertEquals(1, rows.size());
        assertEquals("Alice", rows.get(0).get("name"));
        assertEquals("30", rows.get(0).get("age"));
    }

    @Test
    void parseQuotedFieldContainingDelimiter() throws IOException {
        Path file = tempDir.resolve("quoted.csv");
        Files.writeString(file, "name,description\nAlice,\"hello, world\"\n");
        List<Map<String, String>> rows = CsvParser.parse(file);

        assertEquals(1, rows.size());
        assertEquals("hello, world", rows.get(0).get("description"));
    }

    @Test
    void parseMultipleRowsWithQuotes() throws IOException {
        Path file = tempDir.resolve("multi-quoted.csv");
        Files.writeString(file, "a,b\n\"x,y\",z\np,\"q,r\"\n");
        List<Map<String, String>> rows = CsvParser.parse(file);

        assertEquals(2, rows.size());
        assertEquals("x,y", rows.get(0).get("a"));
        assertEquals("z", rows.get(0).get("b"));
        assertEquals("p", rows.get(1).get("a"));
        assertEquals("q,r", rows.get(1).get("b"));
    }
}
