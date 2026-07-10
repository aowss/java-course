---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 17'
footer: 'Threads and Synchronization'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 17
## Threads and Synchronization

---

## Objectives

- Create threads using `Thread` subclass, `Runnable` lambda, and `Thread.ofPlatform()`
- Understand the thread lifecycle (New → Runnable → Blocked/Waiting → Terminated)
- Protect shared state with `synchronized` and `volatile`
- Use explicit locks (`ReentrantLock`) and `tryLock()` for deadlock avoidance
- Recognize and prevent deadlock, livelock, and starvation

---

## Creating Threads

**1. Extending `Thread`:**

```java
Thread t = new Thread() {
    @Override public void run() { System.out.println("Running in " + getName()); }
};
t.start();
```

**2. `Runnable` lambda:**

```java
Thread t = new Thread(() -> System.out.println("Hello from " + Thread.currentThread().getName()));
t.start();
```

**3. `Thread.ofPlatform()` (Java 21+):**

```java
Thread t = Thread.ofPlatform().name("my-thread").start(() -> doWork());
```

> Always call `start()`, never `run()` directly — `run()` executes on the **current** thread.

---

## Thread Lifecycle

```
      start()
NEW ────────► RUNNABLE ◄──────────────────────────────────┐
                 │                                         │
                 ├─── synchronized (lock busy) ──► BLOCKED ┘
                 ├─── wait() / join() ──────────► WAITING ─┘
                 ├─── sleep(ms) / join(ms) ─► TIMED_WAITING┘
                 └─── run() returns ──────────► TERMINATED
```

Use `Thread.getState()` to inspect a thread's current state.

---

## The `synchronized` Keyword

Provides **mutual exclusion** and **memory visibility** — only one thread at a time per monitor:

```java
public class SafeCounter {
    private int count = 0;
    public synchronized void increment() { count++; }
    public synchronized int get() { return count; }
}
```

Or synchronize on a specific object:

```java
synchronized (lock) { /* critical section */ }
```

---

## The `volatile` Keyword

`volatile` guarantees **visibility** across threads, but **not** atomicity for compound operations:

```java
private volatile boolean running = true;
public void stop() { running = false; } // immediately visible
```

| Use `volatile` for | Use `synchronized` / `j.u.c` for |
|--------------------|-------------------------------------|
| Simple flags | Compound operations like `count++` |

---

## Explicit Locks — `ReentrantLock`

More flexible than `synchronized`: try-locking, timed locking, interruptible locking:

```java
ReentrantLock lock = new ReentrantLock();
if (lock.tryLock(1, TimeUnit.SECONDS)) {
    try { /* critical section */ }
    finally { lock.unlock(); }
} else { /* could not acquire in time */ }
```

Always release the lock in a `finally` block.

---

## wait() and notifyAll()

Threads coordinate on a shared monitor. Always call `wait()` inside a **`while`** loop:

```java
synchronized (buffer) {
    while (buffer.isEmpty()) {
        buffer.wait();  // releases monitor and waits
    }
    return buffer.poll();
}
```

Never use `if` — guards against **spurious wakeups**.

---

## Common Pitfalls

| Pitfall | Description | Fix |
|---------|-------------|-----|
| **Deadlock** | Threads each hold a lock the other needs | Consistent global lock order, or `tryLock()` |
| **Livelock** | Threads respond without making progress | Redesign coordination logic |
| **Starvation** | A thread never gets CPU time | Fair locks (`new ReentrantLock(true)`) |

Keep critical sections **as short as possible** to reduce contention.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `ThreadCreation` | Thread subclass, Runnable lambda, `Thread.ofPlatform()` |
| `SynchronizedCounter` | Thread-safe vs unsafe counter with `synchronized` |
| `DeadlockDemo` | Deadlock scenario and fix with consistent lock ordering |

```bash
mvn test -pl 17-threads-and-synchronization
```

---

## Exercises — Your Turn

1. **SafeCounter** (Guided) — thread-safe counter with `synchronized`
2. **ProducerConsumer** (Practice) — bounded buffer with `wait()` / `notifyAll()`
3. **DiningPhilosophers** (Challenge) — solve without deadlock using `ReentrantLock.tryLock()`

```bash
mvn test -pl 17-threads-and-synchronization -Dtest="SafeCounterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Never access shared mutable state from multiple threads without synchronization
- Prefer `synchronized` for simple cases; use `ReentrantLock` for try-locking or timed locking
- Always call `wait()` inside a `while` loop — never an `if`
- Acquire locks in a **consistent global order** to prevent deadlock
- `volatile` provides visibility but **not** atomicity

Further reading: [JLS §17.4 — Memory Model](https://docs.oracle.com/javase/specs/jls/se25/html/jls-17.html#jls-17.4) · *Effective Java* Items 78–79
