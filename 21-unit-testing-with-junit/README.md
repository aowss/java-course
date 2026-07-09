# Chapter 21: Unit Testing with JUnit

## Objectives

- Understand the purpose and principles of unit testing
- Write tests using JUnit Jupiter assertions: `assertEquals`, `assertTrue`, `assertThrows`, `assertAll`
- Use lifecycle hooks: `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`
- Write parameterized tests with `@ParameterizedTest`, `@CsvSource`, `@ValueSource`, `@MethodSource`
- Organize tests with `@Nested` classes and `@DisplayName`
- Understand test isolation and the one-assertion-per-concept principle

## Concepts

### What is Unit Testing?

A **unit test** verifies that a single unit of code (typically a method or class) behaves correctly in isolation. Good unit tests are:

| Property       | Description                                                    |
|----------------|----------------------------------------------------------------|
| **Fast**       | Execute in milliseconds — no database, network, or file I/O   |
| **Isolated**   | Each test is independent — no shared mutable state             |
| **Repeatable** | Same result every time, regardless of environment              |
| **Self-validating** | Pass or fail automatically — no manual inspection         |
| **Timely**     | Written close to the code they test (ideally before or during) |

### JUnit Jupiter — The Basics

JUnit Jupiter (part of JUnit 5/6) is the modern testing framework for Java. A test class is any class containing methods annotated with `@Test`:

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

Key conventions:
- Test classes don't need to be `public` (JUnit Jupiter uses reflection)
- Test method names should describe the expected behavior
- One concept per test method

### Assertions

JUnit provides rich assertion methods in `org.junit.jupiter.api.Assertions`:

| Assertion                                    | Purpose                                                   |
|----------------------------------------------|-----------------------------------------------------------|
| `assertEquals(expected, actual)`             | Values are equal                                          |
| `assertNotEquals(unexpected, actual)`         | Values are not equal                                      |
| `assertTrue(condition)`                      | Condition is true                                         |
| `assertFalse(condition)`                     | Condition is false                                        |
| `assertNull(value)`                          | Value is null                                             |
| `assertNotNull(value)`                       | Value is not null                                         |
| `assertThrows(ExType.class, executable)`     | Executable throws the expected exception                  |
| `assertDoesNotThrow(executable)`             | Executable does not throw                                 |
| `assertAll(heading, executables...)`         | Run all assertions, report all failures (grouped)         |
| `assertTimeout(duration, executable)`        | Executable completes within the timeout                   |
| `assertIterableEquals(expected, actual)`     | Two iterables have the same elements in the same order    |

#### Example: `assertThrows`

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

`assertThrows` returns the caught exception, so you can make further assertions on it.

#### Example: `assertAll`

`assertAll` runs all assertions even if some fail, then reports all failures:

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

### Lifecycle Hooks

JUnit creates a **new test class instance** for each test method. Lifecycle annotations control setup and teardown:

| Annotation     | Runs                                    | Instance | Typical Use                        |
|----------------|-----------------------------------------|----------|------------------------------------|
| `@BeforeAll`   | Once before all tests in the class      | Static   | Expensive one-time setup           |
| `@BeforeEach`  | Before each test method                 | Instance | Create fresh test fixtures         |
| `@AfterEach`   | After each test method                  | Instance | Release resources                  |
| `@AfterAll`    | Once after all tests in the class       | Static   | Tear down shared resources         |

```java
class DatabaseTest {

    private Connection connection;

    @BeforeEach
    void setUp() {
        connection = createTestConnection();
    }

    @AfterEach
    void tearDown() {
        connection.close();
    }

    @Test
    void queryReturnsResults() {
        // connection is freshly created for this test
    }
}
```

### Parameterized Tests

Parameterized tests run the same test logic with different inputs. Annotate with `@ParameterizedTest` instead of `@Test` and provide a source of arguments:

#### `@ValueSource` — simple literal values

```java
@ParameterizedTest
@ValueSource(strings = {"racecar", "madam", "level"})
void isPalindrome(String word) {
    assertTrue(StringUtils.isPalindrome(word));
}
```

#### `@CsvSource` — comma-separated input/output pairs

```java
@ParameterizedTest(name = "{0}°C = {1}°F")
@CsvSource({
        "0,    32.0",
        "100,  212.0",
        "-40,  -40.0"
})
void celsiusToFahrenheit(double celsius, double expectedF) {
    assertEquals(expectedF, converter.toFahrenheit(celsius), 0.1);
}
```

#### `@MethodSource` — arguments from a static method

```java
static Stream<Arguments> additionCases() {
    return Stream.of(
            Arguments.of(1, 2, 3),
            Arguments.of(-1, -1, -2),
            Arguments.of(0, 0, 0)
    );
}

@ParameterizedTest
@MethodSource("additionCases")
void add(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}
```

### Nested Tests

`@Nested` classes group tests by state or scenario. Inner classes inherit the outer class's `@BeforeEach`:

