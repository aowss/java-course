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
- Recognize comments and **Javadoc** in source code

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

**JDK** (develop and run) → **JRE** (runtime libraries) → **JVM** (executes bytecode)

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

**Source** (.java) → **javac** → **Bytecode** (.class) → **JVM + JIT** → **Program output**

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

## Comments and Javadoc

Comments can appear anywhere in a source file:

| Style | Syntax | Compiler | Javadoc tool |
|-------|--------|----------|--------------|
| End-of-line | `//` … | Ignored | No |
| Block | `/*` … `*/` | Ignored | No |
| **Javadoc** | `/**` … `*/` | Ignored | **Included** in generated docs |

```java
// end-of-line comment
/* block comment */
/**
 * Javadoc comment — documents classes and members.
 */
```

> Inside `/*` … `*/`, `//` and nested `/*` have no special meaning. Comments do not nest.

---

## Generating Javadoc

Document **public** APIs for other developers — see `HelloWorld` and `CommentedHelloWorld`:

```java
/**
 * Greets the user by name.
 *
 * @param name the person to greet
 * @return a greeting string
 */
public static String greet(String name) { ... }
```

| Tag | Use |
|-----|-----|
| `@param` | Method parameter |
| `@return` | Return value |
| `@throws` | Exception a method may throw |

```bash
javadoc -d target/javadoc -sourcepath src/main/java course.ch01.examples
```

Open `target/javadoc/index.html` — same style as the [JDK API documentation](https://docs.oracle.com/en/java/javase/25/docs/api/).

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

## A Simpler Start (Java 25)

Since Java 25, a **compact source file** can omit the class and boilerplate `main`:

```java
void main() {
    System.out.println("Hello, World!");
}
```

Run directly from the source file:

```bash
cd 01-introduction-to-java/compact-examples
java HelloWorld.java
```

**What happens behind the scenes**

1. The `java` launcher **compiles** the source to bytecode (no separate `javac` step)
2. With no class declaration, the compiler creates an **implicit class** wrapping your code
3. The launcher invokes the instance `main` method on a new object
4. The JVM **loads and executes** that bytecode — same interpret + JIT pipeline as before

> The previous slides show the **explicit** form used in real projects and throughout this course. Compact files must live in the **unnamed package**, so they cannot replace our Maven layout.

---

## Examples

| File | Topic |
|------|-------|
| `HelloWorld` | Minimal program — class, main, println |
| `JvmInfo` | Querying JVM properties at runtime |

```bash
mvn test -pl 01-introduction-to-java
```

---

## Exercises

1. **Greeting** (Guided) — print `Hello, <name>!` from command-line args
2. **System Reporter** (Practice) — return Java version, OS name, and architecture

```bash
mvn test -pl 01-introduction-to-java -Dtest="GreetingTest"
```



---

## Key Takeaways

- Java source compiles to platform-independent **bytecode** executed by the **JVM**
- The **JDK** includes the compiler (`javac`) and the runtime
- Real projects use `public static void main(String[] args)` in named classes
- Java 25 **compact source files** can use `void main()` and `java HelloWorld.java` — still bytecode on the JVM

Full lesson: [`README.md`](README.md)
Further reading: [JLS §7.6](https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html#jls-7.6) · [Oracle Getting Started](https://docs.oracle.com/en/java/javase/25/docs/api/)
