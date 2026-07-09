# Chapter 6: Interfaces

## Objectives

- Define and implement interfaces to specify contracts without implementation
- Use default methods to add behavior to interfaces without breaking existing implementors
- Write static methods in interfaces for utility operations
- Understand `@FunctionalInterface` and single abstract method (SAM) types
- Use private methods in interfaces to share logic between default methods

## Concepts

### What Is an Interface?

An **interface** declares a contract — a set of methods that implementing classes must provide. Unlike abstract classes, interfaces support **multiple inheritance of type** — a class can implement several interfaces.

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

### Default Methods

Java 8 introduced **default methods** — methods with a body in an interface. They let you evolve interfaces without forcing every implementor to change:

```java
public interface Logger {
    void log(String message);

    default void logError(String message) {
        log("ERROR: " + message);
    }
}
```

Implementing classes inherit the default behavior but can override it.

### Static Methods in Interfaces

Interfaces can also declare **static methods** for utility operations related to the interface type:

```java
public interface StringUtils {
    static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
```

Call them through the interface name: `StringUtils.isBlank("  ")`.

### Private Methods in Interfaces

Since Java 9, interfaces can declare **private methods** to share code between default methods without exposing helpers to implementors:

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

### Functional Interfaces

A **functional interface** has exactly one abstract method. The `@FunctionalInterface` annotation documents this intent and causes a compile error if a second abstract method is added:

```java
@FunctionalInterface
public interface Validator<T> {
    boolean validate(T value);
}
```

Functional interfaces are the foundation of lambda expressions and method references. Common examples include `Runnable`, `Comparator<T>`, `Predicate<T>`, and `Function<T, R>`.

| Annotation / Concept   | Purpose                                              |
|------------------------|------------------------------------------------------|
| `@FunctionalInterface` | Marks a SAM type; enables lambda/method ref usage    |
| `default`              | Provides inherited behavior with optional override   |
| `static`               | Utility methods scoped to the interface              |
| `private`              | Shared helper logic for default/static methods       |

### Interfaces vs. Abstract Classes

| Feature                  | Interface              | Abstract Class        |
|--------------------------|------------------------|-----------------------|
| Multiple inheritance     | Yes (multiple interfaces) | No (single class) |
| State (fields)           | Only `public static final` constants | Instance fields |
| Constructors             | No                     | Yes                   |
| Default implementation   | Via default methods    | Via concrete methods  |

Prefer interfaces when you need a pure contract. Use abstract classes when related types share significant state or implementation.

## Examples

| File                                                                                | Demonstrates                                              |
|-------------------------------------------------------------------------------------|-----------------------------------------------------------|
| [`Printable.java`](src/main/java/course/ch06/examples/Printable.java)               | Basic interface, default `print()`, static `printAll()`   |
| [`Sortable.java`](src/main/java/course/ch06/examples/Sortable.java)                 | Default methods, private helpers, F-bounded type parameter |
| [`ComparatorDemo.java`](src/main/java/course/ch06/examples/ComparatorDemo.java)     | `Comparator` functional interface, `comparing`, `thenComparing` |

## Exercises

### Exercise 1: Measurable Shapes (Guided)

**File:** [`Measurable.java`](src/main/java/course/ch06/exercises/Measurable.java)

Implement `Circle` and `Rectangle` records that implement `MeasurableShape`:
- `area()` — circle: πr², rectangle: width × height
- `perimeter()` — circle: 2πr, rectangle: 2 × (width + height)
- `totalArea(MeasurableShape...)` — sums all shape areas

```bash
mvn test -Dtest="course.ch06.exercises.MeasurableTest"
```

### Exercise 2: Validator (Practice)

**File:** [`Validator.java`](src/main/java/course/ch06/exercises/Validator.java)

Implement a `@FunctionalInterface Validator<T>` with factory methods:
- `nonNull()` — rejects null values
- `nonBlank()` — rejects null and blank strings
- `lengthBetween(min, max)` — checks string length bounds
- `and(other)` — combines two validators (both must pass)

```bash
mvn test -Dtest="course.ch06.exercises.ValidatorTest"
```

### Exercise 3: Plugin System (Challenge)

**File:** [`PluginSystem.java`](src/main/java/course/ch06/exercises/PluginSystem.java)

Implement a `PluginManager` that:
- Registers plugins and calls `onLoad()` on registration
- Executes all plugins in registration order
- Returns registered plugin names in order

```bash
mvn test -Dtest="course.ch06.exercises.PluginSystemTest"
```

## Key Takeaways

- Interfaces define **contracts** — what a type can do, not how it does it.
- **Default methods** let you extend interfaces without breaking existing code.
- **Static methods** in interfaces provide related utility operations.
- **Private methods** reduce duplication between default methods.
- `@FunctionalInterface` marks a SAM type, enabling **lambdas** and **method references**.
- A class can **implement multiple interfaces**, achieving flexible polymorphism.

## Further Reading

- [JLS §9.1 — Interface Declarations](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.1)
- [JLS §9.4 — Method Declarations](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.4)
- [java.util.Comparator — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Comparator.html)
- JEP 126: Lambda Expressions
- Effective Java, Item 20: Prefer interfaces to abstract classes
