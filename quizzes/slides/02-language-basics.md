---
marp: true
theme: default
paginate: true
header: 'Java Course — Quiz'
footer: 'Chapter 2: Language Basics'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  section.answer { background: #e8f6ef; }
  section.answer h2 { color: #1e5631; }
  code { font-size: 0.85em; }
---


# Quiz
## Chapter 2: Language Basics

Part I: Foundations · ~12 minutes

---

## Q1 · Predict the output

```java
int result = 7 / 2;
System.out.println(result);
```

---

<!-- _class: answer -->

## Answer — Q1

**`3`**

Integer division **truncates** toward zero. It does not produce `3.5`. To get `3.5`, at least one operand must be floating-point: `7.0 / 2` or `7 / 2.0`.

---

## Q2 · Spot the bug

```java
var count = 10;
count = "ten";
```

Does this compile?

---

<!-- _class: answer -->

## Answer — Q2

**No.**

`var` infers `int` from the initializer. Reassigning a `String` is a compile-time type error. `var` is not dynamic typing — it is still **static** typing with inferred types.

---

## Q3 · Predict the output

```java
int x = 5;
System.out.println(x++ + ++x);
```

---

<!-- _class: answer -->

## Answer — Q3

**`12`**

Evaluation is left-to-right for operands:

1. `x++` uses the value `5`, then `x` becomes `6`.
2. `++x` increments `x` to `7` and uses `7`.
3. `5 + 7 = 12`. Final value of `x` is `7`.

(See also the [Operator Precedence appendix](../appendices/operator-precedence/README.md).)

---

## Q4 · Concept check

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

---

<!-- _class: answer -->

## Answer — Q4

**`Wed Thu `**

Classic `switch` **falls through** when there is no `break`. `"WEDNESDAY"` matches the third case, prints `Wed `, then falls into `THURSDAY`, prints `Thu `, then hits `break`.

---

## Q5 · Concept check

How many elements does this array have?

```java
int[] nums = {10, 20, 30};
```

What is `nums[2]`? What happens if you access `nums[3]` at runtime?

---

<!-- _class: answer -->

## Answer — Q5

- **Length:** 3 (valid indices: 0, 1, 2).
- **`nums[2]`:** `30`.
- **`nums[3]`:** `ArrayIndexOutOfBoundsException` at runtime — the compiler does not catch out-of-bounds access.

---

## Q6 · True or false

> A `final` local variable can be assigned more than once as long as each assignment is in a different block.

---

<!-- _class: answer -->

## Answer — Q6

**False.**

`final` means the variable must be assigned **exactly once**. You cannot reassign it after the initial assignment, regardless of block scope.

---

## Q7 · Predict the output

```java
boolean a = true;
boolean b = false;
System.out.println(a || expensive());
System.out.println(b && expensive());

// expensive() prints "called" and returns true
```

Which calls to `expensive()` actually run?

---

<!-- _class: answer -->

## Answer — Q7

**Neither.**

`||` and `&&` are **short-circuit**: the right operand is skipped when the result is already determined. `true || …` never evaluates the right side; `false && …` never evaluates the right side. Nothing prints from `expensive()`.

---

## Q8 · Short answer

Name the **eight** primitive types in Java.

---

<!-- _class: answer -->

## Answer — Q8

`byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`

---
