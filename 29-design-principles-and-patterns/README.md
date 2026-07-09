# Chapter 29: Design Principles and Patterns

## Objectives

- Apply SOLID principles to guide class and interface design
- Implement the Strategy, Observer, Factory, and Builder patterns
- Recognize when a pattern solves a specific design problem
- Prefer composition over inheritance for flexible designs

## Concepts

### SOLID Principles

| Principle | Name                      | Summary                                                    |
|-----------|---------------------------|------------------------------------------------------------|
| S         | Single Responsibility     | A class should have one reason to change                   |
| O         | Open/Closed               | Open for extension, closed for modification                |
| L         | Liskov Substitution       | Subtypes must be substitutable for their base types        |
| I         | Interface Segregation     | Clients should not depend on interfaces they do not use   |
| D         | Dependency Inversion      | Depend on abstractions, not concrete implementations       |

**Single Responsibility** — split classes that handle unrelated concerns:

```java
// Bad: one class handles data AND persistence AND formatting
class Report { /* generate, save, print */ }

// Good: separate concerns
class ReportGenerator { /* generate */ }
class ReportRepository { /* save/load */ }
class ReportFormatter { /* format for display */ }
```

**Open/Closed** — add behavior via new classes, not by editing existing ones. The Strategy pattern (Example 1) demonstrates this: new pricing rules are new `PricingStrategy` implementations.

**Dependency Inversion** — high-level modules depend on interfaces:

```java
class OrderService {
    private final PaymentGateway gateway; // interface, not concrete class
    OrderService(PaymentGateway gateway) { this.gateway = gateway; }
}
```

### Strategy Pattern

Encapsulate interchangeable algorithms behind a common interface. The client selects the strategy at runtime.

```
┌──────────────┐       uses        ┌──────────────────┐
│   Context    │ ────────────────> │ PricingStrategy  │ (interface)
│ (calculate)  │                   └──────────────────┘
└──────────────┘                          △
                                          │
                          ┌───────────────┼───────────────┐
                          │               │               │
                   NoDiscount    PercentageDiscount   FixedDiscount
```

Use when: multiple algorithms exist for the same task and the choice varies at runtime.

### Observer Pattern

A subject maintains a list of observers and notifies them on state changes.

```
┌──────────────┐  register   ┌──────────────────┐
│ WeatherStation│ <───────── │ TemperatureObserver│
│  (subject)   │  notify ─> └──────────────────┘
└──────────────┘                    △
                                    │
                          ┌─────────┴─────────┐
                          │                   │
                     DisplayUnit          DataLogger
```

Use when: changes in one object require updating multiple dependents.

### Factory Pattern

Delegate object creation to a factory, hiding concrete types from clients.

```java
Vehicle vehicle = VehicleFactory.create("car");
// client knows the interface, not Car vs Truck vs Bus
```

Use when: object creation logic is complex, or the concrete type should be decoupled from the client.

### Builder Pattern

Construct complex objects step by step with a fluent API.

```java
Pizza pizza = Pizza.builder()
    .size("large")
    .crust("thin")
    .topping("pepperoni")
    .build();
```

Use when: an object has many optional parameters, or construction involves multiple steps.

| Pattern  | Problem solved                          | Key mechanism                |
|----------|-----------------------------------------|------------------------------|
| Strategy | Interchangeable algorithms              | Interface + composition      |
| Observer | One-to-many state change notification   | Register/notify callbacks    |
| Factory  | Decouple creation from usage            | Factory method/class         |
| Builder  | Complex object construction             | Fluent step-by-step API      |

## Examples

| File                                                                                    | Demonstrates                              |
|-----------------------------------------------------------------------------------------|-------------------------------------------|
| [`StrategyPattern.java`](src/main/java/course/ch29/examples/StrategyPattern.java)       | Pricing strategies (no/percentage/fixed)  |
| [`ObserverPattern.java`](src/main/java/course/ch29/examples/ObserverPattern.java)       | Weather station with temperature observers|
| [`BuilderPattern.java`](src/main/java/course/ch29/examples/BuilderPattern.java)           | Fluent pizza builder                      |
| [`FactoryPattern.java`](src/main/java/course/ch29/examples/FactoryPattern.java)         | Vehicle factory by type name              |

## Exercises

### Exercise 1: NotificationStrategy (Guided)

**File:** [`NotificationStrategy.java`](src/main/java/course/ch29/exercises/NotificationStrategy.java)

Implement email, SMS, and push notification strategies.

```bash
mvn test -Dtest="course.ch29.exercises.NotificationStrategyTest"
```

### Exercise 2: EventSystem (Practice)

**File:** [`EventSystem.java`](src/main/java/course/ch29/exercises/EventSystem.java)

Build a generic event bus with subscribe/publish semantics.

```bash
mvn test -Dtest="course.ch29.exercises.EventSystemTest"
```

### Exercise 3: QueryBuilder (Challenge)

**File:** [`QueryBuilder.java`](src/main/java/course/ch29/exercises/QueryBuilder.java)

Construct SQL SELECT statements with optional WHERE, ORDER BY, and LIMIT clauses.

```bash
mvn test -Dtest="course.ch29.exercises.QueryBuilderTest"
```

## Key Takeaways

- **SOLID principles** guide maintainable design — especially Single Responsibility and Dependency Inversion.
- **Strategy** swaps algorithms at runtime without modifying the client.
- **Observer** decouples event producers from consumers via a subscribe/notify protocol.
- **Factory** hides concrete types and centralizes creation logic.
- **Builder** makes complex object construction readable and safe.
- Patterns are tools, not goals — apply them when they solve a real problem, not preemptively.

## Further Reading

- [Design Patterns: Elements of Reusable Object-Oriented Software (GoF)](https://www.oreilly.com/library/view/design-patterns-elements/0201633612/)
- [Head First Design Patterns](https://www.oreilly.com/library/view/head-first-design/9781492077992/)
- [Effective Java, Chapter on Methods and Classes](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [Refactoring Guru: Design Patterns](https://refactoring.guru/design-patterns)
