---
marp: true
theme: default
paginate: true
header: 'Java Course — Quiz'
footer: 'Chapter 1: Introduction to Java'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  section.answer { background: #e8f6ef; }
  section.answer h2 { color: #1e5631; }
  code { font-size: 0.85em; }
---


# Quiz
## Chapter 1: Introduction to Java

Part I: Foundations · ~10 minutes

---

## Q1 · Concept check

Which download do you need on your machine to **compile** Java source code into bytecode?

- A) JVM only
- B) JRE only
- C) JDK
- D) Any text editor

---

<!-- _class: answer -->

## Answer — Q1

**C) JDK**

The JDK includes the compiler (`javac`) plus the tools to run and package programs. The JRE (or a JRE-equivalent runtime inside the JDK) is enough to *run* bytecode, but not to compile `.java` files.

---

## Q2 · Concept check

Why is Java often described as both **compiled** and **interpreted**?

---

<!-- _class: answer -->

## Answer — Q2

`javac` compiles source to **bytecode** ahead of time (compiled step). The **JVM** executes that bytecode at runtime, initially by interpretation and later by **JIT** compilation of hot paths (interpreted/JIT step). It is not a single pure strategy like C (native compile only) or classic Python (source interpret only).

---

## Q3 · Concept check

What is the main reason bytecode is **platform-independent** while the JVM is **platform-specific**?

---

<!-- _class: answer -->

## Answer — Q3

Bytecode is a standardized intermediate format — the same `.class` file can run on any OS that has a JVM. The JVM itself is implemented separately for each platform (Windows, macOS, Linux, …) and translates bytecode to that platform's native environment.

---

## Q4 · Spot the bug

A student saves this file as `Hello.java` and runs `javac Hello.java`, but compilation fails. What is wrong?

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

---

<!-- _class: answer -->

## Answer — Q4

In Java, the **public class name must match the filename**. The file must be renamed to `HelloWorld.java`, or the class must be renamed to `Hello`.

---

## Q5 · Predict the output

Assume this compiles and runs. What prints?

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(args.length);
    }
}
```

Run with: `java Main` (no arguments)

---

<!-- _class: answer -->

## Answer — Q5

**`0`**

`args` is always a `String` array. With no command-line arguments, its length is 0. It is never `null`.

---

## Q6 · True or false

> Since Java 11, Oracle distributes the JRE as a separate download from the JDK.

---

<!-- _class: answer -->

## Answer — Q6

**False.**

Since Java 11, the JRE is no longer shipped as a standalone product. You download the JDK; it includes what you need to run Java programs.

---

## Q7 · Short answer

Name **two** design principles from Java's original philosophy (from the chapter).

---

<!-- _class: answer -->

## Answer — Q7

Any two of:

- **Write once, run anywhere** (portable bytecode + JVM)
- **Object-oriented** (everything except primitives is an object)
- **Strongly typed** (compile-time type checking)
- **Memory-managed** (garbage collection)

---
