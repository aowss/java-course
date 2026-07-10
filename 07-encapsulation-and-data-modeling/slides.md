---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 7'
footer: 'Encapsulation and Data Modeling'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 7
## Encapsulation and Data Modeling

---

## Objectives

- Model immutable data with Java **records**
- Restrict class hierarchies using **sealed classes** and **sealed interfaces**
- Build rich **enums** with fields, constructors, and methods
- Apply **pattern matching** with `instanceof` and `switch` expressions

---

## Records

A **record** is a compact class for immutable data. The compiler generates the constructor, accessors, `equals()`, `hashCode()`, and `toString()`:

```java
public record Point(double x, double y) {
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}
```

Records are implicitly `final`. Add custom methods and compact constructors for validation — but no extra instance fields beyond the components.

```java
public record Email(String address) {
    public Email {
        if (address == null || !address.contains("@")) {
            throw new IllegalArgumentException("Invalid email: " + address);
        }
    }
}
```

---

## Enums with Fields and Methods

Enums are type-safe constants. They can have **fields**, **constructors**, and **instance methods**:

```java
public enum Season {
    SPRING("Spring", 15.0),
    SUMMER("Summer", 28.0);

    private final String displayName;
    private final double averageTempCelsius;

    Season(String displayName, double averageTempCelsius) {
        this.displayName = displayName;
        this.averageTempCelsius = averageTempCelsius;
    }

    public String displayName() { return displayName; }
}
```

Enum constructors are always `private`. Use enums when a fixed set of named constants carries associated behavior or data.

---

## Sealed Classes and Interfaces

**Sealed** types restrict which classes can extend or implement them:

```java
public sealed interface Shape permits Circle, Rectangle, Triangle { }

public record Circle(double radius) implements Shape { }
public record Rectangle(double width, double height) implements Shape { }
public record Triangle(double base, double height) implements Shape { }
```

Only listed **permitted subclasses** can implement a sealed interface — giving the compiler complete knowledge of all subtypes.

---

## Pattern Matching with `instanceof`

Pattern matching `instanceof` combines a type check with a binding:

```java
if (shape instanceof Circle c) {
    System.out.println("Radius: " + c.radius());
}
```

The pattern variable `c` is in scope only where the compiler can prove the match succeeded — no manual casting needed.

---

## Pattern Matching `switch`

`switch` expressions support type patterns for sealed hierarchies:

```java
String describe = switch (shape) {
    case Circle c    -> "Circle r=" + c.radius();
    case Rectangle r -> "Rectangle " + r.width() + "×" + r.height();
    case Triangle t  -> "Triangle base=" + t.base();
};
```

The compiler verifies **exhaustiveness** — every permitted subtype must be handled.

---

## Choosing the Right Modeling Tool

| Feature            | Use when                                           |
|--------------------|----------------------------------------------------|
| `record`           | Immutable data carrier with named components       |
| `enum`             | Fixed set of named constants with behavior         |
| `sealed`           | Closed hierarchy known at compile time             |
| `instanceof` match | Type-narrowing in conditional logic                |
| `switch` match     | Exhaustive dispatch over a sealed hierarchy        |

Choose the tool that best fits each domain concept.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `Point` | Record basics, custom methods, compact data modeling |
| `Season` | Enum fields, constructors, instance methods |
| `ShapeDemo` | Sealed hierarchy, pattern matching `switch`/`instanceof` |

```bash
mvn test -pl 07-encapsulation-and-data-modeling
```

---

## Exercises — Your Turn

1. **Color Enum** (Guided) — `fromHex()`, `isDark()`, `complement()`
2. **Result Type** (Practice) — sealed `Result<T>` with `Success` and `Failure`
3. **JSON Pretty Printer** (Challenge) — sealed `JsonValue` hierarchy with `prettyPrint()`

```bash
mvn test -pl 07-encapsulation-and-data-modeling -Dtest="ColorTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- **Records** are the idiomatic way to model immutable data with minimal boilerplate
- **Enums** go beyond simple constants — they can carry state and behavior
- **Sealed types** create closed hierarchies that enable exhaustive pattern matching
- Pattern matching **`instanceof`** eliminates manual casting in conditionals
- Pattern matching **`switch`** provides concise, compiler-checked dispatch over subtypes

Further reading: [JLS §8.10](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.10) · JEP 409 · JEP 394 · JEP 441
