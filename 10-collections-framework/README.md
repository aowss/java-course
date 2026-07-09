# Chapter 10: Collections Framework

## Objectives

- Know the core collection interfaces: `List`, `Set`, `Map`, `Queue`, `Deque`
- Choose the right implementation for a given use case
- Use `Collections` and `Arrays` utility methods effectively
- Iterate with iterators and the enhanced `for` loop
- Understand sequenced collections (Java 21+)

## Concepts

### Core Interfaces

| Interface | Ordered? | Duplicates? | Key operations                          |
|-----------|----------|-------------|-----------------------------------------|
| `List`    | Yes      | Yes         | `get`, `add`, `remove`, indexed access  |
| `Set`     | Varies   | No          | `add`, `contains`, uniqueness           |
| `Map`     | Varies   | Keys: no    | `put`, `get`, `containsKey`, key-value  |
| `Queue`   | Yes      | Yes         | `offer`, `poll`, `peek`, FIFO           |
| `Deque`   | Yes      | Yes         | Double-ended queue operations           |

### Common Implementations

| Interface | Implementation   | Backing structure | Notes                              |
|-----------|------------------|-------------------|------------------------------------|
| `List`    | `ArrayList`      | Dynamic array     | Fast random access, default choice |
| `List`    | `LinkedList`     | Doubly-linked     | Fast insert/remove at ends       |
| `Set`     | `HashSet`        | Hash table        | O(1) average, no order             |
| `Set`     | `TreeSet`        | Red-black tree    | Sorted order, O(log n)             |
| `Map`     | `HashMap`        | Hash table        | Default map choice                 |
| `Map`     | `TreeMap`        | Red-black tree    | Sorted by keys                     |

### Choosing a Collection

- Need indexed access and frequent iteration? → `ArrayList`
- Need uniqueness? → `HashSet` (or `TreeSet` if sorted)
- Need key-value lookup? → `HashMap`
- Need FIFO processing? → `ArrayDeque` (preferred over `LinkedList` for queues)

### Iteration

```java
List<String> names = List.of("Alice", "Bob", "Carol");

// Enhanced for loop
for (String name : names) {
    System.out.println(name);
}

// Iterator (supports remove during iteration)
var it = names.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

### Sequenced Collections (Java 21+)

`List`, `Deque`, and sorted sets/maps now implement `SequencedCollection` with first/last element access:

```java
List<String> list = new ArrayList<>(List.of("a", "b", "c"));
list.getFirst();  // "a"
list.getLast();   // "c"
list.reversed();  // ["c", "b", "a"]
```

## Examples

| File                                                                               | Demonstrates                              |
|------------------------------------------------------------------------------------|-------------------------------------------|
| [`ListOperations.java`](src/main/java/course/ch10/examples/ListOperations.java)    | `ArrayList`, sorting, reversing           |
| [`SetOperations.java`](src/main/java/course/ch10/examples/SetOperations.java)      | `HashSet`, `TreeSet`, uniqueness          |
| [`MapOperations.java`](src/main/java/course/ch10/examples/MapOperations.java)      | `HashMap`, `TreeMap`, key-value ops       |
| [`QueueDemo.java`](src/main/java/course/ch10/examples/QueueDemo.java)              | `ArrayDeque`, FIFO and LIFO               |

## Exercises

### Exercise 1: Frequency Counter (Guided)

**File:** [`FrequencyCounter.java`](src/main/java/course/ch10/exercises/FrequencyCounter.java)

Count word frequencies in text using a `HashMap`. Return results sorted by frequency.

```bash
mvn test -pl 10-collections-framework -Dtest="FrequencyCounterTest"
```

### Exercise 2: Unique Queue (Practice)

**File:** [`UniqueQueue.java`](src/main/java/course/ch10/exercises/UniqueQueue.java)

Build a FIFO queue that rejects duplicate elements.

```bash
mvn test -pl 10-collections-framework -Dtest="UniqueQueueTest"
```

### Exercise 3: LRU Cache (Challenge)

**File:** [`LRUCache.java`](src/main/java/course/ch10/exercises/LRUCache.java)

Implement a least-recently-used cache with fixed capacity using `LinkedHashMap`.

```bash
mvn test -pl 10-collections-framework -Dtest="LRUCacheTest"
```

## Key Takeaways

- Prefer interface types (`List`, `Set`, `Map`) in variable declarations.
- `ArrayList` and `HashMap` are the default choices for list and map.
- Collections are not thread-safe unless explicitly designed for concurrency (Chapter 17).
- Use streams (Chapter 12) for declarative collection processing.

## Further Reading

- [JLS §4.8 — Raw Types and Generic Types](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.8)
- Effective Java, Item 54: Return empty collections or arrays, not nulls
