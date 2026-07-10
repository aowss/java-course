---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 18'
footer: 'Concurrency Utilities'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 18
## Concurrency Utilities

---

## Objectives

- Create and manage thread pools with `ExecutorService` and `Executors`
- Submit tasks and retrieve results using `Future`
- Build async pipelines with `CompletableFuture`
- Use concurrent collections (`ConcurrentHashMap`, `CopyOnWriteArrayList`)
- Leverage atomic variables (`AtomicInteger`, `AtomicReference`) for lock-free safety

---

## ExecutorService and Thread Pools

Creating raw threads per task is expensive. Thread pools **reuse** a fixed set of threads:

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
Future<String> future = executor.submit(() -> "result");
String value = future.get();   // blocks until complete
executor.shutdown();
```

Always call `shutdown()` when finished — otherwise the JVM won't exit.

---

## Executor Types

| Factory Method | Pool Behaviour |
|----------------|----------------|
| `newFixedThreadPool(n)` | Exactly *n* threads; queues excess tasks |
| `newCachedThreadPool()` | Creates threads as needed; reuses idle ones |
| `newSingleThreadExecutor()` | One thread; tasks execute sequentially |
| `newVirtualThreadPerTaskExecutor()` | One virtual thread per task (Java 21+) |

---

## Future

A `Future<T>` represents a value available later:

```java
Future<Integer> future = executor.submit(() -> expensiveComputation());
int result = future.get();                        // blocks
int result = future.get(5, TimeUnit.SECONDS);     // with timeout
```

Limitations: no chaining, no combining, no error callbacks → use `CompletableFuture`.

---

## CompletableFuture

Fluent, non-blocking API extending `Future`:

| Method | Purpose |
|--------|---------|
| `thenApply(fn)` | Transform result (like `map`) |
| `thenCompose(fn)` | Flat-map to another `CompletableFuture` |
| `thenCombine(other, fn)` | Combine two independent futures |
| `exceptionally(fn)` | Recover from exceptions |
| `allOf(cf...)` | Wait for all futures to complete |

```java
CompletableFuture.supplyAsync(() -> fetchData())
    .thenApply(data -> transform(data))
    .thenCompose(result -> saveAsync(result))
    .exceptionally(ex -> fallback());
```

---

## Concurrent Collections

Standard collections (`HashMap`, `ArrayList`) are **not thread-safe**:

| Collection | Replaces | Key property |
|------------|----------|--------------|
| `ConcurrentHashMap` | `HashMap` | Fine-grained locking; `merge()`, `compute()` |
| `CopyOnWriteArrayList` | `ArrayList` | Copy-on-write; ideal for read-heavy workloads |
| `ConcurrentLinkedQueue` | `LinkedList` | Lock-free FIFO queue |
| `BlockingQueue` | — | Blocking `put()`/`take()` for producers/consumers |

```java
map.merge(key, 1L, Long::sum);   // atomic increment
```

---

## Atomic Variables

`java.util.concurrent.atomic` provides lock-free thread safety for single variables:

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();                    // atomic ++count
count.compareAndSet(expected, newValue);    // CAS
count.getAndUpdate(x -> x * 2);            // atomic read-modify-write
```

Faster than `synchronized` for simple counters — uses hardware CAS instead of locks.

---

## Graceful Executor Shutdown

```java
executor.shutdown();                          // no new tasks
if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
    executor.shutdownNow();                 // cancel running tasks
    executor.awaitTermination(60, TimeUnit.SECONDS);
}
```

- `shutdown()` — orderly shutdown; queued tasks still run
- `shutdownNow()` — attempt to stop all actively executing tasks
- Always shut down to avoid resource leaks and JVM hang on exit

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `ExecutorServiceDemo` | Thread pools, `Future` results, graceful shutdown |
| `CompletableFutureChaining` | `thenApply`, `thenCompose`, `thenCombine`, `exceptionally` |
| `AtomicCounter` | `AtomicInteger` vs non-atomic, `compareAndSet`, `getAndUpdate` |

```bash
mvn test -pl 18-concurrency-utilities
```

---

## Exercises — Your Turn

1. **ParallelSum** (Guided) — split array into chunks, sum in parallel with `ExecutorService`
2. **AsyncPipeline** (Practice) — chain `CompletableFuture` stages with error handling
3. **ConcurrentWordCounter** (Challenge) — count word frequencies with `ConcurrentHashMap.merge()`

```bash
mvn test -pl 18-concurrency-utilities -Dtest="ParallelSumTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Use `ExecutorService` instead of manually creating threads — it manages pooling and lifecycle
- Always shut down executors (`shutdown()` + `awaitTermination()`) to avoid resource leaks
- `CompletableFuture` enables readable async pipelines; use `exceptionally` or `handle` for errors
- `ConcurrentHashMap` provides atomic compound operations without external synchronization
- `AtomicInteger` and friends are the right tool for simple counters and flags

Further reading: [java.util.concurrent](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/package-summary.html) · *Effective Java* Item 81
