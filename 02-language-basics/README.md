# Chapter 2: Language Basics

## Objectives

- Understand the difference between static and dynamic typing, and why Java chose static
- Know Java's eight primitive types and when to use each
- Declare variables, constants, and use type inference with `var`
- Write expressions using arithmetic, relational, logical, and bitwise operators
- Control program flow with `if`, `switch`, `for`, `while`, and `do-while`
- Create, access, and iterate over arrays

## Concepts

### Static vs. Dynamic Typing

Before diving into types, it's worth understanding *why* Java requires you to declare the type of every variable.

| Typing | When types are checked | What it means | Examples |
|--------|----------------------|---------------|----------|
| **Static** | At **compile time** | Every variable has a fixed type. The compiler rejects type errors before the program runs. | Java, C, C++, Rust, Go |
| **Dynamic** | At **runtime** | Variables can hold any type. Type errors only surface when the faulty line executes. | Python, Ruby, JavaScript |

In Python, you can write:

```python
x = 42
x = "hello"   # no error — x just changes type
```

In Java, this is a compile error:

```java
int x = 42;
x = "hello";   // ✗ compile error: incompatible types
```

Static typing catches entire categories of bugs before you ever run the program. The trade-off is verbosity — you must declare types. Java's `var` keyword (Java 10+) reduces that verbosity without sacrificing static typing: the compiler still infers and enforces the exact type, you just don't write it out.

```java
var x = 42;       // compiler infers int — x is still statically typed as int
x = "hello";      // ✗ compile error: still an int
```

### Primitive Types

Java has exactly eight primitive types. Everything else is a reference type.

| Type | Size | Range | Default | Use case |
|------|------|-------|---------|----------|
| `byte` | 8 bits | -128 to 127 | 0 | Raw binary data, I/O |
| `short` | 16 bits | -32,768 to 32,767 | 0 | Rarely used |
| `int` | 32 bits | -2³¹ to 2³¹-1 | 0 | General-purpose integer |
| `long` | 64 bits | -2⁶³ to 2⁶³-1 | 0L | Large counts, timestamps |
| `float` | 32 bits | ~±3.4×10³⁸ | 0.0f | Rarely used (prefer `double`) |
| `double` | 64 bits | ~±1.8×10³⁰⁸ | 0.0 | Floating-point math |
| `char` | 16 bits | '\u0000' to '\uffff' | '\u0000' | Single Unicode character |
| `boolean` | — | `true` / `false` | `false` | Logical flags |

**Literals:**

```java
int decimal     = 42;
int hex         = 0x2A;
int binary      = 0b0010_1010;   // underscores improve readability
long big        = 10_000_000_000L;
double pi       = 3.141_592_653;
char letter     = 'A';
boolean flag    = true;
```

### Variables and Constants

```java
int count = 0;              // mutable variable
final int MAX_SIZE = 100;   // constant — cannot be reassigned
var name = "Alice";         // type inferred as String (Java 10+)
```

`var` can only be used for local variables with an initializer. It does not mean "untyped" — the compiler infers the exact type at declaration.

### Operators

| Category | Operators |
|----------|----------|
| Arithmetic | `+`, `-`, `*`, `/`, `%` |
| Relational | `==`, `!=`, `<`, `>`, `<=`, `>=` |
| Logical | `&&`, `\|\|`, `!` |
| Bitwise | `&`, `\|`, `^`, `~`, `<<`, `>>`, `>>>` |
| Assignment | `=`, `+=`, `-=`, `*=`, `/=`, `%=` |
| Ternary | `condition ? valueIfTrue : valueIfFalse` |

**Integer division truncates:** `7 / 2` evaluates to `3`, not `3.5`.

### Control Flow

**`if` / `else if` / `else`:**

```java
if (score >= 90) {
    grade = 'A';
} else if (score >= 80) {
    grade = 'B';
} else {
    grade = 'C';
}
```

**`switch` statement (classic form):**

```java
switch (day) {
    case "MONDAY":
    case "TUESDAY":
        System.out.println("Start of the week");
        break;
    case "WEDNESDAY":
        System.out.println("Midweek");
        break;
    case "SATURDAY":
    case "SUNDAY":
        System.out.println("Weekend");
        break;
    default:
        System.out.println("Other");
        break;
}
```

