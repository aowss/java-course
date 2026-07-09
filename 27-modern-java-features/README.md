# Chapter 27: Modern Java Features

## Objectives

- Use pattern matching in `switch` expressions and statements
- Apply `when` guards for conditional pattern cases
- Use unnamed variables (`_`) to mark intentionally unused bindings
- Combine sealed types with exhaustive pattern matching
- Understand which modern features remain preview-only in Java 25

## Concepts

### Pattern Matching Switch

Traditional `switch` only worked on discrete constants. Pattern matching switch lets each `case` test the **type and shape** of a value:

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

Key properties:

| Feature              | Description                                                    |
|----------------------|----------------------------------------------------------------|
| Type patterns        | `case String s` binds a variable when the runtime type matches |
| Null handling        | `case null` matches before type patterns                       |
| Dominance checking   | Compiler rejects unreachable or overlapping cases              |
| Exhaustiveness       | With sealed hierarchies, the compiler verifies all subtypes     |

### Guarded Patterns

A `when` clause adds a boolean guard evaluated **after** the pattern matches:

```java
return switch (shape) {
    case Rectangle r when r.width() == r.height() -> "square";
    case Rectangle r -> "rectangle";
    case Circle c -> "circle";
};
```

Guards enable specialized cases (squares) without duplicating type checks.

### Unnamed Variables

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

Unnamed variables improve readability by documenting that a value is deliberately ignored.

### Preview Features (README Only)

> **Note:** The following APIs require `--enable-preview` at compile and runtime. They are documented here for awareness; this chapter's code uses only finalized features.

| JEP   | Feature                    | Status in Java 25        |
|-------|----------------------------|--------------------------|
| 459   | Primitive types in patterns | Preview                 |
| 455   | Module import declarations  | Preview                 |
| 512   | Compact source files        | Preview                 |
| 453   | Structured concurrency      | Preview (see Ch. 18)    |
| 481   | Scoped values               | Preview                 |

Preview features may change or be removed. Do not use them in production without understanding the stability risk.

## Examples

| File                                                                                          | Demonstrates                                              |
|-----------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| [`PatternMatchingSwitch.java`](src/main/java/course/ch27/examples/PatternMatchingSwitch.java) | Type patterns, null cases, `when` guards on primitives    |
| [`UnnamedVariables.java`](src/main/java/course/ch27/examples/UnnamedVariables.java)           | `_` in loops, catch, lambdas, and record patterns         |
| [`GuardedPatterns.java`](src/main/java/course/ch27/examples/GuardedPatterns.java)             | Sealed `Shape` hierarchy with guarded exhaustive switch   |

## Exercises

### Exercise 1: ShapeFormatter (Guided)

**File:** [`ShapeFormatter.java`](src/main/java/course/ch27/exercises/ShapeFormatter.java)

Implement `format(Shape)` using pattern matching switch with guards for invalid shapes and squares.

```bash
mvn test -Dtest="course.ch27.exercises.ShapeFormatterTest"
```

### Exercise 2: ExpressionEvaluator (Practice)

**File:** [`ExpressionEvaluator.java`](src/main/java/course/ch27/exercises/ExpressionEvaluator.java)

Evaluate a sealed `Expr` hierarchy (`Literal`, `Add`, `Mul`, `Neg`) recursively.

```bash
mvn test -Dtest="course.ch27.exercises.ExpressionEvaluatorTest"
```

### Exercise 3: JsonPrettyPrinter (Challenge)

**File:** [`JsonPrettyPrinter.java`](src/main/java/course/ch27/exercises/JsonPrettyPrinter.java)

Pretty-print a sealed `JsonValue` hierarchy with 2-space indentation.

```bash
mvn test -Dtest="course.ch27.exercises.JsonPrettyPrinterTest"
```

## Key Takeaways

- **Pattern matching switch** replaces `instanceof` chains with readable, exhaustive dispatch.
- **`when` guards** add conditional logic to individual cases without nested `if` statements.
- **Unnamed variables** (`_`) signal deliberately unused bindings and improve code clarity.
- **Sealed types + patterns** give compile-time exhaustiveness checking — the compiler knows all permitted subtypes.
- **Preview features** require explicit opt-in and may change; prefer finalized APIs for production code.

## Further Reading

- [JEP 441: Pattern Matching for switch](https://openjdk.org/jeps/441)
- [JEP 456: Unnamed Variables & Patterns](https://openjdk.org/jeps/456)
- [JEP 409: Sealed Classes](https://openjdk.org/jeps/409)
- [JLS §14.11 — The switch statement](https://docs.oracle.com/javase/specs/jls/se25/html/jls-14.html#jls-14.11)
