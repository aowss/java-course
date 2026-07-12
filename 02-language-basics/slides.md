---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 2'
footer: 'Language Basics'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 2
## Language Basics

---

## Objectives

- Understand **static** vs **dynamic** typing — and why Java chose static
- Know Java's eight **primitive types** and when to use each
- Declare variables, constants, and use `var` for type inference
- Use arithmetic, relational, logical, and bitwise operators
- Control flow with `if`, `switch`, loops, and **arrays**
- Use Java's three **comment** styles

---

## Comments

Comments can appear anywhere in a source file:

| Style | Syntax | Compiler | Javadoc tool |
|-------|--------|----------|--------------|
| End-of-line | `//` … | Ignored | No |
| Block | `/*` … `*/` | Ignored | No |
| **Javadoc** | `/**` … `*/` | Ignored | **Included** in generated docs |

```java
// end-of-line comment
/* block comment */
/**
 * Javadoc comment — documents classes and members (Chapter 3).
 */
```

> Inside `/*` … `*/`, `//` and nested `/*` have no special meaning. Comments do not nest.

---

## Static vs Dynamic Typing

| Typing | When checked | Examples |
|--------|--------------|----------|
| **Static** | Compile time | Java, C, Rust, Go |
| **Dynamic** | Runtime | Python, Ruby, JS |

```java
int x = 42;
x = "hello";   // ✗ compile error
```

`var` reduces verbosity — the compiler still enforces the exact type.

---

## Primitive Types (1 of 2)

| Type | Size | Use case |
|------|------|----------|
| `byte` | 8 bits | Raw binary data, I/O |
| `short` | 16 bits | Rarely used |
| `int` | 32 bits | General-purpose integer |
| `long` | 64 bits | Large counts, timestamps |

Prefer **`int`** for integers unless you need a larger range.

---

## Primitive Types (2 of 2)

| Type | Size | Use case |
|------|------|----------|
| `float` | 32 bits | Rarely used (prefer `double`) |
| `double` | 64 bits | Floating-point math |
| `char` | 16 bits | Single Unicode character |
| `boolean` | — | Logical flags |

```java
int hex    = 0x2A;
long big   = 10_000_000_000L;
double pi  = 3.141_592_653;
```

---

## Variables and Constants

```java
int count = 0;              // mutable
final int MAX_SIZE = 100;   // constant — cannot reassign
var name = "Alice";         // inferred as String (Java 10+)
```

- `var` only for **local** variables with an initializer
- `var` does **not** mean untyped — compiler infers and enforces the type

---

## Operators

| Category | Operators |
|----------|-----------|
| Arithmetic | `+`, `-`, `*`, `/`, `%` |
| Relational | `==`, `!=`, `<`, `>`, `<=`, `>=` |
| Logical | `&&`, `\|\|`, `!` |
| Bitwise | `&`, `\|`, `^`, `~`, `<<`, `>>`, `>>>` |
| Ternary | `condition ? trueVal : falseVal` |

> **Integer division truncates:** `7 / 2` → `3`, not `3.5`

> Deeper precedence and evaluation rules: [Operator Precedence appendix](../../appendices/operator-precedence/README.md)

---

## Control Flow — `if` and `switch`

```java
if (score >= 90) grade = 'A';
else if (score >= 80) grade = 'B';
else grade = 'C';
```

**Classic `switch`** — each `case` needs `break` unless fall-through is intentional:

```java
switch (day) {
    case "MONDAY":
    case "TUESDAY":
        System.out.println("Start of the week");
        break;
    case "WEDNESDAY":
        System.out.println("Midweek");
        break;
    default:
        System.out.println("Other");
        break;
}
```

---

## Switch Fall-Through Pitfall

Without `break`, execution **falls through** to the next case:

```java
switch (day) {
    case "WEDNESDAY":
        System.out.print("Wed ");
    case "THURSDAY":
        System.out.print("Thu ");
        break;
}
// day = "WEDNESDAY" prints: Wed Thu
```

Grouping cases (`MONDAY` / `TUESDAY`) is the only intentional use of fall-through.

---

## Enhanced `switch` (Java 14+)

Arrow form — no fall-through, can return a value:

```java
String label = switch (day) {
    case "MONDAY", "FRIDAY"   -> "Work";
    case "SATURDAY", "SUNDAY" -> "Rest";
    default                   -> "Midweek";
};
```

---

## Loops

```java
for (int i = 0; i < 10; i++) { ... }

while (condition) { ... }

do { ... } while (condition);
```

Use the **enhanced for** when you don't need the index:

```java
for (int p : primes) {
    System.out.println(p);
}
```

---

## Arrays

Fixed-size, zero-indexed containers of a single type:

```java
int[] primes = {2, 3, 5, 7, 11};

primes[0]   // → 2
primes[4]   // → 11
primes[5]   // → ArrayIndexOutOfBoundsException!
```

Indices: `0` to `length - 1`. No compile-time bounds checking.

---

## Examples

| File | Topic |
|------|-------|
| `PrimitiveTypes` | All eight primitives, literals, ranges |
| `CommentedHelloWorld` | End-of-line, block, and Javadoc comments |
| `ControlFlow` | `if`, enhanced `switch`, all loop forms |
| `ArrayBasics` | Creation, access, iteration, pitfalls |

```bash
mvn test -pl 02-language-basics
```

---

## Exercises

1. **Temperature Converter** (Guided) — Celsius ↔ Fahrenheit
2. **FizzBuzz** (Practice) — divisibility by 3 and 5
3. **Array Statistics** (Challenge) — min, max, sum, average

```bash
mvn test -pl 02-language-basics -Dtest="TemperatureConverterTest"
```



---

## Key Takeaways

- Java is **statically typed** — every variable has a fixed compile-time type
- Prefer `int` and `double`; use `final` for values that must not change
- `var` is a local convenience — it does not weaken type safety
- Arrays are fixed-size; for dynamic collections, see Chapter 12
- Three comment styles: `//`, `/* */`, and `/**` (Javadoc — method tags in Chapter 3)

Full lesson: [`README.md`](README.md)
Further reading: [JLS §4.2](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.2) · *Effective Java* Item 61
