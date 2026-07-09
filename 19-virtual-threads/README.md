# Chapter 19: Virtual Threads

## Objectives

- Understand the motivation for virtual threads (thread-per-request vs async)
- Build a mental model of how virtual threads work (user-mode scheduling on carrier threads)
- Create virtual threads using `Thread.ofVirtual()`, `Thread.startVirtualThread()`, and `Executors.newVirtualThreadPerTaskExecutor()`
- Understand structured concurrency concepts and `StructuredTaskScope` (preview)
- Identify migration considerations when adopting virtual threads

## Concepts

### Motivation: Thread-per-Request vs Async

Traditional server applications use a **thread-per-request** model: each incoming request is handled by a dedicated thread. This is simple to code and reason about, but platform threads are expensive (each consumes ~1MB of stack memory and maps to an OS thread), limiting servers to a few thousand concurrent requests.

To scale beyond this, developers adopted **asynchronous** frameworks (reactive streams, callbacks, `CompletableFuture` chains). These scale well but sacrifice readability — the code no longer reads top-to-bottom and debugging becomes difficult.

Virtual threads offer the best of both worlds: write simple, blocking, thread-per-request code that scales to millions of concurrent tasks.

### Mental Model

```
┌─────────────────────────────────────────────────────────────────┐
│  JVM                                                            │
│                                                                 │
│  ┌─────────────────────────────────────────────────────────┐    │
│  │  ForkJoinPool (carrier threads = CPU cores)             │    │
│  │                                                         │    │
│  │  Carrier 1    Carrier 2    Carrier 3    Carrier 4       │    │
│  │  ┌────────┐  ┌────────┐  ┌────────┐  ┌────────┐       │    │
│  │  │ VT-1   │  │ VT-4   │  │ VT-7   │  │ VT-10  │       │    │
│  │  │ VT-2   │  │ VT-5   │  │ VT-8   │  │ VT-11  │       │    │
│  │  │ VT-3   │  │ VT-6   │  │ VT-9   │  │  ...   │       │    │
│  │  └────────┘  └────────┘  └────────┘  └────────┘       │    │
│  └─────────────────────────────────────────────────────────┘    │
│                                                                 │
│  Virtual threads are scheduled onto carrier threads.            │
│  When a VT blocks (I/O, sleep), it is unmounted from its       │
│  carrier, freeing the carrier for another VT.                   │
└─────────────────────────────────────────────────────────────────┘
```

- **Virtual threads** are lightweight (a few hundred bytes of stack initially, growing as needed)
- **Carrier threads** are platform threads in a `ForkJoinPool` (typically one per CPU core)
- When a virtual thread performs a **blocking operation** (I/O, `Thread.sleep`, lock acquisition), it is **unmounted** from the carrier — the carrier is free to run another virtual thread
- Virtual threads are always **daemon threads** and have priority `Thread.NORM_PRIORITY`

### Creating Virtual Threads

```java
// 1. Thread.ofVirtual().start()
Thread vt = Thread.ofVirtual().start(() -> doWork());

// 2. Convenience method
Thread vt = Thread.startVirtualThread(() -> doWork());

// 3. Named thread factory
ThreadFactory factory = Thread.ofVirtual().name("worker-", 0).factory();
Thread t = factory.newThread(() -> doWork());
t.start();

// 4. ExecutorService (recommended for task submission)
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> handleRequest(request));
} // close() waits for all submitted tasks
```

### Structured Concurrency (Preview)

> **Note:** `StructuredTaskScope` is a **preview API** in Java 25. It requires the `--enable-preview` flag at both compile time and runtime. The concepts are stable, but the API may change in future releases.

Structured concurrency ensures that concurrent subtasks are treated as a unit — they start together, and the parent waits for all of them before continuing. If one fails, the others can be cancelled.

```java
// Preview API — requires --enable-preview
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Subtask<String> user  = scope.fork(() -> fetchUser(id));
    Subtask<Order>  order = scope.fork(() -> fetchOrder(id));

    scope.join();
    scope.throwIfFailed();

    return new Response(user.get(), order.get());
}
```

