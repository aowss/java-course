# Appendix: Operator Precedence and Evaluation

Chapter 2 introduces Java's operators at a practical level. This appendix goes deeper — useful for exams, code reviews, and debugging puzzling expressions.

## Evaluation Rules

When Java evaluates an expression, these rules apply (see [JLS §15.7](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html#jls-15.7)):

1. **Left-to-right operand evaluation** — In `a + b`, `a` is fully evaluated before `b` begins.
2. **Left-hand operand first** — For compound assignments and most binary operators, the left side is evaluated completely before the right side contributes to the operation.
3. **Operands before the operation** — Every operand is evaluated before the operator runs (exceptions: `&&`, `||`, and `?:` may skip the right operand).
4. **Parentheses and precedence** — Explicit `()` and implicit precedence determine grouping.
5. **Argument lists left-to-right** — Method arguments are evaluated in source order.

### Tricky Samples

```java
int[] a = {4, 4};
int b = 1;
a[b] = b = 0;          // b becomes 0; a[0] is assigned 0 (index evaluated before assignment)

int i = 2;
int j = (i = 3) * i;   // i is 3, then j = 3 * 3 = 9

int x = 9;
x += (x = 3);          // left x read as 9, then x = 9 + 3 = 12

int p = 3, q = 2;
p = p + q - (q = p);   // p read as 3, q read as 2, then q = 3, then p = 3 + 2 - 3 = 2
```

Short-circuit operators are the main exception to full operand evaluation:

```java
if (obj != null && obj.isValid()) { ... }   // isValid() skipped when obj is null
String label = expensive() ? "yes" : "no"; // only one branch runs
```

## Precedence Table

Higher rows bind tighter. Operators on the same row group together; use parentheses when in doubt.

| Precedence | Operators | Description |
|:----------:|-----------|-------------|
| 1 | `()` `[]` `.` | Parentheses, array access, member access |
| 2 | `++` `--` `+` `-` `!` `~` | Unary (postfix `++`/`--` bind like unary in practice) |
| 3 | `*` `/` `%` | Multiplicative |
| 4 | `+` `-` | Additive (also string concatenation when a `String` is involved) |
| 5 | `<<` `>>` `>>>` | Shift |
| 6 | `<` `<=` `>` `>=` `instanceof` | Relational |
| 7 | `==` `!=` | Equality |
| 8 | `&` | Bitwise AND |
| 9 | `^` | Bitwise XOR |
| 10 | `\|` | Bitwise OR |
| 11 | `&&` | Logical AND (short-circuit) |
| 12 | `\|\|` | Logical OR (short-circuit) |
| 13 | `?:` | Conditional (ternary) |
| 14 | `=` `+=` `-=` `*=` `/=` `%=` `&=` `\|=` `^=` `<<=` `>>=` `>>>=` | Assignment (right-associative) |

**Mnemonic:** Unary → multiplicative → additive → shift → relational → equality → bitwise → logical → ternary → assignment.

## Numeric Promotion

Binary operators promote operands to a common type before the operation runs.

### Integral types (`byte`, `short`, `char`, `int`, `long`)

- If either operand is `long`, both widen to `long` and the result is `long`.
- Otherwise both widen to `int` and the result is `int`.

```java
byte a = 1, b = 2;
int sum = a + b;   // byte + byte promotes to int — cannot assign to byte without cast

long big = 1L + 2; // result is long
```

### Floating-point types (`float`, `double`)

- If either operand is `double`, both widen to `double`.
- Otherwise both widen to `float`.

```java
float f = 1.0f + 2;    // 2 promotes to float
double d = 1.0f + 2.0; // float promotes to double
```

### Mixed integral and floating-point

If a binary arithmetic operator has a floating-point operand, the operation is floating-point — the integral operand promotes to `float` or `double` as above.

```java
double result = 10 / 4.0;   // 10 promotes to 10.0 → 2.5
int truncated = (int) (10 / 4.0);
```

## Operators by Operand Type

| Operand kind | Typical operators | Result type |
|--------------|-------------------|-------------|
| Integral | `+ - * / % << >> >>> & \| ^ ~ ++ --` comparisons | `int` or `long` |
| Floating-point | `+ - * / %` comparisons, `++ --` | `float` or `double` |
| `boolean` | `! & \| ^ && \|\|` comparisons | `boolean` |
| Reference | `== !=` (identity), `instanceof` | `boolean` |
| `String` involved in `+` | concatenation | `String` |

## Shift Operators

Shifts apply to integral types only. The right operand is the shift distance; distances larger than 31 (for `int`) or 63 (for `long`) are reduced modulo the bit width.

```java
int x = 1;
System.out.println(x << 33);   // same as x << 1 → 2
```

| Operator | Name | Vacated bits |
|----------|------|--------------|
| `<<` | Signed left shift | filled with `0` |
| `>>` | Signed right shift | filled with sign bit |
| `>>>` | Unsigned right shift | filled with `0` |

## Equality vs `equals()`

`==` compares primitives by value and references by identity (same object in memory). For semantic object comparison, override `equals()` — covered in Chapter 5.

```java
Integer a = 127, b = 127;
System.out.println(a == b);        // may be true (cached range)

Integer c = 128, d = 128;
System.out.println(c == d);        // false — different objects

Double x = Double.valueOf(1234);
Double y = Double.valueOf(1234);
System.out.println(x.equals(y));   // true — semantic comparison
```

## When to Use This Appendix

- **During Ch. 2** — skim the precedence table; return here if an expression surprises you.
- **Before interviews or exams** — work through the tricky samples by hand.
- **During code review** — simplify expressions whose precedence is not obvious; parentheses are free.

## Further Reading

- [JLS §15 — Expressions](https://docs.oracle.com/javase/specs/jls/se25/html/jls-15.html)
- [JLS §5.6 — Numeric Promotion](https://docs.oracle.com/javase/specs/jls/se25/html/jls-5.html#jls-5.6)
- Chapter 2: [Language Basics](../../02-language-basics/README.md)
