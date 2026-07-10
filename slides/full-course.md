<!-- .slide: class="intro-slide" -->
# Java Course
## Java 25 — complete deck (30 chapters)

| Key | Action |
|-----|--------|
| **→** / **←** | Next / previous **chapter** |
| **↓** / **↑** | Next / previous **slide** in this chapter |
| **Esc** | Slide overview |

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 1
## Introduction to Java

--

## Objectives

- Understand Java's design philosophy and where it fits
- Distinguish compiled vs interpreted — and why Java is **both**
- Know the roles of **JDK**, **JRE**, and **JVM**
- Compile and run a program from the command line
- Trace the path from source code → bytecode → JVM

--

## Java's Core Principles

Created by James Gosling at Sun Microsystems (1995):

- **Write once, run anywhere** — bytecode runs on any JVM
- **Object-oriented** — everything (except primitives) is an object
- **Strongly typed** — compiler catches type errors early
- **Memory-managed** — garbage collector handles allocation

--

## JDK, JRE, JVM

| Component | Purpose | Contains |
|-----------|---------|----------|
| **JVM** | Executes bytecode | Interpreter, JIT, GC |
| **JRE** | Run Java programs | JVM + standard library |
| **JDK** | Develop Java programs | JRE + `javac`, `jshell`, debugger |

> Since Java 11, only the **JDK** is distributed — no separate JRE download.

--

## Compiled vs Interpreted

| Strategy | How it works | Examples |
|----------|--------------|----------|
| **Compiled** | Source → machine code *before* run | C, C++, Rust, Go |
| **Interpreted** | Source executed line-by-line at runtime | Python, Ruby, JS |

Java does **both** — ahead-of-time compilation *and* runtime interpretation.

--

## Java's Two-Stage Model

1. **`javac`** compiles `.java` → `.class` bytecode (ahead-of-time)
2. **JVM** interprets bytecode at runtime
3. **JIT compiler** compiles hot paths to native machine code

**Result:** early error checking + platform independence + near-native speed.

--

## Compilation and Execution

```
  Source (.java)
       │
       ▼  javac
  Bytecode (.class)
       │
       ▼  java (JVM)
  Program output
```

- Bytecode is **platform-independent**
- The JVM is **platform-specific**
- This is the mechanism behind "write once, run anywhere"

--

## Your First Program

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Every Java program needs:
- A `class` — file name must match the class name
- `public static void main(String[] args)` — the entry point

--

## Compile and Run

```bash
javac src/main/java/course/ch01/examples/HelloWorld.java
java -cp src/main/java course.ch01.examples.HelloWorld
```

Or with Maven:

```bash
mvn compile exec:java -Dexec.mainClass="course.ch01.examples.HelloWorld"
```

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `HelloWorld` | Minimal program — class, main, println |
| `JvmInfo` | Querying JVM properties at runtime |

```bash
mvn test -pl 01-introduction-to-java
```

--

## Exercises — Your Turn

1. **Greeting** (Guided) — print `Hello, <name>!` from command-line args
2. **System Reporter** (Practice) — return Java version, OS name, and architecture

```bash
mvn test -pl 01-introduction-to-java -Dtest="GreetingTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Java source compiles to platform-independent **bytecode** executed by the **JVM**
- The **JDK** includes the compiler (`javac`) and the runtime
- Every program starts at `public static void main(String[] args)`
- File name must match the public class name

Further reading: [JLS §7.6](https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html#jls-7.6) · [Oracle Getting Started](https://docs.oracle.com/en/java/javase/25/docs/api/)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 2
## Language Basics

--

## Objectives

- Understand **static** vs **dynamic** typing — and why Java chose static
- Know Java's eight **primitive types** and when to use each
- Declare variables, constants, and use `var` for type inference
- Use arithmetic, relational, logical, and bitwise operators
- Control flow with `if`, `switch`, loops, and **arrays**

--

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

--

## Primitive Types (1 of 2)

| Type | Size | Use case |
|------|------|----------|
| `byte` | 8 bits | Raw binary data, I/O |
| `short` | 16 bits | Rarely used |
| `int` | 32 bits | General-purpose integer |
| `long` | 64 bits | Large counts, timestamps |

Prefer **`int`** for integers unless you need a larger range.

--

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

--

## Variables and Constants

```java
int count = 0;              // mutable
final int MAX_SIZE = 100;   // constant — cannot reassign
var name = "Alice";         // inferred as String (Java 10+)
```

- `var` only for **local** variables with an initializer
- `var` does **not** mean untyped — compiler infers and enforces the type

--

## Operators

| Category | Operators |
|----------|-----------|
| Arithmetic | `+`, `-`, `*`, `/`, `%` |
| Relational | `==`, `!=`, `<`, `>`, `<=`, `>=` |
| Logical | `&&`, `\|\|`, `!` |
| Bitwise | `&`, `\|`, `^`, `~`, `<<`, `>>`, `>>>` |
| Ternary | `condition ? trueVal : falseVal` |

> **Integer division truncates:** `7 / 2` → `3`, not `3.5`

--

## Control Flow — `if` and `switch`

```java
if (score >= 90) grade = 'A';
else if (score >= 80) grade = 'B';
else grade = 'C';
```

**Enhanced `switch`** (Java 14+) — no fall-through, can return a value:

```java
String label = switch (day) {
    case "MONDAY", "FRIDAY"   -> "Work";
    case "SATURDAY", "SUNDAY" -> "Rest";
    default                   -> "Midweek";
};
```

--

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

--

## Arrays

Fixed-size, zero-indexed containers of a single type:

```java
int[] primes = {2, 3, 5, 7, 11};

primes[0]   // → 2
primes[4]   // → 11
primes[5]   // → ArrayIndexOutOfBoundsException!
```

Indices: `0` to `length - 1`. No compile-time bounds checking.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `PrimitiveTypes` | All eight primitives, literals, ranges |
| `ControlFlow` | `if`, enhanced `switch`, all loop forms |
| `ArrayBasics` | Creation, access, iteration, pitfalls |

```bash
mvn test -pl 02-language-basics
```

--

## Exercises — Your Turn

1. **Temperature Converter** (Guided) — Celsius ↔ Fahrenheit
2. **FizzBuzz** (Practice) — divisibility by 3 and 5
3. **Array Statistics** (Challenge) — min, max, sum, average

```bash
mvn test -pl 02-language-basics -Dtest="TemperatureConverterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Java is **statically typed** — every variable has a fixed compile-time type
- Prefer `int` and `double`; use `final` for values that must not change
- `var` is a local convenience — it does not weaken type safety
- Arrays are fixed-size; for dynamic collections, see Chapter 12

Further reading: [JLS §4.2](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.2) · *Effective Java* Item 61

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 3
## Methods

--

## Objectives

- Define methods with parameters and return types
- Understand **method overloading** and compiler resolution
- Use **varargs** for variable-length argument lists
- Model **stack** and **heap** memory — and pass-by-value
- Reason about variable **scope** and **lifetime**

--

## Defining Methods

```java
public static double circleArea(double radius) {
    return Math.PI * radius * radius;
}
```

- **Return type** — value produced (`void` if none)
- **Parameters** — inputs from the caller
- **`static`** — belongs to the class, callable without an instance

--

## Pass-by-Value

Java passes **everything by value**:

- Primitives → a **copy** of the value
- References → a **copy** of the reference (object is shared)

```java
public static void tryToReassign(String s) {
    s = "changed";   // only changes local copy
}
// caller's variable is unchanged
```

--

## Method Overloading

Same name, different parameter lists:

```java
public static int add(int a, int b) { return a + b; }
public static double add(double a, double b) { return a + b; }
public static int add(int a, int b, int c) { return a + b + c; }
```

- Resolved at **compile time** by parameter types and count
- **Never** by return type alone

--

## Varargs

Accept zero or more arguments — treated as an array inside:

```java
public static int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) total += n;
    return total;
}

sum();              // 0
sum(1, 2, 3);      // 6
```

Rules: at most **one** varargs param, must be **last**.

--

## Stack and Heap

| Memory | Holds | Lifetime |
|--------|-------|----------|
| **Stack** | Method frames, primitives, references | Per method call |
| **Heap** | Objects and arrays (`new`) | Until GC reclaims |

- Primitives live **in the stack frame**
- Objects live **on the heap**; stack holds a reference

--

## References and Mutation

Reassigning a reference ≠ mutating the object:

```java
public static void modifyArray(int[] arr) {
    arr[0] = 999;    // follows reference to same heap object
}

int[] numbers = {1, 2, 3};
modifyArray(numbers);
// numbers[0] is now 999
```

> Deep dive on GC and JVM memory in **Chapter 28**.

--

## Scope and Lifetime

- **Local variables** — from declaration to end of block
- **Parameters** — scoped to the method body
- **Loop variables** — created and destroyed each iteration
- Method return → stack frame **popped**, locals gone

```java
for (int i = 0; i < 5; i++) {
    int y = i * 10;   // y exists only inside the loop
}
// i and y not accessible here
```

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `MethodBasics` | Defining, calling, return types, pass-by-value |
| `Overloading` | Overloaded methods and resolution rules |
| `VarargsDemo` | Varargs syntax and usage |

```bash
mvn test -pl 03-methods
```

--

## Exercises — Your Turn

1. **Math Utilities** (Guided) — `clamp`, `isPrime`, `factorial`
2. **String Utilities** (Practice) — `reverse`, `isPalindrome`, `countOccurrences`
3. **Overloaded `format`** (Challenge) — three overloads with varargs

```bash
mvn test -pl 03-methods -Dtest="MathUtilsTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Methods are the unit of behavior — keep them **short** and **focused**
- Java is **pass-by-value** for primitives and references alike
- Overloading ≠ polymorphism (that's Chapter 5)
- Keep variable scope as **narrow** as possible

Further reading: [JLS §8.4](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.4) · *Effective Java* Item 52

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 4
## Classes and Objects

--

## Objectives

- Understand classes as **blueprints** for objects
- Declare **fields**, **constructors**, and **methods**
- Use **`this`** to refer to the current instance
- Apply **access modifiers** for encapsulation
- Distinguish **static** (class) vs **instance** (object) members

--

## Classes and Objects

A **class** defines structure and behavior. An **object** is a concrete instance:

```java
public class Dog {
    String name;
    int age;
}

Dog d = new Dog();
d.name = "Rex";
d.age = 3;
```

Each instance gets its own copy of instance fields.

--

## Fields and Defaults

Fields hold the **state** of an object:

```java
public class Point {
    double x;
    double y;
}
```

Uninitialized fields get defaults: `0` for numbers, `false` for `boolean`, `null` for references.

--

## Constructors

Same name as the class, no return type:

```java
public Point(double x, double y) {
    this.x = x;
    this.y = y;
}

var p = new Point(3.0, 4.0);
```

Write at least one constructor → Java's default no-arg constructor **disappears**.

--

## The `this` Keyword

| Usage | Example |
|-------|---------|
| Disambiguate field from parameter | `this.name = name;` |
| Call another constructor | `this(defaultValue);` — first statement only |
| Pass current object | `list.add(this);` |

--

## Methods and Encapsulation

```java
public class BankAccount {
    private double balance;

    public void deposit(double amount) {
        this.balance += amount;
    }

    public double getBalance() { return balance; }
}
```

Make fields **`private`** — expose only what clients need through methods.

--

## Access Modifiers

| Modifier | Class | Package | Subclass | World |
|----------|:-----:|:-------:|:--------:|:-----:|
| `public` | ✓ | ✓ | ✓ | ✓ |
| `protected` | ✓ | ✓ | ✓ | |
| *(none)* | ✓ | ✓ | | |
| `private` | ✓ | | | |

*(none)* = **package-private**. Prefer the most restrictive level that works.

--

## Static Members

Belong to the **class**, not any instance — one shared copy:

```java
public class Counter {
    private static int totalCount;   // shared
    private int count;               // per instance

    public static int getTotalCount() {
        return totalCount;
    }
}
```

- Static methods have no `this` — cannot access instance members directly
- Access via class name: `Counter.getTotalCount()`

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `BankAccount` | Fields, constructor, `this`, encapsulation |
| `Counter` | Static vs instance fields and methods |

```bash
mvn test -pl 04-classes-and-objects
```

--

## Exercises — Your Turn

1. **Rectangle** (Guided) — area, perimeter, `toString`
2. **Student** (Practice) — grades, average, highest, unmodifiable view
3. **Stopwatch** (Challenge) — `System.nanoTime()` timing with state checks

```bash
mvn test -pl 04-classes-and-objects -Dtest="RectangleTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- A class defines **state** (fields) and **behavior** (methods)
- Use `this` in constructors to disambiguate parameters from fields
- **Encapsulation** — `private` fields, controlled public interface
- `static` members belong to the class, not instances

Further reading: [JLS §8.2](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.2) · *Effective Java* Items 15–16

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 5
## Inheritance and Polymorphism

--

## Objectives

- Extend classes with `extends` and call superclass constructors via `super`
- **Override** methods and understand **dynamic dispatch**
- Define **abstract classes** that force subclass implementations
- Override `equals`, `hashCode`, and `toString` correctly

--

## Extending Classes

A subclass **inherits** accessible fields and methods from its superclass:

```java
public class Car extends Vehicle {
    private final int doors;

    public Car(String make, int doors) {
        super(make);          // must be first statement
        this.doors = doors;
    }
}
```

- **Single inheritance** only — one superclass per class
- Every class implicitly extends `Object`

--

## `super` Keyword

| Usage | Example |
|-------|---------|
| Call superclass constructor | `super(make);` — first line |
| Call overridden method | `super.describe()` |
| Access superclass field | `super.field` (if accessible) |

--

## Method Overriding

Subclass provides a new implementation with the same signature:

```java
@Override
public String describe() {
    return getMake() + " car with " + doors + " doors";
}
```

`@Override` is optional but strongly recommended — compiler catches typos.

--

## Dynamic Dispatch

The JVM calls the method for the object's **actual** type, not the variable's declared type:

```java
Vehicle v = new Car("Toyota", 4);
v.describe();   // → "Toyota car with 4 doors" (Car's version)
```

This is **polymorphism** — same interface, different behavior.

--

## Abstract Classes

Cannot be instantiated; may declare abstract methods subclasses must implement:

```java
public abstract class Shape {
    public abstract double area();
    public abstract double perimeter();
}
```

Abstract classes can also have **concrete** methods and fields — shared behavior plus contract.

--

## Overriding `Object` Methods

Every class inherits from `Object`. Commonly overridden:

| Method | Purpose |
|--------|---------|
| `toString()` | Human-readable representation |
| `equals(Object)` | Logical equality |
| `hashCode()` | Hash value for collections |

**Contract:** override `hashCode` whenever you override `equals`.

--

## `equals` and `hashCode`

```java
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var other = (Employee) o;
    return Objects.equals(name, other.name);
}

@Override
public int hashCode() {
    return Objects.hash(name, salary);
}
```

Equal objects **must** share the same hash code — required for `HashMap` / `HashSet`.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `Shape` + Circle, Rectangle | Abstract classes, overriding, polymorphic calls |
| `Vehicle` + Car, Motorcycle | `extends`, `super`, dynamic dispatch |

```bash
mvn test -pl 05-inheritance-and-polymorphism
```

--

## Exercises — Your Turn

1. **Animal Hierarchy** (Guided) — `speak()` and `toString` for Dog, Cat
2. **Employee Hierarchy** (Practice) — `equals`, `hashCode`, `toString` with `getClass()`
3. **Expression Tree** (Challenge) — `eval()` and parenthesized `toString()`

