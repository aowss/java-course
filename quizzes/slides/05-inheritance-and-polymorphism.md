---
marp: true
theme: default
paginate: true
header: 'Java Course — Quiz'
footer: 'Chapter 5: Inheritance and Polymorphism'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  section.answer { background: #e8f6ef; }
  section.answer h2 { color: #1e5631; }
  code { font-size: 0.85em; }
---


# Quiz
## Chapter 5: Inheritance and Polymorphism

Part II: Object-Oriented Programming · ~12 minutes · no IDE required

---

## Q1 · Spot the bug

```java
public class Car extends Vehicle {
    private int doors;

    public Car(String make, int doors) {
        this.doors = doors;
        super(make);
    }
}
```

---

<!-- _class: answer -->

## Answer — Q1

**Compile error:** `super(make)` must be the **first statement** in the constructor. You cannot assign `this.doors` before calling `super(...)`.

Correct order:

```java
public Car(String make, int doors) {
    super(make);
    this.doors = doors;
}
```

---

## Q2 · Predict the output

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

---

<!-- _class: answer -->

## Answer — Q2

**`woof`**

**Dynamic dispatch:** the JVM calls the method based on the **actual object type** (`Dog`), not the declared variable type (`Animal`).

---

## Q3 · Concept check

How many direct superclasses can a Java class extend?

---

<!-- _class: answer -->

## Answer — Q3

**One** (single inheritance). A class can `extends` at most one class. It may `implements` multiple interfaces.

Every class without an explicit superclass implicitly extends `java.lang.Object`.

---

## Q4 · Concept check

What is the difference between **overriding** and **overloading**?

---

<!-- _class: answer -->

## Answer — Q4

| | Overloading | Overriding |
|---|-------------|------------|
| **Where** | Same class (or inherited + new) | Subclass replaces superclass method |
| **Signature** | Must differ (parameter types/count) | Must match (name + parameters) |
| **Resolved** | Compile time | Runtime (dynamic dispatch) |
| **Return type** | Can differ | Must be compatible (covariant allowed) |

---

## Q5 · True or false

> If a subclass overrides `equals()`, it is optional to override `hashCode()` as long as `equals()` is correct.

---

<!-- _class: answer -->

## Answer — Q5

**False.**

The contract requires: if two objects are equal according to `equals()`, they **must** have the same `hashCode()`. Breaking this breaks hash-based collections (`HashMap`, `HashSet`). Override **both** together.

---

## Q6 · Predict the output

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

---

<!-- _class: answer -->

## Answer — Q6

Prints approximately **`3.14159…`** (`Math.PI`).

You cannot `new Shape()` — abstract classes cannot be instantiated. `new Circle(1.0)` is valid, stored in a `Shape` reference, and `area()` dispatches to `Circle`.

---

## Q7 · Spot the bug

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

---

<!-- _class: answer -->

## Answer — Q7

**`name` may be `null`.** Calling `name.equals(other.name)` throws `NullPointerException` if `name` is null.

Safer: `Objects.equals(name, other.name)`, or compare after null checks. Also consider overriding `hashCode()` (see question 5).

---

## Q8 · Short answer

Why must the superclass constructor run before the subclass constructor body executes?

---

<!-- _class: answer -->

## Answer — Q8

The subclass object **is-a** superclass object. The parent part of the object must be in a valid state before the child adds its own fields. That is why initialization order runs parent static → child static → parent instance/constructor → child instance/constructor.

---
