---
marp: true
theme: default
paginate: true
header: 'Java Course — Quiz'
footer: 'Chapter 6: Interfaces'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  section.answer { background: #e8f6ef; }
  section.answer h2 { color: #1e5631; }
  code { font-size: 0.85em; }
---


# Quiz
## Chapter 6: Interfaces

Part II: Object-Oriented Programming · ~10 minutes · no IDE required

---

## Q1 · Concept check

A class already extends `Vehicle`. How many interfaces can it implement?

```java
class Car extends Vehicle implements Drivable, Serializable, Comparable<Car> { }
```

---

<!-- _class: answer -->

## Answer — Q1

**Multiple** — there is no small fixed limit. A class may extend **one** class and implement **any number** of interfaces. This is Java's form of multiple inheritance of **type**.

---

## Q2 · Concept check

What must class `FileLogger` provide if it declares `implements Logger`?

```java
interface Logger {
    void log(String message);
    default void logError(String message) {
        log("ERROR: " + message);
    }
}
```

---

<!-- _class: answer -->

## Answer — Q2

It **must** implement `log(String)`. It **inherits** `logError` with default behavior unless it chooses to override it. Default methods let interfaces evolve without breaking existing implementors.

---

## Q3 · Spot the bug

```java
interface Converter {
    static double parse(String s) {
        return Double.parseDouble(s);
    }
}

class App {
    void run() {
        double d = parse("3.14");
    }
}
```

---

<!-- _class: answer -->

## Answer — Q3

**Compile error** — static interface methods must be called through the **interface name**: `Converter.parse("3.14")`. They are not inherited like instance methods.

---

## Q4 · True or false

> All methods in an interface are `public` and `abstract` unless marked otherwise.

---

<!-- _class: answer -->

## Answer — Q4

**True** (with modern exceptions).

- Traditional methods: implicitly `public abstract`.
- **`default`** methods: have a body; still `public`.
- **`static`** methods: have a body; not abstract.
- **`private`** methods (Java 9+): helper methods with bodies inside the interface.

---

## Q5 · Concept check

Which of these can be a `@FunctionalInterface`?

- A) An interface with one abstract method and several `default` methods
- B) An interface with two abstract methods
- C) An abstract class with one abstract method

---

<!-- _class: answer -->

## Answer — Q5

**A only.**

A functional interface has **exactly one abstract method** (SAM). Default and static methods do not count. Abstract classes are not interfaces and cannot be `@FunctionalInterface`.

---

## Q6 · Predict the output

```java
interface Greeter {
    String greet(String name);
    default String greetLoud(String name) {
        return greet(name).toUpperCase();
    }
}

class FriendlyGreeter implements Greeter {
    public String greet(String name) { return "Hi, " + name; }
}

public class Main {
    public static void main(String[] args) {
        Greeter g = new FriendlyGreeter();
        System.out.println(g.greetLoud("Ada"));
    }
}
```

---

<!-- _class: answer -->

## Answer — Q6

**`HI, ADA`**

`greetLoud` is a default method on the interface. It calls the implementor's `greet`, then uppercases the result.

---

## Q7 · Short answer

When would you choose an **interface** over an **abstract class**?

---

<!-- _class: answer -->

## Answer — Q7

Prefer an **interface** when:

- You need a **contract** without shared implementation (or only `default` helpers).
- Types should participate in **multiple inheritance of type**.
- You want to allow unrelated classes to share behavior (e.g. `Comparable`).

Prefer an **abstract class** when subclasses share substantial **state and implementation**, and you want protected fields or a single inheritance root with partial behavior filled in.

---

## Q8 · Concept check

Can an interface extend another interface?

---

<!-- _class: answer -->

## Answer — Q8

**Yes.** Interfaces can extend one or more other interfaces, inheriting their abstract and default methods. A class implementing the child must implement everything in the chain.

---
