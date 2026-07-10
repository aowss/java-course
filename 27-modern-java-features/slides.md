---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 27'
footer: 'Modern Java Features'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 27
## Modern Java Features

---

## Objectives

- Use pattern matching in `switch` expressions and statements
- Apply `when` guards for conditional pattern cases
- Use unnamed variables (`_`) to mark intentionally unused bindings
- Combine sealed types with exhaustive pattern matching
- Understand which modern features remain preview-only in Java 25

---

## Pattern Matching Switch

Traditional `switch` only worked on discrete constants. Pattern matching tests the **type and shape** of a value:

```java
String classify(Object obj) {
    return switch (obj) {
        case null -> "null";
        case Integer i when i < 0 -> "negative: " + i;
        case Integer i -> "positive: " + i;
        case String s -> "string: " + s;
        default -> "other";
    };
}
```

---

## Pattern Matching — Key Properties

| Feature | Description |
|---------|-------------|
| Type patterns | `case String s` binds a variable when the runtime type matches |
| Null handling | `case null` matches before type patterns |
| Dominance checking | Compiler rejects unreachable or overlapping cases |
| Exhaustiveness | With sealed hierarchies, the compiler verifies all subtypes |

---

## Guarded Patterns

A `when` clause adds a boolean guard evaluated **after** the pattern matches:

```java
return switch (shape) {
    case Rectangle r when r.width() == r.height() -> "square";
    case Rectangle r -> "rectangle";
    case Circle c -> "circle";
};
```

Guards enable specialized cases (squares) without duplicating type checks.

---

## Pattern Matching vs `instanceof`

```java
// Before — instanceof chain
if (obj instanceof String s) { return "string: " + s; }
else if (obj instanceof Integer i) { return "int: " + i; }

// After — pattern matching switch
return switch (obj) {
    case String s -> "string: " + s;
    case Integer i -> "int: " + i;
    default -> "other";
};
```

Switch expressions return a value; the compiler checks **exhaustiveness** with sealed types.

---

## Sealed Types + Exhaustive Switch

Sealed hierarchies let the compiler verify **all permitted subtypes** are handled:

```java
sealed interface Shape permits Rectangle, Circle { }

return switch (shape) {
    case Rectangle r when r.width() == r.height() -> "square";
    case Rectangle r -> "rectangle";
    case Circle c -> "circle";
    // no default needed — compiler knows all cases
};
```

---

## Unnamed Variables

Use `_` when a binding is required by syntax but intentionally unused:

```java
for (String _ : items) { count++; }

try {
    process();
} catch (IOException _) {
    return false;
}

case Pair(int x, int _) -> x;  // record pattern
```

Unnamed variables document that a value is **deliberately ignored**.

---

## Preview Features (Java 25)

> Require `--enable-preview` at compile and runtime. This chapter uses only **finalized** features.

| JEP | Feature | Status |
|-----|---------|--------|
| 459 | Primitive types in patterns | Preview |
| 455 | Module import declarations | Preview |
| 512 | Compact source files | Preview |
| 453 | Structured concurrency | Preview (see Ch. 18) |
| 481 | Scoped values | Preview |

Do not use preview APIs in production without understanding stability risk.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `PatternMatchingSwitch` | Type patterns, null cases, `when` guards |
| `UnnamedVariables` | `_` in loops, catch, lambdas, record patterns |
| `GuardedPatterns` | Sealed `Shape` hierarchy with exhaustive switch |

```bash
mvn test -pl 27-modern-java-features
```

---

## Exercises — Your Turn

1. **ShapeFormatter** (Guided) — pattern matching switch with guards for squares
2. **ExpressionEvaluator** (Practice) — evaluate sealed `Expr` hierarchy recursively
3. **JsonPrettyPrinter** (Challenge) — pretty-print sealed `JsonValue` with indentation

```bash
mvn test -pl 27-modern-java-features -Dtest="ShapeFormatterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- **Pattern matching switch** replaces `instanceof` chains with readable, exhaustive dispatch
- **`when` guards** add conditional logic to individual cases without nested `if` statements
- **Unnamed variables** (`_`) signal deliberately unused bindings and improve clarity
- **Sealed types + patterns** give compile-time exhaustiveness checking
- **Preview features** require explicit opt-in — prefer finalized APIs for production

Further reading: [JEP 441](https://openjdk.org/jeps/441) · [JEP 456](https://openjdk.org/jeps/456) · [JEP 409](https://openjdk.org/jeps/409)
