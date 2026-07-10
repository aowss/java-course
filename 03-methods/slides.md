---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 3'
footer: 'Methods'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 3
## Methods

---

## Objectives

- Define methods with parameters and return types
- Understand **method overloading** and compiler resolution
- Use **varargs** for variable-length argument lists
- Model **stack** and **heap** memory — and pass-by-value
- Reason about variable **scope** and **lifetime**

---

## Defining Methods

```java
public static double circleArea(double radius) {
    return Math.PI * radius * radius;
}
```

- **Return type** — value produced (`void` if none)
- **Parameters** — inputs from the caller
- **`static`** — belongs to the class, callable without an instance

---

## Pass-by-Value

Java passes **everything by value**:

- Primitives → a **copy** of the value
- References → a **copy** of the reference (object is shared)

```java
public static void tryToReassign(String s) {
    s = "changed";   // only changes local copy
}
// caller's variable is unchanged
```

---

## Method Overloading

Same name, different parameter lists:

```java
public static int add(int a, int b) { return a + b; }
public static double add(double a, double b) { return a + b; }
public static int add(int a, int b, int c) { return a + b + c; }
```

- Resolved at **compile time** by parameter types and count
- **Never** by return type alone

---

## Varargs

Accept zero or more arguments — treated as an array inside:

```java
public static int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) total += n;
    return total;
}

sum();              // 0
sum(1, 2, 3);      // 6
```

Rules: at most **one** varargs param, must be **last**.

---

## Stack and Heap

| Memory | Holds | Lifetime |
|--------|-------|----------|
| **Stack** | Method frames, primitives, references | Per method call |
| **Heap** | Objects and arrays (`new`) | Until GC reclaims |

- Primitives live **in the stack frame**
- Objects live **on the heap**; stack holds a reference

---

## References and Mutation

Reassigning a reference ≠ mutating the object:

```java
public static void modifyArray(int[] arr) {
    arr[0] = 999;    // follows reference to same heap object
}

int[] numbers = {1, 2, 3};
modifyArray(numbers);
// numbers[0] is now 999
```

> Deep dive on GC and JVM memory in **Chapter 28**.

---

## Scope and Lifetime

- **Local variables** — from declaration to end of block
- **Parameters** — scoped to the method body
- **Loop variables** — created and destroyed each iteration
- Method return → stack frame **popped**, locals gone

```java
for (int i = 0; i < 5; i++) {
    int y = i * 10;   // y exists only inside the loop
}
// i and y not accessible here
```

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `MethodBasics` | Defining, calling, return types, pass-by-value |
| `Overloading` | Overloaded methods and resolution rules |
| `VarargsDemo` | Varargs syntax and usage |

```bash
mvn test -pl 03-methods
```

---

## Exercises — Your Turn

1. **Math Utilities** (Guided) — `clamp`, `isPrime`, `factorial`
2. **String Utilities** (Practice) — `reverse`, `isPalindrome`, `countOccurrences`
3. **Overloaded `format`** (Challenge) — three overloads with varargs

```bash
mvn test -pl 03-methods -Dtest="MathUtilsTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Methods are the unit of behavior — keep them **short** and **focused**
- Java is **pass-by-value** for primitives and references alike
- Overloading ≠ polymorphism (that's Chapter 5)
- Keep variable scope as **narrow** as possible

Further reading: [JLS §8.4](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.4) · *Effective Java* Item 52