Each `case` falls through to the next unless you add `break`. Forgetting `break` is a common source of bugs — the code silently executes the next case's body. Grouping cases (like `MONDAY` and `TUESDAY` above) is the only legitimate use of fall-through.

**Enhanced `switch` (expression form, Java 14+):**

The arrow (`->`) form eliminates fall-through entirely and can return a value:

```java
String label = switch (day) {
    case "MONDAY", "FRIDAY"    -> "Work";
    case "SATURDAY", "SUNDAY"  -> "Rest";
    default                    -> "Midweek";
};
```

Prefer the enhanced form in new code — it is more concise, safer (no fall-through), and can be used as an expression (assigning the result to a variable).

**Loops:**

```java
for (int i = 0; i < 10; i++) { ... }

while (condition) { ... }

do { ... } while (condition);
```

### Arrays

Arrays are fixed-size, zero-indexed containers of a single type.

```java
int[] numbers = new int[5];          // five zeros
int[] primes  = {2, 3, 5, 7, 11};   // initialized

primes[0]   // → 2  (first element)
primes[4]   // → 11 (last element)
primes[5]   // → ArrayIndexOutOfBoundsException at runtime!

for (int i = 0; i < primes.length; i++) {
    System.out.println("primes[" + i + "] = " + primes[i]);
}
// primes[0] = 2
// primes[1] = 3
// primes[2] = 5
// primes[3] = 7
// primes[4] = 11
```

Arrays know their length: `primes.length` is `5`. Indices go from `0` to `length - 1`. There is no bounds checking at compile time — accessing an invalid index throws `ArrayIndexOutOfBoundsException` at runtime.

When you don't need the index, the enhanced `for` loop is more concise:

```java
for (int p : primes) {
    System.out.println(p);
}
```

## Examples

| File | Demonstrates |
|------|-------------|
| [`PrimitiveTypes.java`](src/main/java/course/ch02/examples/PrimitiveTypes.java) | All eight primitive types, literals, and ranges |
| [`ControlFlow.java`](src/main/java/course/ch02/examples/ControlFlow.java) | `if`, enhanced `switch`, all loop forms |
| [`ArrayBasics.java`](src/main/java/course/ch02/examples/ArrayBasics.java) | Array creation, access, iteration, common pitfalls |

## Exercises

### Exercise 1: Temperature Converter (Guided)

**File:** [`TemperatureConverter.java`](src/main/java/course/ch02/exercises/TemperatureConverter.java)

Implement two methods:
- `celsiusToFahrenheit(double celsius)` — returns `celsius × 9/5 + 32`
- `fahrenheitToCelsius(double fahrenheit)` — returns `(fahrenheit - 32) × 5/9`

```bash
mvn test -Dtest="course.ch02.exercises.TemperatureConverterTest"
```

### Exercise 2: FizzBuzz (Practice)

**File:** [`FizzBuzz.java`](src/main/java/course/ch02/exercises/FizzBuzz.java)

Implement `fizzBuzz(int n)` that returns:
- `"FizzBuzz"` if `n` is divisible by both 3 and 5
- `"Fizz"` if divisible by 3 only
- `"Buzz"` if divisible by 5 only
- The number as a string otherwise

```bash
mvn test -Dtest="course.ch02.exercises.FizzBuzzTest"
```

### Exercise 3: Array Statistics (Challenge)

**File:** [`ArrayStats.java`](src/main/java/course/ch02/exercises/ArrayStats.java)

Implement methods to compute the minimum, maximum, sum, and average of an `int[]`. Handle edge cases (empty arrays, single elements).

```bash
mvn test -Dtest="course.ch02.exercises.ArrayStatsTest"
```

## Key Takeaways

- Java is **statically typed** — every variable has a fixed type known at compile time.
- Prefer `int` for integers and `double` for floating-point unless you have a specific reason for another type.
- Use `final` for values that should not change.
- `var` is a convenience for local variables — it does not weaken type safety.
- Arrays are fixed-size; for dynamic collections, use the Collections Framework (Chapter 10).

## Further Reading

- [JLS §4.2 — Primitive Types and Values](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.2)
- [JLS §14.11 — The `switch` Statement](https://docs.oracle.com/javase/specs/jls/se25/html/jls-14.html#jls-14.11)
- Effective Java, Item 61: Prefer primitive types to boxed primitives
