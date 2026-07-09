# Chapter 17: Concurrency Utilities

## Objectives

- Create and manage thread pools with `ExecutorService` and `Executors`
- Submit tasks and retrieve results using `Future`
- Build asynchronous pipelines with `CompletableFuture` (`thenApply`, `thenCompose`, `thenCombine`, `exceptionally`)
- Use concurrent collections (`ConcurrentHashMap`, `CopyOnWriteArrayList`) for safe shared-state access
- Leverage atomic variables (`AtomicInteger`, `AtomicReference`) for lock-free thread safety

## Concepts

### ExecutorService and Thread Pools

Creating raw threads for every task is expensive and hard to manage. The `java.util.concurrent` framework provides **thread pools** that reuse a fixed set of threads to execute submitted tasks.

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
Future<String> future = executor.submit(() -> "result");
String value = future.get();   // blocks until complete
executor.shutdown();
```

Key executor types:

| Factory Method                           | Pool Behaviour                                         |
|------------------------------------------|--------------------------------------------------------|
| `Executors.newFixedThreadPool(n)`        | Exactly *n* threads; queues excess tasks               |
| `Executors.newCachedThreadPool()`        | Creates threads as needed; reuses idle ones            |
| `Executors.newSingleThreadExecutor()`    | One thread; tasks execute sequentially                 |
| `Executors.newVirtualThreadPerTaskExecutor()` | One virtual thread per task (Java 21+)            |

Always call `shutdown()` when finished — otherwise the JVM won't exit.

### Future

A `Future<T>` represents a value that will be available later:

```java
Future<Integer> future = executor.submit(() -> expensiveComputation());
// ... do other work ...
int result = future.get();           // blocks
int result = future.get(5, TimeUnit.SECONDS); // blocks with timeout
```

Limitations of `Future`: no chaining, no combining, no error callbacks. That's where `CompletableFuture` comes in.

### CompletableFuture

`CompletableFuture` extends `Future` with a fluent, non-blocking API:

| Method                          | Purpose                                                         |
|---------------------------------|-----------------------------------------------------------------|
| `thenApply(fn)`                 | Transform the result (like `map`)                               |
| `thenCompose(fn)`               | Flat-map to another `CompletableFuture`                         |
| `thenCombine(other, fn)`        | Combine two independent futures                                 |
| `exceptionally(fn)`             | Recover from exceptions with a fallback value                   |
| `thenAccept(consumer)`          | Consume the result (returns `CompletableFuture<Void>`)          |
| `allOf(cf...)`                  | Wait for all futures to complete                                |

```java
CompletableFuture.supplyAsync(() -> fetchData())
    .thenApply(data -> transform(data))
    .thenCompose(result -> saveAsync(result))
    .exceptionally(ex -> fallback());
```

### Concurrent Collections

Standard collections (e.g., `HashMap`, `ArrayList`) are **not thread-safe**. The `java.util.concurrent` package provides drop-in replacements:

| Collection                | Thread-safe alternative to | Key property                                  |
|---------------------------|----------------------------|-----------------------------------------------|
| `ConcurrentHashMap`       | `HashMap`                  | Fine-grained locking; `merge()`, `compute()`  |
| `CopyOnWriteArrayList`    | `ArrayList`                | Copy-on-write; ideal for read-heavy workloads |
| `ConcurrentLinkedQueue`   | `LinkedList` (as queue)    | Lock-free FIFO queue                          |
| `BlockingQueue` (interface) | —                        | Blocking `put()`/`take()` for producers/consumers |

`ConcurrentHashMap` offers atomic compound operations:

```java
map.merge(key, 1L, Long::sum);          // atomic increment
map.compute(key, (k, v) -> v == null ? 1L : v + 1);
```

### Atomic Variables

For single variables, `java.util.concurrent.atomic` classes provide lock-free thread safety:

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();                    // atomic ++count
count.compareAndSet(expected, newValue);    // CAS
count.getAndUpdate(x -> x * 2);            // atomic read-modify-write
```

These are faster than `synchronized` for simple counters and flags because they use hardware CAS instructions instead of locks.

## Examples

| File                                                                                                  | Demonstrates                                                       |
|-------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| [`ExecutorServiceDemo.java`](src/main/java/course/ch17/examples/ExecutorServiceDemo.java)             | Thread pools, `Future` results, graceful shutdown                   |
| [`CompletableFutureChaining.java`](src/main/java/course/ch17/examples/CompletableFutureChaining.java) | `thenApply`, `thenCompose`, `thenCombine`, `exceptionally`          |
| [`AtomicCounter.java`](src/main/java/course/ch17/examples/AtomicCounter.java)                         | `AtomicInteger` vs non-atomic counter, `compareAndSet`, `getAndUpdate` |

## Exercises

### Exercise 1: Parallel Sum (Guided)

**File:** [`ParallelSum.java`](src/main/java/course/ch17/exercises/ParallelSum.java)

Split an array into chunks and sum them in parallel using an `ExecutorService`:
- `parallelSum(int[] array, int numThreads)` — divide the array into `numThreads` chunks, submit each to a thread pool, and combine the partial sums
- The result must equal a sequential sum regardless of thread count

```bash
mvn test -Dtest="course.ch17.exercises.ParallelSumTest"
```

### Exercise 2: Async Pipeline (Practice)

**File:** [`AsyncPipeline.java`](src/main/java/course/ch17/exercises/AsyncPipeline.java)

Chain `CompletableFuture` stages into an asynchronous pipeline:
- `process(String input)` — chain: fetch (uppercase) → transform (reverse) → validate (non-empty) → format (add brackets)
- `processWithErrorHandling(String input)` — same pipeline but with `exceptionally` returning `"[ERROR]"` for null/empty input
- Each stage (`fetch`, `transform`, `validate`, `format`) is a separate static method

```bash
mvn test -Dtest="course.ch17.exercises.AsyncPipelineTest"
```

### Exercise 3: Concurrent Word Counter (Challenge)

**File:** [`ConcurrentWordCounter.java`](src/main/java/course/ch17/exercises/ConcurrentWordCounter.java)

Count word frequencies across multiple files concurrently:
- `countWords(List<Path> files)` — read all files using a thread pool, count word occurrences (case-insensitive), return a `Map<String, Long>`
- Use `ConcurrentHashMap` with `merge()` to safely aggregate counts from multiple threads
- Words are sequences of letters (split on non-letter characters)

```bash
mvn test -Dtest="course.ch17.exercises.ConcurrentWordCounterTest"
```

## Key Takeaways

- Use `ExecutorService` instead of manually creating threads — it manages pooling, queuing, and lifecycle.
- Always shut down executors (`shutdown()` + `awaitTermination()`) to avoid resource leaks.
- `CompletableFuture` enables readable async pipelines; use `exceptionally` or `handle` for error recovery.
- `ConcurrentHashMap` provides thread-safe atomic compound operations (`merge`, `compute`) without external synchronization.
- `AtomicInteger` and friends are the right tool for simple counters and flags — faster than `synchronized` blocks.

## Further Reading

- [java.util.concurrent Package Summary](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/package-summary.html)
- [CompletableFuture Javadoc](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/CompletableFuture.html)
- Effective Java, Item 81: Prefer concurrency utilities to `wait` and `notify`
- Java Concurrency in Practice — Brian Goetz et al.
