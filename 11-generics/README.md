# Chapter 11: Generics

## Objectives

- Understand why generics exist and the problems they solve (type safety without casting)
- Declare generic classes, interfaces, and methods with type parameters
- Use bounded type parameters to constrain type arguments
- Apply wildcards (`?`, `? extends`, `? super`) for flexible APIs
- Understand type erasure and its implications

## Concepts

### Why Generics?

Before generics (Java 5), collections stored `Object` references. Every retrieval required a cast, and type errors were only caught at runtime:

```java
List names = new ArrayList();
names.add("Alice");
names.add(42);                  // no compile error
String name = (String) names.get(1); // ClassCastException at runtime!
```

Generics let the compiler enforce type safety:

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add(42);                  // compile error — int is not String
String name = names.get(0);    // no cast needed
```

### Generic Classes

A generic class declares one or more **type parameters** in angle brackets:

```java
public class Box<T> {
    private T value;

    public Box(T value) { this.value = value; }
    public T getValue() { return value; }
}
```

The type parameter `T` is a placeholder that is replaced with a concrete type when the class is used:

```java
Box<String> stringBox = new Box<>("Hello");
Box<Integer> intBox = new Box<>(42);
```

Multiple type parameters are separated by commas:

```java
public class Pair<K, V> {
    private final K first;
    private final V second;
    // ...
}
```

### Generic Methods

A method can declare its own type parameters independently of the enclosing class:

```java
public static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) >= 0 ? a : b;
}
```

The type parameter `<T extends Comparable<T>>` appears before the return type. The compiler infers `T` from the arguments:

```java
int bigger = max(3, 7);           // T inferred as Integer
String later = max("apple", "banana"); // T inferred as String
```

### Bounded Type Parameters

You can constrain a type parameter with `extends`:

| Declaration                         | Meaning                                         |
|-------------------------------------|-------------------------------------------------|
| `<T>`                               | Any type                                        |
| `<T extends Number>`                | T must be Number or a subclass                  |
| `<T extends Comparable<T>>`         | T must implement Comparable                     |
| `<T extends Number & Comparable<T>>` | T must satisfy both bounds (class first, then interfaces) |

```java
public static <T extends Number> double sum(List<T> numbers) {
    double total = 0;
    for (T n : numbers) {
        total += n.doubleValue();
    }
    return total;
}
```

### Wildcards

Wildcards provide flexibility when you don't need to name the type parameter:

| Wildcard            | Meaning                                | Can read as | Can write |
|---------------------|----------------------------------------|-------------|-----------|
| `<?>`               | Any type (unbounded)                   | `Object`    | Nothing   |
| `<? extends Number>` | Number or any subclass (upper bound)  | `Number`    | Nothing   |
| `<? super Integer>`  | Integer or any superclass (lower bound) | `Object`  | `Integer` |

**Upper-bounded** wildcards are for reading ("producers"):

```java
public static double sumOfNumbers(List<? extends Number> numbers) {
    double sum = 0;
    for (Number n : numbers) {
        sum += n.doubleValue();
    }
    return sum;
}
```

**Lower-bounded** wildcards are for writing ("consumers"):

```java
public static void addIntegers(List<? super Integer> list, int from, int to) {
    for (int i = from; i < to; i++) {
        list.add(i);
    }
}
```

This is the **PECS** principle: **P**roducer **E**xtends, **C**onsumer **S**uper.

### Type Erasure

Java generics are implemented via **type erasure** — the compiler removes all type parameter information at runtime and inserts casts where needed. This means:

- `List<String>` and `List<Integer>` are the same class (`List`) at runtime
- You cannot use `instanceof` with generic types: `obj instanceof List<String>` won't compile
- You cannot create generic arrays: `new T[10]` won't compile
- You cannot call `new T()` — the type is unknown at runtime

```java
List<String> strings = new ArrayList<>();
List<Integer> ints = new ArrayList<>();
System.out.println(strings.getClass() == ints.getClass()); // true — same class
```

To work around these limitations, use `Object[]` internally (as in `ArrayList`'s implementation) and cast when returning elements.

## Common Mistakes

- **Raw types** — writing `List list = new ArrayList()` disables generics and brings back `ClassCastException` at runtime. Always parameterize: `List<String>`.
- **Ignoring PECS** — using `List<? extends T>` when you need to **add** elements, or `List<? super T>` when you only need to **read**. Producers extend, consumers super.
- **`List<Object>` is not a supertype of `List<String>`** — you cannot pass a `List<String>` where a `List<Object>` is expected. Use wildcards: `List<? extends Object>` or a generic method.
- **Overusing wildcards** — if the same type parameter appears in multiple parameter positions, a named `<T>` is clearer than `?`.
- **Expecting runtime type information** — `list instanceof List<String>` does not compile; after erasure, only `List` exists at runtime.
- **Creating generic arrays** — `new T[10]` and `new List<String>[10]` are illegal. Use `List<T>` or `(T[]) new Object[n]` only inside library code with care.
- **Mixing primitives and generics** — `List<int>` is invalid. Use `List<Integer>` or an `IntStream` for primitive-heavy code.

## Examples

| File                                                                       | Demonstrates                                                  |
|----------------------------------------------------------------------------|---------------------------------------------------------------|
| [`Box.java`](src/main/java/course/ch11/examples/Box.java)                 | Simple generic class with one type parameter                  |
| [`Pair.java`](src/main/java/course/ch11/examples/Pair.java)               | Generic class with two type parameters and a factory method   |
| [`GenericUtils.java`](src/main/java/course/ch11/examples/GenericUtils.java) | Generic methods (max, swap) and wildcard examples            |

## Exercises

### Exercise 1: Stack\<T\> (Guided)

**File:** [`Stack.java`](src/main/java/course/ch11/exercises/Stack.java)

Implement a generic LIFO stack backed by an `Object[]` array:
- `push(T)` — add to top (resize when full)
- `pop()` — remove and return top (throw `EmptyStackException` if empty)
- `peek()` — return top without removing
- `isEmpty()` / `size()`

```bash
mvn test -Dtest="course.ch11.exercises.StackTest"
```

### Exercise 2: Transformer\<T,R\> (Practice)

**File:** [`Transformer.java`](src/main/java/course/ch11/exercises/Transformer.java)

Implement a generic functional interface with:
- `transform(T)` — the abstract method
- `andThen(Transformer)` — compose two transformers
- `applyToList(List, Transformer)` — apply a transformer to every element in a list

```bash
mvn test -Dtest="course.ch11.exercises.TransformerTest"
```

### Exercise 3: SortedList\<T extends Comparable\<T\>\> (Challenge)

**File:** [`SortedList.java`](src/main/java/course/ch11/exercises/SortedList.java)

Implement a list that maintains elements in sorted order:
- `add(T)` — insert at the correct position
- `get(int)` — access by index
- `contains(T)` — binary search for O(log n) lookup
- `size()` / `toList()`

```bash
mvn test -Dtest="course.ch11.exercises.SortedListTest"
```

## Key Takeaways

- Generics provide **compile-time type safety** without casting.
- Use **bounded type parameters** (`<T extends X>`) to constrain what types are allowed.
- Use **wildcards** for flexible method signatures: `? extends` for reading, `? super` for writing (PECS).
- **Type erasure** removes generic type information at runtime — you cannot create generic arrays or use `instanceof` with type parameters.
- Prefer generic types over raw types (`List<String>` over `List`).

## Further Reading

- [JLS §4.5 — Parameterized Types](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.5)
- [JLS §8.1.2 — Generic Classes](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.2)
- [Oracle Tutorial: Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
- [Effective Java, Item 31: Use bounded wildcards](https://www.oreilly.com/library/view/effective-java/9780134686097/)
