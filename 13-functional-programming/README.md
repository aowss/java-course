# Chapter 13: Functional Programming

## Objectives

- Write lambda expressions for functional interfaces
- Use method references as shorthand for lambdas
- Know the core `java.util.function` types
- Compose and chain functions and predicates

## Concepts

### Lambda Expressions

A lambda is an anonymous function — a block of code passed as a value:

```java
Comparator<String> byLength = (a, b) -> Integer.compare(a.length(), b.length());
Runnable task = () -> System.out.println("Running");
Consumer<String> printer = s -> System.out.println(s);
```

Lambdas can capture local variables, but only if they are **effectively final**.

### Method References

When a lambda just calls an existing method, use a method reference:

| Form               | Example                    | Equivalent lambda              |
|--------------------|----------------------------|--------------------------------|
| Static             | `Integer::parseInt`        | `s -> Integer.parseInt(s)`     |
| Instance (bound)   | `System.out::println`      | `s -> System.out.println(s)`   |
| Instance (unbound) | `String::toLowerCase`      | `s -> s.toLowerCase()`         |
| Constructor        | `ArrayList::new`           | `() -> new ArrayList<>()`      |

### Core Functional Interfaces

| Interface            | Method               | Use case                    |
|----------------------|----------------------|-----------------------------|
| `Predicate<T>`       | `test(T) → boolean`  | Filtering                   |
| `Function<T, R>`     | `apply(T) → R`       | Transformation              |
| `Consumer<T>`        | `accept(T) → void`   | Side effects                |
| `Supplier<T>`        | `get() → T`          | Lazy value production       |
| `UnaryOperator<T>`   | `apply(T) → T`       | Same-type transformation    |
| `BinaryOperator<T>`  | `apply(T, T) → T`    | Combining two values          |

### Composing Functions

```java
Function<String, String> trim = String::strip;
Function<String, String> upper = String::toUpperCase;
Function<String, String> pipeline = trim.andThen(upper);

Predicate<String> nonEmpty = s -> !s.isEmpty();
Predicate<String> longEnough = s -> s.length() > 5;
Predicate<String> both = nonEmpty.and(longEnough);
```

## Common Mistakes

- **Using a lambda where a method reference reads better** — `s -> System.out.println(s)` should be `System.out::println`; reserve lambdas for non-trivial logic.
- **Wrong method reference form** — `String::toUpperCase` is an unbound instance reference (`s -> s.toUpperCase()`); `System.out::println` is bound. Pick the form that matches how the method is called.
- **Lambdas are not standalone functions** — a lambda must target a **functional interface** (one abstract method). You cannot assign a lambda to `Object` or a non-functional interface.
- **Capturing non-final variables** — lambdas can only use local variables that are **effectively final**. Reassigning `count` before using it inside a lambda is a compile error.
- **Mutating shared state from lambdas** — `list.forEach(x -> total += x)` fails for the same reason, and is unsafe with parallel execution anyway. Use `reduce` or a proper accumulator.
- **Reinventing `java.util.function`** — prefer `Predicate`, `Function`, and `Consumer` over custom single-method interfaces unless you need a domain-specific name.
- **Anonymous classes when a lambda suffices** — for simple functional interfaces, lambdas are shorter. Keep anonymous classes when you need multiple methods or explicit type parameters the compiler cannot infer.

## Examples

| File                                                                                     | Demonstrates                          |
|------------------------------------------------------------------------------------------|---------------------------------------|
| [`LambdaBasics.java`](src/main/java/course/ch13/examples/LambdaBasics.java)              | Comparator lambdas, Runnable          |
| [`MethodReferenceDemo.java`](src/main/java/course/ch13/examples/MethodReferenceDemo.java)| All four method reference forms       |
| [`FunctionComposition.java`](src/main/java/course/ch13/examples/FunctionComposition.java)| andThen, compose, predicate chaining  |

## Exercises

### Exercise 1: String Filters (Guided)

**File:** [`StringFilters.java`](src/main/java/course/ch13/exercises/StringFilters.java)

Build `Predicate<String>` factories and a filter method.

```bash
mvn test -pl 13-functional-programming -Dtest="StringFiltersTest"
```

### Exercise 2: Pipeline (Practice)

**File:** [`Pipeline.java`](src/main/java/course/ch13/exercises/Pipeline.java)

Chain `Function<T, T>` transformations in a fluent builder.

```bash
mvn test -pl 13-functional-programming -Dtest="PipelineTest"
```

### Exercise 3: Event Bus (Challenge)

**File:** [`EventBus.java`](src/main/java/course/ch13/exercises/EventBus.java)

Register listeners by event type and publish events to matching consumers.

```bash
mvn test -pl 13-functional-programming -Dtest="EventBusTest"
```

## Key Takeaways

- Lambdas require a **functional interface** — an interface with exactly one abstract method.
- Prefer method references when they improve readability.
- Use `Predicate`, `Function`, and `Consumer` from `java.util.function` rather than inventing your own.
- Lambdas are the foundation of the Streams API (Chapter 14).

## Further Reading

- [JLS §15.27 — Lambda Expressions](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html#jls-15.27)
- Effective Java, Item 42: Prefer lambdas to anonymous classes
