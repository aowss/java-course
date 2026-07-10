---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 16'
footer: 'I/O and NIO'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 16
## I/O and NIO

---

## Objectives

- Understand **byte streams** vs **character streams**
- Use NIO.2 `Path`, `Files`, and `FileSystem` for modern file operations
- Read and write text with `Files.readString()`, `readAllLines()`, `writeString()`
- Read and write binary data with `Files.readAllBytes()` and `Files.write()`
- Walk directory trees with `Files.walk()` and `Files.walkFileTree()`
- Understand the basics of Java serialization

---

## Byte Streams vs Character Streams

| Family | Types | Use for |
|--------|-------|---------|
| **Byte streams** | `InputStream` / `OutputStream` | Binary data — images, audio, serialized objects |
| **Character streams** | `Reader` / `Writer` | Text with automatic encoding (e.g., UTF-8) |

```java
try (OutputStream out = Files.newOutputStream(path)) {
    out.write(new byte[]{72, 101, 108, 108, 111});
}
try (BufferedWriter writer = Files.newBufferedWriter(path)) {
    writer.write("Hello");
}
```

---

## Path and the FileSystem

`Path` represents a file or directory location — it does **not** require the file to exist:

```java
Path absolute = Path.of("/Users/alice/docs/report.txt");
Path relative = Path.of("src", "main", "java");
```

Useful methods: `getFileName()`, `getParent()`, `resolve(other)`, `toAbsolutePath()`

---

## Reading and Writing Files

NIO.2 one-liners for common operations:

```java
Files.writeString(path, "Hello, NIO.2!");
String content = Files.readString(path);
List<String> lines = Files.readAllLines(path);
Files.write(path, byteArray);
byte[] data = Files.readAllBytes(path);
```

Prefer `Files` / `Path` over legacy `java.io.File` for new code.

---

## Legacy `java.io` vs NIO.2

| Legacy (`java.io`) | NIO.2 (`java.nio.file`) |
|--------------------|---------------------------|
| `File` | `Path` |
| `FileInputStream` / `FileOutputStream` | `Files.newInputStream()` / `newOutputStream()` |
| `FileReader` / `FileWriter` | `Files.newBufferedReader()` / `newBufferedWriter()` |
| Manual stream plumbing | `readString`, `writeString`, `readAllLines` |

NIO.2 integrates with `java.nio` channels and works well with try-with-resources.

---

## Large Files — Buffered I/O

For large files, stream line-by-line instead of loading everything into memory:

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    String line;
    while ((line = reader.readLine()) != null) {
        process(line);
    }
}
```

Always close streams — use **try-with-resources** to guarantee cleanup.

---

## Walking Directory Trees

`Files.walk()` returns a lazy `Stream<Path>` — **must** be closed:

```java
try (Stream<Path> stream = Files.walk(Path.of("src"))) {
    List<Path> javaFiles = stream
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".java"))
            .sorted()
            .toList();
}
```

For pre/post-visit callbacks and skipping subtrees, use `Files.walkFileTree()` with a `FileVisitor`.

---

## Serialization Basics

Convert objects to bytes and back via `java.io.Serializable`:

```java
public class Student implements Serializable {
    private String name;
    private int age;
}
```

Write with `ObjectOutputStream`, read with `ObjectInputStream`.

> **Item 85:** Prefer JSON, CSV, or other structured formats — serialization has security and versioning pitfalls.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `FileReadWrite` | NIO.2 Files API: write, read, append, readAllLines |
| `DirectoryWalker` | Walking directory trees, filtering, calculating sizes |
| `ByteStreamDemo` | Byte-oriented I/O with streams and Files convenience |

```bash
mvn test -pl 16-io-and-nio
```

---

## Exercises — Your Turn

1. **LineCounter** (Guided) — count lines, words, and characters in a file
2. **FileSearch** (Practice) — recursively search directories with glob patterns
3. **CsvParser** (Challenge) — parse CSV into `List<Map<String,String>>`

```bash
mvn test -pl 16-io-and-nio -Dtest="LineCounterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Use **character streams** (or `Files.readString` / `writeString`) for text; **byte streams** (or `readAllBytes` / `write`) for binary data
- Prefer NIO.2 `Files` / `Path` over legacy `java.io.File`
- Always close streams — try-with-resources is the standard pattern
- `Files.walk()` returns a `Stream<Path>` that must be closed
- Avoid Java serialization for data exchange — use JSON or CSV instead

Further reading: [java.nio.file package](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/nio/file/package-summary.html) · *Effective Java* Item 85
