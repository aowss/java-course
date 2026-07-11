---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 4'
footer: 'Classes and Objects'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 4
## Classes and Objects

---

## Objectives

- Understand classes as **blueprints** for objects
- Declare **fields**, **constructors**, and **methods**
- Use **`this`** to refer to the current instance
- Apply **access modifiers** for encapsulation
- Combine **`static`** and **`final`** modifiers
- Distinguish **static** (class) vs **instance** (object) members
- Understand **initialization blocks** and their execution order

---

## Classes and Objects

A **class** defines structure and behavior. An **object** is a concrete instance:

```java
public class Dog {
    String name;
    int age;
}

Dog d = new Dog();
d.name = "Rex";
d.age = 3;
```

Each instance gets its own copy of instance fields.

---

## Fields and Defaults

Fields hold the **state** of an object:

```java
public class Point {
    double x;
    double y;
}
```

Uninitialized fields get defaults: `0` for numbers, `false` for `boolean`, `null` for references.

---

## Constructors

Same name as the class, no return type:

```java
public Point(double x, double y) {
    this.x = x;
    this.y = y;
}

var p = new Point(3.0, 4.0);
```

Write at least one constructor → Java's default no-arg constructor **disappears**.

---

## The `this` Keyword

| Usage | Example |
|-------|---------|
| Disambiguate field from parameter | `this.name = name;` |
| Call another constructor | `this(defaultValue);` — first statement only |
| Pass current object | `list.add(this);` |

---

## Methods and Encapsulation

```java
public class BankAccount {
    private double balance;

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double getBalance() { return balance; }
}
```

Make fields **`private`** — expose only what clients need through methods.

---

## Access Modifiers

| Modifier | Class | Package | Subclass | World |
|----------|:-----:|:-------:|:--------:|:-----:|
| `public` | ✓ | ✓ | ✓ | ✓ |
| `protected` | ✓ | ✓ | ✓ | |
| *(none)* | ✓ | ✓ | | |
| `private` | ✓ | | | |

*(none)* = **package-private**. Prefer the most restrictive level that works.

---

## Static Members

Belong to the **class**, not any instance — one shared copy:

```java
public class Counter {
    private static int totalCount;   // shared
    private int count;               // per instance

    public static int getTotalCount() {
        return totalCount;
    }
}
```

- Static methods have no `this` — cannot access instance members directly
- Access via class name: `Counter.getTotalCount()`

---

## Combining Modifiers

Convention: **access → static → final**

```java
public static final int MAX = 100;   // class constant
private final String name;           // set once per instance
```

---

## Initialization Blocks

```java
static { /* runs once when class loads */ }
{ /* runs before each constructor */ }
```

**Order for `new Demo()`:** static fields → static blocks → instance fields → instance blocks → constructor

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `BankAccount` | Fields, constructor, `this`, encapsulation |
| `Counter` | Static vs instance members |
| `InitializationOrder` | Static/instance blocks and field initializer order |

```bash
mvn test -pl 04-classes-and-objects
```

---

## Exercises — Your Turn

1. **Rectangle** (Guided) — area, perimeter, `toString`
2. **Student** (Practice) — grades, average, highest, unmodifiable view
3. **Stopwatch** (Challenge) — `System.nanoTime()` timing with state checks

```bash
mvn test -pl 04-classes-and-objects -Dtest="RectangleTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- A class defines **state** (fields) and **behavior** (methods)
- Use `this` in constructors to disambiguate parameters from fields
- **Encapsulation** — `private` fields, controlled public interface
- `static` members belong to the class, not instances
- Initialization blocks run in a fixed, predictable order

Further reading: [JLS §8.2](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.2) · *Effective Java* Items 15–16
