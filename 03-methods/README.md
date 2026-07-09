# Chapter 3: Methods

## Objectives

- Define methods with parameters and return types
- Understand method overloading and how the compiler resolves calls
- Use varargs for variable-length argument lists
- Understand the stack and heap memory model and how it relates to pass-by-value
- Reason about variable scope and lifetime

## Concepts

### Defining Methods

A method has a signature (name + parameter types) and a body:

```java
public static double circleArea(double radius) {
    return Math.PI * radius * radius;
}
```

- **Return type** — the type of value the method produces (`void` if none).
- **Parameters** — inputs the caller provides. Java passes everything **by value**: primitives are copied, references are copied (the object is shared, the reference is not).
- **`static`** — the method belongs to the class, not to an instance. You can call it without creating an object.

### Overloading

Multiple methods can share the same name if their parameter lists differ:

```java
public static int add(int a, int b) {
    return a + b;
}

public static double add(double a, double b) {
    return a + b;
}

public static int add(int a, int b, int c) {
    return a + b + c;
}
```

The compiler picks the most specific matching overload at compile time. Overloading is resolved by **parameter types and count**, never by return type.

### Varargs

A varargs parameter accepts zero or more arguments and is treated as an array inside the method:

```java
public static int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) {
        total += n;
    }
    return total;
}

sum();           // 0
sum(1, 2, 3);   // 6
sum(new int[]{4, 5}); // 9
```

Rules:
- A method can have at most **one** varargs parameter.
- It must be the **last** parameter.
- Inside the method, it is an `int[]` (or whatever the declared type is).

### Stack and Heap

To understand how methods, variables, and objects interact at runtime, you need a mental model of where things live in memory.

```
        ┌─────────────────────────────────────────────────┐
        │                     HEAP                        │
        │  (shared, long-lived)                           │
        │                                                 │
        │   ┌──────────────┐    ┌──────────────────────┐  │
        │   │ String "Hi"  │    │ int[] {2, 3, 5, 7}   │  │
        │   └──────────────┘    └──────────────────────┘  │
        │          ▲                      ▲               │
        └──────────┼──────────────────────┼───────────────┘
                   │                      │
        ┌──────────┼──────────────────────┼───────────────┐
        │  STACK   │                      │               │
        │          │                      │               │
        │  ┌───────┴──────────────────────┴────────────┐  │
        │  │ main()                                    │  │
        │  │   greeting = ──► (reference to "Hi")      │  │
        │  │   primes   = ──► (reference to int[])     │  │
        │  │   count    = 42  (primitive, stored here)  │  │
        │  └───────────────────────────────────────────┘  │
        │  ┌───────────────────────────────────────────┐  │
        │  │ add(a=3, b=4)                             │  │
        │  │   result   = 7   (primitive, stored here)  │  │
        │  └───────────────────────────────────────────┘  │
        └─────────────────────────────────────────────────┘
```

**The stack** holds method calls. Each method invocation creates a **stack frame** containing its parameters and local variables. When the method returns, its frame is popped and all its locals are gone. Stack memory is fast, automatic, and scoped to the method call.

**The heap** holds objects and arrays. They are created with `new` (or implicitly, like string literals) and live until no references point to them, at which point the **garbage collector** reclaims the memory.

**Key rules:**
- **Primitives** (`int`, `double`, `boolean`, etc.) are stored directly in the stack frame.
- **Objects and arrays** are stored on the heap. The variable on the stack holds a **reference** (a pointer) to the heap object.
- When you pass a variable to a method, Java copies the value on the stack — the primitive value itself, or the reference. This is why Java is **pass-by-value**: the method gets its own copy of the reference, not the object itself.

```java
public static void tryToReassign(String s) {
    s = "changed";   // reassigns the local copy of the reference
}

String greeting = "hello";
tryToReassign(greeting);
// greeting is still "hello" — the method only changed its own copy
```

```java
public static void modifyArray(int[] arr) {
    arr[0] = 999;    // follows the reference to the same heap object
}

int[] numbers = {1, 2, 3};
modifyArray(numbers);
// numbers[0] is now 999 — both references point to the same array
```

> We cover the heap, garbage collection, and JVM memory areas in depth in Chapter 28.

### Scope and Lifetime

- **Local variables** exist from declaration to the end of their enclosing block.
- **Parameters** are local variables scoped to the method body.
- **A variable declared inside a loop** is created and destroyed on each iteration.
- When a method returns, its **stack frame** is destroyed and all its locals cease to exist.

```java
public static void demo() {
    int x = 10;          // visible for the rest of the method
    for (int i = 0; i < 5; i++) {
        int y = i * x;   // y is created each iteration
    }
    // i and y are not accessible here
}
```

## Examples

| File | Demonstrates |
|------|-------------|
| [`MethodBasics.java`](src/main/java/course/ch03/examples/MethodBasics.java) | Defining, calling, return types, pass-by-value |
| [`Overloading.java`](src/main/java/course/ch03/examples/Overloading.java) | Overloaded methods and resolution rules |
| [`VarargsDemo.java`](src/main/java/course/ch03/examples/VarargsDemo.java) | Varargs syntax and usage |

## Exercises

### Exercise 1: Math Utilities (Guided)

**File:** [`MathUtils.java`](src/main/java/course/ch03/exercises/MathUtils.java)

Implement:
- `clamp(int value, int min, int max)` — constrains a value to a range
- `isPrime(int n)` — returns `true` if `n` is a prime number
- `factorial(int n)` — returns `n!` (throw `IllegalArgumentException` for negative input)

```bash
mvn test -Dtest="course.ch03.exercises.MathUtilsTest"
```

### Exercise 2: String Utilities (Practice)

**File:** [`StringUtils.java`](src/main/java/course/ch03/exercises/StringUtils.java)

Implement:
- `reverse(String s)` — returns the reversed string
- `isPalindrome(String s)` — returns `true` if `s` reads the same forwards and backwards (case-insensitive)
- `countOccurrences(String text, char target)` — returns how many times `target` appears in `text`

```bash
mvn test -Dtest="course.ch03.exercises.StringUtilsTest"
```

### Exercise 3: Overloaded `format` (Challenge)

**File:** [`Formatter.java`](src/main/java/course/ch03/exercises/Formatter.java)

Write overloaded `format` methods that produce formatted strings:
- `format(String label, int value)` → `"label: value"`
- `format(String label, double value)` → `"label: value"` (2 decimal places)
- `format(String label, String... values)` → `"label: [v1, v2, v3]"`

```bash
mvn test -Dtest="course.ch03.exercises.FormatterTest"
```

## Key Takeaways

- Methods are the fundamental unit of behavior — keep them **short**, **focused**, and **well-named**.
- Java is **pass-by-value** for everything: primitives are copied, object references are copied.
- Overloading provides convenience but should not be confused with polymorphism (Chapter 5).
- Varargs are syntactic sugar for array parameters.
- Keep variable scope as narrow as possible — declare variables where they are first used.

## Further Reading

- [JLS §8.4 — Method Declarations](https://docs.oracle.com/javase/specs/jls/se25/html/jls-8.html#jls-8.4)
- [JLS §15.12 — Method Invocation Expressions](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html#jls-15.12)
- Effective Java, Item 52: Use overloading judiciously
