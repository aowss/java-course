---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 20'
footer: 'Build Tools'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 20
## Build Tools

---

## Objectives

- Understand why build tools are essential for Java projects
- Learn Maven POM structure and the role of each element
- Master Maven's build lifecycle: phases, plugins, and goals
- Understand dependency management: scopes, transitive deps, and BOM
- Compare Maven and Gradle at a conceptual level
- Understand reproducible builds and dependency locking

---

## Why Build Tools?

`javac` works for one file, but real projects need automation for:

- **Compilation** — all source files in correct order
- **Dependency management** — downloading and managing libraries
- **Testing** — unit and integration tests
- **Packaging** — JARs, WARs, distributable formats
- **Lifecycle management** — repeatable, ordered build process

---

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

---

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

---

## Key POM Elements

| Element | Purpose |
|---------|---------|
| `<parent>` | Inherit configuration from a parent POM |
| `<properties>` | Reusable variables (`${junit.version}`) |
| `<dependencies>` | Declare external libraries |
| `<dependencyManagement>` | Centralize versions without adding to classpath |
| `<build>` | Configure plugins (compiler, Surefire) |
| `<modules>` | List sub-modules in a multi-module project |

---

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

---

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

---

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

---

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

---

## Reproducible Builds

Building the same source should always produce the same artifact:

- **Lock dependency versions** — never use ranges like `[1.0,2.0)` in production
- **Use a BOM** — centralize versions in one place
- **Pin plugin versions** — always specify `<version>` for build plugins
- **Use `maven.compiler.release`** — ensures API compatibility
- **Dependency verification** — `maven-enforcer-plugin` or Gradle checksums

---

## Examples

| File | Topic |
|------|-------|
| `MavenLifecycleDemo` | Maven lifecycle phases modeled as data, phase ordering |

```bash
mvn test -pl 20-build-tools
```

---

## Exercises

1. **DependencyAnalyzer** (Guided) — parse POM XML into `Dependency` records
2. **VersionResolver** (Practice) — semantic version parsing and range matching
3. **BuildOrderSolver** (Challenge) — topological sort for module build order

```bash
mvn test -pl 20-build-tools -Dtest="DependencyAnalyzerTest"
```



---

## Key Takeaways

- **Maven** uses `pom.xml` with convention-over-configuration for builds, deps, and lifecycle
- Running a phase (e.g., `mvn test`) executes all preceding phases automatically
- **Dependency scopes** control when a library is available on each classpath
- **Gradle** offers a flexible, script-based alternative with incremental builds
- Always **pin dependency and plugin versions** for reproducible builds

Full lesson: [`README.md`](README.md)
Further reading: [Maven in 5 Minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) · [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html)
