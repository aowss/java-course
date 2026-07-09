# Chapter 1: Introduction to Java

## Objectives

- Understand Java's design philosophy and where it fits in the programming landscape
- Distinguish between the JDK, JRE, and JVM
- Compile and run a Java program from the command line
- Understand the relationship between source code, bytecode, and the JVM

## Concepts

### History and Philosophy

Java was created by James Gosling at Sun Microsystems in 1995. Its core design principles are:

- **Write once, run anywhere** — Java compiles to bytecode that runs on any JVM, regardless of the underlying OS or hardware.
- **Object-oriented** — everything (except primitives) is an object.
- **Strongly typed** — the compiler catches type errors before the program runs.
- **Memory-managed** — the garbage collector handles memory allocation and deallocation.

### JDK, JRE, JVM

| Component | What it is | Contains |
|-----------|-----------|----------|
| **JVM** (Java Virtual Machine) | The runtime engine that executes bytecode | Interpreter, JIT compiler, garbage collector |
| **JRE** (Java Runtime Environment) | Everything needed to *run* Java programs | JVM + standard library (rt.jar / modules) |
| **JDK** (Java Development Kit) | Everything needed to *develop* Java programs | JRE + `javac`, `jshell`, `jpackage`, debugger, etc. |

> Since Java 11, the JRE is no longer distributed separately. The JDK is the only download.

### Compilation and Execution

```
  Source code (.java)
        │
        ▼
    javac (compiler)
        │
        ▼
  Bytecode (.class)
        │
        ▼
    java (JVM)
        │
        ▼
   Program output
```

1. You write source code in `.java` files.
2. `javac` compiles source code into `.class` files containing bytecode.
3. `java` launches the JVM, which loads and executes the bytecode.

Bytecode is platform-independent. The JVM is platform-specific. This is the mechanism behind "write once, run anywhere."

### Your First Program

See [`HelloWorld.java`](src/main/java/course/ch01/examples/HelloWorld.java):

```java
package course.ch01.examples;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Every Java program needs:
- A `class` declaration — the file name must match the class name.
- A `main` method with the exact signature `public static void main(String[] args)` — this is the entry point.
- `System.out.println` writes to standard output.

**Compile and run from the command line:**

```bash
javac src/main/java/course/ch01/examples/HelloWorld.java
java -cp src/main/java course.ch01.examples.HelloWorld
```

Or use Maven:

```bash
mvn compile exec:java -Dexec.mainClass="course.ch01.examples.HelloWorld"
```

## Examples

| File | Demonstrates |
|------|-------------|
| [`HelloWorld.java`](src/main/java/course/ch01/examples/HelloWorld.java) | Minimal Java program — class, main method, println |
| [`JvmInfo.java`](src/main/java/course/ch01/examples/JvmInfo.java) | Querying JVM properties at runtime |

## Exercises

### Exercise 1: Greeting (Guided)

**File:** [`Greeting.java`](src/main/java/course/ch01/exercises/Greeting.java)

Write a program that prints `Hello, <name>!` where `<name>` is passed as a command-line argument. If no argument is provided, print `Hello, World!`.

Run the tests to verify:

```bash
mvn test -Dtest="course.ch01.exercises.GreetingTest"
```

### Exercise 2: System Reporter (Practice)

**File:** [`SystemReporter.java`](src/main/java/course/ch01/exercises/SystemReporter.java)

Write a method that returns a formatted string containing the Java version, OS name, and OS architecture. The exact format is defined by the tests.

```bash
mvn test -Dtest="course.ch01.exercises.SystemReporterTest"
```

## Key Takeaways

- Java source code is compiled to platform-independent **bytecode**, which the **JVM** executes.
- The **JDK** is the development kit; it includes the compiler (`javac`) and the runtime.
- Every Java program starts at `public static void main(String[] args)`.
- The file name must match the public class name.

## Further Reading

- [JLS §7.6 — Top Level Type Declarations](https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html#jls-7.6)
- [Oracle: Getting Started](https://docs.oracle.com/en/java/javase/25/docs/api/)
- [JEP 458: Launch Multi-File Source-Code Programs](https://openjdk.org/jeps/458)
