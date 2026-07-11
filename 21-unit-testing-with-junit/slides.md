---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 21'
footer: 'Unit Testing with JUnit'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 21
## Unit Testing with JUnit

---

## Objectives

- Understand the purpose and principles of unit testing
- Write tests using JUnit Jupiter assertions: `assertEquals`, `assertTrue`, `assertThrows`, `assertAll`
- Use lifecycle hooks: `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`
- Write parameterized tests with `@ParameterizedTest`, `@CsvSource`, `@ValueSource`, `@MethodSource`
- Organize tests with `@Nested` classes and `@DisplayName`

---

## What Is Unit Testing?

A **unit test** verifies a single unit of code (typically a method or class) in isolation.

| Property | Description |
|----------|-------------|
| **Fast** | Milliseconds — no database, network, or file I/O |
| **Isolated** | Independent — no shared mutable state |
| **Repeatable** | Same result every time |
| **Self-validating** | Pass or fail automatically |
| **Timely** | Written close to the code they test |

---

## JUnit Jupiter — The Basics

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void addReturnsSum() {
        var calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }
}
```

- Test classes don't need to be `public`
- Method names should describe expected behavior
- One concept per test method

---

## Assertions

| Assertion | Purpose |
|-----------|---------|
| `assertEquals(expected, actual)` | Values are equal |
| `assertTrue` / `assertFalse` | Condition is true / false |
| `assertNull` / `assertNotNull` | Null checks |
| `assertThrows(ExType.class, executable)` | Expected exception thrown |
| `assertAll(heading, executables...)` | Run all, report all failures |
| `assertTimeout(duration, executable)` | Completes within timeout |

---

## `assertThrows`

Returns the caught exception for further assertions:

```java
@Test
void divideByZeroThrows() {
    ArithmeticException ex = assertThrows(
            ArithmeticException.class,
            () -> calculator.divide(10, 0)
    );
    assertEquals("Cannot divide by zero", ex.getMessage());
}
```

---

## `assertAll`

Runs all assertions even if some fail, then reports every failure:

```java
@Test
void userProperties() {
    var user = new User("Alice", 30, "alice@example.com");
    assertAll("user",
            () -> assertEquals("Alice", user.name()),
            () -> assertEquals(30, user.age()),
            () -> assertEquals("alice@example.com", user.email())
    );
}
```

---

## Lifecycle Hooks

JUnit creates a **new test class instance** for each test method:

| Annotation | Runs | Instance | Typical Use |
|------------|------|----------|-------------|
| `@BeforeAll` | Once before all tests | Static | Expensive one-time setup |
| `@BeforeEach` | Before each test | Instance | Fresh test fixtures |
| `@AfterEach` | After each test | Instance | Release resources |
| `@AfterAll` | Once after all tests | Static | Tear down shared resources |

---

## Parameterized Tests

Run the same test logic with different inputs — use `@ParameterizedTest` plus a source:

```java
@ParameterizedTest(name = "{0}°C = {1}°F")
@CsvSource({ "0, 32.0", "100, 212.0", "-40, -40.0" })
void celsiusToFahrenheit(double celsius, double expectedF) {
    assertEquals(expectedF, converter.toFahrenheit(celsius), 0.1);
}
```

---

## Parameter Sources

| Source | Use case |
|--------|----------|
| `@ValueSource` | Simple literal values |
| `@CsvSource` | Comma-separated input/output pairs |
| `@MethodSource` | Arguments from a static factory method |

```java
static Stream<Arguments> additionCases() {
    return Stream.of(
            Arguments.of(1, 2, 3),
            Arguments.of(-1, -1, -2));
}
```

---

## `@Nested` Tests

Group tests by state or scenario — inner classes inherit outer `@BeforeEach`:

```java
@Nested
@DisplayName("after pushing")
class AfterPushing {
    @BeforeEach void push() { stack.push("element"); }

    @Test void isNotEmpty() { assertFalse(stack.isEmpty()); }
}
```

Test reports show a clear hierarchy: `A Stack → after pushing → isNotEmpty`.

---

## `@DisplayName` and Assumptions

**Display names** make output human-readable:

```java
@DisplayName("newly created cart is empty")
void newCartIsEmpty() { ... }
```

**Assumptions** skip tests when a condition isn't met (not a failure):

```java
assumeTrue(System.getProperty("os.name").contains("Linux"));
```

---

## Examples

| File | Topic |
|------|-------|
| `AssertionShowcaseTest` | `assertEquals`, `assertThrows`, `assertAll` |
| `ParameterizedTestDemo` | `@CsvSource`, `@ValueSource`, `@MethodSource` |
| `LifecycleDemo` | `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll` |
| `NestedTestDemo` | `@Nested` state-based organization |

```bash
mvn test -pl 21-unit-testing-with-junit
```

---

## Exercises

Exercises are **reversed** — production code is provided; you write the tests.

1. **Calculator Tests** (Guided) — `add`, `subtract`, `multiply`, `divide`, division by zero
2. **Roman Numeral** (Practice) — parameterized tests with `@CsvSource` / `@MethodSource`
3. **Shopping Cart** (Challenge) — nested tests: `WhenEmpty`, `WhenHasItems`, `WithDiscount`

```bash
mvn test -pl 21-unit-testing-with-junit -Dtest="CalculatorTest"
```



---

## Key Takeaways

- Unit tests verify individual methods in isolation — **fast, isolated, repeatable**
- Rich assertions: `assertEquals`, `assertThrows`, `assertAll` for grouped checks
- **Lifecycle hooks** ensure each test gets a fresh fixture
- **Parameterized tests** eliminate duplication; **nested tests** organize by state
- `@DisplayName` makes test output human-readable

Full lesson: [`README.md`](README.md)
Further reading: [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/) · [Parameterized Tests (Baeldung)](https://www.baeldung.com/parameterized-tests-junit-5)
