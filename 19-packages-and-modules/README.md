# Chapter 19: Packages and Modules

## Objectives

- Understand Java package naming conventions and their role in organizing code
- Learn how packages map to the file system directory structure
- Understand the Java Platform Module System (JPMS) introduced in Java 9
- Read and write `module-info.java` declarations: `exports`, `requires`, `provides`/`uses`
- Use `ServiceLoader` to discover and load service implementations at runtime

## Concepts

### Packages

A **package** is a namespace that groups related classes and interfaces. Packages serve two purposes: they prevent naming collisions and they control access (package-private visibility is the default).

#### Naming Conventions

Java packages follow the **reverse domain name** convention:

```java
// Domain: example.com → base package: com.example
package com.example.onlinestore.model;
package com.example.onlinestore.service;
package com.example.onlinestore.repository;
```

| Rule                            | Example                              | Rationale                              |
|---------------------------------|--------------------------------------|----------------------------------------|
| Reverse domain prefix           | `com.example`                        | Global uniqueness                      |
| All lowercase                   | `com.example.myapp`                  | Avoids conflicts with class names      |
| No hyphens or special chars     | `com.example.myapp` (not `my-app`)   | Must be valid Java identifiers         |
| Singular nouns for layers       | `model`, `service`, `controller`     | Convention across the Java ecosystem   |

#### Standard Project Layout

A typical Java project organizes packages by functional layer:

```
com.example.onlinestore
├── model/          ← domain objects (User, Order, Product)
├── service/        ← business logic (OrderService, PaymentService)
├── repository/     ← data access (UserRepository, OrderDao)
├── controller/     ← request handling (UserController)
├── config/         ← configuration classes
└── util/           ← shared utilities (StringUtils, DateHelper)
```

#### Package Declaration and Imports

Every Java source file begins with a package declaration, followed by imports:

```java
package com.example.onlinestore.service;

import com.example.onlinestore.model.Order;
import com.example.onlinestore.repository.OrderRepository;

public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order findById(long id) {
        return repository.findById(id);
    }
}
```

The package declaration must match the directory structure. The file above must live at:

```
src/main/java/com/example/onlinestore/service/OrderService.java
```

### The Java Platform Module System (JPMS)

Java 9 introduced the **module system** (Project Jigsaw) to provide stronger encapsulation and reliable configuration at a level above packages.

#### Why Modules?

Before modules, the classpath had several problems:

| Problem               | Description                                                                       |
|-----------------------|-----------------------------------------------------------------------------------|
| No encapsulation      | Any public class was accessible to any code on the classpath                      |
| JAR hell              | Duplicate or conflicting JARs caused hard-to-diagnose runtime errors              |
| No reliable config    | Missing dependencies were only discovered at runtime (`NoClassDefFoundError`)     |
| Monolithic JDK        | Applications had to ship the entire JDK runtime, even if they only used a subset  |

#### Module Declaration: `module-info.java`

A module is defined by a `module-info.java` file placed at the root of the module's source tree:

```java
module com.example.onlinestore {
    // Dependencies — modules this module needs
    requires java.sql;
    requires java.logging;

    // Exported packages — visible to other modules
    exports com.example.onlinestore.api;
    exports com.example.onlinestore.model;

    // Internal packages are NOT exported and remain encapsulated
}
```

#### Key Directives

| Directive                          | Purpose                                                            | Example                                            |
|------------------------------------|--------------------------------------------------------------------|---------------------------------------------------|
| `requires`                         | Declares a dependency on another module                            | `requires java.sql;`                               |
| `requires transitive`              | Dependency is passed to modules that require this module           | `requires transitive java.logging;`                |
| `exports`                          | Makes a package accessible to other modules                        | `exports com.example.api;`                         |
| `exports ... to`                   | Restricts export to specific modules (qualified export)            | `exports com.example.internal to com.example.web;` |
| `opens`                            | Allows reflective access to a package at runtime                   | `opens com.example.model;`                         |
| `provides ... with`                | Declares a service implementation                                  | `provides Codec with JsonCodec;`                   |
| `uses`                             | Declares that this module consumes a service                       | `uses com.example.spi.Codec;`                      |

#### Module Graph Example

```
com.example.web
  └── requires com.example.service
        ├── requires com.example.model
        └── requires java.sql
```

Each module explicitly declares what it needs and what it exposes. The module system validates the entire graph at startup — missing modules cause a clear error instead of a runtime `ClassNotFoundException`.

#### The JDK as Modules

The JDK itself is modularized. You can list all platform modules:

```bash
java --list-modules
```

Common JDK modules:

