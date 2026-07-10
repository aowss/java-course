# Chapter 8: Exception Handling

## Objectives

- Understand the exception class hierarchy: `Throwable`, `Error`, `Exception`, `RuntimeException`
- Distinguish between checked and unchecked exceptions and know when to use each
- Use the `throws` clause correctly — including the optional declaration of runtime exceptions
- Use `try-catch-finally` and multi-catch to handle exceptions
- Use try-with-resources and implement `AutoCloseable` for safe resource management
- Define custom exceptions for domain-specific error conditions
- Apply Effective Java guidance on when and how to throw, catch, and document exceptions

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

**Unchecked exceptions** (subclasses of `RuntimeException`) do not require a `throws` declaration. They typically indicate programming errors — bad arguments, illegal state, or violated preconditions.

### The throws Clause

The `throws` clause lists exception types a method may propagate to its caller:

```java
public void withdraw(double amount) throws InsufficientFundsException {
    // checked exception — compiler requires throws or catch
}

public void setAge(int age) throws IllegalArgumentException {
    // unchecked exception — throws is optional but documents the contract
    if (age < 0) {
        throw new IllegalArgumentException("age: " + age);
    }
}
```

You **may** declare runtime exceptions in `throws` even though the compiler does not require it. This is useful when the exception is part of your public API and callers should know about it (Effective Java, Item 74). Always document every thrown type in Javadoc with `@throws`, whether or not it appears in `throws`.

| Exception type        | Compiler requires `throws`? | Typical use                          |
|-----------------------|-----------------------------|--------------------------------------|
| Checked (`IOException`) | Yes                         | Recoverable, caller should handle    |
| Unchecked (`IllegalArgumentException`) | No              | Programming error, bad input/state   |

**Do not use exceptions for ordinary control flow** (Item 69). A well-designed API should not force callers to catch exceptions for expected outcomes — return a value or use `Optional` instead.

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

### Best Practices (Effective Java)

| Item | Guideline |
|------|-----------|
| 69 | Use exceptions only for **exceptional** conditions — not for ordinary control flow. |
| 70 | Use **checked** exceptions for recoverable conditions; use **runtime** exceptions for programming errors. |
| 71 | Avoid **unnecessary** checked exceptions — if callers cannot recover, prefer unchecked. |
| 72 | Favor **standard** exceptions: `IllegalArgumentException` (bad parameter), `IllegalStateException` (bad state), `IndexOutOfBoundsException`, `UnsupportedOperationException`. |
| 73 | Throw exceptions **appropriate to the abstraction** — a higher-level method should not leak low-level details (wrap `SQLException` in a domain exception). |
| 74 | **Document** every exception a method can throw with `@throws` in Javadoc; declare checked exceptions in `throws`, and declare unchecked ones when they are part of the API contract. |
| 75 | Include **failure-capture information** in detail messages (parameter name, offending value, expected range). |
| 77 | **Never ignore** an exception — log it, re-throw it, or handle it. An empty `catch` block hides bugs. |
| 78 | When failure is transient, **retry** with backoff rather than giving up on the first error (see Exercise 2). |

Additional rules:

- **Catch specific exceptions** — avoid `catch (Exception e)` unless re-throwing or at a top-level handler.
- **Use try-with-resources** — never rely on `finally` alone for closing resources.
- **Use exception chaining** — pass the original exception as the `cause` when wrapping (`new DomainException("...", e)`).

## Common Mistakes

- **Empty or silent `catch` blocks** — an empty `catch` hides failures. At minimum, log the exception; better, handle it or rethrow with context.
- **`catch (Exception e)` everywhere** — catches too much, including errors you should not handle. Catch the most specific type you can recover from.
- **Using exceptions for control flow** — don't throw to exit a loop or return a status code. Use normal returns, `Optional`, or a result type for expected outcomes.
- **Checked exceptions callers cannot fix** — if the caller has no meaningful recovery, prefer an unchecked exception (Item 71) rather than forcing a useless `try-catch`.
- **`throws` without `@throws` documentation** — callers need to know what can fail. Document every thrown type in Javadoc, whether checked or unchecked.
- **Wrapping without a cause** — `throw new ServiceException("failed")` loses the original stack trace. Use `throw new ServiceException("failed", e)`.
- **Closing resources manually in `finally`** — easy to get wrong when multiple exceptions occur. Prefer try-with-resources so `close()` runs reliably and suppressed exceptions are preserved.
- **Declaring runtime exceptions you don't intend to throw** — listing `throws IllegalArgumentException` on every method adds noise. Declare unchecked types only when they are part of the real API contract.

