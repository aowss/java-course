# Chapter 16: I/O and NIO

## Objectives

- Understand the difference between byte streams and character streams
- Use the NIO.2 `Path`, `Files`, and `FileSystem` APIs for modern file operations
- Read and write text files with `Files.readString()`, `Files.readAllLines()`, and `Files.writeString()`
- Read and write binary data with `Files.readAllBytes()` and `Files.write()`
- Walk directory trees with `Files.walk()` and `Files.walkFileTree()`
- Understand the basics of Java serialization

## Concepts

### Byte Streams vs Character Streams

Java I/O splits into two families:

- **Byte streams** (`InputStream` / `OutputStream`) — work with raw bytes. Use them for binary data such as images, audio, or serialised objects.
- **Character streams** (`Reader` / `Writer`) — work with characters and handle encoding (e.g., UTF-8) automatically. Use them for text.

```java
// Byte stream — writing raw bytes
try (OutputStream out = Files.newOutputStream(path)) {
    out.write(new byte[]{72, 101, 108, 108, 111});
}

// Character stream — writing text
try (BufferedWriter writer = Files.newBufferedWriter(path)) {
    writer.write("Hello");
}
```

The NIO.2 `Files` utility class provides convenience methods that hide the stream plumbing for common cases.

### Path and the FileSystem

`Path` represents a file or directory location. Create one with `Path.of()`:

```java
Path absolute = Path.of("/Users/alice/docs/report.txt");
Path relative = Path.of("src", "main", "java");
```

A `Path` is purely a reference — it does not require the file to exist. Useful methods:

- `getFileName()` — the last element (e.g., `report.txt`)
- `getParent()` — the parent directory
- `resolve(other)` — append another path
- `toAbsolutePath()` — convert to an absolute path

### Reading and Writing Files

NIO.2 provides one-liner methods for the most common file operations:

```java
// Write text
Files.writeString(path, "Hello, NIO.2!");

// Read text
String content = Files.readString(path);

// Read all lines
List<String> lines = Files.readAllLines(path);

// Write bytes
Files.write(path, byteArray);

// Read bytes
byte[] data = Files.readAllBytes(path);
```

For larger files, use `BufferedReader` / `BufferedWriter` to avoid loading everything into memory:

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    String line;
    while ((line = reader.readLine()) != null) {
        process(line);
    }
}
```

### Walking Directory Trees

`Files.walk()` returns a `Stream<Path>` that lazily traverses a directory tree:

```java
try (Stream<Path> stream = Files.walk(Path.of("src"))) {
    List<Path> javaFiles = stream
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".java"))
            .sorted()
            .toList();
}
```

For more control (e.g., pre/post-visit callbacks, skipping subtrees), use `Files.walkFileTree()` with a `FileVisitor`.

### Serialization Basics

Java can convert objects to byte streams (*serialization*) and back (*deserialization*) via `java.io.Serializable`:

```java
public class Student implements Serializable {
    private String name;
    private int age;
}
```

Write with `ObjectOutputStream`, read with `ObjectInputStream`. Use serialization sparingly — it has security and versioning pitfalls. Prefer structured formats such as JSON or CSV for data exchange.

## Examples

| File                                                                                       | Demonstrates                                           |
|--------------------------------------------------------------------------------------------|--------------------------------------------------------|
| [`FileReadWrite.java`](src/main/java/course/ch16/examples/FileReadWrite.java)              | NIO.2 Files API: write, read, append, readAllLines     |
| [`DirectoryWalker.java`](src/main/java/course/ch16/examples/DirectoryWalker.java)          | Walking directory trees, filtering, calculating sizes  |
| [`ByteStreamDemo.java`](src/main/java/course/ch16/examples/ByteStreamDemo.java)            | Byte-oriented I/O with streams and Files convenience   |

## Exercises

### Exercise 1: Line Counter (Guided)

**File:** [`LineCounter.java`](src/main/java/course/ch16/exercises/LineCounter.java)

Count lines, words, and characters in a file:
- Define an inner record `FileStats` with fields `lines`, `words`, and `characters`
- Implement `count(Path file)` — reads the file and returns a `FileStats`
- Words are whitespace-delimited tokens

```bash
mvn test -Dtest="course.ch16.exercises.LineCounterTest"
```

### Exercise 2: File Search (Practice)

**File:** [`FileSearch.java`](src/main/java/course/ch16/exercises/FileSearch.java)

Recursively search a directory for files matching a glob pattern:
- Implement `search(Path directory, String glob)` — returns all matching regular files, sorted by path
- Use `Files.walk()` and/or `PathMatcher`

```bash
mvn test -Dtest="course.ch16.exercises.FileSearchTest"
```

### Exercise 3: CSV Parser (Challenge)

**File:** [`CsvParser.java`](src/main/java/course/ch16/exercises/CsvParser.java)

Read a CSV file into a `List<Map<String,String>>`:
- Implement `parse(Path csvFile)` — uses the header row as map keys
- Implement `parse(Path csvFile, char delimiter)` — same but with a configurable delimiter
- Handle quoted fields that contain the delimiter character

```bash
mvn test -Dtest="course.ch16.exercises.CsvParserTest"
```

## Key Takeaways

- Prefer the NIO.2 `Files` / `Path` API over the legacy `java.io.File` class for new code.
- Use **character streams** (or `Files.readString` / `writeString`) for text and **byte streams** (or `Files.readAllBytes` / `write`) for binary data.
- Always close streams — use try-with-resources to guarantee cleanup.
- `Files.walk()` returns a `Stream<Path>` that **must** be closed (use try-with-resources).
- Avoid Java serialization for data exchange — use JSON, CSV, or another structured format instead.

## Bridge Project — Log Analyzer

Finished Part IV? Parse and summarize log files with NIO.2 and streams: [`bridges/log-analyzer`](../bridges/log-analyzer/README.md).

## Further Reading

- [JLS §9.7.4 — `java.io.Serializable`](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.7.4)
- [java.nio.file package summary (Java 25)](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/nio/file/package-summary.html)
- [Files API (Java 25)](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/nio/file/Files.html)
- Effective Java, Item 85: Prefer alternatives to Java serialization
