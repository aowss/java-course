---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 19'
footer: 'Virtual Threads'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 19
## Virtual Threads

---

## Objectives

- Understand the motivation for virtual threads (thread-per-request vs async)
- Build a mental model of user-mode scheduling on carrier threads
- Create virtual threads with `Thread.ofVirtual()`, `startVirtualThread()`, and executors
- Understand structured concurrency and `StructuredTaskScope` (preview)
- Identify migration considerations when adopting virtual threads

---

## Motivation: Thread-per-Request vs Async

**Thread-per-request** is simple but platform threads are expensive (~1 MB stack, 1:1 OS mapping) — limiting servers to a few thousand concurrent requests.

**Async frameworks** (reactive streams, `CompletableFuture` chains) scale well but sacrifice readability and debuggability.

**Virtual threads** offer the best of both: write simple, blocking, thread-per-request code that scales to **millions** of concurrent tasks.

---

## Mental Model

```
┌─────────────────────────────────────────────────────────┐
│  ForkJoinPool (carrier threads = CPU cores)             │
│  Carrier 1    Carrier 2    Carrier 3    Carrier 4       │
│  ┌────────┐  ┌────────┐  ┌────────┐  ┌────────┐       │
│  │ VT-1   │  │ VT-4   │  │ VT-7   │  │ VT-10  │       │
│  │ VT-2   │  │ VT-5   │  │ VT-8   │  │  ...   │       │
│  └────────┘  └────────┘  └────────┘  └────────┘       │
└─────────────────────────────────────────────────────────┘
```

When a VT **blocks** (I/O, sleep, lock), it is **unmounted** — the carrier runs another VT.

---

## Virtual vs Platform Threads

| Property | Platform thread | Virtual thread |
|----------|-----------------|----------------|
| Stack memory | ~1 MB | Few hundred bytes, grows as needed |
| OS mapping | 1:1 | Many:1 on carrier threads |
| Best for | CPU-bound work | I/O-bound workloads |
| Pooling | Yes | **No** — create per task |

Virtual threads are always **daemon** threads with `Thread.NORM_PRIORITY`.

---

## Creating Virtual Threads

```java
// 1. Thread.ofVirtual().start()
Thread vt = Thread.ofVirtual().start(() -> doWork());

// 2. Convenience method
Thread vt = Thread.startVirtualThread(() -> doWork());

// 3. Named factory
ThreadFactory factory = Thread.ofVirtual().name("worker-", 0).factory();

// 4. ExecutorService (recommended)
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> handleRequest(request));
} // close() waits for all submitted tasks
```

---

## Structured Concurrency (Preview)

> `StructuredTaskScope` is a **preview API** in Java 25 — requires `--enable-preview`.

Subtasks are treated as a unit — start together, parent waits for all, failures cancel siblings:

```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Subtask<String> user  = scope.fork(() -> fetchUser(id));
    Subtask<Order>  order = scope.fork(() -> fetchOrder(id));
    scope.join();
    scope.throwIfFailed();
    return new Response(user.get(), order.get());
}
```

Without preview: approximate with `newVirtualThreadPerTaskExecutor()` + try-with-resources.

---

## When to Use Virtual Threads

| Workload | Recommendation |
|----------|------------------|
| HTTP / REST handlers | ✓ Virtual threads |
| Database queries | ✓ Virtual threads |
| File I/O | ✓ Virtual threads |
| CPU-intensive computation | Platform thread pool |
| Long-running background jobs | Platform thread pool |

Rule of thumb: if the task **blocks waiting**, virtual threads shine. If it **computes continuously**, use platform threads.

---

## Migration Considerations

| Do | Don't |
|----|-------|
| Create a new VT per task | Pool virtual threads |
| Use `ReentrantLock` on blocking paths | Use `synchronized` on blocking I/O (pins carrier) |
| Use for **I/O-bound** workloads | Expect gains for CPU-bound tasks |
| Consider `ScopedValue` (preview) | Rely heavily on `ThreadLocal` at scale |

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `VirtualThreadBasics` | Creating virtual threads, named factories, daemon behavior |
| `VirtualVsPlatformThreads` | Scalability comparison, `newVirtualThreadPerTaskExecutor()` |
| `StructuredConcurrencyDemo` | Structured concurrency with virtual threads and executors |

```bash
mvn test -pl 19-virtual-threads
```

---

## Exercises — Your Turn

1. **VirtualThreadPool** (Guided) — execute tasks with `newVirtualThreadPerTaskExecutor()`
2. **WebScraper** (Practice) — concurrent URL fetching with timeout support
3. **StructuredFanOut** (Challenge) — `firstSuccess` and `allSuccesses` patterns

```bash
mvn test -pl 19-virtual-threads -Dtest="VirtualThreadPoolTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Virtual threads enable **thread-per-request** at massive scale without platform-thread overhead
- Use `Executors.newVirtualThreadPerTaskExecutor()` as the primary submission mechanism
- Ideal for **I/O-bound** workloads; CPU-bound work still benefits from platform thread pools
- **Structured concurrency** treats subtasks as a unit — no thread leaks, clear error propagation
- Avoid pooling VTs; avoid `synchronized` on blocking paths; prefer `ReentrantLock`

Further reading: [JEP 444: Virtual Threads](https://openjdk.org/jeps/444) · [JEP 453: Structured Concurrency](https://openjdk.org/jeps/453) · *Effective Java* Item 80
