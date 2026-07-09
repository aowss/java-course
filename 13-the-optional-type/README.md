# Chapter 13: The Optional Type

## Objectives

- Create `Optional` values with `of`, `ofNullable`, and `empty`
- Safely unwrap Optionals with `orElse`, `orElseGet`, and `orElseThrow`
- Chain transformations with `map`, `flatMap`, and `filter`
- Recognize common Optional anti-patterns and their fixes
- Use Optional appropriately — for return types, not fields or parameters

## Concepts

### Why Optional?

`Optional<T>` is a container that may or may not hold a value. It was introduced in Java 8 to model **absence explicitly** in return types, reducing `NullPointerException` risk.

```java
public Optional<User> findById(String id) {
    return Optional.ofNullable(database.get(id));
}
```

`Optional` is intended for **return types** — not for fields, method parameters, or collections.

### Creating Optionals

| Method                    | When to use                              |
|---------------------------|------------------------------------------|
| `Optional.of(value)`      | Value is **guaranteed non-null**         |
| `Optional.ofNullable(v)`  | Value **may be null**                    |
| `Optional.empty()`        | No value present                         |

```java
Optional<String> present = Optional.of("hello");       // NPE if null
Optional<String> maybe   = Optional.ofNullable(name);  // safe with null
Optional<String> absent  = Optional.empty();
```

### Unwrapping Optionals

| Method                        | Behavior                                    |
|-------------------------------|---------------------------------------------|
| `orElse(default)`             | Return value, or default if empty             |
| `orElseGet(Supplier)`         | Return value, or lazily compute default       |
| `orElseThrow()`               | Return value, or throw `NoSuchElementException` |
| `orElseThrow(Supplier)`       | Return value, or throw custom exception       |
| `ifPresent(Consumer)`         | Run action only if value is present           |
| `ifPresentOrElse(Consumer, Runnable)` | Run one of two actions based on presence |

**Never** call `get()` without checking `isPresent()` — use the methods above instead.

### Chaining: map, flatMap, filter

```java
Optional<String> domain = findEmail(userId)
        .filter(email -> email.contains("@"))
        .map(email -> email.substring(email.indexOf('@') + 1));
```

| Method     | Purpose                                              |
|------------|------------------------------------------------------|
| `map(fn)`  | Transform the value if present (wraps result in Optional) |
| `flatMap(fn)` | Transform with a function that returns Optional (no nesting) |
| `filter(pred)` | Keep the value only if it matches a predicate     |

Use `flatMap` when your transformation itself returns an `Optional`:

```java
Optional<Integer> port = getString("port").flatMap(SafeParser::parseInt);
```

### Anti-Patterns

| Anti-pattern                          | Correct approach                        |
|---------------------------------------|-----------------------------------------|
| `if (opt.isPresent()) opt.get()`      | Use `map`, `orElse`, `ifPresent`        |
| `Optional.of(possiblyNull)`           | Use `Optional.ofNullable()`             |
| Optional as a field                   | Use `null`; Optional for return types   |
| Optional as a method parameter        | Accept `null` or overload methods       |
| `Optional<List<T>>` for empty lists   | Return `List.of()` instead              |
| Returning `null` instead of empty     | Return `Optional.empty()`               |

### Optional in Streams

```java
Optional<String> first = Stream.of("a", "b", "c").findFirst();
stream.flatMap(Optional::stream)  // Java 9+ — drop empty Optionals
```

## Examples

| File                                                                                        | Demonstrates                                           |
|---------------------------------------------------------------------------------------------|--------------------------------------------------------|
| [`OptionalCreation.java`](src/main/java/course/ch13/examples/OptionalCreation.java)         | `of`, `ofNullable`, `empty`, unwrapping methods        |
| [`OptionalChaining.java`](src/main/java/course/ch13/examples/OptionalChaining.java)         | `map`, `flatMap`, `filter`, chaining pipelines         |
| [`OptionalAntiPatterns.java`](src/main/java/course/ch13/examples/OptionalAntiPatterns.java) | Common mistakes and their correct alternatives         |

## Exercises

### Exercise 1: Safe Parser (Guided)

**File:** [`SafeParser.java`](src/main/java/course/ch13/exercises/SafeParser.java)

Implement safe parsing methods that return `Optional`:
- `parseInt(String)` — parse integer, empty on failure
- `parseDouble(String)` — parse double, empty on failure
- `parseBoolean(String)` — parse "true"/"false" only
- `firstValidInt(String...)` — first successfully parsed integer

```bash
mvn test -Dtest="course.ch13.exercises.SafeParserTest"
```

### Exercise 2: User Service (Practice)

**File:** [`UserService.java`](src/main/java/course/ch13/exercises/UserService.java)

Build a user lookup service with Optional chains:
- `findById(String)` — find user by id
- `findEmail(String)` — email if present and non-blank
- `findEmailDomain(String)` — extract domain from email
- `greet(String)` — greeting or default

```bash
mvn test -Dtest="course.ch13.exercises.UserServiceTest"
```

### Exercise 3: Config Reader (Challenge)

**File:** [`ConfigReader.java`](src/main/java/course/ch13/exercises/ConfigReader.java)

Implement typed configuration accessors:
- `getString` / `getInt` / `getBoolean` — typed Optional accessors
- `getStringOrDefault` / `getIntOrDefault` — with fallback values
- `requireString` — throw if missing

```bash
mvn test -Dtest="course.ch13.exercises.ConfigReaderTest"
```

## Key Takeaways

- `Optional` makes **absence explicit** in return types — use it to avoid NPEs.
- Prefer `orElse` / `orElseThrow` over `isPresent()` + `get()`.
- **`map`** transforms values; **`flatMap`** chains Optional-returning operations.
- **`filter`** narrows to values matching a predicate.
- Use `Optional` for **return types only** — not fields, parameters, or collections.
- `Optional.ofNullable()` is the safe choice when a value might be null.

## Further Reading

- [java.util.Optional — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Optional.html)
- JEP 348: Support for Java Time in `Optional`
- Effective Java, Item 55: Return optionals judiciously
- [OpenJDK — Optional guidelines](https://wiki.openjdk.org/display/CodeTools/JDK+8+Optional+guidelines)