Without the preview flag, you can approximate structured concurrency using `Executors.newVirtualThreadPerTaskExecutor()` with try-with-resources (see Example 3).

### Migration Considerations

- **Do not pool virtual threads** — pooling defeats their purpose. Create a new one per task.
- **Avoid `synchronized` blocks** that guard blocking operations — they pin the virtual thread to its carrier. Use `ReentrantLock` instead.
- **Thread-local variables** work but consider `ScopedValue` (preview) for virtual threads, as thread-locals are expensive when millions of threads exist.
- **CPU-bound tasks** do not benefit from virtual threads — they need a carrier the entire time.
- Virtual threads are ideal for **I/O-bound workloads** (HTTP calls, database queries, file I/O).

## Examples

| File                                                                                                    | Demonstrates                                                              |
| ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------- |
| [`VirtualThreadBasics.java`](src/main/java/course/ch19/examples/VirtualThreadBasics.java)               | Creating virtual threads, named factories, daemon behavior                |
| [`VirtualVsPlatformThreads.java`](src/main/java/course/ch19/examples/VirtualVsPlatformThreads.java)     | Scalability comparison, `Executors.newVirtualThreadPerTaskExecutor()`      |
| [`StructuredConcurrencyDemo.java`](src/main/java/course/ch19/examples/StructuredConcurrencyDemo.java)   | Structured concurrency patterns with virtual threads and `ExecutorService` |

## Exercises

### Exercise 1: Virtual Thread Pool (Guided)

**File:** [`VirtualThreadPool.java`](src/main/java/course/ch19/exercises/VirtualThreadPool.java)

Implement:
- `processAll(List<Callable<T>> tasks)` — execute all tasks using `Executors.newVirtualThreadPerTaskExecutor()`, collect results in submission order, wrap failures in `RuntimeException`

```bash
mvn test -Dtest="course.ch19.exercises.VirtualThreadPoolTest"
```

### Exercise 2: Web Scraper (Practice)

**File:** [`WebScraper.java`](src/main/java/course/ch19/exercises/WebScraper.java)

Implement:
- `fetchAll(List<String> urls)` — simulate fetching each URL concurrently (sleep 10ms, return `"Content of <url>"`)
- `fetchAllWithTimeout(List<String> urls, Duration timeout)` — same but throw `TimeoutException` if the operation exceeds the timeout

```bash
mvn test -Dtest="course.ch19.exercises.WebScraperTest"
```

### Exercise 3: Structured Fan-Out (Challenge)

**File:** [`StructuredFanOut.java`](src/main/java/course/ch19/exercises/StructuredFanOut.java)

Implement:
- `firstSuccess(List<Callable<T>> tasks)` — run all tasks on virtual threads, return the first successful result. If all fail, throw `RuntimeException` with failures as suppressed exceptions.
- `allSuccesses(List<Callable<T>> tasks, Duration timeout)` — run all tasks, collect successful results within the timeout

```bash
mvn test -Dtest="course.ch19.exercises.StructuredFanOutTest"
```

## Key Takeaways

- Virtual threads enable the **thread-per-request** model at massive scale without the memory overhead of platform threads.
- Use `Executors.newVirtualThreadPerTaskExecutor()` as the primary way to submit tasks to virtual threads.
- Virtual threads are ideal for **I/O-bound** workloads; CPU-bound work still benefits from platform thread pools.
- **Structured concurrency** (`StructuredTaskScope`, preview) treats concurrent subtasks as a unit — no thread leaks, clear error propagation, coordinated cancellation.
- Avoid pooling virtual threads, avoid `synchronized` on blocking paths, and prefer `ReentrantLock` or `ScopedValue` for virtual-thread-friendly code.

## Further Reading

- [JEP 444: Virtual Threads](https://openjdk.org/jeps/444)
- [JEP 453: Structured Concurrency (Preview)](https://openjdk.org/jeps/453)
- [JEP 481: Scoped Values (Preview)](https://openjdk.org/jeps/481)
- [Java Virtual Threads Documentation](https://docs.oracle.com/en/java/javase/25/core/virtual-threads.html)
- Effective Java, Item 80: Prefer executors, tasks, and streams to threads
