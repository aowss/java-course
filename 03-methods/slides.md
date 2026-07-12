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
- Document methods with **Javadoc** and generate HTML API docs

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

## Javadoc for Methods

Document **public** methods for other developers — same format as the [JDK API](https://docs.oracle.com/en/java/javase/25/docs/api/) you browsed in Chapter 1:

```java
/**
 * Returns the area of a circle with the given radius.
 *
 * @param radius the radius (must be non-negative)
 * @return the area, or {@code 0} if {@code radius} is negative
 */
public static double circleArea(double radius) { ... }
```

| Tag | Use |
|-----|-----|
| `@param` | Method parameter |
| `@return` | Return value |
| `@throws` | Exception a method may throw |

> Presenter: Exercise skeletons in this chapter include Javadoc stubs — fill them in as you implement each method.

---

## Generating Javadoc

Generate HTML from your documented code:

```bash
javadoc -d target/javadoc -sourcepath src/main/java course.ch03.examples
```

Open `target/javadoc/index.html` in a browser — the same layout as the JDK docs (**Summary** and **Detail** views).

---

## Pass-by-Value

Java passes **everything by value**:

- Primitives → a **copy** of the value
- References → a **copy** of the reference (the object itself is shared)

```java
public static void tryToReassign(String s) {
    s = "changed";   // only changes local copy
}
// caller's variable is unchanged
```

Reassigning the parameter does **not** change the caller's variable.

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

```
        ┌─────────────────────────────────────┐
        │              HEAP (shared)          │
        │   String "Hi"    int[] {2, 3, 5}    │
        └──────────────▲──────────▲───────────┘
                       │          │
        ┌──────────────┴──────────┴───────────┐
        │  STACK (per thread)                 │
        │  main(): greeting → ref, count = 42 │
        │  add():  result = 7                 │
        └─────────────────────────────────────┘
```

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

## Examples

| File | Topic |
|------|-------|
| `MethodBasics` | Defining, calling, return types, pass-by-value |
| `Overloading` | Overloaded methods and resolution rules |
| `VarargsDemo` | Varargs syntax and usage |

```bash
mvn test -pl 03-methods
```

---

## Exercises

1. **Math Utilities** (Guided) — `clamp`, `isPrime`, `factorial`
2. **String Utilities** (Practice) — `reverse`, `isPalindrome`, `countOccurrences`
3. **Overloaded `format`** (Challenge) — three overloads with varargs

```bash
mvn test -pl 03-methods -Dtest="MathUtilsTest"
```



---

## Key Takeaways

- Methods are the unit of behavior — keep them **short** and **focused**
- Java is **pass-by-value** for primitives and references alike
- Overloading ≠ polymorphism (that's Chapter 5)
- Keep variable scope as **narrow** as possible
- Use **Javadoc** (`@param`, `@return`, `@throws`) on public methods; run `javadoc` to publish HTML docs

Full lesson: [`README.md`](README.md)
Further reading: [JLS §8.4](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.4) · *Effective Java* Item 52
