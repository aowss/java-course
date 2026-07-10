# Chapter 17: Threads and Synchronization

## Objectives

- Create threads using `Thread` subclass, `Runnable` lambda, and `Thread.ofPlatform()`
- Understand the thread lifecycle (New, Runnable, Blocked, Waiting, Timed-Waiting, Terminated)
- Protect shared state with the `synchronized` keyword
- Use `volatile` for visibility guarantees without mutual exclusion
- Work with explicit locks (`ReentrantLock`) and `tryLock()` for deadlock avoidance
- Recognize and prevent common pitfalls: deadlock, livelock, and starvation

## Concepts

### Creating Threads

Java offers several ways to create a thread:

**1. Extending `Thread`:**

```java
Thread t = new Thread() {
    @Override
    public void run() {
        System.out.println("Running in " + getName());
    }
};
t.start();
```

**2. Passing a `Runnable` (lambda):**

```java
Thread t = new Thread(() -> System.out.println("Hello from " + Thread.currentThread().getName()));
t.start();
```

**3. Using `Thread.ofPlatform()` (Java 21+):**

```java
Thread t = Thread.ofPlatform()
        .name("my-thread")
        .start(() -> System.out.println("Platform thread"));
```

Always call `start()`, never `run()` directly — calling `run()` executes the code on the current thread instead of starting a new one.

### Thread Lifecycle

```
      start()
NEW ────────► RUNNABLE ◄──────────────────────────────────┐
                 │                                         │
                 ├─── synchronized (lock busy) ──► BLOCKED ┘
                 │                                         │
                 ├─── wait() / join() ──────────► WAITING ─┘
                 │                                         │
                 ├─── sleep(ms) / join(ms) ─► TIMED_WAITING┘
                 │
                 └─── run() returns ──────────► TERMINATED
```

Use `Thread.getState()` to inspect a thread's current state.

### The `synchronized` Keyword

The `synchronized` keyword provides **mutual exclusion** and **memory visibility**. Only one thread at a time can execute a synchronized block guarded by the same monitor:

```java
public class SafeCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int get() {
        return count;
    }
}
```

You can also synchronize on a specific object:

```java
synchronized (lock) {
    // critical section
}
```

### The `volatile` Keyword

`volatile` guarantees that reads and writes to a variable are visible across threads, but it does **not** provide atomicity for compound operations like `count++`:

```java
private volatile boolean running = true;

public void stop() {
    running = false; // immediately visible to other threads
}
```

Use `volatile` for simple flags; use `synchronized` or `java.util.concurrent` classes for compound operations.

### Explicit Locks — `ReentrantLock`

`ReentrantLock` offers more flexibility than `synchronized`: try-locking, timed locking, and interruptible locking:

```java
ReentrantLock lock = new ReentrantLock();

if (lock.tryLock(1, TimeUnit.SECONDS)) {
    try {
        // critical section
    } finally {
        lock.unlock();
    }
} else {
    // could not acquire the lock in time
}
```

Always release the lock in a `finally` block.

### wait() and notifyAll()

Threads can coordinate using `wait()` and `notifyAll()` on a shared monitor. Always call `wait()` inside a `while` loop to guard against spurious wakeups:

```java
synchronized (buffer) {
    while (buffer.isEmpty()) {
        buffer.wait();  // releases the monitor and waits
    }
    return buffer.poll();
}
```

### Common Pitfalls

- **Deadlock** — Two or more threads each hold a lock the other needs. Fix: acquire locks in a consistent global order, or use `tryLock()`.
- **Livelock** — Threads keep responding to each other without making progress (e.g., both yield and retry indefinitely).
- **Starvation** — A thread never gets CPU time because other threads dominate the lock. Fix: use fair locks (`new ReentrantLock(true)`) or redesign the locking strategy.

## Common Mistakes

