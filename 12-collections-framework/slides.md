---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 12'
footer: 'Collections Framework'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 12
## Collections Framework

---

## Objectives

- Know the core interfaces: `List`, `Set`, `Map`, `Queue`, `Deque`
- Choose the right implementation for a given use case
- Use `Collections` and `Arrays` utility methods
- Iterate with iterators and the enhanced `for` loop
- Understand **sequenced collections** (Java 21+)

---

## Core Interfaces

| Interface | Ordered? | Duplicates? | Key operations |
|-----------|----------|-------------|----------------|
| `List` | Yes | Yes | `get`, `add`, `remove`, indexed access |
| `Set` | Varies | No | `add`, `contains`, uniqueness |
| `Map` | Varies | Keys: no | `put`, `get`, `containsKey` |
| `Queue` | Yes | Yes | `offer`, `poll`, `peek`, FIFO |
| `Deque` | Yes | Yes | Double-ended queue operations |

---

## Common Implementations

| Interface | Implementation | Backing structure | Notes |
|-----------|----------------|-------------------|-------|
| `List` | `ArrayList` | Dynamic array | Fast random access — default |
| `List` | `LinkedList` | Doubly-linked | Fast insert/remove at ends |
| `Set` | `HashSet` | Hash table | O(1) average, no order |
| `Set` | `TreeSet` | Red-black tree | Sorted order, O(log n) |
| `Map` | `HashMap` | Hash table | Default map choice |
| `Map` | `TreeMap` | Red-black tree | Sorted by keys |

---

## Choosing a Collection

| Need | Choose |
|------|--------|
| Indexed access + frequent iteration | `ArrayList` |
| Uniqueness | `HashSet` (or `TreeSet` if sorted) |
| Key-value lookup | `HashMap` |
| FIFO processing | `ArrayDeque` (preferred over `LinkedList`) |

> Declare variables with **interface types** (`List`, `Set`, `Map`) — not concrete classes.

---

## List Operations

```java
List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Carol"));
names.add("Dave");
names.get(0);           // "Alice"
names.remove("Bob");
Collections.sort(names);
```

`ArrayList` — O(1) random access, O(n) insert in middle.
`LinkedList` — O(n) access, O(1) insert at ends.

---

## Set and Map Operations

```java
Set<String> unique = new HashSet<>();
unique.add("a"); unique.add("a"); // size stays 1

Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.get("Alice"); // 95
```

`TreeSet` / `TreeMap` — elements sorted by natural order or a `Comparator`.

---

## Iteration

```java
List<String> names = List.of("Alice", "Bob", "Carol");

// Enhanced for loop
for (String name : names) {
    System.out.println(name);
}

// Iterator — supports remove during iteration
var it = names.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

---

## Sequenced Collections (Java 21+)

`List`, `Deque`, and sorted sets/maps implement `SequencedCollection`:

```java
List<String> list = new ArrayList<>(List.of("a", "b", "c"));
list.getFirst();  // "a"
list.getLast();   // "c"
list.reversed();  // ["c", "b", "a"]
```

First/last element access and reversed views — no more `get(0)` / `get(size-1)`.

---

## Queue and Deque

```java
Deque<String> queue = new ArrayDeque<>();
queue.offer("first");   // add to tail
queue.poll();           // remove from head (FIFO)

queue.push("top");      // add to front
queue.pop();            // remove from front (LIFO / stack)
```

Prefer `ArrayDeque` over `LinkedList` for queue and stack operations.

---

## Examples

| File | Topic |
|------|-------|
| `ListOperations` | `ArrayList`, sorting, reversing |
| `SetOperations` | `HashSet`, `TreeSet`, uniqueness |
| `MapOperations` | `HashMap`, `TreeMap`, key-value ops |
| `QueueDemo` | `ArrayDeque`, FIFO and LIFO |

```bash
mvn test -pl 12-collections-framework
```

---

## Exercises

1. **Frequency Counter** (Guided) — word frequencies with `HashMap`, sorted by count
2. **Unique Queue** (Practice) — FIFO queue that rejects duplicates
3. **LRU Cache** (Challenge) — fixed-capacity cache with `LinkedHashMap`

```bash
mvn test -pl 12-collections-framework -Dtest="FrequencyCounterTest"
```



---

## Key Takeaways

- Prefer **interface types** (`List`, `Set`, `Map`) in variable declarations
- `ArrayList` and `HashMap` are the default choices for list and map
- Collections are **not thread-safe** unless designed for concurrency (Chapter 18)
- Use **streams** (Chapter 14) for declarative collection processing

Full lesson: [`README.md`](README.md)
Further reading: *Effective Java* Item 54 · [JLS §4.8](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.8)
