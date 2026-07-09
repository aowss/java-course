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

### 8. Strings and Text

- `String`, `StringBuilder`, `StringBuffer`
- Text blocks
- Formatting and regular expressions

### 9. Generics

- Generic classes and methods
- Bounded type parameters
- Wildcards (`?`, `? extends`, `? super`)
- Type erasure and limitations

### 10. Collections Framework

- `List`, `Set`, `Map`, `Queue`, `Deque`
- Implementations: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `HashMap`, `TreeMap`
- `Collections` and `Arrays` utility classes
- Iterators and the enhanced `for` loop
- Sequenced collections (Java 21+)

### 11. Functional Programming

- Lambda expressions
- Method references
- `java.util.function` — `Predicate`, `Function`, `Consumer`, `Supplier`
- Composing and chaining functions

### 12. Streams API

- Creating streams
- Intermediate operations: `filter`, `map`, `flatMap`, `sorted`, `distinct`
- Terminal operations: `collect`, `reduce`, `forEach`, `count`
- Collectors: `toList`, `groupingBy`, `partitioningBy`, `joining`
- Parallel streams — when and when not to use them

### 13. The Optional Type

- Creating and unwrapping
- Chaining with `map`, `flatMap`, `filter`, `or`
- Anti-patterns to avoid

## Part IV: Error Handling and I/O

### 14. Exception Handling

- Checked vs. unchecked exceptions
- `try-catch-finally`, try-with-resources
- Custom exceptions
- Best practices

### 15. I/O and NIO

- Byte streams and character streams
- `Path`, `Files`, `FileSystem` (NIO.2)
- Reading and writing files
- Serialization basics

## Part V: Concurrency

### 16. Threads and Synchronization

- Creating threads (`Thread`, `Runnable`)
- Thread lifecycle
- `synchronized`, `volatile`, locks
- Common pitfalls: deadlock, livelock, starvation

### 17. Concurrency Utilities

- `ExecutorService` and thread pools
- `Future`, `CompletableFuture`
- Concurrent collections (`ConcurrentHashMap`, `CopyOnWriteArrayList`)
- Atomic variables

### 18. Virtual Threads (Project Loom)

- Motivation and mental model
- Creating and using virtual threads
- Structured concurrency
- Migration considerations

## Part VI: Modules, Packages, and the Build Ecosystem

### 19. Packages and Modules

- Package naming and organization
- The module system (JPMS): `module-info.java`
- Exports, requires, services

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

## Part IX: Putting It All Together

### 28. Design Principles and Patterns

- SOLID principles
- Common patterns: Strategy, Observer, Factory, Builder, Singleton
- When patterns help and when they hurt

### 29. Capstone Project

- Requirements and design
- Implementation walkthrough
- Testing, packaging, and delivery

---

# Design Rationale

## Sources

This table of contents is synthesized from patterns across the Java education ecosystem, including:

- **Oracle's official Java Tutorials and JLS** — the canonical progression Oracle uses (basics → OOP → APIs → concurrency) heavily influences the overall arc.
- **Widely-used Java textbooks** — *Core Java* (Horstmann), *Effective Java* (Bloch), *Head First Java*, *Java Concurrency in Practice* (Goetz), *Modern Java in Action* — these books collectively establish a "standard" teaching order.
- **OCA/OCP certification objectives** — the Java certification exam topics provide a well-vetted checklist of what a Java developer should know, and in what depth.
- **University CS curricula** — typical CS2/CS3 Java courses at universities follow a similar foundations → OOP → data structures → advanced arc.
- **Online courses** (Coursera, Udemy, Pluralsight) — popular Java courses tend to converge on a similar structure.
- **JEP history and release notes** — knowing which features landed in which version (records in 16, sealed in 17, virtual threads in 21) informed where modern features got placed.

## Key Design Decisions

### 1. Nine parts instead of fewer, larger ones

The split into 9 parts creates natural "milestone" boundaries. A student finishing Part II knows OOP. Finishing Part V knows concurrency. This makes the course modular — you could teach Parts I–IV as a "fundamentals" course and V–IX as "advanced."

### 2. OOP gets its own dedicated Part (II) rather than being folded into "basics"

OOP is *the* core paradigm of Java. Lumping it with variables and loops would underweight it. Giving it a full part signals: "this is where you spend real time."

### 3. Functional Programming, Streams, and Optional are three separate chapters (not one)

Students frequently conflate lambdas, streams, and Optional because they arrived together in Java 8. Separating them makes each concept digestible on its own. You learn lambdas → then you see streams as a *consumer* of lambdas → then Optional as a related but distinct tool.

### 4. Records, sealed classes, and pattern matching live in Part II (OOP), not in a "Modern Java" appendix

Teach them *where they belong conceptually* rather than as novelties. Records are a data modeling tool — they belong next to classes. Sealed classes constrain inheritance — they belong next to inheritance. This normalizes modern features instead of treating them as add-ons.

### 5. Testing comes late (Part VII) rather than early

This is debatable. A TDD-first approach would introduce JUnit in Part I. The reasoning here was: you need enough language surface (OOP, exceptions, collections) before testing is meaningful. But this is one of the weakest decisions in the TOC — there's a strong argument for introducing testing incrementally alongside each part.

### 6. Virtual Threads get their own chapter, not a subsection

Virtual threads are a paradigm shift in how Java handles concurrency, not just another API in `java.util.concurrent`. Treating them as a subsection would understate their importance and the mental model change they require.

### 7. Concurrency comes after I/O (Part V after Part IV)

Because concurrency's motivating examples often involve I/O-bound work (reading files, making network calls). Having I/O first gives you concrete scenarios for "why would I want another thread?"

### 8. What's deliberately excluded: Spring, web frameworks, microservices, ORMs in depth

The scope is *the Java language and its standard library*. Spring Boot gets a mention under Networking, JPA/Hibernate are absent. The reasoning: those are ecosystem courses, not language courses. Mixing them in dilutes the core material and dates the course faster.

### 9. The "Modern Java Features Roundup" chapter (Part VIII) exists despite decision #4

This seems contradictory — if modern features are already woven in, why a roundup? Because some features (string templates, FFM API, unnamed variables) don't fit neatly into an earlier chapter's topic. The roundup catches those stragglers and serves as a "what's new" reference for experienced developers who skip to it.

### 10. Capstone Project at the end

Synthesis matters. Without it, the course is a reference manual. With it, students prove to themselves they can build something real by combining what they've learned.