## Examples

| File                                                                                        | Demonstrates                                            |
| ------------------------------------------------------------------------------------------- | ------------------------------------------------------- |
| [`ExceptionHierarchy.java`](src/main/java/course/ch08/examples/ExceptionHierarchy.java)    | Catching specific vs. general exceptions, multi-catch   |
| [`ThrowsClauseDemo.java`](src/main/java/course/ch08/examples/ThrowsClauseDemo.java)      | Declaring checked and unchecked exceptions in `throws`  |
| [`TryWithResources.java`](src/main/java/course/ch08/examples/TryWithResources.java)        | `AutoCloseable`, try-with-resources, suppressed exceptions |
| [`CustomExceptions.java`](src/main/java/course/ch08/examples/CustomExceptions.java)        | Custom checked and unchecked exceptions, `BankAccount`  |

## Exercises

### Exercise 1: Validator (Guided)

**File:** [`Validator.java`](src/main/java/course/ch08/exercises/Validator.java)

Implement input validation methods that throw a custom `ValidationException` with the field name and an error message:

- `ValidationException` — a checked exception with a `fieldName` field and a `getFieldName()` accessor
- `validateName(String name)` — returns the name if non-null, non-blank, and at most 100 characters; throws `ValidationException` otherwise
- `validateAge(int age)` — returns the age if between 0 and 150 (inclusive); throws `ValidationException` otherwise
- `validateEmail(String email)` — returns the email if non-null and contains `@`; throws `ValidationException` otherwise

```bash
mvn test -Dtest="course.ch08.exercises.ValidatorTest"
```

### Exercise 2: RetryExecutor (Practice)

**File:** [`RetryExecutor.java`](src/main/java/course/ch08/exercises/RetryExecutor.java)

Implement a retry executor that runs a `Callable<T>` and retries on failure:

- Constructor takes `int maxRetries` (0 means one attempt, no retries)
- `execute(Callable<T> task)` — runs the task, retrying up to `maxRetries` times on failure. If all attempts fail, throw a `RuntimeException` with the last exception as cause and all earlier exceptions as suppressed
- `getAttemptCount()` — returns how many attempts were made in the last execution

```bash
mvn test -Dtest="course.ch08.exercises.RetryExecutorTest"
```

### Exercise 3: ResourcePool (Challenge)

**File:** [`ResourcePool.java`](src/main/java/course/ch08/exercises/ResourcePool.java)

Implement a generic pool of `AutoCloseable` resources:

- `ResourcePool<T extends AutoCloseable>` implements `AutoCloseable`
- Constructor takes a `Supplier<T>` factory and `int maxSize`
- `borrow()` — returns an available resource or creates a new one (up to `maxSize`); throws `IllegalStateException` if exhausted
- `release(T resource)` — returns a resource to the pool for reuse
- `close()` — closes all managed resources, collecting exceptions as suppressed on the first one thrown

```bash
mvn test -Dtest="course.ch08.exercises.ResourcePoolTest"
```

## Key Takeaways

- **Checked exceptions** force the caller to handle recoverable failures; **unchecked exceptions** signal programming errors.
- You **may declare runtime exceptions** in `throws` to document API contracts, even though the compiler does not require it.
- Always catch the most **specific** exception type first; never use exceptions for control flow.
- **try-with-resources** is the standard pattern for managing `AutoCloseable` resources.
- Prefer **standard exception types** (Item 72) and include **context** in messages (Item 75).
- Use **exception chaining** (`cause` and `addSuppressed`) to preserve the full error history.

## Further Reading

- [JLS §11 — Exceptions](https://docs.oracle.com/javase/specs/jls/se25/html/jls-11.html)
- [JLS §14.20.3 — try-with-resources](https://docs.oracle.com/javase/specs/jls/se25/html/jls-14.html#jls-14.20.3)
- [Java Tutorials — Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- Effective Java, Item 69: Use exceptions only for exceptional conditions
- Effective Java, Item 70: Use checked exceptions for recoverable conditions and runtime exceptions for programming errors
- Effective Java, Item 71: Avoid unnecessary use of checked exceptions
- Effective Java, Item 72: Favor the use of standard exceptions
- Effective Java, Item 73: Throw exceptions appropriate to the abstraction
- Effective Java, Item 74: Document all exceptions thrown by each method
- Effective Java, Item 75: Include failure-capture information in detail messages
- Effective Java, Item 77: Don't ignore exceptions
