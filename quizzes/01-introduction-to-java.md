# Quiz — Chapter 1: Introduction to Java

**Part I: Foundations** · ~10 minutes

---

### 1. Concept check

Which download do you need on your machine to **compile** Java source code into bytecode?

- A) JVM only
- B) JRE only
- C) JDK
- D) Any text editor

<details>
<summary>Show answer</summary>

**C) JDK**

The JDK includes the compiler (`javac`) plus the tools to run and package programs. The JRE (or a JRE-equivalent runtime inside the JDK) is enough to *run* bytecode, but not to compile `.java` files.

</details>

---

### 2. Concept check

Why is Java often described as both **compiled** and **interpreted**?

<details>
<summary>Show answer</summary>

`javac` compiles source to **bytecode** ahead of time (compiled step). The **JVM** executes that bytecode at runtime, initially by interpretation and later by **JIT** compilation of hot paths (interpreted/JIT step). It is not a single pure strategy like C (native compile only) or classic Python (source interpret only).

</details>

---

### 3. Concept check

What is the main reason bytecode is **platform-independent** while the JVM is **platform-specific**?

<details>
<summary>Show answer</summary>

Bytecode is a standardized intermediate format — the same `.class` file can run on any OS that has a JVM. The JVM itself is implemented separately for each platform (Windows, macOS, Linux, …) and translates bytecode to that platform's native environment.

</details>

---

### 4. Spot the bug

A student saves this file as `Hello.java` and runs `javac Hello.java`, but compilation fails. What is wrong?

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

<details>
<summary>Show answer</summary>

In Java, the **public class name must match the filename**. The file must be renamed to `HelloWorld.java`, or the class must be renamed to `Hello`.

</details>

---

### 5. Predict the output

Assume this compiles and runs. What prints?

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(args.length);
    }
}
```

Run with: `java Main` (no arguments)

<details>
<summary>Show answer</summary>

**`0`**

`args` is always a `String` array. With no command-line arguments, its length is 0. It is never `null`.

</details>

---

### 6. True or false

> Since Java 11, Oracle distributes the JRE as a separate download from the JDK.

<details>
<summary>Show answer</summary>

**False.**

Since Java 11, the JRE is no longer shipped as a standalone product. You download the JDK; it includes what you need to run Java programs.

</details>

---

### 7. Short answer

Name **two** design principles from Java's original philosophy (from the chapter).

<details>
<summary>Show answer</summary>

Any two of:

- **Write once, run anywhere** (portable bytecode + JVM)
- **Object-oriented** (everything except primitives is an object)
- **Strongly typed** (compile-time type checking)
- **Memory-managed** (garbage collection)

</details>
