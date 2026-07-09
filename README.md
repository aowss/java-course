# Java Course

A practical, example-driven Java course from language foundations to advanced topics. Each chapter contains concise explanations, runnable examples, and test-driven exercises.

See the [Table of Content](Table%20of%20Content.md) for the full curriculum and the [Approach](Approach.md) for the design rationale.

## Prerequisites

### Java Development Kit (JDK) 25

This course targets **Java 25**, the latest Long-Term Support (LTS) release.

**Download:**

- [Oracle JDK 25](https://www.oracle.com/java/technologies/downloads/) — free for development and production under the Oracle No-Fee Terms and Conditions license.
- [Eclipse Temurin 25](https://adoptium.net/temurin/releases/?version=25) — open-source alternative from the Adoptium project.

**Install:**

| OS      | Recommended method                                                   |
|---------|----------------------------------------------------------------------|
| macOS   | `brew install --cask temurin@25` or download the `.dmg` from Oracle  |
| Linux   | Package manager (`apt`, `dnf`, `pacman`) or download the `.tar.gz`   |
| Windows | Download the `.msi` installer from Oracle                            |

**Verify your installation:**

```bash
java -version
```

You should see output containing `version "25"` (the exact patch version may vary):

```
java version "25.0.3" 2026-04-15
Java(TM) SE Runtime Environment (build 25.0.3+6-30)
Java HotSpot(TM) 64-Bit Server VM (build 25.0.3+6-30, mixed mode, sharing)
```

Also verify the compiler:

```bash
javac -version
```

### Apache Maven

This course uses **Maven** as its build system. Any version 3.9+ will work (latest is 3.9.16).

**Download:**

- [Apache Maven](https://maven.apache.org/download.cgi) — download the binary `.tar.gz` (Linux/macOS) or `.zip` (Windows).

**Install:**

| OS      | Recommended method                                                       |
|---------|--------------------------------------------------------------------------|
| macOS   | `brew install maven`                                                     |
| Linux   | Package manager or download and extract manually                         |
| Windows | Download the `.zip`, extract, and add the `bin` directory to your `PATH` |

**Manual installation (all platforms):**

1. Download and extract the archive.
2. Set the `M2_HOME` environment variable to the extracted directory.
3. Add `$M2_HOME/bin` (or `%M2_HOME%\bin` on Windows) to your `PATH`.

**Verify your installation:**

```bash
mvn -version
```

You should see output showing Maven's version and the JDK it is using:

```
Apache Maven 3.9.16
Maven home: /path/to/maven
Java version: 25.0.3, vendor: Oracle Corporation
```

> Make sure the `Java version` line shows Java 25. If it shows an older version, Maven is picking up a different JDK. Set `JAVA_HOME` to point to your JDK 25 installation.

### IDE (optional but recommended)

Any Java IDE will work. Popular choices:

- [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/) — free, excellent Java support.
- [Visual Studio Code](https://code.visualstudio.com/) with the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
- [Eclipse IDE](https://www.eclipse.org/downloads/).

## Maven Quick Reference

This course is a **multi-module Maven project**. The root `pom.xml` defines shared configuration, and each chapter is a module with its own `pom.xml`.

### Common commands

**Build the entire course:**

```bash
mvn compile
```

**Build a single chapter:**

```bash
mvn compile -pl 03-methods
```

**Run example tests in a chapter:**

```bash
mvn test -pl 03-methods
```

Exercise tests are excluded from the default run — use `-Dtest` to run one explicitly (see step 3).

**Run a specific test class:**

```bash
mvn test -pl 03-methods -Dtest="MathUtilsTest"
```

**Run a specific test method:**

```bash
mvn test -pl 03-methods -Dtest="MathUtilsTest#factorialOfFive"
```

**Clean build artifacts:**

```bash
mvn clean
```

### Understanding the output

When you run `mvn test`, Maven compiles sources and runs **example** tests only.

A passing test run looks like:

```
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

When you run an exercise test with `-Dtest`, failures are expected until you implement the `// TODO` methods:

```
[ERROR] Tests run: 4, Failures: 0, Errors: 3, Skipped: 0
[ERROR] BUILD FAILURE
```

The errors will show the test name and the exception — typically `UnsupportedOperationException: Not yet implemented` until you fill in the `// TODO` methods.

## How to Use This Course

### 1. Read the chapter

Open the chapter's `README.md`. Read the objectives and concepts.

### 2. Study the examples

Look at the source files in `src/main/java/.../examples/`. Run them to see the output:

```bash
mvn compile -pl 02-language-basics
java -cp 02-language-basics/target/classes course.ch02.examples.PrimitiveTypes
```

### 3. Work through the exercises

Open the exercise files in `src/main/java/.../exercises/`. Each has `// TODO` markers where you write your implementation. Run the tests to check your work:

```bash
mvn test -pl 02-language-basics -Dtest="FizzBuzzTest"
```

Keep iterating until all tests pass.

### 4. Check the solutions

If you get stuck, look at the `solutions/` folder inside the chapter. Try to solve the exercise yourself first — you learn more from the struggle.

### 5. Move to the next chapter

The chapters build on each other. Follow the numbered order.
