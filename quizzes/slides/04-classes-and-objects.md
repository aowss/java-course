---
marp: true
theme: default
paginate: true
header: 'Java Course — Quiz'
footer: 'Chapter 4: Classes and Objects'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  section.answer { background: #e8f6ef; }
  section.answer h2 { color: #1e5631; }
  code { font-size: 0.85em; }
---


# Quiz
## Chapter 4: Classes and Objects

Part II: Object-Oriented Programming · ~12 minutes

---

## Q1 · Concept check

A class defines a `Counter` with no constructors written by you. A student writes:

```java
Counter a = new Counter();
Counter b = new Counter(10);
```

Both lines are in the same program. What happens?

---

<!-- _class: answer -->

## Answer — Q1

**Compile error on the second line.**

If you write **no** constructors, Java provides a default no-arg constructor. As soon as you add **any** constructor (e.g. `Counter(int initial)`), the default no-arg constructor **disappears** unless you define it explicitly.

---

## Q2 · Predict the initialization log

```java
public class Demo {
    static { System.out.print("S "); }
    { System.out.print("I "); }
    Demo() { System.out.print("C "); }
    public static void main(String[] args) {
        new Demo();
    }
}
```

What prints?

---

<!-- _class: answer -->

## Answer — Q2

**`S I C `**

Order for the first `new Demo()`:

1. Static field initializers and static blocks (once) → `S `
2. Instance field initializers and instance blocks → `I `
3. Constructor body → `C `

---

## Q3 · Concept check

Which call is valid?

```java
public class Counter {
    private int count;
    public static int getTotal() { return count; }
}
```

- A) `Counter.getTotal()` from `main`
- B) `counter.getTotal()` on an instance
- C) Both A and B
- D) Neither — compile error

---

<!-- _class: answer -->

## Answer — Q3

**D) Neither — compile error**

A **static** method cannot access **instance** fields like `count` directly — there is no `this`. The method must be rewritten to use static state, or made an instance method.

---

## Q4 · Concept check

Match the access modifier to who can access a `private` field:

| Modifier | Same class? | Same package? | Subclass? | Anywhere? |
|----------|:-----------:|:-------------:|:---------:|:---------:|
| `private` | ? | ? | ? | ? |

---

<!-- _class: answer -->

## Answer — Q4

| Modifier | Same class | Same package | Subclass | Anywhere |
|----------|:----------:|:------------:|:--------:|:--------:|
| `private` | ✓ | | | |

`private` is visible **only in the declaring class**. Subclasses do not inherit access to `private` fields (they may use inherited public/protected accessors instead).

---

## Q5 · Spot the bug

```java
public class Account {
    private static final double DEFAULT_BALANCE = 0.0;
    private final String owner;
    private double balance = DEFAULT_BALANCE;

    public Account(String owner) {
        owner = owner.trim();
        this.balance = 100.0;
    }
}
```

What is wrong?

---

<!-- _class: answer -->

## Answer — Q5

The constructor never assigns **`this.owner`**. The parameter `owner` is trimmed locally, but the `final` field `owner` is never initialized — a compile error.

Fix: `this.owner = owner.trim();`

---

## Q6 · True or false

> These two field declarations are equivalent to the compiler: `final public static int X` and `public static final int X`.

---

<!-- _class: answer -->

## Answer — Q6

**True.**

Modifier order is **not** semantically significant. Convention is `public static final`, but the compiler accepts any order.

---

## Q7 · Concept check

Why is this encapsulation?

```java
public class Temperature {
    private double celsius;
    public double getCelsius() { return celsius; }
}
```

---

<!-- _class: answer -->

## Answer — Q7

The internal field is **hidden** (`private`). Outside code uses a **controlled interface** (`getCelsius()`). You can later add validation, logging, or unit conversion without breaking callers — they never touched the field directly.

---

## Q8 · Short answer

List the initialization steps that run when `new Child()` is first called in a program, assuming `Child extends Parent` and both classes have static blocks, instance blocks, and constructors.

---

<!-- _class: answer -->

## Answer — Q8

1. Parent static fields and static blocks (once)
2. Child static fields and static blocks (once)
3. Parent instance fields and instance blocks
4. Parent constructor
5. Child instance fields and instance blocks
6. Child constructor

(Detailed in Chapter 5; introduced here at the end of Chapter 4.)

---