- **Calling `run()` instead of `start()`** — `run()` executes on the **current** thread. Only `start()` creates a new thread of execution.
- **Using `volatile` for `count++`** — `volatile` gives visibility, not atomicity. Compound read-modify-write still races; use `synchronized`, `AtomicInteger`, or concurrent utilities.
- **Synchronizing on the wrong object** — two threads locking different monitors do not exclude each other. Ensure all threads use the **same** lock instance for shared state.
- **`if` instead of `while` with `wait()`** — always loop: `while (!condition) wait();`. A single `if` misses spurious wakeups and multiple waiters waking at once.
- **Calling `wait()`/`notify()` without holding the lock** — these methods require ownership of the monitor; otherwise you get `IllegalMonitorStateException`.
- **Holding locks during slow I/O** — blocks other threads for the entire network/disk wait. Keep critical sections short; do not guard entire HTTP calls with `synchronized`.
- **Inconsistent lock ordering** — thread A locks `lock1` then `lock2` while thread B does the reverse → deadlock. Acquire locks in a fixed global order (see also **Common Pitfalls** above).

## Examples

| File                                                                                     | Demonstrates                                               |
|------------------------------------------------------------------------------------------|------------------------------------------------------------|
| [`ThreadCreation.java`](src/main/java/course/ch17/examples/ThreadCreation.java)          | Thread subclass, Runnable lambda, Thread.ofPlatform()      |
| [`SynchronizedCounter.java`](src/main/java/course/ch17/examples/SynchronizedCounter.java) | Thread-safe vs. unsafe counter with `synchronized`         |
| [`DeadlockDemo.java`](src/main/java/course/ch17/examples/DeadlockDemo.java)              | Deadlock scenario and fix with consistent lock ordering    |

## Exercises

### Exercise 1: Safe Counter (Guided)

**File:** [`SafeCounter.java`](src/main/java/course/ch17/exercises/SafeCounter.java)

Implement a thread-safe counter using `synchronized`:

- `void increment()` — atomically increments the counter
- `void decrement()` — atomically decrements the counter
- `int get()` — returns the current value

```bash
mvn test -Dtest="course.ch17.exercises.SafeCounterTest"
```

### Exercise 2: Producer–Consumer (Practice)

**File:** [`ProducerConsumer.java`](src/main/java/course/ch17/exercises/ProducerConsumer.java)

Implement a bounded buffer using `wait()` and `notifyAll()`:

- Constructor takes `int capacity`
- `void produce(T item)` — blocks if the buffer is full
- `T consume()` — blocks if the buffer is empty
- `int size()` — returns the current number of items

```bash
mvn test -Dtest="course.ch17.exercises.ProducerConsumerTest"
```

### Exercise 3: Dining Philosophers (Challenge)

**File:** [`DiningPhilosophers.java`](src/main/java/course/ch17/exercises/DiningPhilosophers.java)

Solve the dining philosophers problem without deadlock or starvation:

- Inner class `Philosopher` implements `Runnable`
- `void startDining(int rounds)` — each philosopher eats the given number of rounds
- `int getTotalMealsEaten()` — total meals across all philosophers
- Use `ReentrantLock` with `tryLock()` to prevent deadlock

```bash
mvn test -Dtest="course.ch17.exercises.DiningPhilosophersTest"
```

## Key Takeaways

- Never access shared mutable state from multiple threads without synchronization.
- Prefer `synchronized` for simple cases; use `ReentrantLock` when you need try-locking or timed locking.
- Always call `wait()` inside a `while` loop — never an `if` — to handle spurious wakeups.
- Acquire locks in a consistent global order to prevent deadlock.
- Keep critical sections as short as possible to reduce contention.
- `volatile` provides visibility but not atomicity — it is not a substitute for `synchronized`.

## Further Reading

- [JLS §17.1 — Synchronization](https://docs.oracle.com/javase/specs/jls/se25/html/jls-17.html#jls-17.1)
- [JLS §17.4 — Memory Model](https://docs.oracle.com/javase/specs/jls/se25/html/jls-17.html#jls-17.4)
- [java.util.concurrent Package Summary](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/package-summary.html)
- Effective Java, Item 78: Synchronize access to shared mutable data
- Effective Java, Item 79: Avoid excessive synchronization
- Java Concurrency in Practice (Goetz et al.)
