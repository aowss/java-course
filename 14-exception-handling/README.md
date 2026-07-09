# Chapter 14: Exception Handling

## Objectives

- Understand the exception class hierarchy: `Throwable`, `Error`, `Exception`, `RuntimeException`
- Distinguish between checked and unchecked exceptions and know when to use each
- Use `try-catch-finally` and multi-catch to handle exceptions
- Use try-with-resources and implement `AutoCloseable` for safe resource management
- Define custom exception classes for domain-specific error conditions
- Apply best practices: catch specific types, avoid swallowing exceptions, use suppressed exceptions

## Concepts

### The Exception Hierarchy

Every exception in Java extends `Throwable`. The two main branches are `Error` (serious JVM problems you should not catch) and `Exception` (recoverable conditions your code should handle):

```
Throwable
├── Error                        (don't catch)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception                    (checked)
    ├── IOException
    ├── SQLException
    └── RuntimeException         (unchecked)
        ├── NullPointerException
        ├── IllegalArgumentException
        └── ArithmeticException
```

**Checked exceptions** (subclasses of `Exception` but not `RuntimeException`) must be declared in the method's `throws` clause or caught. The compiler enforces this.

**Unchecked exceptions** (subclasses of `RuntimeException`) do not require a `throws` declaration. They typically indicate programming errors.

### try-catch-finally

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
} finally {
    System.out.println("Always executes");
}
```

- Catch the **most specific** exception first, then broaden.
- The `finally` block runs whether or not an exception was thrown.
- Java supports **multi-catch** to handle several types the same way:

```java
try {
    // ...
} catch (NumberFormatException | ArithmeticException e) {
    System.out.println("Caught: " + e.getClass().getSimpleName());
}
```

### try-with-resources

Any object that implements `AutoCloseable` can be declared in a try-with-resources statement. The JVM guarantees that `close()` is called when the block exits, even if an exception is thrown:

```java
try (var resource = new MyResource()) {
    resource.use();
}
// resource.close() is called automatically
```

If both the body and `close()` throw exceptions, the close exception is added as a **suppressed exception** on the body exception, accessible via `getSuppressed()`.

Resources declared in the same statement are closed in **reverse** order.

### Custom Exceptions

Define custom exceptions to represent domain-specific errors:

```java
// Checked — caller must handle
public class InsufficientFundsException extends Exception {
    private final double shortfall;

    public InsufficientFundsException(double shortfall) {
        super("Short by: " + shortfall);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}

// Unchecked — programming error
public class InvalidAccountException extends RuntimeException {
    public InvalidAccountException(String id) {
        super("Invalid account: " + id);
    }
}
```

### Best Practices

- **Catch specific exceptions** — avoid `catch (Exception e)` unless re-throwing.
- **Don't swallow exceptions** — always log or re-throw; an empty catch block hides bugs.
- **Use try-with-resources** — never rely on `finally` for closing resources.
- **Prefer unchecked exceptions** for programming errors (invalid arguments, illegal state).
- **Prefer checked exceptions** for recoverable conditions the caller must handle.
- **Include context** in exception messages (what failed, which values were involved).
- **Use exception chaining** — pass the original exception as the `cause` when wrapping.

## Examples

| File                                                                                        | Demonstrates                                            |
| ------------------------------------------------------------------------------------------- | ------------------------------------------------------- |
| [`ExceptionHierarchy.java`](src/main/java/course/ch14/examples/ExceptionHierarchy.java)    | Catching specific vs. general exceptions, multi-catch   |
| [`TryWithResources.java`](src/main/java/course/ch14/examples/TryWithResources.java)        | `AutoCloseable`, try-with-resources, suppressed exceptions |
| [`CustomExceptions.java`](src/main/java/course/ch14/examples/CustomExceptions.java)        | Custom checked and unchecked exceptions, `BankAccount`  |

## Exercises

### Exercise 1: Validator (Guided)

**File:** [`Validator.java`](src/main/java/course/ch14/exercises/Validator.java)

Implement input validation methods that throw a custom `ValidationException` with the field name and an error message:

- `ValidationException` — a checked exception with a `fieldName` field and a `getFieldName()` accessor
- `validateName(String name)` — returns the name if non-null, non-blank, and at most 100 characters; throws `ValidationException` otherwise
- `validateAge(int age)` — returns the age if between 0 and 150 (inclusive); throws `ValidationException` otherwise
- `validateEmail(String email)` — returns the email if non-null and contains `@`; throws `ValidationException` otherwise

```bash
mvn test -Dtest="course.ch14.exercises.ValidatorTest"
```

### Exercise 2: RetryExecutor (Practice)

**File:** [`RetryExecutor.java`](src/main/java/course/ch14/exercises/RetryExecutor.java)

Implement a retry executor that runs a `Callable<T>` and retries on failure:

- Constructor takes `int maxRetries` (0 means one attempt, no retries)
- `execute(Callable<T> task)` — runs the task, retrying up to `maxRetries` times on failure. If all attempts fail, throw a `RuntimeException` with the last exception as cause and all earlier exceptions as suppressed
- `getAttemptCount()` — returns how many attempts were made in the last execution

```bash
mvn test -Dtest="course.ch14.exercises.RetryExecutorTest"
```

### Exercise 3: ResourcePool (Challenge)

**File:** [`ResourcePool.java`](src/main/java/course/ch14/exercises/ResourcePool.java)

Implement a generic pool of `AutoCloseable` resources:

- `ResourcePool<T extends AutoCloseable>` implements `AutoCloseable`
- Constructor takes a `Supplier<T>` factory and `int maxSize`
- `borrow()` — returns an available resource or creates a new one (up to `maxSize`); throws `IllegalStateException` if exhausted
- `release(T resource)` — returns a resource to the pool for reuse
- `close()` — closes all managed resources, collecting exceptions as suppressed on the first one thrown

```bash
mvn test -Dtest="course.ch14.exercises.ResourcePoolTest"
```

## Key Takeaways

- Exceptions are objects in a class hierarchy — `Error` for JVM problems, `Exception` for application-level conditions.
- **Checked exceptions** force the caller to handle them; **unchecked exceptions** signal programming errors.
- Always catch the most **specific** exception type first.
- **try-with-resources** is the standard pattern for managing `AutoCloseable` resources — it handles `close()` and suppressed exceptions automatically.
- Custom exceptions add **domain meaning** and can carry extra data (like a shortfall amount or a field name).
- Use **exception chaining** (`cause` and `addSuppressed`) to preserve the full error history.

## Further Reading

- [JLS §11 — Exceptions](https://docs.oracle.com/javase/specs/jls/se25/html/jls-11.html)
- [JLS §14.20.3 — try-with-resources](https://docs.oracle.com/javase/specs/jls/se25/html/jls-14.html#jls-14.20.3)
- [Java Tutorials — Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- Effective Java, Item 69: Use exceptions only for exceptional conditions
- Effective Java, Item 70: Use checked exceptions for recoverable conditions
- Effective Java, Item 72: Favor the use of standard exceptions
