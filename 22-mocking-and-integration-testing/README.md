# Chapter 22: Mocking and Integration Testing

## Objectives

- Create mocks, stubs, and spies with Mockito
- Stub method return values and verify interactions
- Use argument matchers for flexible stubbing
- Understand when to mock vs. when to use real dependencies

## Concepts

### Test Doubles

| Type  | What it is                                      | Mockito tool        |
|-------|-------------------------------------------------|---------------------|
| Mock  | Fake object that records interactions           | `@Mock`, `mock()`   |
| Stub  | Returns predefined values for method calls      | `when(...).thenReturn(...)` |
| Spy   | Wraps a real object, can stub specific methods  | `@Spy`, `spy()`     |

### Mockito Basics

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @Test
    void findUserReturnsUserWhenFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(alice));
        assertTrue(userService.findUser(1L).isPresent());
        verify(repository).findById(1L);
    }
}
```

### Argument Matchors

```java
when(gateway.charge(anyInt(), anyString())).thenReturn(true);
verify(gateway).charge(eq(5000), eq("tok_abc"));
verify(emailService).send(contains("@"), eq("Welcome!"), anyString());
```

### When to Mock

- **Mock** external services (databases, APIs, email) — slow, unreliable, or unavailable in tests.
- **Don't mock** the class under test.
- **Don't mock** value objects or simple data structures.
- **Integration tests** use real dependencies where practical (Chapter 22 exercises focus on unit testing with mocks).

## Examples

| File                                                                                     | Demonstrates                    |
|------------------------------------------------------------------------------------------|---------------------------------|
| [`MockitoBasicsTest.java`](src/test/java/course/ch22/examples/MockitoBasicsTest.java)     | `@Mock`, `@InjectMocks`, verify |
| [`SpyDemoTest.java`](src/test/java/course/ch22/examples/SpyDemoTest.java)                | Spying on real objects          |
| [`ArgumentMatcherDemoTest.java`](src/test/java/course/ch22/examples/ArgumentMatcherDemoTest.java) | `any()`, `eq()`, `contains()` |

## Exercises

### Exercise 1: Test UserService (Guided)

**File:** [`UserServiceTest.java`](src/test/java/course/ch22/exercises/UserServiceTest.java)

Write tests for `UserService` by mocking `UserRepository` and `EmailService`.

```bash
mvn test -pl 22-mocking-and-integration-testing -Dtest="UserServiceTest"
```

### Exercise 2: Test OrderService (Practice)

**File:** [`OrderServiceTest.java`](src/test/java/course/ch22/exercises/OrderServiceTest.java)

Mock `PaymentGateway` and `InventoryService` to test order placement scenarios.

```bash
mvn test -pl 22-mocking-and-integration-testing -Dtest="OrderServiceTest"
```

### Exercise 3: Test NotificationService with Spy (Challenge)

**File:** [`NotificationServiceTest.java`](src/test/java/course/ch22/exercises/NotificationServiceTest.java)

Use a spy to verify which dispatch methods are called for different preferences.

```bash
mvn test -pl 22-mocking-and-integration-testing -Dtest="NotificationServiceTest"
```

## Key Takeaways

- Mock **dependencies**, not the class under test.
- Verify **interactions** (was the right method called?) not just return values.
- Use `@ExtendWith(MockitoExtension.class)` to initialize mocks.
- Over-mocking leads to brittle tests — mock only what you need to isolate.

## Bridge Project — Catalog Service

Finished Part VII? Practice a testable service layer with JUnit and Mockito: [`bridges/catalog-service`](../bridges/catalog-service/README.md).

## Further Reading

- [Mockito documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- Effective Java, Item 51: Design method signatures carefully
