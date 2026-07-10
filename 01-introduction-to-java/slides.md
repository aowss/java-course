---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 1'
footer: 'Introduction to Java'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 1
## Introduction to Java

---

## Objectives

- Understand Java's design philosophy and where it fits
- Distinguish compiled vs interpreted — and why Java is **both**
- Know the roles of **JDK**, **JRE**, and **JVM**
- Compile and run a program from the command line
- Trace the path from source code → bytecode → JVM

---

## Java's Core Principles

Created by James Gosling at Sun Microsystems (1995):

- **Write once, run anywhere** — bytecode runs on any JVM
- **Object-oriented** — everything (except primitives) is an object
- **Strongly typed** — compiler catches type errors early
- **Memory-managed** — garbage collector handles allocation

---

## JDK, JRE, JVM

| Component | Purpose | Contains |
|-----------|---------|----------|
| **JVM** | Executes bytecode | Interpreter, JIT, GC |
| **JRE** | Run Java programs | JVM + standard library |
| **JDK** | Develop Java programs | JRE + `javac`, `jshell`, debugger |

> Since Java 11, only the **JDK** is distributed — no separate JRE download.

---

## Compiled vs Interpreted

| Strategy | How it works | Examples |
|----------|--------------|----------|
| **Compiled** | Source → machine code *before* run | C, C++, Rust, Go |
| **Interpreted** | Source executed line-by-line at runtime | Python, Ruby, JS |

Java does **both** — ahead-of-time compilation *and* runtime interpretation.

---

## Java's Two-Stage Model

1. **`javac`** compiles `.java` → `.class` bytecode (ahead-of-time)
2. **JVM** interprets bytecode at runtime
3. **JIT compiler** compiles hot paths to native machine code

**Result:** early error checking + platform independence + near-native speed.

---

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

---

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

---

## Compile and Run

```bash
javac src/main/java/course/ch01/examples/HelloWorld.java
java -cp src/main/java course.ch01.examples.HelloWorld
```

Or with Maven:

```bash
mvn compile exec:java -Dexec.mainClass="course.ch01.examples.HelloWorld"
```

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `HelloWorld` | Minimal program — class, main, println |
| `JvmInfo` | Querying JVM properties at runtime |

```bash
mvn test -pl 01-introduction-to-java
```

---

## Exercises — Your Turn

1. **Greeting** (Guided) — print `Hello, <name>!` from command-line args
2. **System Reporter** (Practice) — return Java version, OS name, and architecture

```bash
mvn test -pl 01-introduction-to-java -Dtest="GreetingTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Java source compiles to platform-independent **bytecode** executed by the **JVM**
- The **JDK** includes the compiler (`javac`) and the runtime
- Every program starts at `public static void main(String[] args)`
- File name must match the public class name

Further reading: [JLS §7.6](https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html#jls-7.6) · [Oracle Getting Started](https://docs.oracle.com/en/java/javase/25/docs/api/)
