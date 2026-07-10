# Bridge Project 2 — Log Analyzer (after Part IV)

A file-based log analysis tool that combines **NIO.2**, **streams**, and **collectors**. Complete this after Chapter 16 (I/O and NIO) — you will also use streams from Chapter 14.

## What you will practice

| Concept from the course | Where it appears |
|-------------------------|------------------|
| `Files.lines` / try-with-resources | `LogAnalyzer` |
| Stream pipelines | `filter`, `map`, `flatMap`, `collect` |
| `groupingBy` / `counting` | `countByLevel`, `topMessages` |
| Regex parsing | `LogParser` |
| Records and enums | `LogEntry`, `LogLevel` |

## Log format

Each line:

```
yyyy-MM-ddTHH:mm:ss LEVEL message text
```

Example:

```
2024-03-15T10:03:01 ERROR Failed to connect
```

Malformed lines are skipped.

## Run it

```bash
cd bridges/log-analyzer
mvn test
mvn compile exec:java -Dexec.mainClass="course.bridge.loganalyzer.cli.LogAnalyzerApp" \
  -Dexec.args="src/test/resources/sample.log"
```

## Run tests

```bash
mvn test -pl bridges/log-analyzer
```

## Suggested extensions

1. **Time window filter** — only include entries between two `LocalDateTime` values.
2. **CSV export** — write `countByLevel` results to a CSV file with `Files.writeString`.
3. **Parallel analysis** — compare sequential vs `parallel()` on a large generated log file.
4. **Custom collector** — build a summary record with total lines, error rate, and top message.

## Connection to the capstone

The capstone persists domain data to a file; this bridge focuses on **reading and analyzing** text with streams — a complementary skill before building larger applications.