```bash
mvn test -pl 05-inheritance-and-polymorphism -Dtest="AnimalTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- **Inheritance** reuses and specializes behavior — favor composition over deep hierarchies
- **Dynamic dispatch** — JVM picks the method by the object's actual type
- **Abstract classes** define a contract subclasses must fulfill
- Always override `hashCode` when you override `equals`; use `@Override` everywhere

Further reading: [JLS §8.1.4](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.1.4) · *Effective Java* Items 10–11

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 6
## Interfaces

--

## Objectives

- Define and implement interfaces to specify contracts without implementation
- Use **default methods** to evolve interfaces without breaking implementors
- Write **static** and **private** methods in interfaces
- Understand `@FunctionalInterface` and single abstract method (SAM) types

--

## What Is an Interface?

An **interface** declares a contract — methods that implementing classes must provide. Unlike abstract classes, interfaces support **multiple inheritance of type**.

```java
public interface Drawable {
    void draw();
}

public class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}
```

All interface methods are implicitly `public` and `abstract` unless marked otherwise.

--

## Default Methods

Java 8 introduced **default methods** — methods with a body in an interface. Evolve interfaces without forcing every implementor to change:

```java
public interface Logger {
    void log(String message);

    default void logError(String message) {
        log("ERROR: " + message);
    }
}
```

Implementing classes inherit the default behavior but can override it.

--

## Static Methods in Interfaces

Interfaces can declare **static methods** for utility operations related to the interface type:

```java
public interface StringUtils {
    static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}
```

Call them through the interface name: `StringUtils.isBlank("  ")`.

--

## Private Methods in Interfaces

Since Java 9, interfaces can declare **private methods** to share code between default methods without exposing helpers:

```java
public interface Sortable<T> {
    int compareTo(T other);

    default T min(T a, T b) {
        return comparePair(a, b) <= 0 ? a : b;
    }

    private int comparePair(T a, T b) {
        return compareTo(a) < 0 ? -1 : (compareTo(b) > 0 ? 1 : 0);
    }
}
```

--

## Functional Interfaces

A **functional interface** has exactly one abstract method. `@FunctionalInterface` documents intent and causes a compile error if a second abstract method is added:

```java
@FunctionalInterface
public interface Validator<T> {
    boolean validate(T value);
}
```

Functional interfaces are the foundation of **lambda expressions** and **method references** — e.g. `Runnable`, `Comparator<T>`, `Predicate<T>`, `Function<T, R>`.

--

## Interface Features at a Glance

| Annotation / Concept   | Purpose                                              |
|------------------------|------------------------------------------------------|
| `@FunctionalInterface` | Marks a SAM type; enables lambda/method ref usage    |
| `default`              | Provides inherited behavior with optional override   |
| `static`               | Utility methods scoped to the interface              |
| `private`              | Shared helper logic for default/static methods       |

> **Effective Java Item 20:** Prefer interfaces to abstract classes.

--

## Interfaces vs. Abstract Classes

| Feature                  | Interface              | Abstract Class        |
|--------------------------|------------------------|-----------------------|
| Multiple inheritance     | Yes (multiple interfaces) | No (single class) |
| State (fields)           | Only `public static final` constants | Instance fields |
| Constructors             | No                     | Yes                   |
| Default implementation   | Via default methods    | Via concrete methods  |

Prefer interfaces for pure contracts. Use abstract classes when related types share significant state or implementation.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `Printable` | Basic interface, default `print()`, static `printAll()` |
| `Sortable` | Default methods, private helpers, F-bounded type parameter |
| `ComparatorDemo` | `Comparator` functional interface, `comparing`, `thenComparing` |

```bash
mvn test -pl 06-interfaces
```

--

## Exercises — Your Turn

1. **Measurable Shapes** (Guided) — `Circle` and `Rectangle` records implementing `MeasurableShape`
2. **Validator** (Practice) — `@FunctionalInterface` with factory methods and `and()`
3. **Plugin System** (Challenge) — `PluginManager` with registration and ordered execution

```bash
mvn test -pl 06-interfaces -Dtest="MeasurableTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Interfaces define **contracts** — what a type can do, not how it does it
- **Default methods** let you extend interfaces without breaking existing code
- **Static** and **private** methods keep utility and helper logic on the interface
- `@FunctionalInterface` marks a SAM type, enabling **lambdas** and **method references**
- A class can **implement multiple interfaces**, achieving flexible polymorphism

Further reading: [JLS §9.1](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.1) · [JLS §9.4](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.4) · *Effective Java* Item 20

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 7
## Encapsulation and Data Modeling

--

## Objectives

- Model immutable data with Java **records**
- Restrict class hierarchies using **sealed classes** and **sealed interfaces**
- Build rich **enums** with fields, constructors, and methods
- Apply **pattern matching** with `instanceof` and `switch` expressions

--

## Records

A **record** is a compact class for immutable data. The compiler generates the constructor, accessors, `equals()`, `hashCode()`, and `toString()`:

```java
public record Point(double x, double y) {
    public double distanceFromOrigin() {
        return Math.sqrt(x * x + y * y);
    }
}
```

Records are implicitly `final`. Add custom methods and compact constructors for validation — but no extra instance fields beyond the components.

```java
public record Email(String address) {
    public Email {
        if (address == null || !address.contains("@")) {
            throw new IllegalArgumentException("Invalid email: " + address);
        }
    }
}
```

--

## Enums with Fields and Methods

Enums are type-safe constants. They can have **fields**, **constructors**, and **instance methods**:

```java
public enum Season {
    SPRING("Spring", 15.0),
    SUMMER("Summer", 28.0);

    private final String displayName;
    private final double averageTempCelsius;

    Season(String displayName, double averageTempCelsius) {
        this.displayName = displayName;
        this.averageTempCelsius = averageTempCelsius;
    }

    public String displayName() { return displayName; }
}
```

Enum constructors are always `private`. Use enums when a fixed set of named constants carries associated behavior or data.

--

## Sealed Classes and Interfaces

**Sealed** types restrict which classes can extend or implement them:

```java
public sealed interface Shape permits Circle, Rectangle, Triangle { }

public record Circle(double radius) implements Shape { }
public record Rectangle(double width, double height) implements Shape { }
public record Triangle(double base, double height) implements Shape { }
```

Only listed **permitted subclasses** can implement a sealed interface — giving the compiler complete knowledge of all subtypes.

--

## Pattern Matching with `instanceof`

Pattern matching `instanceof` combines a type check with a binding:

```java
if (shape instanceof Circle c) {
    System.out.println("Radius: " + c.radius());
}
```

The pattern variable `c` is in scope only where the compiler can prove the match succeeded — no manual casting needed.

--

## Pattern Matching `switch`

`switch` expressions support type patterns for sealed hierarchies:

```java
String describe = switch (shape) {
    case Circle c    -> "Circle r=" + c.radius();
    case Rectangle r -> "Rectangle " + r.width() + "×" + r.height();
    case Triangle t  -> "Triangle base=" + t.base();
};
```

The compiler verifies **exhaustiveness** — every permitted subtype must be handled.

--

## Choosing the Right Modeling Tool

| Feature            | Use when                                           |
|--------------------|----------------------------------------------------|
| `record`           | Immutable data carrier with named components       |
| `enum`             | Fixed set of named constants with behavior         |
| `sealed`           | Closed hierarchy known at compile time             |
| `instanceof` match | Type-narrowing in conditional logic                |
| `switch` match     | Exhaustive dispatch over a sealed hierarchy        |

Choose the tool that best fits each domain concept.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `Point` | Record basics, custom methods, compact data modeling |
| `Season` | Enum fields, constructors, instance methods |
| `ShapeDemo` | Sealed hierarchy, pattern matching `switch`/`instanceof` |

```bash
mvn test -pl 07-encapsulation-and-data-modeling
```

--

## Exercises — Your Turn

1. **Color Enum** (Guided) — `fromHex()`, `isDark()`, `complement()`
2. **Result Type** (Practice) — sealed `Result<T>` with `Success` and `Failure`
3. **JSON Pretty Printer** (Challenge) — sealed `JsonValue` hierarchy with `prettyPrint()`

```bash
mvn test -pl 07-encapsulation-and-data-modeling -Dtest="ColorTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- **Records** are the idiomatic way to model immutable data with minimal boilerplate
- **Enums** go beyond simple constants — they can carry state and behavior
- **Sealed types** create closed hierarchies that enable exhaustive pattern matching
- Pattern matching **`instanceof`** eliminates manual casting in conditionals
- Pattern matching **`switch`** provides concise, compiler-checked dispatch over subtypes

Further reading: [JLS §8.10](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.10) · JEP 409 · JEP 394 · JEP 441

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 8
## Exception Handling

--

## Objectives

- Understand the exception hierarchy (`Throwable`, `Error`, `Exception`, `RuntimeException`)
- Distinguish **checked** vs **unchecked** exceptions
- Use `throws`, `try-catch-finally`, multi-catch, and try-with-resources
- Define custom exceptions and apply **Effective Java** guidance

--

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

--

## Checked vs Unchecked

| Type | Compiler enforces `throws`? | When to use |
|------|----------------------------|-------------|
| **Checked** (`IOException`) | Yes | Recoverable — caller should handle |
| **Unchecked** (`IllegalArgumentException`) | No | Programming error — bad input or state |

> **Item 69:** Use exceptions only for **exceptional** conditions — not control flow.

--

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

--

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

--

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

--

## try-with-resources

```java
try (var resource = new MyResource()) {
    resource.use();
}
// close() called automatically — even if an exception is thrown
```

- Any `AutoCloseable` can be used
- If `close()` also throws, it becomes a **suppressed exception** on the main one

--

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

--

## Effective Java — When to Throw

| Item | Guideline |
|------|-----------|
| **70** | Checked for recoverable; runtime for programming errors |
| **71** | Avoid unnecessary checked exceptions |
| **72** | Prefer standard types (`IllegalArgumentException`, `IllegalStateException`) |
| **73** | Throw exceptions appropriate to the **abstraction** |

--

## Effective Java — How to Throw

| Item | Guideline |
|------|-----------|
| **74** | Document every thrown type with `@throws` in Javadoc |
| **75** | Include context in messages (parameter name, offending value) |
| **77** | Never ignore an exception — log, re-throw, or handle |
| **78** | Retry transient failures with backoff |

Also: catch **specific** types, use **exception chaining** (`cause`), prefer try-with-resources over `finally` for closing.

--

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

--

## Exercises — Your Turn

1. **Validator** (Guided) — custom checked `ValidationException`
2. **RetryExecutor** (Practice) — retry with suppressed exceptions
3. **ResourcePool** (Challenge) — pooled `AutoCloseable` resources

```bash
mvn test -pl 08-exception-handling -Dtest="ValidatorTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- **Checked** = recoverable, caller must handle · **Unchecked** = programming error
- Runtime exceptions **may** appear in `throws` for API documentation
- **try-with-resources** is the standard pattern for closing resources
- Prefer standard exception types with **informative messages**

