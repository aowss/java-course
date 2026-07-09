# Chapter 20: Build Tools

## Objectives

- Understand why build tools are essential for Java projects
- Learn the structure of a Maven POM file and the role of each element
- Master Maven's build lifecycle: phases, plugins, and goals
- Understand dependency management: scopes, transitive dependencies, and the BOM pattern
- Compare Maven and Gradle at a conceptual level
- Understand reproducible builds and dependency locking

## Concepts

### Why Build Tools?

Compiling a single Java file with `javac` works fine, but real projects have hundreds of source files, external libraries, test suites, resource files, and packaging requirements. Build tools automate:

- **Compilation** — compiling all source files in the correct order
- **Dependency management** — downloading and managing external libraries
- **Testing** — running unit and integration tests
- **Packaging** — creating JARs, WARs, or other distributable formats
- **Lifecycle management** — defining a repeatable, ordered build process

### Maven

Maven is the most widely used Java build tool. It follows a **convention-over-configuration** philosophy: if you follow the standard project layout, Maven knows what to do without explicit instructions.

#### POM Structure

The **Project Object Model** (`pom.xml`) is Maven's configuration file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- Project coordinates — uniquely identify this artifact -->
    <groupId>com.example</groupId>
    <artifactId>my-application</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <!-- Inherit configuration from a parent POM -->
    <parent>
        <groupId>com.example</groupId>
        <artifactId>my-parent</artifactId>
        <version>1.0.0</version>
    </parent>

    <!-- Centralized version management -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.release>25</maven.compiler.release>
        <junit.version>6.1.1</junit.version>
    </properties>

    <!-- Dependencies this project needs -->
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <!-- Build plugin configuration -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.15.0</version>
            </plugin>
        </plugins>
    </build>
</project>
```

#### Key POM Elements

| Element                  | Purpose                                                              | Example                                        |
|--------------------------|----------------------------------------------------------------------|-------------------------------------------------|
| `<groupId>`              | Organization identifier (reverse domain)                             | `com.example`                                   |
| `<artifactId>`           | Project name                                                         | `my-application`                                |
| `<version>`              | Project version                                                      | `1.0.0`                                         |
| `<packaging>`            | Output format                                                        | `jar`, `war`, `pom`                             |
| `<parent>`               | Inherit configuration from a parent POM                              | See example above                               |
| `<properties>`           | Define reusable variables                                            | `<junit.version>6.1.1</junit.version>`          |
| `<dependencies>`         | Declare external libraries                                           | See example above                               |
| `<dependencyManagement>` | Centralize dependency versions without adding them to the classpath  | Used in parent POMs and BOMs                    |
| `<build>`                | Configure plugins and build behavior                                 | Compiler settings, Surefire config              |
| `<modules>`              | List sub-modules in a multi-module project                           | `<module>core</module>`                         |

#### Maven Build Lifecycle

Maven organizes its build process into **lifecycles**, each composed of **phases** that execute in order. When you invoke a phase, all preceding phases run first.

##### Default Lifecycle

| Phase       | Description                                                          | Typical Plugin                     |
|-------------|----------------------------------------------------------------------|------------------------------------|
| `validate`  | Validate project structure and configuration                         | (built-in)                         |
| `compile`   | Compile source code (`src/main/java`)                                | `maven-compiler-plugin`            |
| `test`      | Run unit tests (`src/test/java`)                                     | `maven-surefire-plugin`            |
| `package`   | Package compiled code (e.g., create JAR)                             | `maven-jar-plugin`                 |
| `verify`    | Run integration tests and quality checks                             | `maven-failsafe-plugin`            |
| `install`   | Install the artifact in the local repository (`~/.m2/repository`)    | `maven-install-plugin`             |
| `deploy`    | Upload the artifact to a remote repository                           | `maven-deploy-plugin`              |

```bash
# Running 'mvn test' executes: validate → compile → test
mvn test

# Running 'mvn package' executes: validate → compile → test → package
mvn package

# Clean + build: remove previous output, then build through 'install'
mvn clean install
```

##### Clean Lifecycle

| Phase        | Description                               |
|--------------|-------------------------------------------|
| `pre-clean`  | Processes before cleaning                 |
| `clean`      | Remove the `target/` directory            |
| `post-clean` | Processes after cleaning                  |

#### Dependency Scopes

The `<scope>` element controls when a dependency is available and whether it's included in the final artifact:

| Scope        | Compile Classpath | Test Classpath | Runtime Classpath | Packaged in JAR/WAR |
|--------------|:-----------------:|:--------------:|:-----------------:|:-------------------:|
| `compile`    | Yes               | Yes            | Yes               | Yes                 |
| `provided`   | Yes               | Yes            | No                | No                  |
| `runtime`    | No                | Yes            | Yes               | Yes                 |
| `test`       | No                | Yes            | No                | No                  |
| `system`     | Yes               | Yes            | No                | No                  |

- **`compile`** (default) — needed for compilation and at runtime (e.g., Guava, Jackson)
- **`provided`** — needed for compilation but supplied by the runtime environment (e.g., Servlet API in a web container)
- **`runtime`** — not needed for compilation but required at runtime (e.g., JDBC drivers)
- **`test`** — only needed for compiling and running tests (e.g., JUnit, Mockito)

#### Transitive Dependencies

When your project depends on library A, and A depends on library B, Maven automatically includes B on your classpath. This is a **transitive dependency**.

```
my-app
  └── spring-web (compile)
        └── spring-core (compile)     ← transitive, automatically included
        └── spring-beans (compile)    ← transitive, automatically included
