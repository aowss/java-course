---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 24'
footer: 'Date and Time API'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 24
## Date and Time API

---

## Objectives

- Use `LocalDate`, `LocalTime`, and `LocalDateTime` for calendar dates and wall-clock times
- Format and parse date-time values with `DateTimeFormatter`
- Measure elapsed time with `Duration` and calendar spans with `Period`
- Work with time zones using `ZoneId` and `ZonedDateTime`

---

## Why java.time?

Before Java 8, `java.util.Date` and `Calendar` were error-prone and **mutable**.

The **java.time** package (JSR-310) provides an **immutable, thread-safe** model.

| Type | Represents | Example use case |
|------|------------|------------------|
| `LocalDate` | Date without time or zone | Birthdays, due dates |
| `LocalTime` | Time without date or zone | Store opening hours |
| `LocalDateTime` | Date and time without zone | Meeting start (local) |
| `ZonedDateTime` | Date-time with time zone | Global events |
| `Instant` | Point on the UTC timeline | Event timestamps |

---

## Duration and Period

| Type | Based on | Example |
|------|----------|---------|
| `Duration` | Time (seconds, nanos) | 2 hours 30 minutes |
| `Period` | Calendar dates | 2 years, 3 months, 5 days |

```java
Duration elapsed = Duration.between(start, end);
Period age = Period.between(birthDate, today);
```

---

## Creating Values

```java
LocalDate date = LocalDate.of(2024, Month.MARCH, 15);
LocalTime time = LocalTime.of(14, 30);
LocalDateTime dateTime = LocalDateTime.of(date, time);

LocalDate parsed = LocalDate.parse("2024-03-15");
LocalDateTime now = LocalDateTime.now();
```

All values are **immutable** — methods like `plusDays()` return new instances.

---

## Formatting and Parsing

```java
DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE;
String text = date.format(iso);

DateTimeFormatter custom = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
LocalDateTime parsed = LocalDateTime.parse("15/03/2024 09:00", custom);
```

`DateTimeFormatter` is **immutable and thread-safe** — define formatters as constants.

---

## Pattern Letters

| Pattern | Meaning | Example |
|---------|---------|---------|
| `yyyy` | 4-digit year | 2024 |
| `MM` | 2-digit month | 03 |
| `dd` | 2-digit day | 15 |
| `HH` | Hour (0–23) | 14 |
| `mm` | Minute | 30 |
| `ss` | Second | 00 |

Use `DateTimeFormatter.ofPattern(...)` for custom formats.

---

## Time Zones

```java
ZoneId zone = ZoneId.of("America/New_York");
ZonedDateTime ny = localDateTime.atZone(zone);
ZonedDateTime tokyo = ny.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
```

`withZoneSameInstant` converts the same instant to another zone — local clock time changes, but the moment in time is preserved.

---

## Local vs Zoned

| Use | When |
|-----|------|
| `LocalDate` / `LocalDateTime` | Zone is implicit (birthdays, local meetings) |
| `ZonedDateTime` | Global scheduling, cross-region coordination |
| `Instant` | Storing event timestamps in UTC |

Never use legacy `Date`/`Calendar` in new code.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `DateTimeCreation` | Creating and parsing local dates and times |
| `DateTimeFormatting` | Formatting with ISO and custom patterns |
| `DurationAndPeriod` | Elapsed time and calendar periods |
| `ZonedDateTimeDemo` | Zone conversion and offsets |

```bash
mvn test -pl 24-date-and-time-api
```

---

## Exercises — Your Turn

1. **Age Calculator** (Guided) — age in years and as a full `Period`
2. **Meeting Scheduler** (Practice) — detect overlapping meetings, find next slot
3. **Time Zone Converter** (Challenge) — convert zoned date-times between regions

```bash
mvn test -pl 24-date-and-time-api -Dtest="AgeCalculatorTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Prefer **java.time** over legacy `Date` and `Calendar`
- Use **`LocalDate`/`LocalDateTime`** when the zone is implicit; **`ZonedDateTime`** for global scheduling
- **`Duration`** measures time; **`Period`** measures calendar dates
- **`DateTimeFormatter`** is immutable and thread-safe — define formatters as constants

Further reading: [java.time API](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/time/package-summary.html) · [JEP 150](https://openjdk.org/jeps/150)
