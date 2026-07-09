# Chapter 24: Date and Time API

## Objectives

- Use `LocalDate`, `LocalTime`, and `LocalDateTime` for calendar dates and wall-clock times
- Format and parse date-time values with `DateTimeFormatter`
- Measure elapsed time with `Duration` and calendar spans with `Period`
- Work with time zones using `ZoneId` and `ZonedDateTime`

## Concepts

### Why java.time?

Before Java 8, `java.util.Date` and `Calendar` were error-prone and mutable. The **java.time** package (JSR-310) provides an immutable, thread-safe model:

| Type              | Represents                          | Example use case              |
|-------------------|-------------------------------------|-------------------------------|
| `LocalDate`       | Date without time or zone           | Birthdays, due dates          |
| `LocalTime`       | Time without date or zone           | Store opening hours           |
| `LocalDateTime`   | Date and time without zone          | Meeting start (local)         |
| `ZonedDateTime`   | Date-time with time zone            | Global events                 |
| `Instant`         | Point on the UTC timeline           | Event timestamps              |
| `Duration`        | Time-based amount (hours, minutes)  | Elapsed time, timeouts        |
| `Period`          | Date-based amount (years, months)   | Age, billing cycles           |

### Creating Values

```java
LocalDate date = LocalDate.of(2024, Month.MARCH, 15);
LocalTime time = LocalTime.of(14, 30);
LocalDateTime dateTime = LocalDateTime.of(date, time);

LocalDate parsed = LocalDate.parse("2024-03-15");
```

### Formatting and Parsing

```java
DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE;
String text = date.format(iso);

DateTimeFormatter custom = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
LocalDateTime parsed = LocalDateTime.parse("15/03/2024 09:00", custom);
```

Common pattern letters:

| Pattern | Meaning          | Example |
|---------|------------------|---------|
| `yyyy`  | 4-digit year     | 2024    |
| `MM`    | 2-digit month    | 03      |
| `dd`    | 2-digit day      | 15      |
| `HH`    | Hour (0–23)      | 14      |
| `mm`    | Minute           | 30      |
| `ss`    | Second           | 00      |

### Duration vs Period

```java
Duration elapsed = Duration.between(start, end);
Period age = Period.between(birthDate, today);
```

| Type       | Based on        | Example                    |
|------------|-----------------|----------------------------|
| `Duration` | Time (seconds)  | 2 hours 30 minutes         |
| `Period`   | Calendar dates  | 2 years, 3 months, 5 days  |

### Time Zones

```java
ZoneId zone = ZoneId.of("America/New_York");
ZonedDateTime ny = localDateTime.atZone(zone);
ZonedDateTime tokyo = ny.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
```

`withZoneSameInstant` converts the same instant to another zone. The local clock time changes, but the moment in time is preserved.

## Examples

| File                                                                                        | Demonstrates                              |
|---------------------------------------------------------------------------------------------|-------------------------------------------|
| [`DateTimeCreation.java`](src/main/java/course/ch24/examples/DateTimeCreation.java)         | Creating and parsing local dates and times |
| [`DateTimeFormatting.java`](src/main/java/course/ch24/examples/DateTimeFormatting.java)     | Formatting with ISO and custom patterns   |
| [`DurationAndPeriod.java`](src/main/java/course/ch24/examples/DurationAndPeriod.java)       | Elapsed time and calendar periods         |
| [`ZonedDateTimeDemo.java`](src/main/java/course/ch24/examples/ZonedDateTimeDemo.java)       | Zone conversion and offsets               |

## Exercises

### Exercise 1: Age Calculator (Guided)

**File:** [`AgeCalculator.java`](src/main/java/course/ch24/exercises/AgeCalculator.java)

Calculate a person's age in years and as a full `Period`.

```bash
mvn test -Dtest="course.ch24.exercises.AgeCalculatorTest"
```

### Exercise 2: Meeting Scheduler (Practice)

**File:** [`MeetingScheduler.java`](src/main/java/course/ch24/exercises/MeetingScheduler.java)

Detect overlapping meetings and find the next available time slot.

```bash
mvn test -Dtest="course.ch24.exercises.MeetingSchedulerTest"
```

### Exercise 3: Time Zone Converter (Challenge)

**File:** [`TimeZoneConverter.java`](src/main/java/course/ch24/exercises/TimeZoneConverter.java)

Convert zoned date-times between regions while preserving the instant.

```bash
mvn test -Dtest="course.ch24.exercises.TimeZoneConverterTest"
```

## Key Takeaways

- Prefer **java.time** over legacy `Date` and `Calendar`.
- Use **`LocalDate`/`LocalDateTime`** when the zone is implicit; use **`ZonedDateTime`** for global scheduling.
- **`Duration`** measures time; **`Period`** measures calendar dates.
- **`DateTimeFormatter`** is immutable and thread-safe — define formatters as constants.

## Further Reading

- [java.time — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/time/package-summary.html)
- [Date Time Formatter — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/time/format/DateTimeFormatter.html)
- [JEP 150: Date & Time API](https://openjdk.org/jeps/150)
