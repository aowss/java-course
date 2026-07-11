# Quiz — Chapter 5: Inheritance and Polymorphism

**Part II: Object-Oriented Programming** · ~12 minutes

---

### 1. Spot the bug

```java
public class Car extends Vehicle {
    private int doors;

    public Car(String make, int doors) {
        this.doors = doors;
        super(make);
    }
}
```

<details>
<summary>Show answer</summary>

**Compile error:** `super(make)` must be the **first statement** in the constructor. You cannot assign `this.doors` before calling `super(...)`.

Correct order:

```java
public Car(String make, int doors) {
    super(make);
    this.doors = doors;
}
```

</details>

---

### 2. Predict the output

```java
class Animal {
    String speak() { return "..."; }
}
class Dog extends Animal {
    @Override String speak() { return "woof"; }
}

Animal a = new Dog();
System.out.println(a.speak());
```

<details>
<summary>Show answer</summary>

**`woof`**

**Dynamic dispatch:** the JVM calls the method based on the **actual object type** (`Dog`), not the declared variable type (`Animal`).

</details>

---

### 3. Concept check

How many direct superclasses can a Java class extend?

<details>
<summary>Show answer</summary>

**One** (single inheritance). A class can `extends` at most one class. It may `implements` multiple interfaces.

Every class without an explicit superclass implicitly extends `java.lang.Object`.

</details>

---

### 4. Concept check

What is the difference between **overriding** and **overloading**?

<details>
<summary>Show answer</summary>

| | Overloading | Overriding |
|---|-------------|------------|
| **Where** | Same class (or inherited + new) | Subclass replaces superclass method |
| **Signature** | Must differ (parameter types/count) | Must match (name + parameters) |
| **Resolved** | Compile time | Runtime (dynamic dispatch) |
| **Return type** | Can differ | Must be compatible (covariant allowed) |

</details>

---

### 5. True or false

> If a subclass overrides `equals()`, it is optional to override `hashCode()` as long as `equals()` is correct.

<details>
<summary>Show answer</summary>

**False.**

The contract requires: if two objects are equal according to `equals()`, they **must** have the same `hashCode()`. Breaking this breaks hash-based collections (`HashMap`, `HashSet`). Override **both** together.

</details>

---

### 6. Predict the output

```java
abstract class Shape {
    abstract double area();
}
class Circle extends Shape {
    private final double radius;
    Circle(double radius) { this.radius = radius; }
    @Override double area() { return Math.PI * radius * radius; }
}

public class Main {
    public static void main(String[] args) {
        Shape s = new Circle(1.0);
        System.out.println(s.area());
    }
}
```

<details>
<summary>Show answer</summary>

Prints approximately **`3.14159…`** (`Math.PI`).

You cannot `new Shape()` — abstract classes cannot be instantiated. `new Circle(1.0)` is valid, stored in a `Shape` reference, and `area()` dispatches to `Circle`.

</details>

---

### 7. Spot the bug

```java
class Employee {
    String name;
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) return false;
        Employee other = (Employee) o;
        return name.equals(other.name);
    }
}
```

What edge case is mishandled?

<details>
<summary>Show answer</summary>

**`name` may be `null`.** Calling `name.equals(other.name)` throws `NullPointerException` if `name` is null.

Safer: `Objects.equals(name, other.name)`, or compare after null checks. Also consider overriding `hashCode()` (see question 5).

</details>

---

### 8. Short answer

Why must the superclass constructor run before the subclass constructor body executes?

<details>
<summary>Show answer</summary>

The subclass object **is-a** superclass object. The parent part of the object must be in a valid state before the child adds its own fields. That is why initialization order runs parent static → child static → parent instance/constructor → child instance/constructor.

</details>