```java
@DisplayName("A Stack")
class StackTest {

    private Deque<String> stack;

    @BeforeEach
    void createStack() {
        stack = new ArrayDeque<>();
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Nested
    @DisplayName("after pushing")
    class AfterPushing {
        @BeforeEach
        void push() {
            stack.push("element");
        }

        @Test
        void isNotEmpty() {
            assertFalse(stack.isEmpty());
        }
    }
}
```

This creates a clear hierarchy in test reports:
```
A Stack
  ✓ isEmpty
  after pushing
    ✓ isNotEmpty
```

### Display Names

`@DisplayName` provides human-readable names for test classes and methods:

```java
@DisplayName("Shopping Cart")
class ShoppingCartTest {

    @Test
    @DisplayName("newly created cart is empty")
    void newCartIsEmpty() { ... }
}
```

### Assumptions

Assumptions conditionally skip tests based on runtime conditions:

```java
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Test
void onlyOnLinux() {
    assumeTrue(System.getProperty("os.name").contains("Linux"));
    // this test only runs on Linux
}
```

If the assumption fails, the test is **skipped** (not failed).

## Examples

| File                                                                                                            | Demonstrates                                                  |
|-----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------|
| [`AssertionShowcase.java`](src/main/java/course/ch21/examples/AssertionShowcase.java)                           | Utility class used in assertion demos                         |
| [`AssertionShowcaseTest.java`](src/test/java/course/ch21/examples/AssertionShowcaseTest.java)                   | `assertEquals`, `assertTrue`, `assertThrows`, `assertAll`     |
| [`ParameterizedTestDemo.java`](src/test/java/course/ch21/examples/ParameterizedTestDemo.java)                  | `@CsvSource`, `@ValueSource`, `@MethodSource`                |
| [`LifecycleDemo.java`](src/test/java/course/ch21/examples/LifecycleDemo.java)                                  | `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll`        |
| [`NestedTestDemo.java`](src/test/java/course/ch21/examples/NestedTestDemo.java)                                | `@Nested` classes for state-based test organization           |

## Exercises

In this chapter, the exercises are *reversed*: the production code is provided, and you write the tests.

### Exercise 1: Calculator Tests (Guided)

**Production code:** [`Calculator.java`](src/main/java/course/ch21/examples/Calculator.java)
**Test file:** [`CalculatorTest.java`](src/test/java/course/ch21/exercises/CalculatorTest.java)

Write tests for the `Calculator` class. Test `add`, `subtract`, `multiply`, `divide`, and division by zero. The skeleton has `@Test` methods with `// TODO` — implement each one.

```bash
mvn test -Dtest="course.ch21.exercises.CalculatorTest"
```

### Exercise 2: Roman Numeral Parameterized Tests (Practice)

**Production code:** [`RomanNumeral.java`](src/main/java/course/ch21/examples/RomanNumeral.java)
**Test file:** [`RomanNumeralTest.java`](src/test/java/course/ch21/exercises/RomanNumeralTest.java)

Write parameterized tests for the `RomanNumeral` converter using `@CsvSource`, `@ValueSource`, and/or `@MethodSource`. Test at least 8 conversion pairs for `toRoman` and `fromRoman`, plus roundtrip verification and edge cases.

```bash
mvn test -Dtest="course.ch21.exercises.RomanNumeralTest"
```

### Exercise 3: Shopping Cart Nested Tests (Challenge)

**Production code:** [`ShoppingCart.java`](src/main/java/course/ch21/examples/ShoppingCart.java)
**Test file:** [`ShoppingCartTest.java`](src/test/java/course/ch21/exercises/ShoppingCartTest.java)

Write nested tests for `ShoppingCart` organized by cart state:
- `WhenEmpty` — verify empty cart behavior
- `WhenHasItems` — add items in `@BeforeEach`, verify counts, subtotal, quantities
- `WithDiscount` (nested inside `WhenHasItems`) — apply a discount, verify total calculation

```bash
mvn test -Dtest="course.ch21.exercises.ShoppingCartTest"
```

## Key Takeaways

- **Unit tests** verify individual methods in isolation — they should be fast, isolated, and repeatable
- JUnit Jupiter provides rich assertions: `assertEquals`, `assertThrows`, `assertAll` for grouped checks
- **Lifecycle hooks** (`@BeforeEach`, `@AfterEach`) ensure each test gets a fresh fixture
- **Parameterized tests** eliminate duplication by running the same logic with different data
- **Nested tests** (`@Nested`) organize tests by state or scenario for clear, hierarchical reports
- `@DisplayName` makes test output human-readable

## Further Reading

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [JUnit 5 Assertions Javadoc](https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html)
- [Baeldung — Guide to JUnit 5 Parameterized Tests](https://www.baeldung.com/parameterized-tests-junit-5)
- [Effective Unit Testing by Lasse Koskela](https://www.manning.com/books/effective-unit-testing)
