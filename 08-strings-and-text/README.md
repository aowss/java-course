# Chapter 8: Strings and Text

## Objectives

- Understand why Java strings are immutable and how interning works
- Use common `String` methods for searching, comparing, and transforming text
- Build strings efficiently with `StringBuilder` and `StringBuffer`
- Write multi-line strings using text blocks
- Format strings with `String.format()` and `formatted()`
- Apply regular expressions for pattern matching and replacement

## Concepts

### String Immutability

In Java, `String` objects are **immutable** — once created, their content cannot be changed. Every method that appears to modify a string (e.g. `toUpperCase()`, `substring()`, `replace()`) returns a **new** `String` instance.

```java
String greeting = "hello";
String upper = greeting.toUpperCase();

System.out.println(greeting); // "hello" — unchanged
System.out.println(upper);    // "HELLO" — new object
```

This design enables:
- **Thread safety** — immutable objects can be shared between threads without synchronization.
- **String interning** — the JVM can reuse identical string literals from a shared pool.
- **Security** — strings used as keys, class names, or file paths cannot be altered.

### String Interning

The JVM maintains a **string pool** of unique string literals. Two literal strings with the same content share the same reference:

```java
String a = "Java";
String b = "Java";
System.out.println(a == b);      // true — same pooled reference

String c = new String("Java");
System.out.println(a == c);      // false — c is a new object
System.out.println(a.equals(c)); // true — same content
```

Always use `.equals()` to compare string content. Use `==` only when you intentionally want reference equality.

### Common String Methods

| Method                                  | Description                                        | Example                                           |
|-----------------------------------------|----------------------------------------------------|---------------------------------------------------|
| `length()`                              | Number of characters                               | `"hello".length()` → `5`                          |
| `charAt(int)`                           | Character at index                                 | `"hello".charAt(1)` → `'e'`                       |
| `substring(int, int)`                   | Extract a range                                    | `"hello".substring(1, 4)` → `"ell"`               |
| `indexOf(String)`                       | First occurrence index                             | `"hello".indexOf("ll")` → `2`                     |
| `contains(CharSequence)`               | Check if present                                   | `"hello".contains("ell")` → `true`                |
| `startsWith(String)` / `endsWith(String)` | Prefix / suffix check                           | `"hello".startsWith("he")` → `true`               |
| `toUpperCase()` / `toLowerCase()`       | Case conversion                                    | `"Hello".toLowerCase()` → `"hello"`               |
| `trim()` / `strip()`                    | Remove whitespace                                  | `"  hi  ".strip()` → `"hi"`                       |
| `replace(char, char)`                   | Replace characters                                 | `"hello".replace('l', 'r')` → `"herro"`           |
| `split(String)`                         | Split by regex                                     | `"a,b,c".split(",")` → `["a", "b", "c"]`          |
| `join(CharSequence, CharSequence...)`   | Join with delimiter                                | `String.join("-", "a", "b")` → `"a-b"`            |

### StringBuilder and StringBuffer

When building strings through repeated concatenation, use `StringBuilder` to avoid creating many intermediate `String` objects:

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 5; i++) {
    sb.append("item").append(i).append(", ");
}
String result = sb.toString(); // "item0, item1, item2, item3, item4, "
```

| Class             | Thread-safe? | Performance | Use when                          |
|-------------------|--------------|-------------|-----------------------------------|
| `StringBuilder`   | No           | Faster      | Single-threaded string building   |
| `StringBuffer`    | Yes          | Slower      | Multi-threaded string building    |

In practice, `StringBuilder` is almost always the right choice. `StringBuffer` exists for legacy compatibility.

### Text Blocks

Text blocks (Java 15+) let you write multi-line strings with triple quotes (`"""`):

```java
String json = """
        {
            "name": "Alice",
            "age": 30
        }""";
```

The compiler strips incidental leading whitespace based on the closing `"""` position. Text blocks support the same escape sequences as regular strings, plus `\s` (space) and `\` (line continuation).

### String Formatting

**`String.format()`** uses `printf`-style format specifiers:

```java
String msg = String.format("Hello, %s! You are %d years old.", "Alice", 30);
```

**`formatted()`** (Java 15+) is an instance method that does the same thing:

```java
String msg = "Hello, %s! You scored %.1f%%".formatted("Bob", 95.5);
```

| Specifier | Type                    | Example                          |
|-----------|-------------------------|----------------------------------|
| `%s`      | String                  | `"hello"`                        |
| `%d`      | Integer                 | `42`                             |
| `%f`      | Floating point          | `3.140000`                       |
| `%.2f`    | Float with 2 decimals   | `3.14`                           |
| `%,d`     | Integer with commas     | `1,000,000`                      |
| `%n`      | Platform line separator |                                  |

### Regular Expressions

Java provides regex support through `java.util.regex.Pattern` and `Matcher`:

```java
Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher("Order 42, Item 7");

