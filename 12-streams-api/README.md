# Chapter 12: Streams API

## Objectives

- Create streams from collections, arrays, generators, and ranges
- Apply intermediate operations: `filter`, `map`, `flatMap`, `distinct`, `sorted`, `limit`
- Use terminal operations: `collect`, `reduce`, `forEach`, `count`, `min`/`max`, `findFirst`
- Aggregate data with `Collectors`: grouping, partitioning, joining, statistics
- Understand when and how to use parallel streams

## Concepts

### What Is a Stream?

A **stream** is a sequence of elements supporting sequential and parallel aggregate operations. Streams do not store data — they pipe elements from a source through a pipeline of operations.

Key properties:
- **No storage** — streams are views over a source
- **Lazy** — intermediate operations are deferred until a terminal operation runs
- **Consumable** — each stream can be traversed only once
- **Non-interfering** — source must not be modified during pipeline execution

### Creating Streams

```java
List<String> list = List.of("a", "b", "c");
Stream<String> fromList = list.stream();

Stream<Integer> range = IntStream.range(1, 10).boxed();
Stream<Integer> infinite = Stream.iterate(0, n -> n + 1).limit(5);
```

| Source              | Creation method                          |
|---------------------|------------------------------------------|
| Collection          | `collection.stream()`                    |
| Array               | `Arrays.stream(array)`                   |
| Range               | `IntStream.range(start, end)`            |
| Generator           | `Stream.iterate(seed, next)`             |
| String split        | `Arrays.stream(text.split(regex))`       |

### Intermediate Operations

Intermediate operations return a new stream and are **lazy**:

| Operation    | Description                                    |
|--------------|------------------------------------------------|
| `filter`     | Keep elements matching a predicate             |
| `map`        | Transform each element                         |
| `flatMap`    | Transform and flatten nested streams           |
| `distinct`   | Remove duplicates (uses `equals`)            |
| `sorted`     | Sort elements (natural or custom order)        |
| `limit`      | Truncate to first n elements                   |
| `skip`       | Discard first n elements                       |

```java
List<String> result = words.stream()
        .filter(w -> w.length() > 3)
        .map(String::toUpperCase)
        .sorted()
        .toList();
```

### Terminal Operations

Terminal operations trigger pipeline execution and produce a result:

| Operation       | Description                              |
|-----------------|------------------------------------------|
| `collect`       | Accumulate into a collection or map      |
| `reduce`        | Combine elements into a single value     |
| `forEach`       | Perform an action on each element        |
| `count`         | Count elements                           |
| `min` / `max`   | Find extremal element                    |
| `findFirst`     | Return first element as `Optional`       |
| `anyMatch`      | True if any element matches              |
| `allMatch`      | True if all elements match               |

### Collectors

`Collectors` provides pre-built reduction strategies:

```java
Map<Integer, List<String>> byLength = words.stream()
        .collect(Collectors.groupingBy(String::length));

Map<Boolean, List<Integer>> evenOdd = numbers.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

String joined = words.stream()
        .collect(Collectors.joining(", "));
```

| Collector                | Result                              |
|--------------------------|-------------------------------------|
| `toList()`               | Mutable list                        |
| `toSet()`                | Set of elements                     |
| `groupingBy(classifier)` | Map of groups                       |
| `partitioningBy(pred)`   | Map with `true`/`false` keys        |
| `joining(delimiter)`     | Concatenated string                 |
| `counting()`             | Element count per group             |
| `summingInt/Long/Double` | Sum per group                       |

### Parallel Streams

Call `.parallel()` or `.parallelStream()` to process elements concurrently:

```java
long count = largeList.parallelStream()
        .filter(expensivePredicate)
        .count();
```

Use parallel streams when:
- The data set is **large**
- Operations are **CPU-intensive** and **side-effect free**
- The source supports **efficient splitting** (ArrayList, arrays)

Avoid parallel streams for small data sets, I/O-bound work, or when order matters.

## Examples

| File                                                                                    | Demonstrates                                           |
|-----------------------------------------------------------------------------------------|--------------------------------------------------------|
| [`StreamCreation.java`](src/main/java/course/ch12/examples/StreamCreation.java)         | Creating streams from collections, arrays, ranges, generators |
| [`IntermediateOps.java`](src/main/java/course/ch12/examples/IntermediateOps.java)       | `filter`, `map`, `flatMap`, `distinct`, `sorted`, `limit` |
| [`TerminalOps.java`](src/main/java/course/ch12/examples/TerminalOps.java)               | `reduce`, `collect`, `count`, `min`/`max`, `findFirst` |
| [`CollectorExamples.java`](src/main/java/course/ch12/examples/CollectorExamples.java)     | `groupingBy`, `partitioningBy`, `joining`, statistics  |

## Exercises

### Exercise 1: Stream Exercises (Guided)

**File:** [`StreamExercises.java`](src/main/java/course/ch12/exercises/StreamExercises.java)

Implement basic stream operations:
- `evensOnly(List)` — filter even numbers
- `squareAll(List)` — map to squares
- `sumPositives(List)` — sum positive values
- `average(List)` — arithmetic mean (0 if empty)

```bash
mvn test -Dtest="course.ch12.exercises.StreamExercisesTest"
```

### Exercise 2: Text Analyzer (Practice)

**File:** [`TextAnalyzer.java`](src/main/java/course/ch12/exercises/TextAnalyzer.java)

Analyze text with streams:
- `words(String)` — split and lowercase
- `wordCount(String)` — total word count
- `averageWordLength(String)` — mean word length
- `wordFrequencies(String)` — frequency map
- `longestWord(String)` — longest word

```bash
mvn test -Dtest="course.ch12.exercises.TextAnalyzerTest"
```

### Exercise 3: Transaction Report (Challenge)

**File:** [`TransactionReport.java`](src/main/java/course/ch12/exercises/TransactionReport.java)

Build financial reports with collectors:
- `totalAmount` / `totalExpenses`
- `groupByCategory` / `totalByCategory`
- `topExpenseCategories(n)` — top N spending categories

```bash
mvn test -Dtest="course.ch12.exercises.TransactionReportTest"
```

## Key Takeaways

- Streams provide a **declarative** way to process collections — describe *what*, not *how*.
- **Intermediate operations** are lazy; a **terminal operation** triggers execution.
- **`Collectors`** offer powerful grouping, partitioning, and aggregation.
- Prefer **method references** (`String::toUpperCase`) over lambdas when possible.
- **Parallel streams** help with large, CPU-bound workloads but add overhead for small data.
- Streams are **one-shot** — create a new stream for each pipeline.

## Further Reading

- [java.util.stream — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/package-summary.html)
- [java.util.stream.Collectors — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/Collectors.html)
- JEP 107: Bulk Data Operations for Collections
- Effective Java, Item 45: Use streams judiciously
- Effective Java, Item 48: Use caution when making streams parallel
