# Quiz — Chapter 7: Encapsulation and Data Modeling

**Part II: Object-Oriented Programming** · ~12 minutes · no IDE required

---

### 1. Concept check

What does the compiler generate for this record?

```java
public record Point(double x, double y) { }
```

Name at least **four** members.

<details>
<summary>Show answer</summary>

Among others, the compiler generates:

- A **canonical constructor** (`Point(double x, double y)`)
- **Accessor methods** (`x()`, `y()` — not `getX()`)
- **`equals()`** and **`hashCode()`**
- **`toString()`**

Records are implicitly `final`. You cannot add extra instance fields beyond the components.

</details>

---

### 2. Spot the bug

```java
public record User(String email) {
    public void setEmail(String email) {
        this.email = email;
    }
}
```

<details>
<summary>Show answer</summary>

**Compile error** — record components are **final**. There is no `this.email` setter field to assign. Records model **immutable** data; create a new record with the updated value instead.

</details>

---

### 3. Predict the output

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

<details>
<summary>Show answer</summary>

Prints approximately **`3.14159…`**

The `switch` is **exhaustive** because `Shape` is sealed and only permits `Circle` and `Rectangle`. No `default` is required.

</details>

---

### 4. Concept check

```java
if (obj instanceof String s) {
    System.out.println(s.length());
}
System.out.println(s.length());
```

Does the second line compile?

<details>
<summary>Show answer</summary>

**No.**

The pattern variable `s` is in scope **only where the compiler knows the match succeeded** — inside the `if` block (with flow-scoping rules). Outside that block, `s` is not defined.

</details>

---

### 5. Concept check

What is wrong?

```java
public enum Planet {
    EARTH, MARS;
    private final double mass;
    Planet(double mass) { this.mass = mass; }
}
```

<details>
<summary>Show answer</summary>

The enum constants `EARTH` and `MARS` invoke the **no-arg** constructor, but only `Planet(double mass)` is defined. Either add a no-arg constructor or pass values:

```java
EARTH(5.97e24), MARS(6.39e23);
```

Enum constructors are implicitly **private**.

</details>

---

### 6. True or false

> A sealed class can be extended by any class in the same package.

<details>
<summary>Show answer</summary>

**False.**

Only types listed in **`permits`** may extend or implement a sealed type (direct subclasses). The compiler enforces the closed hierarchy — that is the point of sealing.

</details>

---

### 7. Concept check

When should you choose each tool?

| Concept | Best for |
|---------|----------|
| `class` | ? |
| `record` | ? |
| `enum` | ? |
| `sealed` hierarchy | ? |

<details>
<summary>Show answer</summary>

| Concept | Best for |
|---------|----------|
| `class` | Mutable objects, rich behavior, identity matters |
| `record` | Immutable data carriers (DTOs, value objects) |
| `enum` | Fixed set of named constants, possibly with data/behavior |
| `sealed` hierarchy | Closed set of subtypes — exhaustive `switch`, domain variants |

</details>

---

### 8. Short answer

Why do sealed types and pattern-matching `switch` work well together?

<details>
<summary>Show answer</summary>

Sealing tells the compiler **every possible subtype**. The compiler can verify that a `switch` over a sealed root is **exhaustive** — if you handle every permitted subtype, no `default` is needed and you get a compile-time error when a new subtype is added but not handled.

</details>