Further reading: *Effective Java* Items 69–75, 77–78 · [JLS §11](https://docs.oracle.com/javase/specs/jls/se25/html/jls-11.html)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 9
## Packages and Modules

--

## Objectives

- Understand Java package naming conventions and their role in organizing code
- Learn how packages map to the file system directory structure
- Read and write `module-info.java` declarations: `exports`, `requires`, `provides`/`uses`
- Use `ServiceLoader` to discover and load service implementations at runtime

--

## Packages

A **package** is a namespace that groups related classes and interfaces. Packages prevent naming collisions and control access (package-private visibility is the default).

Java packages follow the **reverse domain name** convention:

```java
package com.example.onlinestore.model;
package com.example.onlinestore.service;
package com.example.onlinestore.repository;
```

| Rule | Example | Rationale |
|------|---------|-----------|
| Reverse domain prefix | `com.example` | Global uniqueness |
| All lowercase | `com.example.myapp` | Avoids conflicts with class names |
| Singular nouns for layers | `model`, `service`, `controller` | Ecosystem convention |

--

## Standard Project Layout

A typical Java project organizes packages by functional layer:

```
com.example.onlinestore
├── model/          ← domain objects (User, Order, Product)
├── service/        ← business logic (OrderService, PaymentService)
├── repository/     ← data access (UserRepository, OrderDao)
├── controller/     ← request handling (UserController)
├── config/         ← configuration classes
└── util/           ← shared utilities (StringUtils, DateHelper)
```

--

## Package Declaration and Imports

Every Java source file begins with a package declaration, followed by imports:

```java
package com.example.onlinestore.service;

import com.example.onlinestore.model.Order;
import com.example.onlinestore.repository.OrderRepository;

public class OrderService {
    public Order findById(long id) {
        return repository.findById(id);
    }
}
```

The package declaration must match the directory structure:

`src/main/java/com/example/onlinestore/service/OrderService.java`

--

## Why Modules?

Java 9 introduced the **module system** (Project Jigsaw) for stronger encapsulation above packages.

| Problem | Description |
|---------|-------------|
| No encapsulation | Any public class was accessible to any code on the classpath |
| JAR hell | Duplicate or conflicting JARs caused hard-to-diagnose errors |
| No reliable config | Missing dependencies discovered only at runtime |
| Monolithic JDK | Applications shipped the entire JDK runtime |

--

## `module-info.java`

A module is defined by a `module-info.java` file at the root of the module's source tree:

```java
module com.example.onlinestore {
    requires java.sql;
    requires java.logging;

    exports com.example.onlinestore.api;
    exports com.example.onlinestore.model;

    // Internal packages are NOT exported and remain encapsulated
}
```

--

## Key Module Directives

| Directive | Purpose | Example |
|-----------|---------|---------|
| `requires` | Declares a dependency on another module | `requires java.sql;` |
| `requires transitive` | Dependency passed to modules that require this one | `requires transitive java.logging;` |
| `exports` | Makes a package accessible to other modules | `exports com.example.api;` |
| `exports ... to` | Qualified export to specific modules | `exports com.example.internal to com.example.web;` |
| `opens` | Allows reflective access at runtime | `opens com.example.model;` |
| `provides ... with` | Declares a service implementation | `provides Codec with JsonCodec;` |
| `uses` | Declares that this module consumes a service | `uses com.example.spi.Codec;` |

--

## Module Graph Example

```
com.example.web
  └── requires com.example.service
        ├── requires com.example.model
        └── requires java.sql
```

Each module explicitly declares what it needs and what it exposes. The module system validates the entire graph at startup — missing modules cause a clear error instead of `ClassNotFoundException`.

--

## The JDK as Modules

The JDK itself is modularized (~70 platform modules):

```bash
java --list-modules
```

| Module | Contains |
|--------|----------|
| `java.base` | Core classes (`String`, `List`, `Map`) |
| `java.sql` | JDBC API |
| `java.logging` | `java.util.logging` |
| `java.net.http` | HTTP client API |

Every module implicitly requires `java.base` — you never need to declare it.

--

## ServiceLoader (SPI Pattern)

`ServiceLoader` decouples a service interface from its implementations, discovering them at runtime:

```java
// Provider module
module com.example.json {
    requires com.example.spi;
    provides com.example.spi.Codec with com.example.json.JsonCodec;
}

// Consumer module
module com.example.app {
    requires com.example.spi;
    uses com.example.spi.Codec;
}
```

```java
ServiceLoader<Codec> loader = ServiceLoader.load(Codec.class);
for (Codec codec : loader) {
    System.out.println("Found codec: " + codec.getClass().getName());
}
```

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `PackageStructure` | Package naming conventions, validation, and project layout |
| `ServiceLoaderDemo` | Discovering JDK tools via `ServiceLoader` and `ToolProvider` |

```bash
mvn test -pl 09-packages-and-modules
```

--

## Exercises — Your Turn

1. **Package Organizer** (Guided) — assign class names to correct package layers
2. **Module Info Parser** (Practice) — parse `module-info.java` into a `ModuleInfo` record
3. **Service Registry** (Challenge) — type-safe service locator mimicking `ServiceLoader`

```bash
mvn test -pl 09-packages-and-modules -Dtest="PackageOrganizerTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- **Packages** organize classes into namespaces using reverse-domain naming (`com.example.myapp.model`)
- The **Java Platform Module System** adds strong encapsulation above packages
- `module-info.java` uses `requires` for dependencies and `exports` for public API packages
- **`ServiceLoader`** decouples interfaces from implementations, enabling plug-in architectures
- Every module implicitly requires `java.base`; the JDK itself is modularized

Further reading: [JLS §7](https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html) · [JEP 261](https://openjdk.org/jeps/261) · [ServiceLoader Javadoc](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ServiceLoader.html)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 10
## Strings and Text

--

## Objectives

- Understand why Java strings are immutable and how interning works
- Use common `String` methods for searching, comparing, and transforming text
- Build strings efficiently with `StringBuilder` and `StringBuffer`
- Write multi-line strings using **text blocks** and format with `formatted()`

--

## String Immutability

In Java, `String` objects are **immutable** — once created, their content cannot be changed. Every "modifying" method returns a **new** `String` instance:

```java
String greeting = "hello";
String upper = greeting.toUpperCase();

System.out.println(greeting); // "hello" — unchanged
System.out.println(upper);    // "HELLO" — new object
```

This design enables **thread safety**, **string interning**, and **security** (keys, class names, and file paths cannot be altered).

--

## String Interning

The JVM maintains a **string pool** of unique string literals. Two literals with the same content share the same reference:

```java
String a = "Java";
String b = "Java";
System.out.println(a == b);      // true — same pooled reference

String c = new String("Java");
System.out.println(a == c);      // false — c is a new object
System.out.println(a.equals(c)); // true — same content
```

Always use `.equals()` to compare string content. Use `==` only when you intentionally want reference equality.

--

## Common String Methods

| Method | Description | Example |
|--------|-------------|---------|
| `length()` | Number of characters | `"hello".length()` → `5` |
| `charAt(int)` | Character at index | `"hello".charAt(1)` → `'e'` |
| `substring(int, int)` | Extract a range | `"hello".substring(1, 4)` → `"ell"` |
| `indexOf(String)` | First occurrence index | `"hello".indexOf("ll")` → `2` |
| `contains(CharSequence)` | Check if present | `"hello".contains("ell")` → `true` |
| `startsWith` / `endsWith` | Prefix / suffix check | `"hello".startsWith("he")` → `true` |

--

## More String Methods

| Method | Description | Example |
|--------|-------------|---------|
| `toUpperCase()` / `toLowerCase()` | Case conversion | `"Hello".toLowerCase()` → `"hello"` |
| `trim()` / `strip()` | Remove whitespace | `"  hi  ".strip()` → `"hi"` |
| `replace(char, char)` | Replace characters | `"hello".replace('l', 'r')` → `"herro"` |
| `split(String)` | Split by regex | `"a,b,c".split(",")` → `["a", "b", "c"]` |
| `join(CharSequence, ...)` | Join with delimiter | `String.join("-", "a", "b")` → `"a-b"` |

--

## StringBuilder and StringBuffer

When building strings through repeated concatenation, use `StringBuilder` to avoid creating many intermediate `String` objects:

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 5; i++) {
    sb.append("item").append(i).append(", ");
}
String result = sb.toString(); // "item0, item1, item2, item3, item4, "
```

| Class | Thread-safe? | Use when |
|-------|--------------|----------|
| `StringBuilder` | No | Single-threaded string building (preferred) |
| `StringBuffer` | Yes | Multi-threaded string building (legacy) |

--

## Text Blocks

Text blocks (Java 15+) let you write multi-line strings with triple quotes (`"""`):

```java
String json = """
        {
            "name": "Alice",
            "age": 30
        }""";
```

The compiler strips incidental leading whitespace based on the closing `"""` position. Supports `\s` (space) and `\` (line continuation).

--

## String Formatting

**`String.format()`** uses `printf`-style format specifiers:

```java
String msg = String.format("Hello, %s! You are %d years old.", "Alice", 30);
```

**`formatted()`** (Java 15+) is an instance method that does the same thing:

```java
String msg = "Hello, %s! You scored %.1f%%".formatted("Bob", 95.5);
```

| Specifier | Type | Example |
|-----------|------|---------|
| `%s` | String | `"hello"` |
| `%d` | Integer | `42` |
| `%.2f` | Float with 2 decimals | `3.14` |
| `%,d` | Integer with commas | `1,000,000` |

--

## Regular Expressions

Java provides regex support through `Pattern` and `Matcher`:

```java
Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher("Order 42, Item 7");

while (matcher.find()) {
    System.out.println(matcher.group()); // "42", then "7"
}
```

Quick methods on `String` itself:

```java
boolean valid = "abc123".matches("[a-z]+\\d+");       // true
String clean = "a  b  c".replaceAll("\\s+", " ");     // "a b c"
String[] parts = "one,two,three".split(",");           // ["one","two","three"]
```

--

## Common Regex Patterns

| Pattern | Matches |
|---------|---------|
| `\\d` | A digit |
| `\\w` | A word character (letter/digit/_) |
| `\\s` | A whitespace character |
| `.` | Any character (except newline) |
| `+` | One or more of the preceding |
| `*` | Zero or more of the preceding |
| `?` | Zero or one of the preceding |
| `[abc]` | Any one of a, b, or c |
| `(group)` | Capture group |

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `StringImmutability` | Immutability, interning, common String methods |
| `TextBlockDemo` | Text blocks, `String.format()`, `formatted()` |
| `RegexBasics` | `Pattern`, `Matcher`, `findAll`, `replaceAll`, capture groups |

```bash
mvn test -pl 10-strings-and-text
```

--

## Exercises — Your Turn

1. **Word Counter** (Guided) — `countWords()`, `countUniqueWords()`, `mostFrequentWord()`
2. **Template Engine** (Practice) — replace `{{placeholder}}` patterns from a map
3. **Markdown to HTML** (Challenge) — convert `**bold**`, `*italic*`, and `` `code` `` to HTML

```bash
mvn test -pl 10-strings-and-text -Dtest="WordCounterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Java strings are **immutable** — every modification creates a new object
- Use `.equals()` for content comparison, never `==`
- Use `StringBuilder` for efficient string concatenation in loops
- **Text blocks** (`"""`) simplify multi-line string literals
- `String.format()` and `formatted()` provide powerful formatting with `%s`, `%d`, `%f`, etc.
- `Pattern` and `Matcher` provide full regex support; `matches()`, `replaceAll()`, and `split()` offer shortcuts

Further reading: [JLS §3.10.5](https://docs.oracle.com/javase/specs/jls/se25/html/jls-3.html#jls-3.10.5) · [JEP 378](https://openjdk.org/jeps/378) · [Pattern API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/regex/Pattern.html)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 11
## Generics

--

## Objectives

- Understand why generics exist — type safety without casting
- Declare generic classes, interfaces, and methods
- Use **bounded type parameters** to constrain type arguments
- Apply wildcards (`?`, `? extends`, `? super`) for flexible APIs
- Understand **type erasure** and its implications

--

## Why Generics?

Before generics, collections stored `Object` — every retrieval required a cast:

```java
List names = new ArrayList();
names.add("Alice");
names.add(42);                       // no compile error
String name = (String) names.get(1); // ClassCastException!
```

With generics, the compiler enforces type safety:

```java
List<String> names = new ArrayList<>();
names.add("Alice");
names.add(42);                  // compile error
String name = names.get(0);    // no cast needed
```

--

## Generic Classes

Declare **type parameters** in angle brackets:

```java
public class Box<T> {
    private T value;
    public Box(T value) { this.value = value; }
    public T getValue() { return value; }
}
```

```java
Box<String> stringBox = new Box<>("Hello");
Box<Integer> intBox = new Box<>(42);
```

Multiple parameters: `Pair<K, V>` with `first` and `second`.

--

## Generic Methods

Methods declare their own type parameters — independent of the enclosing class:

```java
public static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) >= 0 ? a : b;
}
```

The type parameter appears **before** the return type. The compiler infers `T`:

```java
int bigger = max(3, 7);                // T = Integer
String later = max("apple", "banana"); // T = String
```

--

## Bounded Type Parameters

Constrain a type parameter with `extends`:

| Declaration | Meaning |
|-------------|---------|
| `<T>` | Any type |
| `<T extends Number>` | T must be Number or subclass |
| `<T extends Comparable<T>>` | T must implement Comparable |
| `<T extends Number & Comparable<T>>` | Class first, then interfaces |

```java
public static <T extends Number> double sum(List<T> numbers) {
    double total = 0;
    for (T n : numbers) total += n.doubleValue();
    return total;
}
```

--

## Wildcards Overview

Wildcards provide flexibility when you don't need to name the type parameter:

| Wildcard | Meaning | Can read as | Can write |
|----------|---------|-------------|-----------|
| `<?>` | Any type | `Object` | Nothing |
| `<? extends Number>` | Number or subclass | `Number` | Nothing |
| `<? super Integer>` | Integer or superclass | `Object` | `Integer` |

--

## Upper-Bounded Wildcards — Producers

Use `? extends` when you **read** from a collection:

```java
public static double sumOfNumbers(List<? extends Number> numbers) {
    double sum = 0;
    for (Number n : numbers) {
        sum += n.doubleValue();
    }
    return sum;
}
```

Works with `List<Integer>`, `List<Double>`, etc. — you cannot add elements.

--

## Lower-Bounded Wildcards — Consumers

Use `? super` when you **write** to a collection:

```java
public static void addIntegers(List<? super Integer> list, int from, int to) {
    for (int i = from; i < to; i++) {
        list.add(i);
    }
}
```

Accepts `List<Integer>`, `List<Number>`, `List<Object>`.

--

## PECS Principle

> **P**roducer **E**xtends, **C**onsumer **S**uper

| Role | Wildcard | Example |
|------|----------|---------|
| **Producer** (read) | `? extends T` | `List<? extends Number>` |
| **Consumer** (write) | `? super T` | `List<? super Integer>` |

> **Item 31:** Use bounded wildcards to increase API flexibility.

--

## Type Erasure

Java generics are implemented via **type erasure** — the compiler removes type parameter information at runtime:

```java
List<String> strings = new ArrayList<>();
List<Integer> ints = new ArrayList<>();
System.out.println(strings.getClass() == ints.getClass()); // true
```

The compiler inserts casts where needed. Both lists are just `List` at runtime.

--

## Type Erasure — Limitations

Because type info is erased at runtime:

- Cannot use `instanceof` with generics: `obj instanceof List<String>` won't compile
- Cannot create generic arrays: `new T[10]` won't compile
- Cannot call `new T()` — the type is unknown at runtime

Workaround: use `Object[]` internally (as `ArrayList` does) and cast on return.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `Box` | Simple generic class with one type parameter |
| `Pair` | Two type parameters and a factory method |
| `GenericUtils` | Generic methods (max, swap) and wildcards |

```bash
mvn test -pl 11-generics
```

--

## Exercises — Your Turn

1. **Stack\<T\>** (Guided) — LIFO stack backed by `Object[]`
2. **Transformer\<T,R\>** (Practice) — compose transformers, apply to lists
3. **SortedList\<T extends Comparable\<T\>\>** (Challenge) — sorted insert + binary search

```bash
mvn test -pl 11-generics -Dtest="StackTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Generics provide **compile-time type safety** without casting
- Use **bounded type parameters** (`<T extends X>`) to constrain allowed types
- Use **wildcards** for flexible APIs: `? extends` for reading, `? super` for writing (PECS)
- **Type erasure** removes generic info at runtime — no `instanceof`, no generic arrays
- Prefer `List<String>` over raw `List`

Further reading: *Effective Java* Item 31 · [JLS §4.5](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.5)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 12
## Collections Framework

--

## Objectives

- Know the core interfaces: `List`, `Set`, `Map`, `Queue`, `Deque`
- Choose the right implementation for a given use case
- Use `Collections` and `Arrays` utility methods
- Iterate with iterators and the enhanced `for` loop
- Understand **sequenced collections** (Java 21+)

--

## Core Interfaces

| Interface | Ordered? | Duplicates? | Key operations |
|-----------|----------|-------------|----------------|
| `List` | Yes | Yes | `get`, `add`, `remove`, indexed access |
| `Set` | Varies | No | `add`, `contains`, uniqueness |
| `Map` | Varies | Keys: no | `put`, `get`, `containsKey` |
| `Queue` | Yes | Yes | `offer`, `poll`, `peek`, FIFO |
| `Deque` | Yes | Yes | Double-ended queue operations |

--

## Common Implementations

| Interface | Implementation | Backing structure | Notes |
|-----------|----------------|-------------------|-------|
| `List` | `ArrayList` | Dynamic array | Fast random access — default |
| `List` | `LinkedList` | Doubly-linked | Fast insert/remove at ends |
| `Set` | `HashSet` | Hash table | O(1) average, no order |
| `Set` | `TreeSet` | Red-black tree | Sorted order, O(log n) |
| `Map` | `HashMap` | Hash table | Default map choice |
| `Map` | `TreeMap` | Red-black tree | Sorted by keys |

--

## Choosing a Collection

| Need | Choose |
|------|--------|
| Indexed access + frequent iteration | `ArrayList` |
| Uniqueness | `HashSet` (or `TreeSet` if sorted) |
| Key-value lookup | `HashMap` |
| FIFO processing | `ArrayDeque` (preferred over `LinkedList`) |

> Declare variables with **interface types** (`List`, `Set`, `Map`) — not concrete classes.

--

## List Operations

```java
List<String> names = new ArrayList<>(List.of("Alice", "Bob", "Carol"));
names.add("Dave");
names.get(0);           // "Alice"
names.remove("Bob");
Collections.sort(names);
```

`ArrayList` — O(1) random access, O(n) insert in middle.
`LinkedList` — O(n) access, O(1) insert at ends.

--

## Set and Map Operations

```java
Set<String> unique = new HashSet<>();
unique.add("a"); unique.add("a"); // size stays 1

Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.get("Alice"); // 95
```

`TreeSet` / `TreeMap` — elements sorted by natural order or a `Comparator`.

--

## Iteration

```java
List<String> names = List.of("Alice", "Bob", "Carol");

// Enhanced for loop
for (String name : names) {
    System.out.println(name);
}

// Iterator — supports remove during iteration
var it = names.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

--

## Sequenced Collections (Java 21+)

`List`, `Deque`, and sorted sets/maps implement `SequencedCollection`:

```java
List<String> list = new ArrayList<>(List.of("a", "b", "c"));
list.getFirst();  // "a"
list.getLast();   // "c"
list.reversed();  // ["c", "b", "a"]
```

First/last element access and reversed views — no more `get(0)` / `get(size-1)`.

--

## Queue and Deque

```java
Deque<String> queue = new ArrayDeque<>();
queue.offer("first");   // add to tail
queue.poll();           // remove from head (FIFO)

queue.push("top");      // add to front
queue.pop();            // remove from front (LIFO / stack)
```

Prefer `ArrayDeque` over `LinkedList` for queue and stack operations.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `ListOperations` | `ArrayList`, sorting, reversing |
| `SetOperations` | `HashSet`, `TreeSet`, uniqueness |
| `MapOperations` | `HashMap`, `TreeMap`, key-value ops |
| `QueueDemo` | `ArrayDeque`, FIFO and LIFO |

```bash
mvn test -pl 12-collections-framework
```

--

## Exercises — Your Turn

1. **Frequency Counter** (Guided) — word frequencies with `HashMap`, sorted by count
2. **Unique Queue** (Practice) — FIFO queue that rejects duplicates
3. **LRU Cache** (Challenge) — fixed-capacity cache with `LinkedHashMap`

```bash
mvn test -pl 12-collections-framework -Dtest="FrequencyCounterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Prefer **interface types** (`List`, `Set`, `Map`) in variable declarations
- `ArrayList` and `HashMap` are the default choices for list and map
- Collections are **not thread-safe** unless designed for concurrency (Chapter 18)
- Use **streams** (Chapter 14) for declarative collection processing

Further reading: *Effective Java* Item 54 · [JLS §4.8](https://docs.oracle.com/javase/specs/jls/se25/html/jls-4.html#jls-4.8)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 13
## Functional Programming

--

## Objectives

- Write **lambda expressions** for functional interfaces
- Use **method references** as shorthand for lambdas
- Know the core `java.util.function` types
- Compose and chain functions and predicates

--

## Lambda Expressions

A lambda is an anonymous function — a block of code passed as a value:

```java
Comparator<String> byLength = (a, b) -> Integer.compare(a.length(), b.length());
Runnable task = () -> System.out.println("Running");
Consumer<String> printer = s -> System.out.println(s);
```

Lambdas require a **functional interface** — exactly one abstract method.
Captured variables must be **effectively final**.

--

## Functional Interface Syntax

```java
// Single parameter — parentheses optional
Function<String, Integer> len = s -> s.length();

// Multiple parameters
BinaryOperator<Integer> add = (a, b) -> a + b;

// Block body
Predicate<String> nonEmpty = s -> {
    return !s.isEmpty();
};
```

The compiler infers parameter types from the target interface.

--

## Effectively Final

Lambdas can capture local variables, but only if they are **effectively final**:

```java
String prefix = "Hello, ";
Consumer<String> greet = name -> System.out.println(prefix + name);
// prefix = "Hi, ";  // compile error — cannot reassign captured variable
```

Instance fields and `this` are freely accessible — no effectively-final restriction.

--

## Method References

When a lambda just calls an existing method, use a method reference:

| Form | Example | Equivalent lambda |
|------|---------|-------------------|
| Static | `Integer::parseInt` | `s -> Integer.parseInt(s)` |
| Instance (bound) | `System.out::println` | `s -> System.out.println(s)` |
| Instance (unbound) | `String::toLowerCase` | `s -> s.toLowerCase()` |
| Constructor | `ArrayList::new` | `() -> new ArrayList<>()` |

--

## Core Functional Interfaces

| Interface | Method | Use case |
|-----------|--------|----------|
| `Predicate<T>` | `test(T) → boolean` | Filtering |
| `Function<T, R>` | `apply(T) → R` | Transformation |
| `Consumer<T>` | `accept(T) → void` | Side effects |
| `Supplier<T>` | `get() → T` | Lazy value production |
| `UnaryOperator<T>` | `apply(T) → T` | Same-type transformation |
| `BinaryOperator<T>` | `apply(T, T) → T` | Combining two values |

--

## Composing Functions

```java
Function<String, String> trim = String::strip;
Function<String, String> upper = String::toUpperCase;
Function<String, String> pipeline = trim.andThen(upper);

Predicate<String> nonEmpty = s -> !s.isEmpty();
Predicate<String> longEnough = s -> s.length() > 5;
Predicate<String> both = nonEmpty.and(longEnough);
```

Also: `compose()` (reverse order), `negate()`, `or()` on predicates.

--

## Lambdas vs Anonymous Classes

```java
// Anonymous class — verbose
Runnable r1 = new Runnable() {
    public void run() { System.out.println("Hi"); }
};

// Lambda — concise
Runnable r2 = () -> System.out.println("Hi");
```

> **Item 42:** Prefer lambdas to anonymous classes when the interface is functional.

Lambdas are the foundation of the **Streams API** (Chapter 14).

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `LambdaBasics` | Comparator lambdas, Runnable |
| `MethodReferenceDemo` | All four method reference forms |
| `FunctionComposition` | `andThen`, `compose`, predicate chaining |

```bash
mvn test -pl 13-functional-programming
```

--

## Exercises — Your Turn

1. **String Filters** (Guided) — `Predicate<String>` factories and a filter method
2. **Pipeline** (Practice) — chain `Function<T, T>` transformations in a fluent builder
3. **Event Bus** (Challenge) — register listeners by type, publish to matching consumers

```bash
mvn test -pl 13-functional-programming -Dtest="StringFiltersTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Lambdas require a **functional interface** — one abstract method
- Prefer **method references** when they improve readability
- Use `Predicate`, `Function`, `Consumer` from `java.util.function` — don't reinvent
- Lambdas are the foundation of the **Streams API** (Chapter 14)

Further reading: *Effective Java* Item 42 · [JLS §15.27](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html#jls-15.27)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 14
## Streams API

--

## Objectives

- Create streams from collections, arrays, generators, and ranges
- Apply intermediate operations: `filter`, `map`, `flatMap`, `distinct`, `sorted`, `limit`
- Use terminal operations: `collect`, `reduce`, `forEach`, `count`, `min`/`max`, `findFirst`
- Aggregate with `Collectors`: grouping, partitioning, joining, statistics
- Understand when to use **parallel streams**

--

## What Is a Stream?

A **stream** is a sequence of elements supporting aggregate operations. Streams do **not** store data — they pipe elements from a source through a pipeline.

Key properties:
- **No storage** — views over a source
- **Lazy** — intermediate ops deferred until a terminal op runs
- **Consumable** — each stream traversed only once
- **Non-interfering** — don't modify the source during execution

--

## Creating Streams

```java
List<String> list = List.of("a", "b", "c");
Stream<String> fromList = list.stream();

Stream<Integer> range = IntStream.range(1, 10).boxed();
Stream<Integer> infinite = Stream.iterate(0, n -> n + 1).limit(5);
```

| Source | Creation method |
|--------|-----------------|
| Collection | `collection.stream()` |
| Array | `Arrays.stream(array)` |
| Range | `IntStream.range(start, end)` |
| Generator | `Stream.iterate(seed, next)` |

--

## Intermediate Operations

Intermediate operations return a new stream and are **lazy**:

| Operation | Description |
|-----------|-------------|
| `filter` | Keep elements matching a predicate |
| `map` | Transform each element |
| `flatMap` | Transform and flatten nested streams |
| `distinct` | Remove duplicates (uses `equals`) |
| `sorted` | Sort (natural or custom order) |
| `limit` / `skip` | Truncate or discard first n |

--

## Pipeline Example

```java
List<String> result = words.stream()
        .filter(w -> w.length() > 3)
        .map(String::toUpperCase)
        .sorted()
        .toList();
```

Nothing executes until a **terminal operation** triggers the pipeline.
Prefer **method references** (`String::toUpperCase`) over lambdas when possible.

--

## Terminal Operations

Terminal operations trigger execution and produce a result:

| Operation | Description |
|-----------|-------------|
| `collect` | Accumulate into a collection or map |
| `reduce` | Combine elements into a single value |
| `forEach` | Perform an action on each element |
| `count` | Count elements |
| `min` / `max` | Find extremal element |
| `findFirst` | First element as `Optional` |
| `anyMatch` / `allMatch` | Short-circuit boolean tests |

--

## Collectors

`Collectors` provides pre-built reduction strategies:

```java
Map<Integer, List<String>> byLength = words.stream()
        .collect(Collectors.groupingBy(String::length));

Map<Boolean, List<Integer>> evenOdd = numbers.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

String joined = words.stream()
        .collect(Collectors.joining(", "));
```

Also: `counting()`, `summingInt`, `averagingDouble`, `summarizingInt`.

--

## Grouping and Partitioning

| Collector | Result |
|-----------|--------|
| `groupingBy(classifier)` | Map of groups |
| `partitioningBy(pred)` | Map with `true`/`false` keys |
| `joining(delimiter)` | Concatenated string |
| `counting()` | Element count per group |
| `summingInt/Long/Double` | Sum per group |

Downstream collectors: `groupingBy(classifier, downstreamCollector)`.

--

## Parallel Streams

```java
long count = largeList.parallelStream()
        .filter(expensivePredicate)
        .count();
```

Use when:
- Data set is **large**
- Operations are **CPU-intensive** and **side-effect free**
- Source supports **efficient splitting** (`ArrayList`, arrays)

Avoid for small data, I/O-bound work, or when order matters.

> **Item 48:** Use caution when making streams parallel.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `StreamCreation` | Collections, arrays, ranges, generators |
| `IntermediateOps` | `filter`, `map`, `flatMap`, `distinct`, `sorted` |
| `TerminalOps` | `reduce`, `collect`, `count`, `min`/`max` |
| `CollectorExamples` | `groupingBy`, `partitioningBy`, `joining` |

```bash
mvn test -pl 14-streams-api
```

--

## Exercises — Your Turn

1. **Stream Exercises** (Guided) — evens, squares, sum, average
2. **Text Analyzer** (Practice) — word count, frequencies, longest word
3. **Transaction Report** (Challenge) — group by category, top expenses

```bash
mvn test -pl 14-streams-api -Dtest="StreamExercisesTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Streams provide a **declarative** way to process collections — describe *what*, not *how*
- **Intermediate operations** are lazy; a **terminal operation** triggers execution
- **`Collectors`** offer powerful grouping, partitioning, and aggregation
- **Parallel streams** help large CPU-bound workloads but add overhead for small data
- Streams are **one-shot** — create a new stream for each pipeline

Further reading: *Effective Java* Items 45, 48 · [java.util.stream](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/stream/package-summary.html)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 15
## The Optional Type

--

## Objectives

- Create `Optional` values with `of`, `ofNullable`, and `empty`
- Safely unwrap with `orElse`, `orElseGet`, and `orElseThrow`
- Chain transformations with `map`, `flatMap`, and `filter`
- Recognize common **anti-patterns** and their fixes
- Use Optional for **return types** — not fields or parameters

--

## Why Optional?

`Optional<T>` is a container that may or may not hold a value. It models **absence explicitly** in return types:

```java
public Optional<User> findById(String id) {
    return Optional.ofNullable(database.get(id));
}
```

Reduces `NullPointerException` risk by forcing callers to handle absence.

Intended for **return types** — not fields, method parameters, or collections.

--

## Creating Optionals

| Method | When to use |
|--------|-------------|
| `Optional.of(value)` | Value is **guaranteed non-null** |
| `Optional.ofNullable(v)` | Value **may be null** |
| `Optional.empty()` | No value present |

```java
Optional<String> present = Optional.of("hello");       // NPE if null
Optional<String> maybe   = Optional.ofNullable(name);  // safe with null
Optional<String> absent  = Optional.empty();
```

--

## Unwrapping Optionals

| Method | Behavior |
|--------|----------|
| `orElse(default)` | Return value, or default if empty |
| `orElseGet(Supplier)` | Return value, or **lazily** compute default |
| `orElseThrow()` | Return value, or throw `NoSuchElementException` |
| `orElseThrow(Supplier)` | Return value, or throw custom exception |
| `ifPresent(Consumer)` | Run action only if present |
| `ifPresentOrElse(Consumer, Runnable)` | One of two actions based on presence |

**Never** call `get()` without checking — use the methods above.

--

## Chaining: map, flatMap, filter

```java
Optional<String> domain = findEmail(userId)
        .filter(email -> email.contains("@"))
        .map(email -> email.substring(email.indexOf('@') + 1));
```

| Method | Purpose |
|--------|---------|
| `map(fn)` | Transform value if present (wraps in Optional) |
| `flatMap(fn)` | Transform with a function returning Optional (no nesting) |
| `filter(pred)` | Keep value only if it matches a predicate |

--

## When to Use flatMap

Use `flatMap` when your transformation itself returns an `Optional`:

```java
Optional<Integer> port = getString("port")
        .flatMap(SafeParser::parseInt);
```

`map` would nest: `Optional<Optional<Integer>>`.
`flatMap` flattens: `Optional<Integer>`.

--

## Anti-Patterns

| Anti-pattern | Correct approach |
|--------------|------------------|
| `if (opt.isPresent()) opt.get()` | Use `map`, `orElse`, `ifPresent` |
| `Optional.of(possiblyNull)` | Use `Optional.ofNullable()` |
| Optional as a field | Use `null`; Optional for return types |
| Optional as a method parameter | Accept `null` or overload methods |
| `Optional<List<T>>` for empty lists | Return `List.of()` instead |
| Returning `null` instead of empty | Return `Optional.empty()` |

--

## Optional in Streams

```java
Optional<String> first = Stream.of("a", "b", "c").findFirst();

// Java 9+ — drop empty Optionals from a stream
stream.flatMap(Optional::stream)
```

Terminal ops like `findFirst`, `min`, `max` return `Optional` — no more sentinel values.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `OptionalCreation` | `of`, `ofNullable`, `empty`, unwrapping |
| `OptionalChaining` | `map`, `flatMap`, `filter`, pipelines |
| `OptionalAntiPatterns` | Common mistakes and correct alternatives |

```bash
mvn test -pl 15-the-optional-type
```

--

## Exercises — Your Turn

1. **Safe Parser** (Guided) — `parseInt`, `parseDouble`, `firstValidInt`
2. **User Service** (Practice) — lookup, email domain, greeting with Optional chains
3. **Config Reader** (Challenge) — typed accessors with defaults and `requireString`

```bash
mvn test -pl 15-the-optional-type -Dtest="SafeParserTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- `Optional` makes **absence explicit** in return types — use it to avoid NPEs
- Prefer `orElse` / `orElseThrow` over `isPresent()` + `get()`
- **`map`** transforms values; **`flatMap`** chains Optional-returning operations
- **`filter`** narrows to values matching a predicate
- Use Optional for **return types only** — not fields, parameters, or collections

Further reading: *Effective Java* Item 55 · [java.util.Optional](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Optional.html)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 16
## I/O and NIO

--

## Objectives

- Understand **byte streams** vs **character streams**
- Use NIO.2 `Path`, `Files`, and `FileSystem` for modern file operations
- Read and write text with `Files.readString()`, `readAllLines()`, `writeString()`
- Read and write binary data with `Files.readAllBytes()` and `Files.write()`
- Walk directory trees with `Files.walk()` and `Files.walkFileTree()`
- Understand the basics of Java serialization

--

## Byte Streams vs Character Streams

| Family | Types | Use for |
|--------|-------|---------|
| **Byte streams** | `InputStream` / `OutputStream` | Binary data — images, audio, serialized objects |
| **Character streams** | `Reader` / `Writer` | Text with automatic encoding (e.g., UTF-8) |

```java
try (OutputStream out = Files.newOutputStream(path)) {
    out.write(new byte[]{72, 101, 108, 108, 111});
}
try (BufferedWriter writer = Files.newBufferedWriter(path)) {
    writer.write("Hello");
}
```

--

## Path and the FileSystem

`Path` represents a file or directory location — it does **not** require the file to exist:

```java
Path absolute = Path.of("/Users/alice/docs/report.txt");
Path relative = Path.of("src", "main", "java");
```

Useful methods: `getFileName()`, `getParent()`, `resolve(other)`, `toAbsolutePath()`

--

## Reading and Writing Files

NIO.2 one-liners for common operations:

```java
Files.writeString(path, "Hello, NIO.2!");
String content = Files.readString(path);
List<String> lines = Files.readAllLines(path);
Files.write(path, byteArray);
byte[] data = Files.readAllBytes(path);
```

Prefer `Files` / `Path` over legacy `java.io.File` for new code.

--

## Legacy `java.io` vs NIO.2

| Legacy (`java.io`) | NIO.2 (`java.nio.file`) |
|--------------------|---------------------------|
| `File` | `Path` |
| `FileInputStream` / `FileOutputStream` | `Files.newInputStream()` / `newOutputStream()` |
| `FileReader` / `FileWriter` | `Files.newBufferedReader()` / `newBufferedWriter()` |
| Manual stream plumbing | `readString`, `writeString`, `readAllLines` |

NIO.2 integrates with `java.nio` channels and works well with try-with-resources.

--

## Large Files — Buffered I/O

For large files, stream line-by-line instead of loading everything into memory:

```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    String line;
    while ((line = reader.readLine()) != null) {
        process(line);
    }
}
```

Always close streams — use **try-with-resources** to guarantee cleanup.

--

## Walking Directory Trees

`Files.walk()` returns a lazy `Stream<Path>` — **must** be closed:

```java
try (Stream<Path> stream = Files.walk(Path.of("src"))) {
    List<Path> javaFiles = stream
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".java"))
            .sorted()
            .toList();
}
```

For pre/post-visit callbacks and skipping subtrees, use `Files.walkFileTree()` with a `FileVisitor`.

--

## Serialization Basics

Convert objects to bytes and back via `java.io.Serializable`:

```java
public class Student implements Serializable {
    private String name;
    private int age;
}
```

Write with `ObjectOutputStream`, read with `ObjectInputStream`.

> **Item 85:** Prefer JSON, CSV, or other structured formats — serialization has security and versioning pitfalls.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `FileReadWrite` | NIO.2 Files API: write, read, append, readAllLines |
| `DirectoryWalker` | Walking directory trees, filtering, calculating sizes |
| `ByteStreamDemo` | Byte-oriented I/O with streams and Files convenience |

```bash
mvn test -pl 16-io-and-nio
```

--

## Exercises — Your Turn

1. **LineCounter** (Guided) — count lines, words, and characters in a file
2. **FileSearch** (Practice) — recursively search directories with glob patterns
3. **CsvParser** (Challenge) — parse CSV into `List<Map<String,String>>`

```bash
mvn test -pl 16-io-and-nio -Dtest="LineCounterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Use **character streams** (or `Files.readString` / `writeString`) for text; **byte streams** (or `readAllBytes` / `write`) for binary data
- Prefer NIO.2 `Files` / `Path` over legacy `java.io.File`
- Always close streams — try-with-resources is the standard pattern
- `Files.walk()` returns a `Stream<Path>` that must be closed
- Avoid Java serialization for data exchange — use JSON or CSV instead

Further reading: [java.nio.file package](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/nio/file/package-summary.html) · *Effective Java* Item 85

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 17
## Threads and Synchronization

--

## Objectives

- Create threads using `Thread` subclass, `Runnable` lambda, and `Thread.ofPlatform()`
- Understand the thread lifecycle (New → Runnable → Blocked/Waiting → Terminated)
- Protect shared state with `synchronized` and `volatile`
- Use explicit locks (`ReentrantLock`) and `tryLock()` for deadlock avoidance
- Recognize and prevent deadlock, livelock, and starvation

--

## Creating Threads

**1. Extending `Thread`:**

```java
Thread t = new Thread() {
    @Override public void run() { System.out.println("Running in " + getName()); }
};
t.start();
```

**2. `Runnable` lambda:**

```java
Thread t = new Thread(() -> System.out.println("Hello from " + Thread.currentThread().getName()));
t.start();
```

**3. `Thread.ofPlatform()` (Java 21+):**

```java
Thread t = Thread.ofPlatform().name("my-thread").start(() -> doWork());
```

> Always call `start()`, never `run()` directly — `run()` executes on the **current** thread.

--

## Thread Lifecycle

```
      start()
NEW ────────► RUNNABLE ◄──────────────────────────────────┐
                 │                                         │
                 ├─── synchronized (lock busy) ──► BLOCKED ┘
                 ├─── wait() / join() ──────────► WAITING ─┘
                 ├─── sleep(ms) / join(ms) ─► TIMED_WAITING┘
                 └─── run() returns ──────────► TERMINATED
```

Use `Thread.getState()` to inspect a thread's current state.

--

## The `synchronized` Keyword

Provides **mutual exclusion** and **memory visibility** — only one thread at a time per monitor:

```java
public class SafeCounter {
    private int count = 0;
    public synchronized void increment() { count++; }
    public synchronized int get() { return count; }
}
```

Or synchronize on a specific object:

```java
synchronized (lock) { /* critical section */ }
```

--

## The `volatile` Keyword

`volatile` guarantees **visibility** across threads, but **not** atomicity for compound operations:

```java
private volatile boolean running = true;
public void stop() { running = false; } // immediately visible
```

| Use `volatile` for | Use `synchronized` / `j.u.c` for |
|--------------------|-------------------------------------|
| Simple flags | Compound operations like `count++` |

--

## Explicit Locks — `ReentrantLock`

More flexible than `synchronized`: try-locking, timed locking, interruptible locking:

```java
ReentrantLock lock = new ReentrantLock();
if (lock.tryLock(1, TimeUnit.SECONDS)) {
    try { /* critical section */ }
    finally { lock.unlock(); }
} else { /* could not acquire in time */ }
```

Always release the lock in a `finally` block.

--

## wait() and notifyAll()

Threads coordinate on a shared monitor. Always call `wait()` inside a **`while`** loop:

```java
synchronized (buffer) {
    while (buffer.isEmpty()) {
        buffer.wait();  // releases monitor and waits
    }
    return buffer.poll();
}
```

Never use `if` — guards against **spurious wakeups**.

--

## Common Pitfalls

| Pitfall | Description | Fix |
|---------|-------------|-----|
| **Deadlock** | Threads each hold a lock the other needs | Consistent global lock order, or `tryLock()` |
| **Livelock** | Threads respond without making progress | Redesign coordination logic |
| **Starvation** | A thread never gets CPU time | Fair locks (`new ReentrantLock(true)`) |

Keep critical sections **as short as possible** to reduce contention.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `ThreadCreation` | Thread subclass, Runnable lambda, `Thread.ofPlatform()` |
| `SynchronizedCounter` | Thread-safe vs unsafe counter with `synchronized` |
| `DeadlockDemo` | Deadlock scenario and fix with consistent lock ordering |

```bash
mvn test -pl 17-threads-and-synchronization
```

--

## Exercises — Your Turn

1. **SafeCounter** (Guided) — thread-safe counter with `synchronized`
2. **ProducerConsumer** (Practice) — bounded buffer with `wait()` / `notifyAll()`
3. **DiningPhilosophers** (Challenge) — solve without deadlock using `ReentrantLock.tryLock()`

```bash
mvn test -pl 17-threads-and-synchronization -Dtest="SafeCounterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Never access shared mutable state from multiple threads without synchronization
- Prefer `synchronized` for simple cases; use `ReentrantLock` for try-locking or timed locking
- Always call `wait()` inside a `while` loop — never an `if`
- Acquire locks in a **consistent global order** to prevent deadlock
- `volatile` provides visibility but **not** atomicity

Further reading: [JLS §17.4 — Memory Model](https://docs.oracle.com/javase/specs/jls/se25/html/jls-17.html#jls-17.4) · *Effective Java* Items 78–79

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 18
## Concurrency Utilities

--

## Objectives

- Create and manage thread pools with `ExecutorService` and `Executors`
- Submit tasks and retrieve results using `Future`
- Build async pipelines with `CompletableFuture`
- Use concurrent collections (`ConcurrentHashMap`, `CopyOnWriteArrayList`)
- Leverage atomic variables (`AtomicInteger`, `AtomicReference`) for lock-free safety

--

## ExecutorService and Thread Pools

Creating raw threads per task is expensive. Thread pools **reuse** a fixed set of threads:

```java
ExecutorService executor = Executors.newFixedThreadPool(4);
Future<String> future = executor.submit(() -> "result");
String value = future.get();   // blocks until complete
executor.shutdown();
```

Always call `shutdown()` when finished — otherwise the JVM won't exit.

--

## Executor Types

| Factory Method | Pool Behaviour |
|----------------|----------------|
| `newFixedThreadPool(n)` | Exactly *n* threads; queues excess tasks |
| `newCachedThreadPool()` | Creates threads as needed; reuses idle ones |
| `newSingleThreadExecutor()` | One thread; tasks execute sequentially |
| `newVirtualThreadPerTaskExecutor()` | One virtual thread per task (Java 21+) |

--

## Future

A `Future<T>` represents a value available later:

```java
Future<Integer> future = executor.submit(() -> expensiveComputation());
int result = future.get();                        // blocks
int result = future.get(5, TimeUnit.SECONDS);     // with timeout
```

Limitations: no chaining, no combining, no error callbacks → use `CompletableFuture`.

--

## CompletableFuture

Fluent, non-blocking API extending `Future`:

| Method | Purpose |
|--------|---------|
| `thenApply(fn)` | Transform result (like `map`) |
| `thenCompose(fn)` | Flat-map to another `CompletableFuture` |
| `thenCombine(other, fn)` | Combine two independent futures |
| `exceptionally(fn)` | Recover from exceptions |
| `allOf(cf...)` | Wait for all futures to complete |

```java
CompletableFuture.supplyAsync(() -> fetchData())
    .thenApply(data -> transform(data))
    .thenCompose(result -> saveAsync(result))
    .exceptionally(ex -> fallback());
```

--

## Concurrent Collections

Standard collections (`HashMap`, `ArrayList`) are **not thread-safe**:

| Collection | Replaces | Key property |
|------------|----------|--------------|
| `ConcurrentHashMap` | `HashMap` | Fine-grained locking; `merge()`, `compute()` |
| `CopyOnWriteArrayList` | `ArrayList` | Copy-on-write; ideal for read-heavy workloads |
| `ConcurrentLinkedQueue` | `LinkedList` | Lock-free FIFO queue |
| `BlockingQueue` | — | Blocking `put()`/`take()` for producers/consumers |

```java
map.merge(key, 1L, Long::sum);   // atomic increment
```

--

## Atomic Variables

`java.util.concurrent.atomic` provides lock-free thread safety for single variables:

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();                    // atomic ++count
count.compareAndSet(expected, newValue);    // CAS
count.getAndUpdate(x -> x * 2);            // atomic read-modify-write
```

Faster than `synchronized` for simple counters — uses hardware CAS instead of locks.

--

## Graceful Executor Shutdown

```java
executor.shutdown();                          // no new tasks
if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
    executor.shutdownNow();                 // cancel running tasks
    executor.awaitTermination(60, TimeUnit.SECONDS);
}
```

- `shutdown()` — orderly shutdown; queued tasks still run
- `shutdownNow()` — attempt to stop all actively executing tasks
- Always shut down to avoid resource leaks and JVM hang on exit

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `ExecutorServiceDemo` | Thread pools, `Future` results, graceful shutdown |
| `CompletableFutureChaining` | `thenApply`, `thenCompose`, `thenCombine`, `exceptionally` |
| `AtomicCounter` | `AtomicInteger` vs non-atomic, `compareAndSet`, `getAndUpdate` |

```bash
mvn test -pl 18-concurrency-utilities
```

--

## Exercises — Your Turn

1. **ParallelSum** (Guided) — split array into chunks, sum in parallel with `ExecutorService`
2. **AsyncPipeline** (Practice) — chain `CompletableFuture` stages with error handling
3. **ConcurrentWordCounter** (Challenge) — count word frequencies with `ConcurrentHashMap.merge()`

```bash
mvn test -pl 18-concurrency-utilities -Dtest="ParallelSumTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Use `ExecutorService` instead of manually creating threads — it manages pooling and lifecycle
- Always shut down executors (`shutdown()` + `awaitTermination()`) to avoid resource leaks
- `CompletableFuture` enables readable async pipelines; use `exceptionally` or `handle` for errors
- `ConcurrentHashMap` provides atomic compound operations without external synchronization
- `AtomicInteger` and friends are the right tool for simple counters and flags

Further reading: [java.util.concurrent](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/package-summary.html) · *Effective Java* Item 81

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 19
## Virtual Threads

--

## Objectives

- Understand the motivation for virtual threads (thread-per-request vs async)
- Build a mental model of user-mode scheduling on carrier threads
- Create virtual threads with `Thread.ofVirtual()`, `startVirtualThread()`, and executors
- Understand structured concurrency and `StructuredTaskScope` (preview)
- Identify migration considerations when adopting virtual threads

--

## Motivation: Thread-per-Request vs Async

**Thread-per-request** is simple but platform threads are expensive (~1 MB stack, 1:1 OS mapping) — limiting servers to a few thousand concurrent requests.

**Async frameworks** (reactive streams, `CompletableFuture` chains) scale well but sacrifice readability and debuggability.

**Virtual threads** offer the best of both: write simple, blocking, thread-per-request code that scales to **millions** of concurrent tasks.

--

## Mental Model

```
┌─────────────────────────────────────────────────────────┐
│  ForkJoinPool (carrier threads = CPU cores)             │
│  Carrier 1    Carrier 2    Carrier 3    Carrier 4       │
│  ┌────────┐  ┌────────┐  ┌────────┐  ┌────────┐       │
│  │ VT-1   │  │ VT-4   │  │ VT-7   │  │ VT-10  │       │
│  │ VT-2   │  │ VT-5   │  │ VT-8   │  │  ...   │       │
│  └────────┘  └────────┘  └────────┘  └────────┘       │
└─────────────────────────────────────────────────────────┘
```

When a VT **blocks** (I/O, sleep, lock), it is **unmounted** — the carrier runs another VT.

--

## Virtual vs Platform Threads

| Property | Platform thread | Virtual thread |
|----------|-----------------|----------------|
| Stack memory | ~1 MB | Few hundred bytes, grows as needed |
| OS mapping | 1:1 | Many:1 on carrier threads |
| Best for | CPU-bound work | I/O-bound workloads |
| Pooling | Yes | **No** — create per task |

Virtual threads are always **daemon** threads with `Thread.NORM_PRIORITY`.

--

## Creating Virtual Threads

```java
// 1. Thread.ofVirtual().start()
Thread vt = Thread.ofVirtual().start(() -> doWork());

// 2. Convenience method
Thread vt = Thread.startVirtualThread(() -> doWork());

// 3. Named factory
ThreadFactory factory = Thread.ofVirtual().name("worker-", 0).factory();

// 4. ExecutorService (recommended)
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> handleRequest(request));
} // close() waits for all submitted tasks
```

--

## Structured Concurrency (Preview)

> `StructuredTaskScope` is a **preview API** in Java 25 — requires `--enable-preview`.

Subtasks are treated as a unit — start together, parent waits for all, failures cancel siblings:

```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    Subtask<String> user  = scope.fork(() -> fetchUser(id));
    Subtask<Order>  order = scope.fork(() -> fetchOrder(id));
    scope.join();
    scope.throwIfFailed();
    return new Response(user.get(), order.get());
}
```

Without preview: approximate with `newVirtualThreadPerTaskExecutor()` + try-with-resources.

--

## When to Use Virtual Threads

| Workload | Recommendation |
|----------|------------------|
| HTTP / REST handlers | ✓ Virtual threads |
| Database queries | ✓ Virtual threads |
| File I/O | ✓ Virtual threads |
| CPU-intensive computation | Platform thread pool |
| Long-running background jobs | Platform thread pool |

Rule of thumb: if the task **blocks waiting**, virtual threads shine. If it **computes continuously**, use platform threads.

--

## Migration Considerations

| Do | Don't |
|----|-------|
| Create a new VT per task | Pool virtual threads |
| Use `ReentrantLock` on blocking paths | Use `synchronized` on blocking I/O (pins carrier) |
| Use for **I/O-bound** workloads | Expect gains for CPU-bound tasks |
| Consider `ScopedValue` (preview) | Rely heavily on `ThreadLocal` at scale |

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `VirtualThreadBasics` | Creating virtual threads, named factories, daemon behavior |
| `VirtualVsPlatformThreads` | Scalability comparison, `newVirtualThreadPerTaskExecutor()` |
| `StructuredConcurrencyDemo` | Structured concurrency with virtual threads and executors |

```bash
mvn test -pl 19-virtual-threads
```

--

## Exercises — Your Turn

1. **VirtualThreadPool** (Guided) — execute tasks with `newVirtualThreadPerTaskExecutor()`
2. **WebScraper** (Practice) — concurrent URL fetching with timeout support
3. **StructuredFanOut** (Challenge) — `firstSuccess` and `allSuccesses` patterns

```bash
mvn test -pl 19-virtual-threads -Dtest="VirtualThreadPoolTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Virtual threads enable **thread-per-request** at massive scale without platform-thread overhead
- Use `Executors.newVirtualThreadPerTaskExecutor()` as the primary submission mechanism
- Ideal for **I/O-bound** workloads; CPU-bound work still benefits from platform thread pools
- **Structured concurrency** treats subtasks as a unit — no thread leaks, clear error propagation
- Avoid pooling VTs; avoid `synchronized` on blocking paths; prefer `ReentrantLock`

Further reading: [JEP 444: Virtual Threads](https://openjdk.org/jeps/444) · [JEP 453: Structured Concurrency](https://openjdk.org/jeps/453) · *Effective Java* Item 80

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 20
## Build Tools

--

## Objectives

- Understand why build tools are essential for Java projects
- Learn Maven POM structure and the role of each element
- Master Maven's build lifecycle: phases, plugins, and goals
- Understand dependency management: scopes, transitive deps, and BOM
- Compare Maven and Gradle at a conceptual level
- Understand reproducible builds and dependency locking

--

## Why Build Tools?

`javac` works for one file, but real projects need automation for:

- **Compilation** — all source files in correct order
- **Dependency management** — downloading and managing libraries
- **Testing** — unit and integration tests
- **Packaging** — JARs, WARs, distributable formats
- **Lifecycle management** — repeatable, ordered build process

--

## Maven — Convention over Configuration

Maven follows standard project layout — minimal configuration needed:

```
project-root/
├── pom.xml
├── src/main/java/       ← application source
├── src/main/resources/  ← config files
├── src/test/java/       ← test source
└── target/              ← compiled output
```

Gradle uses the same layout with `build/` instead of `target/`.

--

## POM Structure

```xml
<groupId>com.example</groupId>
<artifactId>my-application</artifactId>
<version>1.0.0</version>
<packaging>jar</packaging>

<properties>
    <maven.compiler.release>25</maven.compiler.release>
</properties>

<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Coordinates (`groupId`, `artifactId`, `version`) uniquely identify each artifact.

--

## Key POM Elements

| Element | Purpose |
|---------|---------|
| `<parent>` | Inherit configuration from a parent POM |
| `<properties>` | Reusable variables (`${junit.version}`) |
| `<dependencies>` | Declare external libraries |
| `<dependencyManagement>` | Centralize versions without adding to classpath |
| `<build>` | Configure plugins (compiler, Surefire) |
| `<modules>` | List sub-modules in a multi-module project |

--

## Maven Build Lifecycle

Invoking a phase runs **all preceding phases**:

| Phase | Description | Typical Plugin |
|-------|-------------|----------------|
| `validate` | Validate project structure | (built-in) |
| `compile` | Compile `src/main/java` | `maven-compiler-plugin` |
| `test` | Run unit tests | `maven-surefire-plugin` |
| `package` | Create JAR/WAR | `maven-jar-plugin` |
| `install` | Install to `~/.m2/repository` | `maven-install-plugin` |
| `deploy` | Upload to remote repository | `maven-deploy-plugin` |

```bash
mvn test          # validate → compile → test
mvn clean install # clean + build through install
```

--

## Dependency Scopes

| Scope | Compile | Test | Runtime | Packaged |
|-------|:-------:|:----:|:-------:|:--------:|
| `compile` (default) | ✓ | ✓ | ✓ | ✓ |
| `provided` | ✓ | ✓ | — | — |
| `runtime` | — | ✓ | ✓ | ✓ |
| `test` | — | ✓ | — | — |

- **`compile`** — needed everywhere (Guava, Jackson)
- **`provided`** — supplied by runtime (Servlet API)
- **`test`** — only for tests (JUnit, Mockito)

--

## Transitive Dependencies and BOM

Maven automatically includes transitive dependencies:

```
my-app → spring-web → spring-core, spring-beans  (transitive)
```

Visualize with `mvn dependency:tree`.

A **BOM** centralizes versions — import in `<dependencyManagement>`:

```xml
<dependency>
    <groupId>org.junit</groupId>
    <artifactId>junit-bom</artifactId>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```

Then omit `<version>` for managed dependencies.

--

## Gradle — Alternative Build Tool

Uses **Groovy** or **Kotlin** DSL instead of XML:

```kotlin
plugins { java }
group = "com.example"
version = "1.0.0"

dependencies {
    implementation("com.google.guava:guava:33.0.0-jre")
    testImplementation("org.junit.jupiter:junit-jupiter:6.1.1")
}
```

| Aspect | Maven | Gradle |
|--------|-------|--------|
| Configuration | XML | Groovy/Kotlin DSL |
| Build speed | Full rebuild | Incremental + cache |
| Learning curve | Lower | Higher |
| Ecosystem | Largest | Android standard |

--

## Reproducible Builds

Building the same source should always produce the same artifact:

- **Lock dependency versions** — never use ranges like `[1.0,2.0)` in production
- **Use a BOM** — centralize versions in one place
- **Pin plugin versions** — always specify `<version>` for build plugins
- **Use `maven.compiler.release`** — ensures API compatibility
- **Dependency verification** — `maven-enforcer-plugin` or Gradle checksums

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `MavenLifecycleDemo` | Maven lifecycle phases modeled as data, phase ordering |

```bash
mvn test -pl 20-build-tools
```

--

## Exercises — Your Turn

1. **DependencyAnalyzer** (Guided) — parse POM XML into `Dependency` records
2. **VersionResolver** (Practice) — semantic version parsing and range matching
3. **BuildOrderSolver** (Challenge) — topological sort for module build order

```bash
mvn test -pl 20-build-tools -Dtest="DependencyAnalyzerTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- **Maven** uses `pom.xml` with convention-over-configuration for builds, deps, and lifecycle
- Running a phase (e.g., `mvn test`) executes all preceding phases automatically
- **Dependency scopes** control when a library is available on each classpath
- **Gradle** offers a flexible, script-based alternative with incremental builds
- Always **pin dependency and plugin versions** for reproducible builds

Further reading: [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) · [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 21
## Unit Testing with JUnit

--

## Objectives

- Understand the purpose and principles of unit testing
- Write tests using JUnit Jupiter assertions: `assertEquals`, `assertTrue`, `assertThrows`, `assertAll`
- Use lifecycle hooks: `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`
- Write parameterized tests with `@ParameterizedTest`, `@CsvSource`, `@ValueSource`, `@MethodSource`
- Organize tests with `@Nested` classes and `@DisplayName`

--

## What Is Unit Testing?

A **unit test** verifies a single unit of code (typically a method or class) in isolation.

| Property | Description |
|----------|-------------|
| **Fast** | Milliseconds — no database, network, or file I/O |
| **Isolated** | Independent — no shared mutable state |
| **Repeatable** | Same result every time |
| **Self-validating** | Pass or fail automatically |
| **Timely** | Written close to the code they test |

--

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

--

## Assertions

| Assertion | Purpose |
|-----------|---------|
| `assertEquals(expected, actual)` | Values are equal |
| `assertTrue` / `assertFalse` | Condition is true / false |
| `assertNull` / `assertNotNull` | Null checks |
| `assertThrows(ExType.class, executable)` | Expected exception thrown |
| `assertAll(heading, executables...)` | Run all, report all failures |
| `assertTimeout(duration, executable)` | Completes within timeout |

--

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

--

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

--

## Lifecycle Hooks

JUnit creates a **new test class instance** for each test method:

| Annotation | Runs | Instance | Typical Use |
|------------|------|----------|-------------|
| `@BeforeAll` | Once before all tests | Static | Expensive one-time setup |
| `@BeforeEach` | Before each test | Instance | Fresh test fixtures |
| `@AfterEach` | After each test | Instance | Release resources |
| `@AfterAll` | Once after all tests | Static | Tear down shared resources |

--

## Parameterized Tests

Run the same test logic with different inputs — use `@ParameterizedTest` plus a source:

```java
@ParameterizedTest(name = "{0}°C = {1}°F")
@CsvSource({ "0, 32.0", "100, 212.0", "-40, -40.0" })
void celsiusToFahrenheit(double celsius, double expectedF) {
    assertEquals(expectedF, converter.toFahrenheit(celsius), 0.1);
}
```

--

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

--

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

--

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

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `AssertionShowcaseTest` | `assertEquals`, `assertThrows`, `assertAll` |
| `ParameterizedTestDemo` | `@CsvSource`, `@ValueSource`, `@MethodSource` |
| `LifecycleDemo` | `@BeforeAll`, `@BeforeEach`, `@AfterEach`, `@AfterAll` |
| `NestedTestDemo` | `@Nested` state-based organization |

```bash
mvn test -pl 21-unit-testing-with-junit
```

--

## Exercises — Your Turn

Exercises are **reversed** — production code is provided; you write the tests.

1. **Calculator Tests** (Guided) — `add`, `subtract`, `multiply`, `divide`, division by zero
2. **Roman Numeral** (Practice) — parameterized tests with `@CsvSource` / `@MethodSource`
3. **Shopping Cart** (Challenge) — nested tests: `WhenEmpty`, `WhenHasItems`, `WithDiscount`

```bash
mvn test -pl 21-unit-testing-with-junit -Dtest="CalculatorTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Unit tests verify individual methods in isolation — **fast, isolated, repeatable**
- Rich assertions: `assertEquals`, `assertThrows`, `assertAll` for grouped checks
- **Lifecycle hooks** ensure each test gets a fresh fixture
- **Parameterized tests** eliminate duplication; **nested tests** organize by state
- `@DisplayName` makes test output human-readable

Further reading: [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/) · [Parameterized Tests (Baeldung)](https://www.baeldung.com/parameterized-tests-junit-5)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 22
## Mocking and Integration Testing

--

## Objectives

- Create mocks, stubs, and spies with Mockito
- Stub method return values and verify interactions
- Use argument matchers for flexible stubbing
- Understand when to mock vs. when to use real dependencies

--

## Test Doubles

| Type | What it is | Mockito tool |
|------|------------|--------------|
| **Mock** | Fake object that records interactions | `@Mock`, `mock()` |
| **Stub** | Returns predefined values for method calls | `when(...).thenReturn(...)` |
| **Spy** | Wraps a real object; can stub specific methods | `@Spy`, `spy()` |

--

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

--

## Stubbing Return Values

```java
@Test
void findUserReturnsUserWhenFound() {
    when(repository.findById(1L)).thenReturn(Optional.of(alice));

    assertTrue(userService.findUser(1L).isPresent());
}
```

Stub only the methods your test needs — unrelated calls return defaults (`null`, `0`, empty collections).

--

## Verifying Interactions

Mocks record **how** they were called — verify behavior, not just return values:

```java
verify(repository).findById(1L);
verify(emailService).send(eq("alice@example.com"), eq("Welcome!"));
verifyNoInteractions(auditLog);  // method should never be called
```

--

## Argument Matchers

Flexible stubbing and verification when exact values don't matter:

```java
when(gateway.charge(anyInt(), anyString())).thenReturn(true);
verify(gateway).charge(eq(5000), eq("tok_abc"));
verify(emailService).send(contains("@"), eq("Welcome!"), anyString());
```

Import statically: `any()`, `eq()`, `contains()`, `anyInt()`, `anyString()`.

--

## When to Mock

| Mock | Don't mock |
|------|------------|
| External services (DB, APIs, email) | The class under test |
| Slow, unreliable, or unavailable deps | Value objects / simple data structures |

- **Integration tests** use real dependencies where practical
- Over-mocking leads to **brittle tests** — mock only what you need to isolate

--

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

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `MockitoBasicsTest` | `@Mock`, `@InjectMocks`, `when`, `verify` |
| `SpyDemoTest` | Spying on real objects |
| `ArgumentMatcherDemoTest` | `any()`, `eq()`, `contains()` |

```bash
mvn test -pl 22-mocking-and-integration-testing
```

--

## Exercises — Your Turn

1. **UserService** (Guided) — mock `UserRepository` and `EmailService`
2. **OrderService** (Practice) — mock `PaymentGateway` and `InventoryService`
3. **NotificationService** (Challenge) — use a spy to verify dispatch methods by preference

```bash
mvn test -pl 22-mocking-and-integration-testing -Dtest="UserServiceTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Mock **dependencies**, not the class under test
- Verify **interactions** — was the right method called with the right args?
- Use `@ExtendWith(MockitoExtension.class)` to initialize mocks
- Over-mocking leads to brittle tests — mock only what you need to isolate

Further reading: [Mockito documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html) · *Effective Java* Item 51

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 23
## Annotations and Reflection

--

## Objectives

- Understand annotations, retention policies, and targets
- Use built-in annotations: `@Override`, `@Deprecated`, `@FunctionalInterface`
- Define custom annotations and read them at runtime
- Inspect classes, fields, and methods with the reflection API

--

## What Are Annotations?

An **annotation** is metadata attached to program elements (classes, methods, fields, parameters).

Annotations do **not** change program logic by themselves — frameworks and libraries read them to configure behavior.

```java
@Override
public String toString() {
    return "Example";
}
```

--

## Meta-Annotations

Annotations are defined with meta-annotations:

| Meta-annotation | Purpose |
|-----------------|---------|
| `@Retention` | How long the annotation is kept |
| `@Target` | Where the annotation may appear |
| `@Documented` | Include in generated Javadoc |
| `@Inherited` | Inherited by subclasses |

--

## Retention Policies

| Policy | Available at |
|--------|--------------|
| `SOURCE` | Compile time only (e.g. `@Override`) |
| `CLASS` | In bytecode, not at runtime |
| `RUNTIME` | Via reflection at runtime |

Use `RUNTIME` when your code reads annotations dynamically.

--

## Built-In Annotations

| Annotation | Purpose |
|------------|---------|
| `@Override` | Marks a method overriding a superclass method |
| `@Deprecated` | Marks an API element scheduled for removal |
| `@SuppressWarnings` | Suppresses compiler warnings |
| `@FunctionalInterface` | Documents a single-abstract-method interface |
| `@SafeVarargs` | Asserts a varargs method is safe |

--

## Defining Custom Annotations

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    int min();
    int max();
}
```

Members of an annotation are called **elements** and must be compile-time constants.

--

## The Reflection API

Reflection lets code inspect and manipulate classes at runtime:

```java
Class<?> type = object.getClass();
Field field = type.getDeclaredField("name");
field.setAccessible(true);
Object value = field.get(object);

Method method = type.getDeclaredMethod("greet");
Object result = method.invoke(object);
```

--

## Reflection Types

| Type | Purpose |
|------|---------|
| `Class<?>` | Represents a loaded class |
| `Field` | Represents a declared field |
| `Method` | Represents a declared method |
| `Constructor<?>` | Represents a constructor |
| `Parameter` | Represents a method parameter |

Use `getDeclared*` to include private members; `setAccessible(true)` bypasses access checks.

--

## Reading Annotations at Runtime

```java
if (field.isAnnotationPresent(NotNull.class)) {
    // validate non-null
}

Range range = field.getAnnotation(Range.class);
if (value < range.min() || value > range.max()) {
    throw new IllegalArgumentException("Out of range");
}
```

This pattern powers validation, serialization, and framework-style behavior.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `BuiltInAnnotations` | `@Override`, `@Deprecated`, `@FunctionalInterface` |
| `CustomAnnotationDemo` | Custom `@NotNull` and `@Range` with runtime validation |
| `ReflectionBasics` | Inspecting fields, methods, invoking behavior |

```bash
mvn test -pl 23-annotations-and-reflection
```

--

## Exercises — Your Turn

1. **Object Inspector** (Guided) — summarize class name, fields, and methods via reflection
2. **Simple Serializer** (Practice) — serialize only `@Serializable` fields to `key=value,...`
3. **Mini Test Runner** (Challenge) — discover and run methods annotated with course `@Test`

```bash
mvn test -pl 23-annotations-and-reflection -Dtest="ObjectInspectorTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Annotations attach metadata; **retention** and **target** control scope and lifetime
- **Built-in annotations** document intent and enable compiler checks
- **Custom annotations** power validation, serialization, and framework behavior
- **Reflection** inspects types at runtime — use sparingly (performance and encapsulation costs)

Further reading: [JLS §9.6](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.6) · [java.lang.reflect API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/reflect/package-summary.html)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 24
## Date and Time API

--

## Objectives

- Use `LocalDate`, `LocalTime`, and `LocalDateTime` for calendar dates and wall-clock times
- Format and parse date-time values with `DateTimeFormatter`
- Measure elapsed time with `Duration` and calendar spans with `Period`
- Work with time zones using `ZoneId` and `ZonedDateTime`

--

## Why java.time?

Before Java 8, `java.util.Date` and `Calendar` were error-prone and **mutable**.

The **java.time** package (JSR-310) provides an **immutable, thread-safe** model.

| Type | Represents | Example use case |
|------|------------|------------------|
| `LocalDate` | Date without time or zone | Birthdays, due dates |
| `LocalTime` | Time without date or zone | Store opening hours |
| `LocalDateTime` | Date and time without zone | Meeting start (local) |
| `ZonedDateTime` | Date-time with time zone | Global events |
| `Instant` | Point on the UTC timeline | Event timestamps |

--

## Duration and Period

| Type | Based on | Example |
|------|----------|---------|
| `Duration` | Time (seconds, nanos) | 2 hours 30 minutes |
| `Period` | Calendar dates | 2 years, 3 months, 5 days |

```java
Duration elapsed = Duration.between(start, end);
Period age = Period.between(birthDate, today);
```

--

## Creating Values

```java
LocalDate date = LocalDate.of(2024, Month.MARCH, 15);
LocalTime time = LocalTime.of(14, 30);
LocalDateTime dateTime = LocalDateTime.of(date, time);

LocalDate parsed = LocalDate.parse("2024-03-15");
LocalDateTime now = LocalDateTime.now();
```

All values are **immutable** — methods like `plusDays()` return new instances.

--

## Formatting and Parsing

```java
DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE;
String text = date.format(iso);

DateTimeFormatter custom = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
LocalDateTime parsed = LocalDateTime.parse("15/03/2024 09:00", custom);
```

`DateTimeFormatter` is **immutable and thread-safe** — define formatters as constants.

--

## Pattern Letters

| Pattern | Meaning | Example |
|---------|---------|---------|
| `yyyy` | 4-digit year | 2024 |
| `MM` | 2-digit month | 03 |
| `dd` | 2-digit day | 15 |
| `HH` | Hour (0–23) | 14 |
| `mm` | Minute | 30 |
| `ss` | Second | 00 |

Use `DateTimeFormatter.ofPattern(...)` for custom formats.

--

## Time Zones

```java
ZoneId zone = ZoneId.of("America/New_York");
ZonedDateTime ny = localDateTime.atZone(zone);
ZonedDateTime tokyo = ny.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
```

`withZoneSameInstant` converts the same instant to another zone — local clock time changes, but the moment in time is preserved.

--

## Local vs Zoned

| Use | When |
|-----|------|
| `LocalDate` / `LocalDateTime` | Zone is implicit (birthdays, local meetings) |
| `ZonedDateTime` | Global scheduling, cross-region coordination |
| `Instant` | Storing event timestamps in UTC |

Never use legacy `Date`/`Calendar` in new code.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `DateTimeCreation` | Creating and parsing local dates and times |
| `DateTimeFormatting` | Formatting with ISO and custom patterns |
| `DurationAndPeriod` | Elapsed time and calendar periods |
| `ZonedDateTimeDemo` | Zone conversion and offsets |

```bash
mvn test -pl 24-date-and-time-api
```

--

## Exercises — Your Turn

1. **Age Calculator** (Guided) — age in years and as a full `Period`
2. **Meeting Scheduler** (Practice) — detect overlapping meetings, find next slot
3. **Time Zone Converter** (Challenge) — convert zoned date-times between regions

```bash
mvn test -pl 24-date-and-time-api -Dtest="AgeCalculatorTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Prefer **java.time** over legacy `Date` and `Calendar`
- Use **`LocalDate`/`LocalDateTime`** when the zone is implicit; **`ZonedDateTime`** for global scheduling
- **`Duration`** measures time; **`Period`** measures calendar dates
- **`DateTimeFormatter`** is immutable and thread-safe — define formatters as constants

Further reading: [java.time API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/time/package-summary.html) · [JEP 150](https://openjdk.org/jeps/150)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 25
## Networking Basics

--

## Objectives

- Understand the basics of TCP sockets and HTTP
- Use the modern `java.net.http.HttpClient` API
- Build HTTP requests with headers, bodies, and timeouts
- Make REST calls and handle responses
- Design network code that is testable without hitting the real internet

--

## Sockets and HTTP

At the transport layer, **sockets** are endpoints for sending and receiving data over TCP:

```java
try (ServerSocket server = new ServerSocket(8080)) {
    Socket client = server.accept();
    // read/write streams on client.getInputStream()
}
```

HTTP is an application-layer protocol built on TCP. Modern Java code uses **`HttpClient`** (Java 11+) instead of raw sockets.

--

## HttpClient

`HttpClient` is **immutable and thread-safe** — create one instance and reuse it:

```java
HttpClient client = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();
```

| Setting | Description |
|---------|-------------|
| `connectTimeout` | Max time to establish a connection |
| `followRedirects` | Whether to follow 3xx redirects |
| `version(HTTP_2)` | Prefer HTTP/2 |

--

## HttpRequest

Build requests with the builder pattern:

```java
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.example.com/users"))
        .header("Accept", "application/json")
        .header("Authorization", "Bearer " + token)
        .timeout(Duration.ofSeconds(30))
        .GET()
        .build();
```

Set **timeouts** on both the client and individual requests.

--

## HTTP Methods

| Method | Body publisher | Use case |
|--------|----------------|----------|
| `GET()` | None | Fetch a resource |
| `POST(body)` | `BodyPublishers.ofString(json)` | Create a resource |
| `PUT(body)` | `BodyPublishers.ofString(json)` | Replace a resource |
| `DELETE()` | None | Remove a resource |

--

## HttpResponse

Send a request synchronously:

```java
HttpResponse<String> response = client.send(
        request,
        HttpResponse.BodyHandlers.ofString());

int status = response.statusCode();
String body = response.body();
```

Handle **status codes** explicitly — do not assume every response is 200.

--

## Common Status Codes

| Code | Meaning | Typical action |
|------|---------|----------------|
| 200 | OK | Process the response body |
| 201 | Created | Resource created (POST) |
| 400 | Bad Request | Fix the request payload |
| 401 | Unauthorized | Add or refresh credentials |
| 404 | Not Found | Check the URL or resource id |
| 500 | Server Error | Retry or report upstream |

--

## Async Requests

`HttpClient` supports non-blocking sends:

```java
CompletableFuture<HttpResponse<String>> future = client.sendAsync(
        request,
        HttpResponse.BodyHandlers.ofString());

future.thenApply(HttpResponse::body)
      .thenAccept(System.out::println);
```

Use async when you need concurrency without blocking threads.

--

## Testing Without the Real Network

Production code should accept **injectable dependencies**:

```java
var client = new JsonPlaceholderClient(HttpClient.newHttpClient(), baseUrl);

public interface HttpSender {
    HttpResponse<String> send(HttpRequest request)
            throws IOException, InterruptedException;
}
```

In tests: use `com.sun.net.httpserver.HttpServer` on a random port or a stub `HttpSender` with canned responses.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `HttpClientDemo` | Creating clients, sending GET requests |
| `RequestBuilderDemo` | POST with JSON, timeouts, Authorization headers |

```bash
mvn test -pl 25-networking-basics
```

--

## Exercises — Your Turn

1. **Request Builder** (Guided) — build GET and POST requests with appropriate headers
2. **JSON Placeholder Client** (Practice) — REST client with injectable `HttpClient` and base URL
3. **Retrying HTTP Client** (Challenge) — automatic retries via injectable `HttpSender`

```bash
mvn test -pl 25-networking-basics -Dtest="RequestBuilderTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Prefer **`HttpClient`** over legacy `HttpURLConnection` for new code
- Reuse a single **`HttpClient`** instance — it is thread-safe
- Set **timeouts** on both the client and individual requests
- Accept **injectable dependencies** so network code can be tested locally
- Handle **status codes** explicitly — do not assume every response is 200

Further reading: [HttpClient API](https://docs.oracle.com/en/java/javase/25/docs/api/java.net.http/java/net/http/HttpClient.html) · [JEP 321](https://openjdk.org/jeps/321)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 26
## Database Access with JDBC

--

## Objectives

- Connect to a database using `DriverManager` or `DataSource`
- Execute queries with `Statement` and `PreparedStatement`
- Read data from a `ResultSet`
- Prevent SQL injection with parameterized queries
- Manage transactions with commit and rollback
- Understand connection pooling concepts

--

## JDBC Architecture

JDBC (Java Database Connectivity) is the standard API for relational databases:

```
Application  →  JDBC API  →  JDBC Driver  →  Database
```

--

## Key JDBC Interfaces

| Interface | Role |
|-----------|------|
| `Connection` | A session with a specific database |
| `Statement` | Execute static SQL |
| `PreparedStatement` | Execute parameterized SQL (precompiled) |
| `ResultSet` | Cursor over query results |
| `DataSource` | Factory for connections (used by pools) |

--

## Connecting to a Database

```java
String url = "jdbc:h2:mem:testdb";
try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
    // use connection
}
```

| Vendor | Example URL |
|--------|-------------|
| H2 (memory) | `jdbc:h2:mem:mydb;DB_CLOSE_DELAY=-1` |
| PostgreSQL | `jdbc:postgresql://localhost:5432/mydb` |
| MySQL | `jdbc:mysql://localhost:3306/mydb` |

Always close connections with **try-with-resources**.

--

## Statements and Result Sets

```java
try (Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT name FROM users")) {
    while (rs.next()) {
        String name = rs.getString("name");
    }
}
```

| Method | Description |
|--------|-------------|
| `next()` | Advance to the next row |
| `getString(col)` | Read a string column |
| `getInt(col)` / `getDouble(col)` | Read numeric columns |
| `wasNull()` | Check if last read value was SQL NULL |

--

## Prepared Statements

Use `PreparedStatement` for any query with user input — prevents **SQL injection**:

```java
String sql = "SELECT name FROM users WHERE id = ?";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setInt(1, userId);
    try (ResultSet rs = ps.executeQuery()) { /* process */ }
}
```

| Method | Parameter type |
|--------|----------------|
| `setString(i, v)` | String |
| `setInt(i, v)` | int |
| `setObject(i, v)` | any Object |
| `setNull(i, type)` | SQL NULL |

--

## SQL Injection — Avoid Concatenation

```java
// DANGEROUS — SQL injection risk
"SELECT * FROM users WHERE name = '" + userInput + "'"

// SAFE — parameterized
"SELECT * FROM users WHERE name = ?"
```

Prefer **`PreparedStatement`** over string concatenation for every user-supplied value.

--

## Inserting and Generated Keys

```java
String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql,
        PreparedStatement.RETURN_GENERATED_KEYS)) {
    ps.setString(1, name);
    ps.setString(2, email);
    ps.executeUpdate();
    try (ResultSet keys = ps.getGeneratedKeys()) {
        keys.next();
        int newId = keys.getInt(1);
    }
}
```

--

## Transactions

By default, JDBC uses **auto-commit** (each statement is its own transaction):

```java
conn.setAutoCommit(false);
try {
    debit(fromAccount, amount);
    credit(toAccount, amount);
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
    throw e;
} finally {
    conn.setAutoCommit(true);
}
```

Use transactions when multi-step operations must **succeed or fail together**.

--

## ACID Properties

| Property | Meaning |
|----------|---------|
| **Atomicity** | All operations succeed or all are rolled back |
| **Consistency** | Database moves from one valid state to another |
| **Isolation** | Concurrent transactions do not interfere |
| **Durability** | Committed changes survive crashes |

--

## Connection Pooling

Creating a new connection is expensive. Pools (HikariCP, Tomcat Pool) maintain reusable connections:

```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:postgresql://localhost/mydb");
HikariDataSource ds = new HikariDataSource(config);

try (Connection conn = ds.getConnection()) {
    // use pooled connection
}
```

In production, always use a pool — never `DriverManager` per request.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `JdbcBasics` | `Statement`, `ResultSet`, row counting |
| `PreparedStatementDemo` | Parameterized queries, inserts, generated keys |
| `TransactionDemo` | Commit, rollback, fund transfer |

```bash
mvn test -pl 26-database-access-with-jdbc
```

--

## Exercises — Your Turn

1. **StudentDao** (Guided) — find and insert for a students table
2. **TransactionManager** (Practice) — wrap work in commit/rollback
3. **GenericDao** (Challenge) — reusable query helper with `RowMapper`

```bash
mvn test -pl 26-database-access-with-jdbc -Dtest="StudentDaoTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Always use **try-with-resources** for `Connection`, `Statement`, and `ResultSet`
- Prefer **`PreparedStatement`** over string concatenation to prevent SQL injection
- Use **transactions** for multi-step operations that must succeed or fail together
- In production, use a **connection pool** — not `DriverManager` per request
- Tests can use in-memory **H2** without external infrastructure

Further reading: [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html) · [HikariCP](https://github.com/brettwooldridge/HikariCP)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 27
## Modern Java Features

--

## Objectives

- Use pattern matching in `switch` expressions and statements
- Apply `when` guards for conditional pattern cases
- Use unnamed variables (`_`) to mark intentionally unused bindings
- Combine sealed types with exhaustive pattern matching
- Understand which modern features remain preview-only in Java 25

--

## Pattern Matching Switch

Traditional `switch` only worked on discrete constants. Pattern matching tests the **type and shape** of a value:

```java
String classify(Object obj) {
    return switch (obj) {
        case null -> "null";
        case Integer i when i < 0 -> "negative: " + i;
        case Integer i -> "positive: " + i;
        case String s -> "string: " + s;
        default -> "other";
    };
}
```

--

## Pattern Matching — Key Properties

| Feature | Description |
|---------|-------------|
| Type patterns | `case String s` binds a variable when the runtime type matches |
| Null handling | `case null` matches before type patterns |
| Dominance checking | Compiler rejects unreachable or overlapping cases |
| Exhaustiveness | With sealed hierarchies, the compiler verifies all subtypes |

--

## Guarded Patterns

A `when` clause adds a boolean guard evaluated **after** the pattern matches:

```java
return switch (shape) {
    case Rectangle r when r.width() == r.height() -> "square";
    case Rectangle r -> "rectangle";
    case Circle c -> "circle";
};
```

Guards enable specialized cases (squares) without duplicating type checks.

--

## Pattern Matching vs `instanceof`

```java
// Before — instanceof chain
if (obj instanceof String s) { return "string: " + s; }
else if (obj instanceof Integer i) { return "int: " + i; }

// After — pattern matching switch
return switch (obj) {
    case String s -> "string: " + s;
    case Integer i -> "int: " + i;
    default -> "other";
};
```

Switch expressions return a value; the compiler checks **exhaustiveness** with sealed types.

--

## Sealed Types + Exhaustive Switch

Sealed hierarchies let the compiler verify **all permitted subtypes** are handled:

```java
sealed interface Shape permits Rectangle, Circle { }

return switch (shape) {
    case Rectangle r when r.width() == r.height() -> "square";
    case Rectangle r -> "rectangle";
    case Circle c -> "circle";
    // no default needed — compiler knows all cases
};
```

--

## Unnamed Variables

Use `_` when a binding is required by syntax but intentionally unused:

```java
for (String _ : items) { count++; }

try {
    process();
} catch (IOException _) {
    return false;
}

case Pair(int x, int _) -> x;  // record pattern
```

Unnamed variables document that a value is **deliberately ignored**.

--

## Preview Features (Java 25)

> Require `--enable-preview` at compile and runtime. This chapter uses only **finalized** features.

| JEP | Feature | Status |
|-----|---------|--------|
| 459 | Primitive types in patterns | Preview |
| 455 | Module import declarations | Preview |
| 512 | Compact source files | Preview |
| 453 | Structured concurrency | Preview (see Ch. 18) |
| 481 | Scoped values | Preview |

Do not use preview APIs in production without understanding stability risk.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `PatternMatchingSwitch` | Type patterns, null cases, `when` guards |
| `UnnamedVariables` | `_` in loops, catch, lambdas, record patterns |
| `GuardedPatterns` | Sealed `Shape` hierarchy with exhaustive switch |

```bash
mvn test -pl 27-modern-java-features
```

--

## Exercises — Your Turn

1. **ShapeFormatter** (Guided) — pattern matching switch with guards for squares
2. **ExpressionEvaluator** (Practice) — evaluate sealed `Expr` hierarchy recursively
3. **JsonPrettyPrinter** (Challenge) — pretty-print sealed `JsonValue` with indentation

```bash
mvn test -pl 27-modern-java-features -Dtest="ShapeFormatterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- **Pattern matching switch** replaces `instanceof` chains with readable, exhaustive dispatch
- **`when` guards** add conditional logic to individual cases without nested `if` statements
- **Unnamed variables** (`_`) signal deliberately unused bindings and improve clarity
- **Sealed types + patterns** give compile-time exhaustiveness checking
- **Preview features** require explicit opt-in — prefer finalized APIs for production

Further reading: [JEP 441](https://openjdk.org/jeps/441) · [JEP 456](https://openjdk.org/jeps/456) · [JEP 409](https://openjdk.org/jeps/409)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 28
## JVM Internals

--

## Objectives

- Understand how the JVM loads and initializes classes
- Map the major JVM memory areas (heap, metaspace, stacks, PC registers)
- Explain garbage collection algorithms and collector trade-offs
- Describe how the JIT compiler optimizes hot code
- Use diagnostic tools to observe JVM behavior at runtime

--

## Class Loading — Three Phases

The JVM loads classes **lazily** — when first referenced:

| Phase | What happens |
|-------|--------------|
| **Loading** | Read `.class` bytes, create a `Class` object in metaspace |
| **Linking** | Verify bytecode, prepare static fields, resolve references |
| **Initialization** | Run static initializers (`<clinit>`) in source order |

--

## Initialization Order

```
1. Parent static fields (in source order)
2. Parent static blocks
3. Child static fields
4. Child static blocks
5. Parent instance fields / instance blocks
6. Parent constructor
7. Child instance fields / instance blocks
8. Child constructor
```

Parent before child; static before instance.

--

## Class Loader Hierarchy

```
Bootstrap ClassLoader (JDK core: java.lang.*)
    ↑
Platform ClassLoader (JDK modules)
    ↑
Application ClassLoader (classpath / module path)
```

Each loader asks its **parent first**, ensuring core classes cannot be spoofed.

--

## JVM Memory Areas

```
┌─────────────────────────────────────────────┐
│  Heap (shared) — Young Gen │ Old Gen         │
│  Metaspace — class metadata, constant pools │
│  Thread Stacks — local variables, frames    │
│  Code Cache — JIT-compiled native code      │
│  PC Registers — current instruction pointer │
└─────────────────────────────────────────────┘
```

--

## Memory Areas — Details

| Area | Stores | GC? | Size flag |
|------|--------|-----|-----------|
| Heap | Object instances, arrays | Yes | `-Xmx`, `-Xms` |
| Metaspace | Class metadata, method bytecode | Yes* | `-XX:MaxMetaspaceSize` |
| Thread stack | Method frames, local variables | No | `-Xss` |
| Code cache | JIT-compiled machine code | No | `-XX:ReservedCodeCacheSize` |

*Metaspace collected when classes are unloaded (rare in typical apps).

--

## Heap Stats Programmatically

```java
Runtime rt = Runtime.getRuntime();
long used  = rt.totalMemory() - rt.freeMemory();
long max   = rt.maxMemory();
```

Use `Runtime` for coarse heap visibility; use JMX and `jstat` for production diagnostics.

--

## Garbage Collection

GC reclaims objects no longer **reachable** from GC roots (thread stacks, static fields, JNI references).

**Generational hypothesis:** most objects die young.

| Generation | Contents | Collector activity |
|------------|----------|-------------------|
| Young | Newly allocated objects | Frequent, fast minor GC |
| Old | Long-lived survivors | Infrequent, slower major GC |

--

## Common Collectors (Java 25)

| Collector | Flag | Best for |
|-----------|------|----------|
| G1 (default) | (none needed) | General-purpose, balanced latency |
| ZGC | `-XX:+UseZGC` | Very low pause times, large heaps |
| Shenandoah | `-XX:+UseShenandoahGC` | Low pause, concurrent compaction |
| Serial | `-XX:+UseSerialGC` | Small heaps, single-threaded apps |

--

## Observing GC via JMX

```java
List<GarbageCollectorMXBean> beans =
    ManagementFactory.getGarbageCollectorMXBeans();
for (GarbageCollectorMXBean bean : beans) {
    System.out.println(bean.getName() + ": " + bean.getCollectionCount());
}
```

--

## JIT Compilation

The JVM starts by **interpreting** bytecode. HotSpot profiles execution and JIT-compiles hot methods to native code.

| Tier | Description |
|------|-------------|
| Interpreter | Bytecode interpreted line by line (startup) |
| C1 (client) | Fast compilation, light optimization |
| C2 (server) | Aggressive optimization (inlining, loop unroll) |

Key optimizations: **inlining**, **escape analysis**, **dead code elimination**, **loop unrolling**.

--

## Diagnostic Tools

| Tool | Purpose | Example |
|------|---------|---------|
| `jcmd` | Commands to a running JVM | `jcmd <pid> VM.flags` |
| `jps` | List Java processes | `jps -l` |
| `jstat` | GC and memory statistics | `jstat -gc <pid> 1s` |
| `jmap` | Heap dump, class histogram | `jmap -histo <pid>` |
| `jstack` | Thread dump | `jstack <pid>` |
| `jfr` | Low-overhead profiling | `jcmd <pid> JFR.start` |

```bash
java -Xlog:gc* MyApp
java -XX:+HeapDumpOnOutOfMemoryError MyApp
```

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `ClassLoadingOrder` | Static and instance initialization order |
| `MemoryDemo` | Heap sizing via `Runtime`, allocation, `System.gc()` |
| `GCObserver` | GC statistics via `GarbageCollectorMXBean` |

```bash
mvn test -pl 28-jvm-internals
```

--

## Exercises — Your Turn

1. **MemoryAnalyzer** (Guided) — report heap usage with `Runtime`
2. **ClassInspector** (Practice) — extract class metadata via reflection
3. **ObjectSizeEstimator** (Challenge) — estimate sizes with HotSpot heuristics

```bash
mvn test -pl 28-jvm-internals -Dtest="MemoryAnalyzerTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- Classes load **lazily** and initialize static members in a defined order
- The **heap** holds objects; **metaspace** holds class metadata; **stacks** hold per-thread frames
- **Generational GC** exploits that most objects die young, making minor collections fast
- The **JIT compiler** profiles hot methods and compiles them after warmup
- **Diagnostic tools** (`jcmd`, `jstat`, `jfr`, JMX) observe memory, GC, and threads at runtime

Further reading: [JVM Spec Ch. 5](https://docs.oracle.com/javase/specs/jvms/se25/html/jvms-5.html) · [Java Performance (Oaks)](https://www.oreilly.com/library/view/java-performance-2nd/9781492056102/)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 29
## Design Principles and Patterns

--

## Objectives

- Apply SOLID principles to guide class and interface design
- Implement the Strategy, Observer, Factory, and Builder patterns
- Recognize when a pattern solves a specific design problem
- Prefer composition over inheritance for flexible designs

--

## SOLID Principles

| Principle | Name | Summary |
|-----------|------|---------|
| **S** | Single Responsibility | A class should have one reason to change |
| **O** | Open/Closed | Open for extension, closed for modification |
| **L** | Liskov Substitution | Subtypes must be substitutable for base types |
| **I** | Interface Segregation | Clients should not depend on unused interfaces |
| **D** | Dependency Inversion | Depend on abstractions, not concrete classes |

--

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

--

## Open/Closed & Dependency Inversion

**Open/Closed** — add behavior via new classes, not by editing existing ones. Strategy pattern demonstrates this: new pricing rules are new `PricingStrategy` implementations.

**Dependency Inversion** — high-level modules depend on interfaces:

```java
class OrderService {
    private final PaymentGateway gateway; // interface
    OrderService(PaymentGateway gateway) { this.gateway = gateway; }
}
```

--

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

--

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

--

## Factory Pattern

Delegate object creation to a factory, hiding concrete types from clients:

```java
Vehicle vehicle = VehicleFactory.create("car");
// client knows the interface, not Car vs Truck vs Bus
```

Use when: creation logic is complex, or the concrete type should be **decoupled** from the client.

--

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

--

## Patterns at a Glance

| Pattern | Problem solved | Key mechanism |
|---------|----------------|---------------|
| Strategy | Interchangeable algorithms | Interface + composition |
| Observer | One-to-many state change notification | Register/notify callbacks |
| Factory | Decouple creation from usage | Factory method/class |
| Builder | Complex object construction | Fluent step-by-step API |

> Patterns are tools, not goals — apply them when they solve a **real** problem.

--

## Examples in This Chapter

| File | Topic |
|------|-------|
| `StrategyPattern` | Pricing strategies (no/percentage/fixed) |
| `ObserverPattern` | Weather station with temperature observers |
| `BuilderPattern` | Fluent pizza builder |
| `FactoryPattern` | Vehicle factory by type name |

```bash
mvn test -pl 29-design-principles-and-patterns
```

--

## Exercises — Your Turn

1. **NotificationStrategy** (Guided) — email, SMS, and push notification strategies
2. **EventSystem** (Practice) — generic event bus with subscribe/publish
3. **QueryBuilder** (Challenge) — SQL SELECT with optional WHERE, ORDER BY, LIMIT

```bash
mvn test -pl 29-design-principles-and-patterns -Dtest="NotificationStrategyTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

--

## Key Takeaways

- **SOLID principles** guide maintainable design — especially SRP and Dependency Inversion
- **Strategy** swaps algorithms at runtime without modifying the client
- **Observer** decouples event producers from consumers via subscribe/notify
- **Factory** hides concrete types and centralizes creation logic
- **Builder** makes complex object construction readable and safe

Further reading: [GoF Design Patterns](https://www.oreilly.com/library/view/design-patterns-elements/0201633612/) · [Refactoring Guru](https://refactoring.guru/design-patterns)

---

<!-- .slide: data-background-color="#1a5276" -->
# Chapter 30
## Capstone Project — Task Manager CLI

--

## Overview

This capstone brings together concepts from across the course into a complete command-line application.

- **Task Manager** — persists tasks to a file, supports full CRUD
- Follows **layered architecture** principles
- A finished reference implementation — no exercise stubs
- Starting point for your own extensions

--

## Requirements

| Requirement | Description |
|-------------|-------------|
| Create tasks | Add a task with title and optional description |
| List tasks | Display all tasks, optionally filtered by status |
| View details | Show full details for a single task by ID |
| Update status | Change a task's lifecycle status |
| Delete tasks | Remove a task by ID |
| Persistence | Tasks survive restarts via file storage |
| Validation | Reject blank titles and invalid UUIDs/statuses |

--

## Layered Architecture

```
┌─────────────────────────────────────────────┐
│  CLI Layer (TaskManagerCli)                 │
│  Parses commands, formats output            │
├─────────────────────────────────────────────┤
│  Service Layer (TaskService)                │
│  Business logic, validation                 │
├─────────────────────────────────────────────┤
│  Repository Layer (TaskRepository)          │
│  Persistence abstraction                    │
├─────────────────────────────────────────────┤
│  Model Layer (Task, TaskStatus)             │
│  Domain data types                          │
└─────────────────────────────────────────────┘
```

Each layer depends only on the layer **below** it.

--

## Layer Responsibilities

| Layer | Responsibility | Does **not** do |
|-------|----------------|-----------------|
| CLI | Parse commands, format output | Touch files directly |
| Service | Business logic, validation | Parse command-line input |
| Repository | Persist and retrieve tasks | Enforce business rules |
| Model | Domain data types | I/O or user interaction |

This separation makes each layer **independently testable**.

--

## Model Layer

- **`Task`** — immutable record with `id`, `title`, `description`, `status`, `createdAt`
  - Factory method `Task.create()` generates a new pending task
- **`TaskStatus`** — enum: `PENDING`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`

Immutable records simplify reasoning — updates produce **new instances**.

--

## Repository Layer

- **`TaskRepository`** — interface: `save`, `findById`, `findAll`, `findByStatus`, `deleteById`
- **`FileTaskRepository`** — pipe-delimited text file implementation
  - Escapes titles/descriptions containing `|`

Swapping to a database repository requires **no service changes** — only a new implementation.

--

## Service Layer

- **`TaskService`** — orchestrates repository calls
  - Creates tasks, updates statuses
  - Enforces "task not found" errors
  - Validates input before persistence

Depends on `TaskRepository` **interface**, not `FileTaskRepository`.

--

## CLI Layer

- **`TaskManagerCli`** — interactive command loop

| Command | Action |
|---------|--------|
| `add` | Create a new task |
| `list` | List tasks (optional `--status` filter) |
| `show` | View task details by ID |
| `update` | Change task status |
| `delete` | Remove a task |
| `count` / `help` / `exit` | Utility commands |

--

## SOLID in Practice

| Principle | How it appears |
|-----------|----------------|
| Single Responsibility | CLI parses, service validates, repo persists |
| Open/Closed | New repositories implement the interface without changing service |
| Liskov Substitution | `FileTaskRepository` replaces any `TaskRepository` |
| Interface Segregation | `TaskRepository` exposes only persistence methods |
| Dependency Inversion | `TaskService` depends on interface, not file implementation |

--

## Project Structure

```
30-capstone-project/
  src/main/java/course/ch30/
    model/       Task.java, TaskStatus.java
    repository/  TaskRepository.java, FileTaskRepository.java
    service/     TaskService.java
    cli/         TaskManagerCli.java
  src/test/java/course/ch30/
    repository/  FileTaskRepositoryTest.java
    service/     TaskServiceTest.java
```

--

## Build and Test

```bash
cd 30-capstone-project
mvn test
```

Tests cover the repository and service layers in isolation — no CLI interaction required.

--

## Run the Application

```bash
cd 30-capstone-project
mvn compile exec:java -Dexec.mainClass="course.ch30.cli.TaskManagerCli"
```

With an explicit data file:

```bash
mvn compile exec:java -Dexec.mainClass="course.ch30.cli.TaskManagerCli" \
  -Dexec.args="/tmp/my-tasks.txt"
```

Build a runnable JAR:

```bash
mvn package
java -jar target/ch30-capstone-project-1.0.0.jar
```

--

## Example Session

```
Task Manager — type 'help' for commands
> add Buy groceries --desc Milk and eggs
Created: [a1b2c3d4-...] Buy groceries (PENDING)
> list
[a1b2c3d4-...] Buy groceries (PENDING)
> update a1b2c3d4-... IN_PROGRESS
Updated: [a1b2c3d4-...] Buy groceries (IN_PROGRESS)
> list --status IN_PROGRESS
[a1b2c3d4-...] Buy groceries (IN_PROGRESS)
> count
Total tasks: 1
> exit
Goodbye.
```

--

## Extensions — Make It Your Own

| Extension | Concepts practiced |
|-----------|-------------------|
| Due dates and priorities | `LocalDate`, `Comparable`, sorting |
| JSON persistence | I/O (Ch. 15), text processing (Ch. 8) |
| Search by keyword | Streams API (Ch. 12), filtering |
| Undo/redo | Command pattern (Ch. 29), stack data structure |
| Configuration file | Properties, packages (Ch. 19) |
| Concurrent access | File locking, synchronized methods (Ch. 16) |
| Web API version | HTTP server (Ch. 25), service layer reuse |
| Unit test coverage | JUnit parameterized tests (Ch. 21) |

--

## Key Takeaways

- **Layered architecture** separates concerns and makes each component testable in isolation
- **Interfaces** (`TaskRepository`) enable swapping implementations without changing business logic
- **Immutable records** (`Task`) simplify reasoning about state
- A complete application combines language features, design patterns, I/O, testing, and build tooling
- This project is a **foundation** — extend it to make it your own

Full lesson: [`README.md`](README.md)
