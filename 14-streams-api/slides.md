---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 14'
footer: 'Streams API'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 14
## Streams API

---

## Objectives

- Create streams from collections, arrays, generators, and ranges
- Apply intermediate operations: `filter`, `map`, `flatMap`, `distinct`, `sorted`, `limit`
- Use terminal operations: `collect`, `reduce`, `forEach`, `count`, `min`/`max`, `findFirst`
- Aggregate with `Collectors`: grouping, partitioning, joining, statistics
- Understand when to use **parallel streams**

---

## What Is a Stream?

A **stream** is a sequence of elements supporting aggregate operations. Streams do **not** store data — they pipe elements from a source through a pipeline.

Key properties:
- **No storage** — views over a source
- **Lazy** — intermediate ops deferred until a terminal op runs
- **Consumable** — each stream traversed only once
- **Non-interfering** — don't modify the source during execution

---

## Creating Streams

```java
List<String> list = List.of("a", "b", "c");
Stream<String> fromList = list.stream();

Stream<Integer> range = IntStream.range(1, 10).boxed();
Stream<Integer> infinite = Stream.iterate(0, n -> n + 1).limit(5);
```

| Source | Creation method |
|--------|-----------------|
| Collection | `collection.stream()` |
| Array | `Arrays.stream(array)` |
| Range | `IntStream.range(start, end)` |
| Generator | `Stream.iterate(seed, next)` |

---

## Intermediate Operations

Intermediate operations return a new stream and are **lazy**:

| Operation | Description |
|-----------|-------------|
| `filter` | Keep elements matching a predicate |
| `map` | Transform each element |
| `flatMap` | Transform and flatten nested streams |
| `distinct` | Remove duplicates (uses `equals`) |
| `sorted` | Sort (natural or custom order) |
| `limit` / `skip` | Truncate or discard first n |

---

## Pipeline Example

```java
List<String> result = words.stream()
        .filter(w -> w.length() > 3)
        .map(String::toUpperCase)
        .sorted()
        .toList();
```

Nothing executes until a **terminal operation** triggers the pipeline.
Prefer **method references** (`String::toUpperCase`) over lambdas when possible.

---

## Terminal Operations

Terminal operations trigger execution and produce a result:

| Operation | Description |
|-----------|-------------|
| `collect` | Accumulate into a collection or map |
| `reduce` | Combine elements into a single value |
| `forEach` | Perform an action on each element |
| `count` | Count elements |
| `min` / `max` | Find extremal element |
| `findFirst` | First element as `Optional` |
| `anyMatch` / `allMatch` | Short-circuit boolean tests |

---

## Collectors

`Collectors` provides pre-built reduction strategies:

```java
Map<Integer, List<String>> byLength = words.stream()
        .collect(Collectors.groupingBy(String::length));

Map<Boolean, List<Integer>> evenOdd = numbers.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

String joined = words.stream()
        .collect(Collectors.joining(", "));
```

Also: `counting()`, `summingInt`, `averagingDouble`, `summarizingInt`.

---

## Grouping and Partitioning

| Collector | Result |
|-----------|--------|
| `groupingBy(classifier)` | Map of groups |
| `partitioningBy(pred)` | Map with `true`/`false` keys |
| `joining(delimiter)` | Concatenated string |
| `counting()` | Element count per group |
| `summingInt/Long/Double` | Sum per group |

Downstream collectors: `groupingBy(classifier, downstreamCollector)`.

---

## Parallel Streams

```java
long count = largeList.parallelStream()
        .filter(expensivePredicate)
        .count();
```

Use when:
- Data set is **large**
- Operations are **CPU-intensive** and **side-effect free**
- Source supports **efficient splitting** (`ArrayList`, arrays)

Avoid for small data, I/O-bound work, or when order matters.

> **Item 48:** Use caution when making streams parallel.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `StreamCreation` | Collections, arrays, ranges, generators |
| `IntermediateOps` | `filter`, `map`, `flatMap`, `distinct`, `sorted` |
| `TerminalOps` | `reduce`, `collect`, `count`, `min`/`max` |
| `CollectorExamples` | `groupingBy`, `partitioningBy`, `joining` |

```bash
mvn test -pl 14-streams-api
```

---

## Exercises — Your Turn

1. **Stream Exercises** (Guided) — evens, squares, sum, average
2. **Text Analyzer** (Practice) — word count, frequencies, longest word
3. **Transaction Report** (Challenge) — group by category, top expenses

```bash
mvn test -pl 14-streams-api -Dtest="StreamExercisesTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Streams provide a **declarative** way to process collections — describe *what*, not *how*
- **Intermediate operations** are lazy; a **terminal operation** triggers execution
- **`Collectors`** offer powerful grouping, partitioning, and aggregation
- **Parallel streams** help large CPU-bound workloads but add overhead for small data
- Streams are **one-shot** — create a new stream for each pipeline

Further reading: *Effective Java* Items 45, 48 · [java.util.stream](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/package-summary.html)
