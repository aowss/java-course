---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 13'
footer: 'Functional Programming'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 13
## Functional Programming

---

## Objectives

- Write **lambda expressions** for functional interfaces
- Use **method references** as shorthand for lambdas
- Know the core `java.util.function` types
- Compose and chain functions and predicates

---

## Lambda Expressions

A lambda is an anonymous function — a block of code passed as a value:

```java
Comparator<String> byLength = (a, b) -> Integer.compare(a.length(), b.length());
Runnable task = () -> System.out.println("Running");
Consumer<String> printer = s -> System.out.println(s);
```

Lambdas require a **functional interface** — exactly one abstract method.
Captured variables must be **effectively final**.

---

## Functional Interface Syntax

```java
// Single parameter — parentheses optional
Function<String, Integer> len = s -> s.length();

// Multiple parameters
BinaryOperator<Integer> add = (a, b) -> a + b;

// Block body
Predicate<String> nonEmpty = s -> {
    return !s.isEmpty();
};
```

The compiler infers parameter types from the target interface.

---

## Effectively Final

Lambdas can capture local variables, but only if they are **effectively final**:

```java
String prefix = "Hello, ";
Consumer<String> greet = name -> System.out.println(prefix + name);
// prefix = "Hi, ";  // compile error — cannot reassign captured variable
```

Instance fields and `this` are freely accessible — no effectively-final restriction.

---

## Method References

When a lambda just calls an existing method, use a method reference:

| Form | Example | Equivalent lambda |
|------|---------|-------------------|
| Static | `Integer::parseInt` | `s -> Integer.parseInt(s)` |
| Instance (bound) | `System.out::println` | `s -> System.out.println(s)` |
| Instance (unbound) | `String::toLowerCase` | `s -> s.toLowerCase()` |
| Constructor | `ArrayList::new` | `() -> new ArrayList<>()` |

---

## Core Functional Interfaces

| Interface | Method | Use case |
|-----------|--------|----------|
| `Predicate<T>` | `test(T) → boolean` | Filtering |
| `Function<T, R>` | `apply(T) → R` | Transformation |
| `Consumer<T>` | `accept(T) → void` | Side effects |
| `Supplier<T>` | `get() → T` | Lazy value production |
| `UnaryOperator<T>` | `apply(T) → T` | Same-type transformation |
| `BinaryOperator<T>` | `apply(T, T) → T` | Combining two values |

---

## Composing Functions

```java
Function<String, String> trim = String::strip;
Function<String, String> upper = String::toUpperCase;
Function<String, String> pipeline = trim.andThen(upper);

Predicate<String> nonEmpty = s -> !s.isEmpty();
Predicate<String> longEnough = s -> s.length() > 5;
Predicate<String> both = nonEmpty.and(longEnough);
```

Also: `compose()` (reverse order), `negate()`, `or()` on predicates.

---

## Lambdas vs Anonymous Classes

```java
// Anonymous class — verbose
Runnable r1 = new Runnable() {
    public void run() { System.out.println("Hi"); }
};

// Lambda — concise
Runnable r2 = () -> System.out.println("Hi");
```

> **Item 42:** Prefer lambdas to anonymous classes when the interface is functional.

Lambdas are the foundation of the **Streams API** (Chapter 14).

---

## Examples

| File | Topic |
|------|-------|
| `LambdaBasics` | Comparator lambdas, Runnable |
| `MethodReferenceDemo` | All four method reference forms |
| `FunctionComposition` | `andThen`, `compose`, predicate chaining |

```bash
mvn test -pl 13-functional-programming
```

---

## Exercises

1. **String Filters** (Guided) — `Predicate<String>` factories and a filter method
2. **Pipeline** (Practice) — chain `Function<T, T>` transformations in a fluent builder
3. **Event Bus** (Challenge) — register listeners by type, publish to matching consumers

```bash
mvn test -pl 13-functional-programming -Dtest="StringFiltersTest"
```



---

## Key Takeaways

- Lambdas require a **functional interface** — one abstract method
- Prefer **method references** when they improve readability
- Use `Predicate`, `Function`, `Consumer` from `java.util.function` — don't reinvent
- Lambdas are the foundation of the **Streams API** (Chapter 14)

Full lesson: [`README.md`](README.md)
Further reading: *Effective Java* Item 42 · [JLS §15.27](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html#jls-15.27)
