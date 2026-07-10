---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 9'
footer: 'Packages and Modules'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 9
## Packages and Modules

---

## Objectives

- Understand Java package naming conventions and their role in organizing code
- Learn how packages map to the file system directory structure
- Read and write `module-info.java` declarations: `exports`, `requires`, `provides`/`uses`
- Use `ServiceLoader` to discover and load service implementations at runtime

---

## Packages

A **package** is a namespace that groups related classes and interfaces. Packages prevent naming collisions and control access (package-private visibility is the default).

Java packages follow the **reverse domain name** convention:

```java
package com.example.onlinestore.model;
package com.example.onlinestore.service;
package com.example.onlinestore.repository;
```

| Rule | Example | Rationale |
|------|---------|-----------|
| Reverse domain prefix | `com.example` | Global uniqueness |
| All lowercase | `com.example.myapp` | Avoids conflicts with class names |
| Singular nouns for layers | `model`, `service`, `controller` | Ecosystem convention |

---

## Standard Project Layout

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

---

## Package Declaration and Imports

Every Java source file begins with a package declaration, followed by imports:

```java
package com.example.onlinestore.service;

import com.example.onlinestore.model.Order;
import com.example.onlinestore.repository.OrderRepository;

public class OrderService {
    public Order findById(long id) {
        return repository.findById(id);
    }
}
```

The package declaration must match the directory structure:

`src/main/java/com/example/onlinestore/service/OrderService.java`

---

## Why Modules?

Java 9 introduced the **module system** (Project Jigsaw) for stronger encapsulation above packages.

| Problem | Description |
|---------|-------------|
| No encapsulation | Any public class was accessible to any code on the classpath |
| JAR hell | Duplicate or conflicting JARs caused hard-to-diagnose errors |
| No reliable config | Missing dependencies discovered only at runtime |
| Monolithic JDK | Applications shipped the entire JDK runtime |

---

## `module-info.java`

A module is defined by a `module-info.java` file at the root of the module's source tree:

```java
module com.example.onlinestore {
    requires java.sql;
    requires java.logging;

    exports com.example.onlinestore.api;
    exports com.example.onlinestore.model;

    // Internal packages are NOT exported and remain encapsulated
}
```

---

## Key Module Directives

| Directive | Purpose | Example |
|-----------|---------|---------|
| `requires` | Declares a dependency on another module | `requires java.sql;` |
| `requires transitive` | Dependency passed to modules that require this one | `requires transitive java.logging;` |
| `exports` | Makes a package accessible to other modules | `exports com.example.api;` |
| `exports ... to` | Qualified export to specific modules | `exports com.example.internal to com.example.web;` |
| `opens` | Allows reflective access at runtime | `opens com.example.model;` |
| `provides ... with` | Declares a service implementation | `provides Codec with JsonCodec;` |
| `uses` | Declares that this module consumes a service | `uses com.example.spi.Codec;` |

---

## Module Graph Example

```
com.example.web
  └── requires com.example.service
        ├── requires com.example.model
        └── requires java.sql
```

Each module explicitly declares what it needs and what it exposes. The module system validates the entire graph at startup — missing modules cause a clear error instead of `ClassNotFoundException`.

---

## The JDK as Modules

The JDK itself is modularized (~70 platform modules):

```bash
java --list-modules
```

| Module | Contains |
|--------|----------|
| `java.base` | Core classes (`String`, `List`, `Map`) |
| `java.sql` | JDBC API |
| `java.logging` | `java.util.logging` |
| `java.net.http` | HTTP client API |

Every module implicitly requires `java.base` — you never need to declare it.

---

## ServiceLoader (SPI Pattern)

`ServiceLoader` decouples a service interface from its implementations, discovering them at runtime:

```java
// Provider module
module com.example.json {
    requires com.example.spi;
    provides com.example.spi.Codec with com.example.json.JsonCodec;
}

// Consumer module
module com.example.app {
    requires com.example.spi;
    uses com.example.spi.Codec;
}
```

```java
ServiceLoader<Codec> loader = ServiceLoader.load(Codec.class);
for (Codec codec : loader) {
    System.out.println("Found codec: " + codec.getClass().getName());
}
```

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `PackageStructure` | Package naming conventions, validation, and project layout |
| `ServiceLoaderDemo` | Discovering JDK tools via `ServiceLoader` and `ToolProvider` |

```bash
mvn test -pl 09-packages-and-modules
```

---

## Exercises — Your Turn

1. **Package Organizer** (Guided) — assign class names to correct package layers
2. **Module Info Parser** (Practice) — parse `module-info.java` into a `ModuleInfo` record
3. **Service Registry** (Challenge) — type-safe service locator mimicking `ServiceLoader`

```bash
mvn test -pl 09-packages-and-modules -Dtest="PackageOrganizerTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- **Packages** organize classes into namespaces using reverse-domain naming (`com.example.myapp.model`)
- The **Java Platform Module System** adds strong encapsulation above packages
- `module-info.java` uses `requires` for dependencies and `exports` for public API packages
- **`ServiceLoader`** decouples interfaces from implementations, enabling plug-in architectures
- Every module implicitly requires `java.base`; the JDK itself is modularized

Further reading: [JLS §7](https://docs.oracle.com/javase/specs/jls/se25/html/jls-7.html) · [JEP 261](https://openjdk.org/jeps/261) · [ServiceLoader Javadoc](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/ServiceLoader.html)