```

Use `mvn dependency:tree` to visualize the full dependency tree:

```bash
mvn dependency:tree
```

#### BOM (Bill of Materials)

A BOM is a special POM that centralizes version management for a set of related artifacts. Import it in `<dependencyManagement>` to avoid specifying versions for each dependency:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.junit</groupId>
            <artifactId>junit-bom</artifactId>
            <version>6.1.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<!-- Now you can omit <version> for JUnit dependencies -->
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

#### Maven Profiles

Profiles allow you to customize the build for different environments:

```xml
<profiles>
    <profile>
        <id>dev</id>
        <properties>
            <db.url>jdbc:h2:mem:devdb</db.url>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <db.url>jdbc:postgresql://prod-server:5432/mydb</db.url>
        </properties>
    </profile>
</profiles>
```

```bash
mvn package -Pdev     # Activate the 'dev' profile
mvn package -Pprod    # Activate the 'prod' profile
```

### Gradle

Gradle is an alternative build tool that uses a **Groovy** or **Kotlin** DSL instead of XML. It offers more flexibility and often faster builds through incremental compilation and build caching.

#### Groovy DSL (`build.gradle`)

```groovy
plugins {
    id 'java'
}

group = 'com.example'
version = '1.0.0'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.google.guava:guava:33.0.0-jre'
    testImplementation 'org.junit.jupiter:junit-jupiter:6.1.1'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

#### Kotlin DSL (`build.gradle.kts`)

```kotlin
plugins {
    java
}

group = "com.example"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.0.0-jre")
    testImplementation("org.junit.jupiter:junit-jupiter:6.1.1")
}

tasks.test {
    useJUnitPlatform()
}
```

#### Maven vs. Gradle Comparison

| Aspect               | Maven                                | Gradle                                 |
|----------------------|--------------------------------------|----------------------------------------|
| Configuration        | XML (`pom.xml`)                      | Groovy or Kotlin DSL                   |
| Philosophy           | Convention over configuration        | Flexibility with conventions           |
| Build speed          | Full rebuild each time               | Incremental + build cache              |
| Dependency scopes    | `compile`, `test`, `provided`, etc.  | `implementation`, `api`, `testImpl`    |
| Multi-module         | `<modules>` in parent POM           | `include` in `settings.gradle`         |
| Learning curve       | Lower (structured XML)              | Higher (scripting language)            |
| IDE support          | Excellent                            | Excellent                              |
| Ecosystem            | Largest plugin ecosystem             | Growing, Android standard              |

### Reproducible Builds

A **reproducible build** ensures that building the same source code always produces the same artifact, regardless of when or where it's built. Key practices:

- **Lock dependency versions** — never use version ranges like `[1.0,2.0)` in production
- **Use a BOM** — centralize versions in one place
- **Pin plugin versions** — always specify `<version>` for build plugins
- **Use `maven.compiler.release`** — instead of `source`/`target`, which ensures API compatibility
- **Dependency verification** — Gradle supports checksum verification; Maven has the `maven-enforcer-plugin`

### Standard Project Layout

Both Maven and Gradle follow the same standard directory structure:

```
project-root/
├── pom.xml (Maven) or build.gradle (Gradle)
├── src/
│   ├── main/
│   │   ├── java/          ← application source code
│   │   └── resources/     ← configuration files, properties
│   └── test/
│       ├── java/          ← test source code
│       └── resources/     ← test-specific resources
└── target/ (Maven) or build/ (Gradle)
    └── classes/           ← compiled output
```

## Examples

| File                                                                                       | Demonstrates                                                       |
|--------------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| [`MavenLifecycleDemo.java`](src/main/java/course/ch20/examples/MavenLifecycleDemo.java)   | Maven lifecycle phases modeled as data, phase ordering              |

## Exercises

### Exercise 1: Dependency Analyzer (Guided)

**File:** [`DependencyAnalyzer.java`](src/main/java/course/ch20/exercises/DependencyAnalyzer.java)

Parse a simplified POM XML string containing a `<dependencies>` block. Extract each `<dependency>` into a `Dependency` record with `groupId`, `artifactId`, `version`, and `scope`. If `<scope>` is absent, default to `"compile"`.

```bash
mvn test -Dtest="course.ch20.exercises.DependencyAnalyzerTest"
```

### Exercise 2: Version Resolver (Practice)

**File:** [`VersionResolver.java`](src/main/java/course/ch20/exercises/VersionResolver.java)

Implement semantic version parsing, comparison, and range matching. A `SemVer` record holds `major.minor.patch` and implements `Comparable`. The `satisfies` method checks a version against a range expression like `">=1.2.0,<2.0.0"`.

```bash
mvn test -Dtest="course.ch20.exercises.VersionResolverTest"
```

### Exercise 3: Build Order Solver (Challenge)

**File:** [`BuildOrderSolver.java`](src/main/java/course/ch20/exercises/BuildOrderSolver.java)

Given a map of modules and their dependencies, compute a valid build order using topological sort. If a circular dependency exists, throw an `IllegalStateException`.

```bash
mvn test -Dtest="course.ch20.exercises.BuildOrderSolverTest"
```

## Key Takeaways

- **Maven** uses a `pom.xml` with convention-over-configuration to manage builds, dependencies, and lifecycle phases
- Running a Maven phase (e.g., `mvn test`) automatically executes all preceding phases (`validate` → `compile` → `test`)
- **Dependency scopes** (`compile`, `test`, `provided`, `runtime`) control when a library is available
- **Gradle** offers a more flexible, script-based alternative with incremental builds and build caching
- Always **pin dependency and plugin versions** for reproducible builds

## Further Reading

- [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- [Maven POM Reference](https://maven.apache.org/pom.html)
- [Maven Lifecycle Reference](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
- [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html)
- [Maven vs. Gradle Comparison](https://gradle.org/maven-vs-gradle/)
