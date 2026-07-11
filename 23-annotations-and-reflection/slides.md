---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 23'
footer: 'Annotations and Reflection'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 23
## Annotations and Reflection

---

## Objectives

- Understand annotations, retention policies, and targets
- Use built-in annotations: `@Override`, `@Deprecated`, `@FunctionalInterface`
- Define custom annotations and read them at runtime
- Inspect classes, fields, and methods with the reflection API

---

## What Are Annotations?

An **annotation** is metadata attached to program elements (classes, methods, fields, parameters).

Annotations do **not** change program logic by themselves — frameworks and libraries read them to configure behavior.

```java
@Override
public String toString() {
    return "Example";
}
```

---

## Meta-Annotations

Annotations are defined with meta-annotations:

| Meta-annotation | Purpose |
|-----------------|---------|
| `@Retention` | How long the annotation is kept |
| `@Target` | Where the annotation may appear |
| `@Documented` | Include in generated Javadoc |
| `@Inherited` | Inherited by subclasses |

---

## Retention Policies

| Policy | Available at |
|--------|--------------|
| `SOURCE` | Compile time only (e.g. `@Override`) |
| `CLASS` | In bytecode, not at runtime |
| `RUNTIME` | Via reflection at runtime |

Use `RUNTIME` when your code reads annotations dynamically.

---

## Built-In Annotations

| Annotation | Purpose |
|------------|---------|
| `@Override` | Marks a method overriding a superclass method |
| `@Deprecated` | Marks an API element scheduled for removal |
| `@SuppressWarnings` | Suppresses compiler warnings |
| `@FunctionalInterface` | Documents a single-abstract-method interface |
| `@SafeVarargs` | Asserts a varargs method is safe |

---

## Defining Custom Annotations

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    int min();
    int max();
}
```

Members of an annotation are called **elements** and must be compile-time constants.

---

## The Reflection API

Reflection lets code inspect and manipulate classes at runtime:

```java
Class<?> type = object.getClass();
Field field = type.getDeclaredField("name");
field.setAccessible(true);
Object value = field.get(object);

Method method = type.getDeclaredMethod("greet");
Object result = method.invoke(object);
```

---

## Reflection Types

| Type | Purpose |
|------|---------|
| `Class<?>` | Represents a loaded class |
| `Field` | Represents a declared field |
| `Method` | Represents a declared method |
| `Constructor<?>` | Represents a constructor |
| `Parameter` | Represents a method parameter |

Use `getDeclared*` to include private members; `setAccessible(true)` bypasses access checks.

---

## Reading Annotations at Runtime

```java
if (field.isAnnotationPresent(NotNull.class)) {
    // validate non-null
}

Range range = field.getAnnotation(Range.class);
if (value < range.min() || value > range.max()) {
    throw new IllegalArgumentException("Out of range");
}
```

This pattern powers validation, serialization, and framework-style behavior.

---

## Examples

| File | Topic |
|------|-------|
| `BuiltInAnnotations` | `@Override`, `@Deprecated`, `@FunctionalInterface` |
| `CustomAnnotationDemo` | Custom `@NotNull` and `@Range` with runtime validation |
| `ReflectionBasics` | Inspecting fields, methods, invoking behavior |

```bash
mvn test -pl 23-annotations-and-reflection
```

---

## Exercises

1. **Object Inspector** (Guided) — summarize class name, fields, and methods via reflection
2. **Simple Serializer** (Practice) — serialize only `@Serializable` fields to `key=value,...`
3. **Mini Test Runner** (Challenge) — discover and run methods annotated with course `@Test`

```bash
mvn test -pl 23-annotations-and-reflection -Dtest="ObjectInspectorTest"
```



---

## Key Takeaways

- Annotations attach metadata; **retention** and **target** control scope and lifetime
- **Built-in annotations** document intent and enable compiler checks
- **Custom annotations** power validation, serialization, and framework behavior
- **Reflection** inspects types at runtime — use sparingly (performance and encapsulation costs)

Full lesson: [`README.md`](README.md)
Further reading: [JLS §9.6](https://docs.oracle.com/javase/specs/jls/se25/html/jls-9.html#jls-9.6) · [java.lang.reflect API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/lang/reflect/package-summary.html)
