# Quiz — Chapter 2: Language Basics

**Part I: Foundations** · ~12 minutes · no IDE required

---

### 1. Predict the output

```java
int result = 7 / 2;
System.out.println(result);
```

<details>
<summary>Show answer</summary>

**`3`**

Integer division **truncates** toward zero. It does not produce `3.5`. To get `3.5`, at least one operand must be floating-point: `7.0 / 2` or `7 / 2.0`.

</details>

---

### 2. Spot the bug

```java
var count = 10;
count = "ten";
```

Does this compile?

<details>
<summary>Show answer</summary>

**No.**

`var` infers `int` from the initializer. Reassigning a `String` is a compile-time type error. `var` is not dynamic typing — it is still **static** typing with inferred types.

</details>

---

### 3. Predict the output

```java
int x = 5;
System.out.println(x++ + ++x);
```

<details>
<summary>Show answer</summary>

**`12`**

Evaluation is left-to-right for operands:

1. `x++` uses the value `5`, then `x` becomes `6`.
2. `++x` increments `x` to `7` and uses `7`.
3. `5 + 7 = 12`. Final value of `x` is `7`.

(See also the [Operator Precedence appendix](../appendices/operator-precedence/README.md).)

</details>

---

### 4. Concept check

What prints?

```java
String day = "WEDNESDAY";
switch (day) {
    case "MONDAY":
        System.out.print("Mon ");
    case "TUESDAY":
        System.out.print("Tue ");
        break;
    case "WEDNESDAY":
        System.out.print("Wed ");
    case "THURSDAY":
        System.out.print("Thu ");
        break;
    default:
        System.out.print("Other ");
}
```

<details>
<summary>Show answer</summary>

**`Wed Thu `**

Classic `switch` **falls through** when there is no `break`. `"WEDNESDAY"` matches the third case, prints `Wed `, then falls into `THURSDAY`, prints `Thu `, then hits `break`.

</details>

---

### 5. Concept check

How many elements does this array have?

```java
int[] nums = {10, 20, 30};
```

What is `nums[2]`? What happens if you access `nums[3]` at runtime?

<details>
<summary>Show answer</summary>

- **Length:** 3 (valid indices: 0, 1, 2).
- **`nums[2]`:** `30`.
- **`nums[3]`:** `ArrayIndexOutOfBoundsException` at runtime — the compiler does not catch out-of-bounds access.

</details>

---

### 6. True or false

> A `final` local variable can be assigned more than once as long as each assignment is in a different block.

<details>
<summary>Show answer</summary>

**False.**

`final` means the variable must be assigned **exactly once**. You cannot reassign it after the initial assignment, regardless of block scope.

</details>

---

### 7. Predict the output

```java
boolean a = true;
boolean b = false;
System.out.println(a || expensive());
System.out.println(b && expensive());

// expensive() prints "called" and returns true
```

Which calls to `expensive()` actually run?

<details>
<summary>Show answer</summary>

**Neither.**

`||` and `&&` are **short-circuit**: the right operand is skipped when the result is already determined. `true || …` never evaluates the right side; `false && …` never evaluates the right side. Nothing prints from `expensive()`.

</details>

---

### 8. Short answer

Name the **eight** primitive types in Java.

<details>
<summary>Show answer</summary>

`byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`

</details>
