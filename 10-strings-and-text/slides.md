---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 10'
footer: 'Strings and Text'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 10
## Strings and Text

---

## Objectives

- Understand why Java strings are immutable and how interning works
- Use common `String` methods for searching, comparing, and transforming text
- Build strings efficiently with `StringBuilder` and `StringBuffer`
- Write multi-line strings using **text blocks** and format with `formatted()`

---

## String Immutability

In Java, `String` objects are **immutable** — once created, their content cannot be changed. Every "modifying" method returns a **new** `String` instance:

```java
String greeting = "hello";
String upper = greeting.toUpperCase();

System.out.println(greeting); // "hello" — unchanged
System.out.println(upper);    // "HELLO" — new object
```

This design enables **thread safety**, **string interning**, and **security** (keys, class names, and file paths cannot be altered).

---

## String Interning

The JVM maintains a **string pool** of unique string literals. Two literals with the same content share the same reference:

```java
String a = "Java";
String b = "Java";
System.out.println(a == b);      // true — same pooled reference

String c = new String("Java");
System.out.println(a == c);      // false — c is a new object
System.out.println(a.equals(c)); // true — same content
```

Always use `.equals()` to compare string content. Use `==` only when you intentionally want reference equality.

---

## Common String Methods

| Method | Description | Example |
|--------|-------------|---------|
| `length()` | Number of characters | `"hello".length()` → `5` |
| `charAt(int)` | Character at index | `"hello".charAt(1)` → `'e'` |
| `substring(int, int)` | Extract a range | `"hello".substring(1, 4)` → `"ell"` |
| `indexOf(String)` | First occurrence index | `"hello".indexOf("ll")` → `2` |
| `contains(CharSequence)` | Check if present | `"hello".contains("ell")` → `true` |
| `startsWith` / `endsWith` | Prefix / suffix check | `"hello".startsWith("he")` → `true` |

---

## More String Methods

| Method | Description | Example |
|--------|-------------|---------|
| `toUpperCase()` / `toLowerCase()` | Case conversion | `"Hello".toLowerCase()` → `"hello"` |
| `trim()` / `strip()` | Remove whitespace | `"  hi  ".strip()` → `"hi"` |
| `replace(char, char)` | Replace characters | `"hello".replace('l', 'r')` → `"herro"` |
| `split(String)` | Split by regex | `"a,b,c".split(",")` → `["a", "b", "c"]` |
| `join(CharSequence, ...)` | Join with delimiter | `String.join("-", "a", "b")` → `"a-b"` |

---

## StringBuilder and StringBuffer

When building strings through repeated concatenation, use `StringBuilder` to avoid creating many intermediate `String` objects:

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 5; i++) {
    sb.append("item").append(i).append(", ");
}
String result = sb.toString(); // "item0, item1, item2, item3, item4, "
```

| Class | Thread-safe? | Use when |
|-------|--------------|----------|
| `StringBuilder` | No | Single-threaded string building (preferred) |
| `StringBuffer` | Yes | Multi-threaded string building (legacy) |

---

## Text Blocks

Text blocks (Java 15+) let you write multi-line strings with triple quotes (`"""`):

```java
String json = """
        {
            "name": "Alice",
            "age": 30
        }""";
```

The compiler strips incidental leading whitespace based on the closing `"""` position. Supports `\s` (space) and `\` (line continuation).

---

## String Formatting

**`String.format()`** uses `printf`-style format specifiers:

```java
String msg = String.format("Hello, %s! You are %d years old.", "Alice", 30);
```

**`formatted()`** (Java 15+) is an instance method that does the same thing:

```java
String msg = "Hello, %s! You scored %.1f%%".formatted("Bob", 95.5);
```

| Specifier | Type | Example |
|-----------|------|---------|
| `%s` | String | `"hello"` |
| `%d` | Integer | `42` |
| `%.2f` | Float with 2 decimals | `3.14` |
| `%,d` | Integer with commas | `1,000,000` |

---

## Regular Expressions

Java provides regex support through `Pattern` and `Matcher`:

```java
Pattern pattern = Pattern.compile("\\d+");
Matcher matcher = pattern.matcher("Order 42, Item 7");

while (matcher.find()) {
    System.out.println(matcher.group()); // "42", then "7"
}
```

Quick methods on `String` itself:

```java
boolean valid = "abc123".matches("[a-z]+\\d+");       // true
String clean = "a  b  c".replaceAll("\\s+", " ");     // "a b c"
String[] parts = "one,two,three".split(",");           // ["one","two","three"]
```

---

## Common Regex Patterns

| Pattern | Matches |
|---------|---------|
| `\\d` | A digit |
| `\\w` | A word character (letter/digit/_) |
| `\\s` | A whitespace character |
| `.` | Any character (except newline) |
| `+` | One or more of the preceding |
| `*` | Zero or more of the preceding |
| `?` | Zero or one of the preceding |
| `[abc]` | Any one of a, b, or c |
| `(group)` | Capture group |

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `StringImmutability` | Immutability, interning, common String methods |
| `TextBlockDemo` | Text blocks, `String.format()`, `formatted()` |
| `RegexBasics` | `Pattern`, `Matcher`, `findAll`, `replaceAll`, capture groups |

```bash
mvn test -pl 10-strings-and-text
```

---

## Exercises — Your Turn

1. **Word Counter** (Guided) — `countWords()`, `countUniqueWords()`, `mostFrequentWord()`
2. **Template Engine** (Practice) — replace `{{placeholder}}` patterns from a map
3. **Markdown to HTML** (Challenge) — convert `**bold**`, `*italic*`, and `` `code` `` to HTML

```bash
mvn test -pl 10-strings-and-text -Dtest="WordCounterTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Java strings are **immutable** — every modification creates a new object
- Use `.equals()` for content comparison, never `==`
- Use `StringBuilder` for efficient string concatenation in loops
- **Text blocks** (`"""`) simplify multi-line string literals
- `String.format()` and `formatted()` provide powerful formatting with `%s`, `%d`, `%f`, etc.
- `Pattern` and `Matcher` provide full regex support; `matches()`, `replaceAll()`, and `split()` offer shortcuts

Further reading: [JLS §3.10.5](https://docs.oracle.com/javase/specs/jls/se25/html/jls-3.html#jls-3.10.5) · [JEP 378](https://openjdk.org/jeps/378) · [Pattern API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/regex/Pattern.html)