| Module            | Contains                                  |
|-------------------|-------------------------------------------|
| `java.base`       | Core classes (`String`, `List`, `Map`)    |
| `java.sql`        | JDBC API                                  |
| `java.logging`    | `java.util.logging`                       |
| `java.net.http`   | HTTP client API                           |
| `java.desktop`    | AWT and Swing                             |

Every module implicitly requires `java.base` — you never need to declare it.

### ServiceLoader

`ServiceLoader` is Java's built-in mechanism for the **service provider interface (SPI)** pattern. It decouples a service interface from its implementations, allowing implementations to be discovered at runtime.

#### How It Works

1. **Define a service interface** (or abstract class):

```java
package com.example.spi;

public interface Codec {
    String encode(String data);
    String decode(String data);
}
```

2. **Implement the service** in a provider module:

```java
package com.example.json;

import com.example.spi.Codec;

public class JsonCodec implements Codec {
    @Override
    public String encode(String data) { /* ... */ return data; }

    @Override
    public String decode(String data) { /* ... */ return data; }
}
```

3. **Declare the provider** in `module-info.java` (or `META-INF/services` on the classpath):

```java
// In the provider module
module com.example.json {
    requires com.example.spi;
    provides com.example.spi.Codec with com.example.json.JsonCodec;
}

// In the consumer module
module com.example.app {
    requires com.example.spi;
    uses com.example.spi.Codec;
}
```

4. **Discover implementations** at runtime:

```java
ServiceLoader<Codec> loader = ServiceLoader.load(Codec.class);
for (Codec codec : loader) {
    System.out.println("Found codec: " + codec.getClass().getName());
}
```

The JDK uses `ServiceLoader` extensively — for example, JDBC drivers, `ToolProvider`, and `FileSystemProvider` are all discovered this way.

## Examples

| File                                                                                    | Demonstrates                                                    |
|-----------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| [`PackageStructure.java`](src/main/java/course/ch19/examples/PackageStructure.java)     | Package naming conventions, validation, and project layout      |
| [`ServiceLoaderDemo.java`](src/main/java/course/ch19/examples/ServiceLoaderDemo.java)   | Discovering JDK tools via `ServiceLoader` and `ToolProvider`    |

## Exercises

### Exercise 1: Package Organizer (Guided)

**File:** [`PackageOrganizer.java`](src/main/java/course/ch19/exercises/PackageOrganizer.java)

Given a list of class names, assign each to the correct package layer based on naming conventions:
- Names ending with `Service` → `"service"`
- Names ending with `Repository` or `Dao` → `"repository"`
- Names ending with `Controller` → `"controller"`
- Names ending with `Util`, `Utils`, or `Helper` → `"util"`
- All others → `"model"`

```bash
mvn test -Dtest="course.ch19.exercises.PackageOrganizerTest"
```

### Exercise 2: Module Info Parser (Practice)

**File:** [`ModuleInfoParser.java`](src/main/java/course/ch19/exercises/ModuleInfoParser.java)

Parse a `module-info.java` source string and extract the module name, `requires` directives, and `exports` directives into a `ModuleInfo` record. Handle `requires transitive` by extracting just the module name.

```bash
mvn test -Dtest="course.ch19.exercises.ModuleInfoParserTest"
```

### Exercise 3: Service Registry (Challenge)

**File:** [`ServiceRegistry.java`](src/main/java/course/ch19/exercises/ServiceRegistry.java)

Implement a generic, type-safe service locator that mimics `ServiceLoader`:
- `register(Class<T>, T)` — register an implementation for a service type
- `lookupAll(Class<T>)` — return all implementations (unmodifiable list)
- `lookupFirst(Class<T>)` — return the first implementation or throw `NoSuchElementException`

```bash
mvn test -Dtest="course.ch19.exercises.ServiceRegistryTest"
```

## Key Takeaways

- **Packages** organize classes into namespaces using reverse-domain naming (`com.example.myapp.model`)
- The **Java Platform Module System** adds strong encapsulation above packages — modules declare what they export and what they require
- `module-info.java` is the module descriptor: `requires` for dependencies, `exports` for public API packages
- **`ServiceLoader`** decouples interfaces from implementations, enabling plug-in architectures
- Every module implicitly requires `java.base`; the JDK itself is modularized into ~70 platform modules

## Further Reading

- [JLS §7 — Packages and Modules](https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html)
- [The State of the Module System](https://openjdk.org/projects/jigsaw/spec/sotms/)
- [ServiceLoader Javadoc](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ServiceLoader.html)
- [JEP 261: Module System](https://openjdk.org/jeps/261)
