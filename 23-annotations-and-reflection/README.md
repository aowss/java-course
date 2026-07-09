# Chapter 23: Annotations and Reflection

## Objectives

- Understand what annotations are and how retention policies and targets work
- Use built-in annotations such as `@Override`, `@Deprecated`, and `@FunctionalInterface`
- Define custom annotations and read them at runtime
- Inspect classes, fields, and methods with the reflection API
- Invoke methods and read field values dynamically

## Concepts

### What Are Annotations?

An **annotation** is metadata attached to program elements (classes, methods, fields, parameters). Annotations do not change program logic by themselves — frameworks and libraries read them to configure behavior.

```java
@Override
public String toString() {
    return "Example";
}
```

### Meta-Annotations

Annotations are defined with meta-annotations:

| Meta-annotation | Purpose                                      |
|-----------------|----------------------------------------------|
| `@Retention`    | How long the annotation is kept              |
| `@Target`       | Where the annotation may appear              |
| `@Documented`   | Include in generated Javadoc                 |
| `@Inherited`    | Inherited by subclasses                      |

Retention policies:

| Policy    | Available at                          |
|-----------|---------------------------------------|
| `SOURCE`  | Compile time only (e.g. `@Override`)  |
| `CLASS`   | In bytecode, not at runtime           |
| `RUNTIME` | Via reflection at runtime             |

### Built-In Annotations

| Annotation              | Purpose                                           |
|-------------------------|---------------------------------------------------|
| `@Override`             | Marks a method overriding a superclass method     |
| `@Deprecated`             | Marks an API element scheduled for removal        |
| `@SuppressWarnings`     | Suppresses compiler warnings                      |
| `@FunctionalInterface`  | Documents a single-abstract-method interface      |
| `@SafeVarargs`          | Asserts a varargs method is safe                  |

### Defining Custom Annotations

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    int min();
    int max();
}
```

Members of an annotation are called **elements** and must be compile-time constants.

### The Reflection API

Reflection lets code inspect and manipulate classes at runtime:

```java
Class<?> type = object.getClass();
Field field = type.getDeclaredField("name");
field.setAccessible(true);
Object value = field.get(object);

Method method = type.getDeclaredMethod("greet");
Object result = method.invoke(object);
```

Common reflection types:

| Type            | Purpose                                |
|-----------------|----------------------------------------|
| `Class<?>`      | Represents a loaded class              |
| `Field`         | Represents a declared field            |
| `Method`        | Represents a declared method           |
| `Constructor<?>`| Represents a constructor               |
| `Parameter`     | Represents a method parameter          |

### Reading Annotations at Runtime

```java
if (field.isAnnotationPresent(NotNull.class)) {
    // validate non-null
}

Range range = field.getAnnotation(Range.class);
if (value < range.min() || value > range.max()) {
    throw new IllegalArgumentException("Out of range");
}
```

## Examples

| File                                                                                              | Demonstrates                                           |
|---------------------------------------------------------------------------------------------------|--------------------------------------------------------|
| [`BuiltInAnnotations.java`](src/main/java/course/ch23/examples/BuiltInAnnotations.java)           | `@Override`, `@Deprecated`, `@FunctionalInterface`     |
| [`CustomAnnotationDemo.java`](src/main/java/course/ch23/examples/CustomAnnotationDemo.java)       | Custom `@NotNull` and `@Range` with runtime validation |
| [`ReflectionBasics.java`](src/main/java/course/ch23/examples/ReflectionBasics.java)               | Inspecting fields, methods, and invoking behavior      |

## Exercises

### Exercise 1: Object Inspector (Guided)

**File:** [`ObjectInspector.java`](src/main/java/course/ch23/exercises/ObjectInspector.java)

Use reflection to summarize an object's class name, declared fields, and declared methods.

```bash
mvn test -Dtest="course.ch23.exercises.ObjectInspectorTest"
```

### Exercise 2: Simple Serializer (Practice)

**File:** [`SimpleSerializer.java`](src/main/java/course/ch23/exercises/SimpleSerializer.java)

Serialize only fields annotated with `@Serializable` into a `key=value,key=value` string.

```bash
mvn test -Dtest="course.ch23.exercises.SimpleSerializerTest"
```

### Exercise 3: Mini Test Runner (Challenge)

**File:** [`MiniTestRunner.java`](src/main/java/course/ch23/exercises/MiniTestRunner.java)

Build a tiny test runner that discovers and executes methods annotated with the course `@Test` annotation.

```bash
mvn test -Dtest="course.ch23.exercises.MiniTestRunnerTest"
```

## Key Takeaways

- Annotations attach metadata; **retention** and **target** control where they apply and how long they survive.
- **Built-in annotations** document intent and enable compiler checks.
- **Custom annotations** power validation, serialization, and framework-style behavior.
- **Reflection** inspects types at runtime but should be used sparingly due to performance and encapsulation costs.

## Further Reading

- [JLS §9.6 — Annotation Types](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.6)
- [java.lang.reflect — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/reflect/package-summary.html)
- [JEP 468: Derived Record Creation (annotation-driven)](https://openjdk.org/jeps/468)