while (matcher.find()) {
    System.out.println(matcher.group()); // "42", then "7"
}
```

Common patterns:

| Pattern    | Matches                           |
|------------|-----------------------------------|
| `\\d`      | A digit                           |
| `\\w`      | A word character (letter/digit/_) |
| `\\s`      | A whitespace character            |
| `.`        | Any character (except newline)    |
| `+`        | One or more of the preceding      |
| `*`        | Zero or more of the preceding     |
| `?`        | Zero or one of the preceding      |
| `[abc]`    | Any one of a, b, or c             |
| `(group)`  | Capture group                     |

Quick methods on `String` itself:

```java
boolean valid = "abc123".matches("[a-z]+\\d+");       // true
String clean = "a  b  c".replaceAll("\\s+", " ");     // "a b c"
String[] parts = "one,two,three".split(",");           // ["one","two","three"]
```

## Examples

| File                                                                                  | Demonstrates                                                |
|---------------------------------------------------------------------------------------|-------------------------------------------------------------|
| [`StringImmutability.java`](src/main/java/course/ch08/examples/StringImmutability.java) | Immutability, interning, common String methods              |
| [`TextBlockDemo.java`](src/main/java/course/ch08/examples/TextBlockDemo.java)          | Text blocks, String.format(), formatted()                   |
| [`RegexBasics.java`](src/main/java/course/ch08/examples/RegexBasics.java)              | Pattern, Matcher, findAll, replaceAll, capture groups       |

## Exercises

### Exercise 1: Word Counter (Guided)

**File:** [`WordCounter.java`](src/main/java/course/ch08/exercises/WordCounter.java)

Implement three methods for word frequency analysis:
- `countWords(String)` — count total words (split on whitespace)
- `countUniqueWords(String)` — count distinct words (case-insensitive)
- `mostFrequentWord(String)` — return the most common word (case-insensitive)

```bash
mvn test -Dtest="course.ch08.exercises.WordCounterTest"
```

### Exercise 2: Template Engine (Practice)

**File:** [`TemplateEngine.java`](src/main/java/course/ch08/exercises/TemplateEngine.java)

Implement a `render(String, Map)` method that replaces `{{placeholder}}` patterns in a template string with values from a map. Unknown placeholders are left unchanged.

```bash
mvn test -Dtest="course.ch08.exercises.TemplateEngineTest"
```

### Exercise 3: Markdown to HTML (Challenge)

**File:** [`MarkdownToHtml.java`](src/main/java/course/ch08/exercises/MarkdownToHtml.java)

Implement a `convert(String)` method that transforms simple Markdown formatting to HTML:
- `**bold**` → `<strong>bold</strong>`
- `*italic*` → `<em>italic</em>`
- `` `code` `` → `<code>code</code>`

```bash
mvn test -Dtest="course.ch08.exercises.MarkdownToHtmlTest"
```

## Key Takeaways

- Java strings are **immutable** — every modification creates a new object.
- Use `.equals()` for content comparison, never `==`.
- Use `StringBuilder` for efficient string concatenation in loops.
- **Text blocks** (`"""`) simplify multi-line string literals.
- `String.format()` and `formatted()` provide powerful formatting with `%s`, `%d`, `%f`, etc.
- `Pattern` and `Matcher` provide full regex support; `String.matches()`, `replaceAll()`, and `split()` offer convenient shortcuts.

## Further Reading

- [JLS §3.10.5 — String Literals](https://docs.oracle.com/javase/specs/jls/se25/html/jls-3.html#jls-3.10.5)
- [JLS §3.10.6 — Text Blocks](https://docs.oracle.com/javase/specs/jls/se25/html/jls-3.html#jls-3.10.6)
- [java.util.regex.Pattern — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/regex/Pattern.html)
- [JEP 378: Text Blocks](https://openjdk.org/jeps/378)
