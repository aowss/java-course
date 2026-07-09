# Chapter 7: Encapsulation and Data Modeling

## Objectives

- Model immutable data with Java **records**
- Restrict class hierarchies using **sealed classes** and **sealed interfaces**
- Build rich **enums** with fields, constructors, and methods
- Apply **pattern matching** with `instanceof` and `switch` expressions
- Choose the right modeling tool for each domain concept

## Concepts

### Records

A **record** is a compact class for immutable data. The compiler generates the constructor, accessors, `equals()`, `hashCode()`, and `toString()`:

```java
public record Point(double x, double y) {
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}
```

Records are implicitly `final`. You can add custom methods, static methods, and compact constructors for validation, but cannot add instance fields beyond the record components.

### Enums with Fields and Methods

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

### Sealed Classes and Interfaces

**Sealed** types restrict which classes can extend or implement them:

```java
public sealed interface Shape permits Circle, Rectangle, Triangle { }

public record Circle(double radius) implements Shape { }
public record Rectangle(double width, double height) implements Shape { }
public record Triangle(double base, double height) implements Shape { }
```

Only listed **permitted subclasses** can implement a sealed interface. This gives the compiler complete knowledge of all subtypes — essential for exhaustive pattern matching.

### Pattern Matching with `instanceof`

Pattern matching `instanceof` combines a type check with a binding:

```java
if (shape instanceof Circle c) {
    System.out.println("Radius: " + c.radius());
}
```

The pattern variable `c` is in scope only where the compiler can prove the match succeeded.

### Pattern Matching `switch`

`switch` expressions support type patterns for sealed hierarchies:

```java
String describe = switch (shape) {
    case Circle c    -> "Circle r=" + c.radius();
    case Rectangle r -> "Rectangle " + r.width() + "×" + r.height();
    case Triangle t  -> "Triangle base=" + t.base();
};
```

The compiler verifies **exhaustiveness** — every permitted subtype must be handled.

| Feature            | Use when                                           |
|--------------------|----------------------------------------------------|
| `record`           | Immutable data carrier with named components       |
| `enum`             | Fixed set of named constants with behavior         |
| `sealed`           | Closed hierarchy known at compile time             |
| `instanceof` match | Type-narrowing in conditional logic                |
| `switch` match     | Exhaustive dispatch over a sealed hierarchy        |

## Examples

| File                                                                                    | Demonstrates                                           |
|-----------------------------------------------------------------------------------------|--------------------------------------------------------|
| [`Point.java`](src/main/java/course/ch07/examples/Point.java)                           | Record basics, custom methods, compact data modeling   |
| [`Season.java`](src/main/java/course/ch07/examples/Season.java)                         | Enum fields, constructors, instance methods            |
| [`ShapeDemo.java`](src/main/java/course/ch07/examples/ShapeDemo.java)                   | Sealed hierarchy, pattern matching `switch`/`instanceof` |

## Exercises

### Exercise 1: Color Enum (Guided)

**File:** [`Color.java`](src/main/java/course/ch07/exercises/Color.java)

Implement a `Color` enum with hex codes:
- `fromHex(String)` — parse hex to a color constant (case-insensitive)
- `isDark()` — true if all RGB components are below 128
- `complement()` — return inverted hex string

```bash
mvn test -Dtest="course.ch07.exercises.ColorTest"
```

### Exercise 2: Result Type (Practice)

**File:** [`Result.java`](src/main/java/course/ch07/exercises/Result.java)

Implement a sealed `Result<T>` with `Success` and `Failure`:
- `getOrElse(T)` — return value or default
- `map(Function)` — transform success values
- `describe()` — human-readable description

```bash
mvn test -Dtest="course.ch07.exercises.ResultTest"
```

### Exercise 3: JSON Pretty Printer (Challenge)

**File:** [`JsonValue.java`](src/main/java/course/ch07/exercises/JsonValue.java)

Implement a sealed `JsonValue` hierarchy with `prettyPrint()`:
- Leaf types: `JsonNull`, `JsonBool`, `JsonNumber`, `JsonString`
- Container types: `JsonArray`, `JsonObject`
- Format with 2-space indentation per level

```bash
mvn test -Dtest="course.ch07.exercises.JsonValueTest"
```

## Key Takeaways

- **Records** are the idiomatic way to model immutable data with minimal boilerplate.
- **Enums** go beyond simple constants — they can carry state and behavior.
- **Sealed types** create closed hierarchies that enable exhaustive pattern matching.
- Pattern matching **`instanceof`** eliminates manual casting in conditionals.
- Pattern matching **`switch`** provides concise, compiler-checked dispatch over subtypes.
- Choose the modeling tool that best fits: records for data, enums for fixed sets, sealed types for known hierarchies.

## Further Reading

- [JLS §8.10 — Record Classes](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.10)
- [JLS §8.9 — Enum Classes](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.9)
- [JLS §8.1.6 — Sealed Classes](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.6)
- JEP 409: Sealed Classes
- JEP 394: Pattern Matching for instanceof
- JEP 441: Pattern Matching for switch
