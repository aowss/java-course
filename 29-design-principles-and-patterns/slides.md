---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 29'
footer: 'Design Principles and Patterns'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 29
## Design Principles and Patterns

---

## Objectives

- Apply SOLID principles to guide class and interface design
- Implement the Strategy, Observer, Factory, and Builder patterns
- Recognize when a pattern solves a specific design problem
- Prefer composition over inheritance for flexible designs

---

## SOLID Principles

| Principle | Name | Summary |
|-----------|------|---------|
| **S** | Single Responsibility | A class should have one reason to change |
| **O** | Open/Closed | Open for extension, closed for modification |
| **L** | Liskov Substitution | Subtypes must be substitutable for base types |
| **I** | Interface Segregation | Clients should not depend on unused interfaces |
| **D** | Dependency Inversion | Depend on abstractions, not concrete classes |

---

## Single Responsibility

Split classes that handle unrelated concerns:

```java
// Bad: one class handles data AND persistence AND formatting
class Report { /* generate, save, print */ }

// Good: separate concerns
class ReportGenerator { /* generate */ }
class ReportRepository { /* save/load */ }
class ReportFormatter { /* format for display */ }
```

---

## Open/Closed & Dependency Inversion

**Open/Closed** — add behavior via new classes, not by editing existing ones. Strategy pattern demonstrates this: new pricing rules are new `PricingStrategy` implementations.

**Dependency Inversion** — high-level modules depend on interfaces:

```java
class OrderService {
    private final PaymentGateway gateway; // interface
    OrderService(PaymentGateway gateway) { this.gateway = gateway; }
}
```

---

## Strategy Pattern

Encapsulate interchangeable algorithms behind a common interface:

```
┌──────────────┐       uses        ┌──────────────────┐
│   Context    │ ────────────────> │ PricingStrategy  │ (interface)
└──────────────┘                   └──────────────────┘
                                          △
                          NoDiscount  PercentageDiscount  FixedDiscount
```

Use when: multiple algorithms exist and the choice varies at **runtime**.

---

## Observer Pattern

A subject maintains observers and notifies them on state changes:

```
┌──────────────┐  register   ┌──────────────────┐
│ WeatherStation│ <───────── │ TemperatureObserver│
│  (subject)   │  notify ─> └──────────────────┘
└──────────────┘                    △
                          DisplayUnit    DataLogger
```

Use when: changes in one object require updating **multiple dependents**.

---

## Factory Pattern

Delegate object creation to a factory, hiding concrete types from clients:

```java
Vehicle vehicle = VehicleFactory.create("car");
// client knows the interface, not Car vs Truck vs Bus
```

Use when: creation logic is complex, or the concrete type should be **decoupled** from the client.

---

## Builder Pattern

Construct complex objects step by step with a fluent API:

```java
Pizza pizza = Pizza.builder()
    .size("large")
    .crust("thin")
    .topping("pepperoni")
    .build();
```

Use when: an object has many optional parameters, or construction involves multiple steps.

---

## Patterns at a Glance

| Pattern | Problem solved | Key mechanism |
|---------|----------------|---------------|
| Strategy | Interchangeable algorithms | Interface + composition |
| Observer | One-to-many state change notification | Register/notify callbacks |
| Factory | Decouple creation from usage | Factory method/class |
| Builder | Complex object construction | Fluent step-by-step API |

> Patterns are tools, not goals — apply them when they solve a **real** problem.

---

## Examples

| File | Topic |
|------|-------|
| `StrategyPattern` | Pricing strategies (no/percentage/fixed) |
| `ObserverPattern` | Weather station with temperature observers |
| `BuilderPattern` | Fluent pizza builder |
| `FactoryPattern` | Vehicle factory by type name |

```bash
mvn test -pl 29-design-principles-and-patterns
```

---

## Exercises

1. **NotificationStrategy** (Guided) — email, SMS, and push notification strategies
2. **EventSystem** (Practice) — generic event bus with subscribe/publish
3. **QueryBuilder** (Challenge) — SQL SELECT with optional WHERE, ORDER BY, LIMIT

```bash
mvn test -pl 29-design-principles-and-patterns -Dtest="NotificationStrategyTest"
```



---

## Key Takeaways

- **SOLID principles** guide maintainable design — especially SRP and Dependency Inversion
- **Strategy** swaps algorithms at runtime without modifying the client
- **Observer** decouples event producers from consumers via subscribe/notify
- **Factory** hides concrete types and centralizes creation logic
- **Builder** makes complex object construction readable and safe

Full lesson: [`README.md`](README.md)
Further reading: [GoF Design Patterns](https://www.oreilly.com/library/view/design-patterns-elements/0201633612/) · [Refactoring Guru](https://refactoring.guru/design-patterns)
