---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 11'
footer: 'Generics'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 11
## Generics

---

## Objectives

- Understand why generics exist — type safety without casting
- Declare generic classes, interfaces, and methods
- Use **bounded type parameters** to constrain type arguments
- Apply wildcards (`?`, `? extends`, `? super`) for flexible APIs
- Understand **type erasure** and its implications

---

## Why Generics?

Before generics, collections stored `Object` — every retrieval required a cast:

```java
List names = new ArrayList();
names.add("Alice");
names.add(42);                       // no compile error
String name = (String) names.get(1); // ClassCastException!
```

With generics, the compiler enforces type safety:

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add(42);                  // compile error
String name = names.get(0);    // no cast needed
```

---

## Generic Classes

Declare **type parameters** in angle brackets:

```java
public class Box<T> {
    private T value;
    public Box(T value) { this.value = value; }
    public T getValue() { return value; }
}
```

```java
Box<String> stringBox = new Box<>("Hello");
Box<Integer> intBox = new Box<>(42);
```

Multiple parameters: `Pair<K, V>` with `first` and `second`.

---

## Generic Methods

Methods declare their own type parameters — independent of the enclosing class:

```java
public static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) >= 0 ? a : b;
}
```

The type parameter appears **before** the return type. The compiler infers `T`:

```java
int bigger = max(3, 7);                // T = Integer
String later = max("apple", "banana"); // T = String
```

---

## Bounded Type Parameters

Constrain a type parameter with `extends`:

| Declaration | Meaning |
|-------------|---------|
| `<T>` | Any type |
| `<T extends Number>` | T must be Number or subclass |
| `<T extends Comparable<T>>` | T must implement Comparable |
| `<T extends Number & Comparable<T>>` | Class first, then interfaces |

```java
public static <T extends Number> double sum(List<T> numbers) {
    double total = 0;
    for (T n : numbers) total += n.doubleValue();
    return total;
}
```

---

## Wildcards Overview

Wildcards provide flexibility when you don't need to name the type parameter:

| Wildcard | Meaning | Can read as | Can write |
|----------|---------|-------------|-----------|
| `<?>` | Any type | `Object` | Nothing |
| `<? extends Number>` | Number or subclass | `Number` | Nothing |
| `<? super Integer>` | Integer or superclass | `Object` | `Integer` |

---

## Upper-Bounded Wildcards — Producers

Use `? extends` when you **read** from a collection:

```java
public static double sumOfNumbers(List<? extends Number> numbers) {
    double sum = 0;
    for (Number n : numbers) {
        sum += n.doubleValue();
    }
    return sum;
}
```

Works with `List<Integer>`, `List<Double>`, etc. — you cannot add elements.

---

## Lower-Bounded Wildcards — Consumers

Use `? super` when you **write** to a collection:

```java
public static void addIntegers(List<? super Integer> list, int from, int to) {
    for (int i = from; i < to; i++) {
        list.add(i);
    }
}
```

Accepts `List<Integer>`, `List<Number>`, `List<Object>`.

---

## PECS Principle

> **P**roducer **E**xtends, **C**onsumer **S**uper

| Role | Wildcard | Example |
|------|----------|---------|
| **Producer** (read) | `? extends T` | `List<? extends Number>` |
| **Consumer** (write) | `? super T` | `List<? super Integer>` |

> **Item 31:** Use bounded wildcards to increase API flexibility.

---

## Type Erasure

Java generics are implemented via **type erasure** — the compiler removes type parameter information at runtime:

```java
List<String> strings = new ArrayList<>();
List<Integer> ints = new ArrayList<>();
System.out.println(strings.getClass() == ints.getClass()); // true
```

The compiler inserts casts where needed. Both lists are just `List` at runtime.

---

## Type Erasure — Limitations

Because type info is erased at runtime:

- Cannot use `instanceof` with generics: `obj instanceof List<String>` won't compile
- Cannot create generic arrays: `new T[10]` won't compile
- Cannot call `new T()` — the type is unknown at runtime

Workaround: use `Object[]` internally (as `ArrayList` does) and cast on return.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `Box` | Simple generic class with one type parameter |
| `Pair` | Two type parameters and a factory method |
| `GenericUtils` | Generic methods (max, swap) and wildcards |

```bash
mvn test -pl 11-generics
```

---

## Exercises — Your Turn

1. **Stack\<T\>** (Guided) — LIFO stack backed by `Object[]`
2. **Transformer\<T,R\>** (Practice) — compose transformers, apply to lists
3. **SortedList\<T extends Comparable\<T\>\>** (Challenge) — sorted insert + binary search

```bash
mvn test -pl 11-generics -Dtest="StackTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Generics provide **compile-time type safety** without casting
- Use **bounded type parameters** (`<T extends X>`) to constrain allowed types
- Use **wildcards** for flexible APIs: `? extends` for reading, `? super` for writing (PECS)
- **Type erasure** removes generic info at runtime — no `instanceof`, no generic arrays
- Prefer `List<String>` over raw `List`

Further reading: *Effective Java* Item 31 · [JLS §4.5](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.5)
