---
marp: true
theme: default
paginate: true
header: 'Java Course — Chapter 26'
footer: 'Database Access with JDBC'
style: |
  section { font-size: 28px; }
  h2 { color: #1a5276; }
  code { font-size: 0.85em; }
---

# Chapter 26
## Database Access with JDBC

---

## Objectives

- Connect to a database using `DriverManager` or `DataSource`
- Execute queries with `Statement` and `PreparedStatement`
- Read data from a `ResultSet`
- Prevent SQL injection with parameterized queries
- Manage transactions with commit and rollback
- Understand connection pooling concepts

---

## JDBC Architecture

JDBC (Java Database Connectivity) is the standard API for relational databases:

```
Application  →  JDBC API  →  JDBC Driver  →  Database
```

---

## Key JDBC Interfaces

| Interface | Role |
|-----------|------|
| `Connection` | A session with a specific database |
| `Statement` | Execute static SQL |
| `PreparedStatement` | Execute parameterized SQL (precompiled) |
| `ResultSet` | Cursor over query results |
| `DataSource` | Factory for connections (used by pools) |

---

## Connecting to a Database

```java
String url = "jdbc:h2:mem:testdb";
try (Connection conn = DriverManager.getConnection(url, "sa", "")) {
    // use connection
}
```

| Vendor | Example URL |
|--------|-------------|
| H2 (memory) | `jdbc:h2:mem:mydb;DB_CLOSE_DELAY=-1` |
| PostgreSQL | `jdbc:postgresql://localhost:5432/mydb` |
| MySQL | `jdbc:mysql://localhost:3306/mydb` |

Always close connections with **try-with-resources**.

---

## Statements and Result Sets

```java
try (Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT name FROM users")) {
    while (rs.next()) {
        String name = rs.getString("name");
    }
}
```

| Method | Description |
|--------|-------------|
| `next()` | Advance to the next row |
| `getString(col)` | Read a string column |
| `getInt(col)` / `getDouble(col)` | Read numeric columns |
| `wasNull()` | Check if last read value was SQL NULL |

---

## Prepared Statements

Use `PreparedStatement` for any query with user input — prevents **SQL injection**:

```java
String sql = "SELECT name FROM users WHERE id = ?";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setInt(1, userId);
    try (ResultSet rs = ps.executeQuery()) { /* process */ }
}
```

| Method | Parameter type |
|--------|----------------|
| `setString(i, v)` | String |
| `setInt(i, v)` | int |
| `setObject(i, v)` | any Object |
| `setNull(i, type)` | SQL NULL |

---

## SQL Injection — Avoid Concatenation

```java
// DANGEROUS — SQL injection risk
"SELECT * FROM users WHERE name = '" + userInput + "'"

// SAFE — parameterized
"SELECT * FROM users WHERE name = ?"
```

Prefer **`PreparedStatement`** over string concatenation for every user-supplied value.

---

## Inserting and Generated Keys

```java
String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql,
        PreparedStatement.RETURN_GENERATED_KEYS)) {
    ps.setString(1, name);
    ps.setString(2, email);
    ps.executeUpdate();
    try (ResultSet keys = ps.getGeneratedKeys()) {
        keys.next();
        int newId = keys.getInt(1);
    }
}
```

---

## Transactions

By default, JDBC uses **auto-commit** (each statement is its own transaction):

```java
conn.setAutoCommit(false);
try {
    debit(fromAccount, amount);
    credit(toAccount, amount);
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
    throw e;
} finally {
    conn.setAutoCommit(true);
}
```

Use transactions when multi-step operations must **succeed or fail together**.

---

## ACID Properties

| Property | Meaning |
|----------|---------|
| **Atomicity** | All operations succeed or all are rolled back |
| **Consistency** | Database moves from one valid state to another |
| **Isolation** | Concurrent transactions do not interfere |
| **Durability** | Committed changes survive crashes |

---

## Connection Pooling

Creating a new connection is expensive. Pools (HikariCP, Tomcat Pool) maintain reusable connections:

```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:postgresql://localhost/mydb");
HikariDataSource ds = new HikariDataSource(config);

try (Connection conn = ds.getConnection()) {
    // use pooled connection
}
```

In production, always use a pool — never `DriverManager` per request.

---

## Examples in This Chapter

| File | Topic |
|------|-------|
| `JdbcBasics` | `Statement`, `ResultSet`, row counting |
| `PreparedStatementDemo` | Parameterized queries, inserts, generated keys |
| `TransactionDemo` | Commit, rollback, fund transfer |

```bash
mvn test -pl 26-database-access-with-jdbc
```

---

## Exercises — Your Turn

1. **StudentDao** (Guided) — find and insert for a students table
2. **TransactionManager** (Practice) — wrap work in commit/rollback
3. **GenericDao** (Challenge) — reusable query helper with `RowMapper`

```bash
mvn test -pl 26-database-access-with-jdbc -Dtest="StudentDaoTest"
```

Full lesson: [`README.md`](README.md) · Solutions: `solutions/`

---

## Key Takeaways

- Always use **try-with-resources** for `Connection`, `Statement`, and `ResultSet`
- Prefer **`PreparedStatement`** over string concatenation to prevent SQL injection
- Use **transactions** for multi-step operations that must succeed or fail together
- In production, use a **connection pool** — not `DriverManager` per request
- Tests can use in-memory **H2** without external infrastructure

Further reading: [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html) · [HikariCP](https://github.com/brettwooldridge/HikariCP)
