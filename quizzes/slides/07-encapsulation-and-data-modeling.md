---
marp: true
theme: default
paginate: true
header: 'Java Course — Quiz'
footer: 'Chapter 7: Encapsulation and Data Modeling'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  section.answer { background: #e8f6ef; }
  section.answer h2 { color: #1e5631; }
  code { font-size: 0.85em; }
---


# Quiz
## Chapter 7: Encapsulation and Data Modeling

Part II: Object-Oriented Programming · ~12 minutes · no IDE required

---

## Q1 · Concept check

What does the compiler generate for this record?

```java
public record Point(double x, double y) { }
```

Name at least **four** members.

---

<!-- _class: answer -->

## Answer — Q1

Among others, the compiler generates:

- A **canonical constructor** (`Point(double x, double y)`)
- **Accessor methods** (`x()`, `y()` — not `getX()`)
- **`equals()`** and **`hashCode()`**
- **`toString()`**

Records are implicitly `final`. You cannot add extra instance fields beyond the components.

---

## Q2 · Spot the bug

```java
public record User(String email) {
    public void setEmail(String email) {
        this.email = email;
    }
}
```

---

<!-- _class: answer -->

## Answer — Q2

**Compile error** — record components are **final**. There is no `this.email` setter field to assign. Records model **immutable** data; create a new record with the updated value instead.

---

## Q3 · Predict the output

```java
sealed interface Shape permits Circle, Rectangle { }
record Circle(double radius) implements Shape { }
record Rectangle(double width, double height) implements Shape { }

static double area(Shape shape) {
    return switch (shape) {
        case Circle c -> Math.PI * c.radius() * c.radius();
        case Rectangle r -> r.width() * r.height();
    };
}

public static void main(String[] args) {
    System.out.println(area(new Circle(1.0)));
}
```

---

<!-- _class: answer -->

## Answer — Q3

Prints approximately **`3.14159…`**

The `switch` is **exhaustive** because `Shape` is sealed and only permits `Circle` and `Rectangle`. No `default` is required.

---

## Q4 · Concept check

```java
if (obj instanceof String s) {
    System.out.println(s.length());
}
System.out.println(s.length());
```

Does the second line compile?

---

<!-- _class: answer -->

## Answer — Q4

**No.**

The pattern variable `s` is in scope **only where the compiler knows the match succeeded** — inside the `if` block (with flow-scoping rules). Outside that block, `s` is not defined.

---

## Q5 · Concept check

What is wrong?

```java
public enum Planet {
    EARTH, MARS;
    private final double mass;
    Planet(double mass) { this.mass = mass; }
}
```

---

<!-- _class: answer -->

## Answer — Q5

The enum constants `EARTH` and `MARS` invoke the **no-arg** constructor, but only `Planet(double mass)` is defined. Either add a no-arg constructor or pass values:

```java
EARTH(5.97e24), MARS(6.39e23);
```

Enum constructors are implicitly **private**.

---

## Q6 · True or false

> A sealed class can be extended by any class in the same package.

---

<!-- _class: answer -->

## Answer — Q6

**False.**

Only types listed in **`permits`** may extend or implement a sealed type (direct subclasses). The compiler enforces the closed hierarchy — that is the point of sealing.

---

## Q7 · Concept check

When should you choose each tool?

| Concept | Best for |
|---------|----------|
| `class` | ? |
| `record` | ? |
| `enum` | ? |
| `sealed` hierarchy | ? |

---

<!-- _class: answer -->

## Answer — Q7

| Concept | Best for |
|---------|----------|
| `class` | Mutable objects, rich behavior, identity matters |
| `record` | Immutable data carriers (DTOs, value objects) |
| `enum` | Fixed set of named constants, possibly with data/behavior |
| `sealed` hierarchy | Closed set of subtypes — exhaustive `switch`, domain variants |

---

## Q8 · Short answer

Why do sealed types and pattern-matching `switch` work well together?

---

<!-- _class: answer -->

## Answer — Q8

Sealing tells the compiler **every possible subtype**. The compiler can verify that a `switch` over a sealed root is **exhaustive** — if you handle every permitted subtype, no `default` is needed and you get a compile-time error when a new subtype is added but not handled.

---
