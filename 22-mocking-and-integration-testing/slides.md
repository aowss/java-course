---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 22'
footer: 'Mocking and Integration Testing'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 22
## Mocking and Integration Testing

---

## Objectives

- Create mocks, stubs, and spies with Mockito
- Stub method return values and verify interactions
- Use argument matchers for flexible stubbing
- Understand when to mock vs. when to use real dependencies

---

## Test Doubles

| Type | What it is | Mockito tool |
|------|------------|--------------|
| **Mock** | Fake object that records interactions | `@Mock`, `mock()` |
| **Stub** | Returns predefined values for method calls | `when(...).thenReturn(...)` |
| **Spy** | Wraps a real object; can stub specific methods | `@Spy`, `spy()` |

---

## Mockito Setup

Use `@ExtendWith(MockitoExtension.class)` to initialize mocks automatically:

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;
}
```

`@InjectMocks` creates the class under test and injects mocks into its fields.

---

## Stubbing Return Values

```java
@Test
void findUserReturnsUserWhenFound() {
    when(repository.findById(1L)).thenReturn(Optional.of(alice));

    assertTrue(userService.findUser(1L).isPresent());
}
```

Stub only the methods your test needs — unrelated calls return defaults (`null`, `0`, empty collections).

---

## Verifying Interactions

Mocks record **how** they were called — verify behavior, not just return values:

```java
verify(repository).findById(1L);
verify(emailService).send(eq("alice@example.com"), eq("Welcome!"));
verifyNoInteractions(auditLog);  // method should never be called
```

---

## Argument Matchers

Flexible stubbing and verification when exact values don't matter:

```java
when(gateway.charge(anyInt(), anyString())).thenReturn(true);
verify(gateway).charge(eq(5000), eq("tok_abc"));
verify(emailService).send(contains("@"), eq("Welcome!"), anyString());
```

Import statically: `any()`, `eq()`, `contains()`, `anyInt()`, `anyString()`.

---

## When to Mock

| Mock | Don't mock |
|------|------------|
| External services (DB, APIs, email) | The class under test |
| Slow, unreliable, or unavailable deps | Value objects / simple data structures |

- **Integration tests** use real dependencies where practical
- Over-mocking leads to **brittle tests** — mock only what you need to isolate

---

## Spies

A **spy** wraps a real object — real methods run unless stubbed:

```java
@Spy
private NotificationService service = new NotificationService();

@Test
void dispatchesViaEmailWhenPreferred() {
    service.notify(user, "Hello");
    verify(service).sendEmail(user, "Hello");
    verify(service, never()).sendSms(any(), any());
}
```

Use spies when you want partial real behavior with selective stubbing.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `MockitoBasicsTest` | `@Mock`, `@InjectMocks`, `when`, `verify` |
| `SpyDemoTest` | Spying on real objects |
| `ArgumentMatcherDemoTest` | `any()`, `eq()`, `contains()` |

```bash
mvn test -pl 22-mocking-and-integration-testing
```

---

## Exercises — Your Turn

1. **UserService** (Guided) — mock `UserRepository` and `EmailService`
2. **OrderService** (Practice) — mock `PaymentGateway` and `InventoryService`
3. **NotificationService** (Challenge) — use a spy to verify dispatch methods by preference

```bash
mvn test -pl 22-mocking-and-integration-testing -Dtest="UserServiceTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Mock **dependencies**, not the class under test
- Verify **interactions** — was the right method called with the right args?
- Use `@ExtendWith(MockitoExtension.class)` to initialize mocks
- Over-mocking leads to brittle tests — mock only what you need to isolate

Further reading: [Mockito documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html) · *Effective Java* Item 51
