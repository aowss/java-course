---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 8'
footer: 'Exception Handling'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 8
## Exception Handling

---

## Objectives

- Understand the exception hierarchy (`Throwable`, `Error`, `Exception`, `RuntimeException`)
- Distinguish **checked** vs **unchecked** exceptions
- Use `throws`, `try-catch-finally`, multi-catch, and try-with-resources
- Define custom exceptions and apply **Effective Java** guidance

---

## The Exception Hierarchy

```
Throwable
├── Error                 (don't catch — JVM problems)
└── Exception             (checked)
    ├── IOException
    └── RuntimeException  (unchecked)
        ├── NullPointerException
        ├── IllegalArgumentException
        └── ArithmeticException
```

---

## Checked vs Unchecked

| Type | Compiler enforces `throws`? | When to use |
|------|----------------------------|-------------|
| **Checked** (`IOException`) | Yes | Recoverable — caller should handle |
| **Unchecked** (`IllegalArgumentException`) | No | Programming error — bad input or state |

> **Item 69:** Use exceptions only for **exceptional** conditions — not control flow.

---

## The `throws` Clause

```java
public void withdraw(double amount) throws InsufficientFundsException {
    // checked — compiler requires throws or catch
}

public void setAge(int age) throws IllegalArgumentException {
    // unchecked — throws is optional but documents the API
    if (age < 0) throw new IllegalArgumentException("age: " + age);
}
```

You **may** declare runtime exceptions in `throws` to document your public API (**Item 74**).

---

## try-catch-finally

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("Always executes");
}
```

- Catch the **most specific** type first
- `finally` runs whether or not an exception was thrown

---

## Multi-catch

Handle several types with one block:

```java
try {
    int value = Integer.parseInt(input);
    int result = 100 / value;
} catch (NumberFormatException | ArithmeticException e) {
    System.out.println("Caught: " + e.getClass().getSimpleName());
}
```

---

## try-with-resources

```java
try (var resource = new MyResource()) {
    resource.use();
}
// close() called automatically — even if an exception is thrown
```

- Any `AutoCloseable` can be used
- If `close()` also throws, it becomes a **suppressed exception** on the main one

---

## Custom Exceptions

```java
// Checked — recoverable domain error
public class InsufficientFundsException extends Exception {
    private final double shortfall;
    public InsufficientFundsException(double shortfall) {
        super("Short by: " + shortfall);
        this.shortfall = shortfall;
    }
}

// Unchecked — programming error
public class InvalidAccountException extends RuntimeException { ... }
```

---

## Effective Java — When to Throw

| Item | Guideline |
|------|-----------|
| **70** | Checked for recoverable; runtime for programming errors |
| **71** | Avoid unnecessary checked exceptions |
| **72** | Prefer standard types (`IllegalArgumentException`, `IllegalStateException`) |
| **73** | Throw exceptions appropriate to the **abstraction** |

---

## Effective Java — How to Throw

| Item | Guideline |
|------|-----------|
| **74** | Document every thrown type with `@throws` in Javadoc |
| **75** | Include context in messages (parameter name, offending value) |
| **77** | Never ignore an exception — log, re-throw, or handle |
| **78** | Retry transient failures with backoff |

Also: catch **specific** types, use **exception chaining** (`cause`), prefer try-with-resources over `finally` for closing.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `ExceptionHierarchy` | Specific catch, multi-catch |
| `ThrowsClauseDemo` | Checked + unchecked in `throws` |
| `TryWithResources` | `AutoCloseable`, suppressed exceptions |
| `CustomExceptions` | `BankAccount` with domain exceptions |

```bash
mvn test -pl 08-exception-handling
```

---

## Exercises — Your Turn

1. **Validator** (Guided) — custom checked `ValidationException`
2. **RetryExecutor** (Practice) — retry with suppressed exceptions
3. **ResourcePool** (Challenge) — pooled `AutoCloseable` resources

```bash
mvn test -pl 08-exception-handling -Dtest="ValidatorTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- **Checked** = recoverable, caller must handle · **Unchecked** = programming error
- Runtime exceptions **may** appear in `throws` for API documentation
- **try-with-resources** is the standard pattern for closing resources
- Prefer standard exception types with **informative messages**

Further reading: *Effective Java* Items 69–75, 77–78 · [JLS §11](https://docs.oracle.com/javase/specs/jls/se25/html/jls-11.html)
