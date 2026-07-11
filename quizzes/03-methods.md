# Quiz — Chapter 3: Methods

**Part I: Foundations** · ~12 minutes

---

### 1. Concept check

Java passes arguments **by value**. What does that mean for:

1. A `int` parameter?
2. A `String` parameter?

<details>
<summary>Show answer</summary>

1. **`int`:** A copy of the value is passed. Changing the parameter inside the method does not affect the caller's variable.
2. **`String`:** A copy of the **reference** is passed. The method and caller share the same `String` object, but reassigning the parameter to a different object does not change the caller's variable.

Java is always pass-by-value — there is no pass-by-reference for parameters.

</details>

---

### 2. Predict the behavior

```java
public static void main(String[] args) {
    int[] data = {1, 2, 3};
    mutate(data);
    System.out.println(data[0]);
}

static void mutate(int[] arr) {
    arr[0] = 99;
}
```

<details>
<summary>Show answer</summary>

**`99`**

The reference is copied, but both copies point to the **same array object** on the heap. Mutating through `arr[0]` changes the shared object. This is not pass-by-reference — the reference itself was copied.

</details>

---

### 3. Spot the bug

```java
public static int add(int a, int b) {
    return a + b;
}

public static double add(int a, int b) {
    return a + b;
}
```

Does this compile?

<details>
<summary>Show answer</summary>

**No.**

You cannot overload methods by **return type alone**. The compiler cannot distinguish `add(1, 2)` between these two signatures. Overloading requires different parameter types or counts.

</details>

---

### 4. Concept check

Which overload is called?

```java
add(1, 2);        // (a)
add(1.0, 2.0);    // (b)
add(1, 2, 3);     // (c)
```

Given:

```java
static int add(int a, int b) { return a + b; }
static double add(double a, double b) { return a + b; }
static int add(int a, int b, int c) { return a + b + c; }
```

<details>
<summary>Show answer</summary>

- **(a)** `int add(int, int)` — exact match.
- **(b)** `double add(double, double)` — exact match.
- **(c)** `int add(int, int, int)` — three-parameter overload.

The compiler picks the most specific match at **compile time**.

</details>

---

### 5. Spot the bug

```java
public static void report(int count, int... tags) {
    System.out.println(count);
}

public static void report(int... tags, int count) {
    System.out.println(count);
}
```

What's wrong with the second method?

<details>
<summary>Show answer</summary>

The **varargs parameter must be last**. `int... tags` cannot appear before `int count`. A method may have at most one varargs parameter.

</details>

---

### 6. Concept check

Where do these live at runtime?

| Item | Stack or heap? |
|------|----------------|
| Local `int count = 42` in `main` | ? |
| `new int[]{1, 2, 3}` | ? |
| The reference variable pointing to that array | ? |

<details>
<summary>Show answer</summary>

| Item | Location |
|------|----------|
| Local `int count = 42` | **Stack** (in `main`'s stack frame) |
| `new int[]{1, 2, 3}` (the array object) | **Heap** |
| The reference variable in `main` | **Stack** (holds the address/reference to the heap object) |

Primitives and references stored in local variables live on the stack; objects live on the heap.

</details>

---

### 7. Predict the output

```java
public static void main(String[] args) {
    System.out.println(sum());
    System.out.println(sum(1, 2, 3));
}

static int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) {
        total += n;
    }
    return total;
}
```

<details>
<summary>Show answer</summary>

```
0
6
```

Varargs accepts **zero or more** arguments. With no arguments, `numbers` is an empty array and the sum is `0`.

</details>

---

### 8. Short answer

What is the difference between a method's **signature** and its **return type**? Which one matters for overloading?

<details>
<summary>Show answer</summary>

- **Signature:** method name + parameter types (and arity). Defines how the compiler identifies overloads.
- **Return type:** the type of value the method produces (`void` if none).

Overloading is resolved by **signature only**, not return type.

</details>
