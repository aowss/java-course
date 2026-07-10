---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 6'
footer: 'Interfaces'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 6
## Interfaces

---

## Objectives

- Define and implement interfaces to specify contracts without implementation
- Use **default methods** to evolve interfaces without breaking implementors
- Write **static** and **private** methods in interfaces
- Understand `@FunctionalInterface` and single abstract method (SAM) types

---

## What Is an Interface?

An **interface** declares a contract — methods that implementing classes must provide. Unlike abstract classes, interfaces support **multiple inheritance of type**.

```java
public interface Drawable {
    void draw();
}

public class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}
```

All interface methods are implicitly `public` and `abstract` unless marked otherwise.

---

## Default Methods

Java 8 introduced **default methods** — methods with a body in an interface. Evolve interfaces without forcing every implementor to change:

```java
public interface Logger {
    void log(String message);

    default void logError(String message) {
        log("ERROR: " + message);
    }
}
```

Implementing classes inherit the default behavior but can override it.

---

## Static Methods in Interfaces

Interfaces can declare **static methods** for utility operations related to the interface type:

```java
public interface StringUtils {
    static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
```

Call them through the interface name: `StringUtils.isBlank("  ")`.

---

## Private Methods in Interfaces

Since Java 9, interfaces can declare **private methods** to share code between default methods without exposing helpers:

```java
public interface Sortable<T> {
    int compareTo(T other);

    default T min(T a, T b) {
        return comparePair(a, b) <= 0 ? a : b;
    }

    private int comparePair(T a, T b) {
        return compareTo(a) < 0 ? -1 : (compareTo(b) > 0 ? 1 : 0);
    }
}
```

---

## Functional Interfaces

A **functional interface** has exactly one abstract method. `@FunctionalInterface` documents intent and causes a compile error if a second abstract method is added:

```java
@FunctionalInterface
public interface Validator<T> {
    boolean validate(T value);
}
```

Functional interfaces are the foundation of **lambda expressions** and **method references** — e.g. `Runnable`, `Comparator<T>`, `Predicate<T>`, `Function<T, R>`.

---

## Interface Features at a Glance

| Annotation / Concept   | Purpose                                              |
|------------------------|------------------------------------------------------|
| `@FunctionalInterface` | Marks a SAM type; enables lambda/method ref usage    |
| `default`              | Provides inherited behavior with optional override   |
| `static`               | Utility methods scoped to the interface              |
| `private`              | Shared helper logic for default/static methods       |

> **Effective Java Item 20:** Prefer interfaces to abstract classes.

---

## Interfaces vs. Abstract Classes

| Feature                  | Interface              | Abstract Class        |
|--------------------------|------------------------|-----------------------|
| Multiple inheritance     | Yes (multiple interfaces) | No (single class) |
| State (fields)           | Only `public static final` constants | Instance fields |
| Constructors             | No                     | Yes                   |
| Default implementation   | Via default methods    | Via concrete methods  |

Prefer interfaces for pure contracts. Use abstract classes when related types share significant state or implementation.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `Printable` | Basic interface, default `print()`, static `printAll()` |
| `Sortable` | Default methods, private helpers, F-bounded type parameter |
| `ComparatorDemo` | `Comparator` functional interface, `comparing`, `thenComparing` |

```bash
mvn test -pl 06-interfaces
```

---

## Exercises — Your Turn

1. **Measurable Shapes** (Guided) — `Circle` and `Rectangle` records implementing `MeasurableShape`
2. **Validator** (Practice) — `@FunctionalInterface` with factory methods and `and()`
3. **Plugin System** (Challenge) — `PluginManager` with registration and ordered execution

```bash
mvn test -pl 06-interfaces -Dtest="MeasurableTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Interfaces define **contracts** — what a type can do, not how it does it
- **Default methods** let you extend interfaces without breaking existing code
- **Static** and **private** methods keep utility and helper logic on the interface
- `@FunctionalInterface` marks a SAM type, enabling **lambdas** and **method references**
- A class can **implement multiple interfaces**, achieving flexible polymorphism

Further reading: [JLS §9.1](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.1) · [JLS §9.4](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.4) · *Effective Java* Item 20
