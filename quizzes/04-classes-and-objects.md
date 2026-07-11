# Quiz — Chapter 4: Classes and Objects

**Part II: Object-Oriented Programming** · ~12 minutes

---

### 1. Concept check

A class defines a `Counter` with no constructors written by you. A student writes:

```java
Counter a = new Counter();
Counter b = new Counter(10);
```

Both lines are in the same program. What happens?

<details>
<summary>Show answer</summary>

**Compile error on the second line.**

If you write **no** constructors, Java provides a default no-arg constructor. As soon as you add **any** constructor (e.g. `Counter(int initial)`), the default no-arg constructor **disappears** unless you define it explicitly.

</details>

---

### 2. Predict the initialization log

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

<details>
<summary>Show answer</summary>

**`S I C `**

Order for the first `new Demo()`:

1. Static field initializers and static blocks (once) → `S `
2. Instance field initializers and instance blocks → `I `
3. Constructor body → `C `

</details>

---

### 3. Concept check

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

<details>
<summary>Show answer</summary>

**D) Neither — compile error**

A **static** method cannot access **instance** fields like `count` directly — there is no `this`. The method must be rewritten to use static state, or made an instance method.

</details>

---

### 4. Concept check

Match the access modifier to who can access a `private` field:

| Modifier | Same class? | Same package? | Subclass? | Anywhere? |
|----------|:-----------:|:-------------:|:---------:|:---------:|
| `private` | ? | ? | ? | ? |

<details>
<summary>Show answer</summary>

| Modifier | Same class | Same package | Subclass | Anywhere |
|----------|:----------:|:------------:|:--------:|:--------:|
| `private` | ✓ | | | |

`private` is visible **only in the declaring class**. Subclasses do not inherit access to `private` fields (they may use inherited public/protected accessors instead).

</details>

---

### 5. Spot the bug

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

<details>
<summary>Show answer</summary>

The constructor never assigns **`this.owner`**. The parameter `owner` is trimmed locally, but the `final` field `owner` is never initialized — a compile error.

Fix: `this.owner = owner.trim();`

</details>

---

### 6. True or false

> These two field declarations are equivalent to the compiler: `final public static int X` and `public static final int X`.

<details>
<summary>Show answer</summary>

**True.**

Modifier order is **not** semantically significant. Convention is `public static final`, but the compiler accepts any order.

</details>

---

### 7. Concept check

Why is this encapsulation?

```java
public class Temperature {
    private double celsius;
    public double getCelsius() { return celsius; }
}
```

<details>
<summary>Show answer</summary>

The internal field is **hidden** (`private`). Outside code uses a **controlled interface** (`getCelsius()`). You can later add validation, logging, or unit conversion without breaking callers — they never touched the field directly.

</details>

---

### 8. Short answer

List the initialization steps that run when `new Child()` is first called in a program, assuming `Child extends Parent` and both classes have static blocks, instance blocks, and constructors.

<details>
<summary>Show answer</summary>

1. Parent static fields and static blocks (once)
2. Child static fields and static blocks (once)
3. Parent instance fields and instance blocks
4. Parent constructor
5. Child instance fields and instance blocks
6. Child constructor

(Detailed in Chapter 5; introduced here at the end of Chapter 4.)

</details>
