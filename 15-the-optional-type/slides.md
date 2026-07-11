---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 15'
footer: 'The Optional Type'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 15
## The Optional Type

---

## Objectives

- Create `Optional` values with `of`, `ofNullable`, and `empty`
- Safely unwrap with `orElse`, `orElseGet`, and `orElseThrow`
- Chain transformations with `map`, `flatMap`, and `filter`
- Recognize common **anti-patterns** and their fixes
- Use Optional for **return types** — not fields or parameters

---

## Why Optional?

`Optional<T>` is a container that may or may not hold a value. It models **absence explicitly** in return types:

```java
public Optional<User> findById(String id) {
    return Optional.ofNullable(database.get(id));
}
```

Reduces `NullPointerException` risk by forcing callers to handle absence.

Intended for **return types** — not fields, method parameters, or collections.

---

## Creating Optionals

| Method | When to use |
|--------|-------------|
| `Optional.of(value)` | Value is **guaranteed non-null** |
| `Optional.ofNullable(v)` | Value **may be null** |
| `Optional.empty()` | No value present |

```java
Optional<String> present = Optional.of("hello");       // NPE if null
Optional<String> maybe   = Optional.ofNullable(name);  // safe with null
Optional<String> absent  = Optional.empty();
```

---

## Unwrapping Optionals

| Method | Behavior |
|--------|----------|
| `orElse(default)` | Return value, or default if empty |
| `orElseGet(Supplier)` | Return value, or **lazily** compute default |
| `orElseThrow()` | Return value, or throw `NoSuchElementException` |
| `orElseThrow(Supplier)` | Return value, or throw custom exception |
| `ifPresent(Consumer)` | Run action only if present |
| `ifPresentOrElse(Consumer, Runnable)` | One of two actions based on presence |

**Never** call `get()` without checking — use the methods above.

---

## Chaining: map, flatMap, filter

```java
Optional<String> domain = findEmail(userId)
        .filter(email -> email.contains("@"))
        .map(email -> email.substring(email.indexOf('@') + 1));
```

| Method | Purpose |
|--------|---------|
| `map(fn)` | Transform value if present (wraps in Optional) |
| `flatMap(fn)` | Transform with a function returning Optional (no nesting) |
| `filter(pred)` | Keep value only if it matches a predicate |

---

## When to Use flatMap

Use `flatMap` when your transformation itself returns an `Optional`:

```java
Optional<Integer> port = getString("port")
        .flatMap(SafeParser::parseInt);
```

`map` would nest: `Optional<Optional<Integer>>`.
`flatMap` flattens: `Optional<Integer>`.

---

## Anti-Patterns

| Anti-pattern | Correct approach |
|--------------|------------------|
| `if (opt.isPresent()) opt.get()` | Use `map`, `orElse`, `ifPresent` |
| `Optional.of(possiblyNull)` | Use `Optional.ofNullable()` |
| Optional as a field | Use `null`; Optional for return types |
| Optional as a method parameter | Accept `null` or overload methods |
| `Optional<List<T>>` for empty lists | Return `List.of()` instead |
| Returning `null` instead of empty | Return `Optional.empty()` |

---

## Optional in Streams

```java
Optional<String> first = Stream.of("a", "b", "c").findFirst();

// Java 9+ — drop empty Optionals from a stream
stream.flatMap(Optional::stream)
```

Terminal ops like `findFirst`, `min`, `max` return `Optional` — no more sentinel values.

---

## Examples

| File | Topic |
|------|-------|
| `OptionalCreation` | `of`, `ofNullable`, `empty`, unwrapping |
| `OptionalChaining` | `map`, `flatMap`, `filter`, pipelines |
| `OptionalAntiPatterns` | Common mistakes and correct alternatives |

```bash
mvn test -pl 15-the-optional-type
```

---

## Exercises

1. **Safe Parser** (Guided) — `parseInt`, `parseDouble`, `firstValidInt`
2. **User Service** (Practice) — lookup, email domain, greeting with Optional chains
3. **Config Reader** (Challenge) — typed accessors with defaults and `requireString`

```bash
mvn test -pl 15-the-optional-type -Dtest="SafeParserTest"
```



---

## Key Takeaways

- `Optional` makes **absence explicit** in return types — use it to avoid NPEs
- Prefer `orElse` / `orElseThrow` over `isPresent()` + `get()`
- **`map`** transforms values; **`flatMap`** chains Optional-returning operations
- **`filter`** narrows to values matching a predicate
- Use Optional for **return types only** — not fields, parameters, or collections

Full lesson: [`README.md`](README.md)
Further reading: *Effective Java* Item 55 · [java.util.Optional](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Optional.html)
