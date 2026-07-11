# Java Course — Table of Contents

## Part I: Foundations

### 1. Introduction to Java

- History and philosophy
- JDK, JRE, JVM — what they are and how they relate
- Installing Java and setting up an IDE
- Hello World — compile, run, understand

### 2. Language Basics

- Primitive types and literals
- Variables, constants (`final`), and type inference (`var`)
- Operators and expressions
- Control flow: `if`, `switch`, loops (`for`, `while`, `do-while`)
- Arrays

### 3. Methods

- Defining and calling methods
- Parameters, return types, overloading
- Variable arguments (varargs)
- Stack and heap — memory model for method calls and objects
- Scope and lifetime

## Part II: Object-Oriented Programming

### 4. Classes and Objects

- Fields, constructors, methods
- `this` keyword
- Access modifiers (`private`, `protected`, `public`, package-private)
- Static members

### 5. Inheritance and Polymorphism

- Extending classes, `super`
- Method overriding and dynamic dispatch
- Abstract classes
- The `Object` class and its key methods (`equals`, `hashCode`, `toString`)

### 6. Interfaces

- Defining and implementing interfaces
- Default and static methods
- Functional interfaces and `@FunctionalInterface`
- Interface evolution (private methods)

### 7. Encapsulation and Data Modeling

- Records
- Sealed classes and interfaces
- Enums (with fields, methods, and abstract methods)
- Pattern matching for `instanceof` and `switch`

## Part III: Core APIs and Language Features

### 8. Exception Handling

- Checked vs. unchecked exceptions
- The `throws` clause (including optional declaration of runtime exceptions)
- `try-catch-finally`, try-with-resources
- Custom exceptions
- Best practices (Effective Java)

### 9. Packages and Modules

- Package naming and organization
- The module system (JPMS): `module-info.java`
- Exports, requires, services

### 10. Strings and Text

- `String`, `StringBuilder`, `StringBuffer`
- Text blocks
- Formatting and regular expressions

### 11. Generics

- Generic classes and methods
- Bounded type parameters
- Wildcards (`?`, `? extends`, `? super`)
- Type erasure and limitations

### 12. Collections Framework

- `List`, `Set`, `Map`, `Queue`, `Deque`
- Implementations: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `TreeMap`
- `Collections` and `Arrays` utility classes
- Iterators and the enhanced `for` loop
- Sequenced collections (Java 21+)

### 13. Functional Programming

- Lambda expressions
- Method references
- `java.util.function` — `Predicate`, `Function`, `Consumer`, `Supplier`
- Composing and chaining functions

### 14. Streams API

- Creating streams
- Intermediate operations: `filter`, `map`, `flatMap`, `sorted`, `distinct`
- Terminal operations: `collect`, `reduce`, `forEach`, `count`
- Collectors: `toList`, `groupingBy`, `partitioningBy`, `joining`
- Parallel streams — when and when not to use them

### 15. The Optional Type

- Creating and unwrapping
- Chaining with `map`, `flatMap`, `filter`, `or`
- Anti-patterns to avoid

## Part IV: I/O

### 16. I/O and NIO

- Byte streams and character streams
- `Path`, `Files`, `FileSystem` (NIO.2)
- Reading and writing files
- Serialization basics

## Part V: Concurrency

### 17. Threads and Synchronization

- Creating threads (`Thread`, `Runnable`)
- Thread lifecycle
- `synchronized`, `volatile`, locks
- Common pitfalls: deadlock, livelock, starvation

### 18. Concurrency Utilities

- `ExecutorService` and thread pools
- `Future`, `CompletableFuture`
- Concurrent collections (`ConcurrentHashMap`, `CopyOnWriteArrayList`)
- Atomic variables

### 19. Virtual Threads (Project Loom)

- Motivation and mental model
- Creating and using virtual threads
- Structured concurrency
- Migration considerations

## Part VI: Build Tools

### 20. Build Tools

- Maven — POM, lifecycle, dependency management
- Gradle — build scripts, tasks, plugins
- Managing dependencies and reproducible builds

## Part VII: Testing

### 21. Unit Testing with JUnit 5

- Writing and organizing tests
- Assertions and assumptions
- Parameterized tests
- Lifecycle hooks (`@BeforeEach`, `@AfterAll`, etc.)

### 22. Mocking and Integration Testing

- Mockito fundamentals
- Test doubles: mocks, stubs, spies
- Integration testing strategies

## Part VIII: Advanced Topics

### 23. Annotations and Reflection

- Built-in annotations (`@Override`, `@Deprecated`, `@SuppressWarnings`)
- Custom annotations
- Reflection API — uses and dangers

### 24. Date and Time API (`java.time`)

- `LocalDate`, `LocalTime`, `LocalDateTime`, `ZonedDateTime`
- `Duration`, `Period`, `Instant`
- Formatting and parsing

### 25. Networking Basics

- Sockets and `ServerSocket`
- The HTTP Client API (`java.net.http`)
- Making REST calls

### 26. Database Access with JDBC

- Connections, statements, result sets
- Prepared statements and SQL injection prevention
- Connection pooling concepts
- Transactions

### 27. Modern Java Features Roundup (Java 17–21+)

- Pattern matching for `switch` (exhaustiveness, guards)
- String templates (preview)
- Unnamed variables and patterns
- Foreign Function & Memory API (overview)

### 28. JVM Internals

- Class loading lifecycle (load, link, initialize)
- Memory areas in depth: stack, heap, metaspace, program counter
- Garbage collection (generational GC, GC roots, pauses)
- JIT compilation (tiered compilation, C1/C2)
- Diagnostic tools (`jcmd`, `jfr`, `jmap`, `jstack`)

## Part IX: Putting It All Together

### 29. Design Principles and Patterns

- SOLID principles
- Common patterns: Strategy, Observer, Factory, Builder, Singleton
- When patterns help and when they hurt

### 30. Capstone Project

- Requirements and design
- Implementation walkthrough
- Testing, packaging, and delivery

## Appendices

Optional reference material — read when you want more depth on a topic already introduced in the main chapters.

### A. Operator Precedence and Evaluation

- Operand evaluation order and short-circuit rules
- Precedence table and numeric promotion
- Tricky expression samples
- Related: Chapter 2

## In-Class Quizzes

Optional formative quizzes for live sessions (Parts I–II). Answers are in collapsible sections in the written quizzes — or press **↓** in the [online quiz deck](https://aowss.github.io/java-course/quizzes/). See [`quizzes/`](quizzes/README.md).

| Part | Chapters |
|------|----------|
| Part I: Foundations | [Ch. 1](quizzes/01-introduction-to-java.md) · [Ch. 2](quizzes/02-language-basics.md) · [Ch. 3](quizzes/03-methods.md) |
| Part II: OOP | [Ch. 4](quizzes/04-classes-and-objects.md) · [Ch. 5](quizzes/05-inheritance-and-polymorphism.md) · [Ch. 6](quizzes/06-interfaces.md) · [Ch. 7](quizzes/07-encapsulation-and-data-modeling.md) |
