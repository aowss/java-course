---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 5'
footer: 'Inheritance and Polymorphism'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 5
## Inheritance and Polymorphism

---

## Objectives

- Extend classes with `extends` and call superclass constructors via `super`
- **Override** methods and understand **dynamic dispatch**
- Define **abstract classes** that force subclass implementations
- Override `equals`, `hashCode`, and `toString` correctly

---

## Extending Classes

A subclass **inherits** accessible fields and methods from its superclass:

```java
public class Car extends Vehicle {
    private final int doors;

    public Car(String make, int doors) {
        super(make);          // must be first statement
        this.doors = doors;
    }
}
```

- **Single inheritance** only — one superclass per class
- Every class implicitly extends `Object`

---

## Initialization with Inheritance

**Parent static** → **Child static** → **Parent instance + ctor** → **Child instance + ctor**

`super(...)` must be the **first** statement in a subclass constructor.

---

## `super` Keyword

| Usage | Example |
|-------|---------|
| Call superclass constructor | `super(make);` — first line |
| Call overridden method | `super.describe()` |
| Access superclass field | `super.field` (if accessible) |

---

## Method Overriding

Subclass provides a new implementation with the same signature:

```java
@Override
public String describe() {
    return getMake() + " car with " + doors + " doors";
}
```

`@Override` is optional but strongly recommended — compiler catches typos.

---

## Dynamic Dispatch

The JVM calls the method for the object's **actual** type, not the variable's declared type:

```java
Vehicle v = new Car("Toyota", 4);
v.describe();   // → "Toyota car with 4 doors" (Car's version)
```

**Declared: Vehicle** → **Actual: Car** → **Calls `Car.describe()`**

This is **polymorphism** — same interface, different behavior.

---

## Abstract Classes

Cannot be instantiated; may declare abstract methods subclasses must implement:

```java
public abstract class Shape {
    public abstract double area();
    public abstract double perimeter();
}
```

Abstract classes can also have **concrete** methods and fields — shared behavior plus contract.

---

## Overriding `Object` Methods

Every class inherits from `Object`. Commonly overridden:

| Method | Purpose |
|--------|---------|
| `toString()` | Human-readable representation |
| `equals(Object)` | Logical equality |
| `hashCode()` | Hash value for collections |

**Contract:** override `hashCode` whenever you override `equals`.

---

## `equals` and `hashCode`

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var other = (Employee) o;
    return Objects.equals(name, other.name);
}

@Override
public int hashCode() {
    return Objects.hash(name, salary);
}
```

Equal objects **must** share the same hash code — required for `HashMap` / `HashSet`.

---

## Examples

| File | Topic |
|------|-------|
| `Shape` + Circle, Rectangle | Abstract classes, overriding, polymorphic calls |
| `Vehicle` + Car, Motorcycle | `extends`, `super`, dynamic dispatch |

```bash
mvn test -pl 05-inheritance-and-polymorphism
```

---

## Exercises

1. **Animal Hierarchy** (Guided) — `speak()` and `toString` for Dog, Cat
2. **Employee Hierarchy** (Practice) — `equals`, `hashCode`, `toString` with `getClass()`
3. **Expression Tree** (Challenge) — `eval()` and parenthesized `toString()`

```bash
mvn test -pl 05-inheritance-and-polymorphism -Dtest="AnimalTest"
```



---

## Key Takeaways

- **Inheritance** reuses and specializes behavior — favor composition over deep hierarchies
- **Dynamic dispatch** — JVM picks the method by the object's actual type
- **Abstract classes** define a contract subclasses must fulfill
- Always override `hashCode` when you override `equals`; use `@Override` everywhere

Full lesson: [`README.md`](README.md)
Further reading: [JLS §8.1.4](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.4) · *Effective Java* Items 10–11
