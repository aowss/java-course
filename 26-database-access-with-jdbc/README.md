# Chapter 26: Database Access with JDBC

## Objectives

- Connect to a database using JDBC `DriverManager` or `DataSource`
- Execute queries with `Statement` and `PreparedStatement`
- Read data from a `ResultSet`
- Prevent SQL injection with parameterized queries
- Manage transactions with commit and rollback
- Understand connection pooling concepts

## Concepts

### JDBC Architecture

JDBC (Java Database Connectivity) is the standard API for accessing relational databases:

```
Application  →  JDBC API  →  JDBC Driver  →  Database
```

Key interfaces:

| Interface             | Role                                      |
|-----------------------|-------------------------------------------|
| `Connection`          | A session with a specific database        |
| `Statement`           | Execute static SQL                        |
| `PreparedStatement`   | Execute parameterized SQL (precompiled)   |
| `ResultSet`           | Cursor over query results                 |
| `DataSource`          | Factory for connections (used by pools)   |

### Connecting to a Database

```java
String url = "jdbc:h2:mem:testdb";
Connection conn = DriverManager.getConnection(url, "sa", "");
```

The URL format is `jdbc:<vendor>:<details>`. Common vendors:

| Vendor     | Example URL                                      |
|------------|--------------------------------------------------|
| H2 (memory)| `jdbc:h2:mem:mydb;DB_CLOSE_DELAY=-1`           |
| PostgreSQL | `jdbc:postgresql://localhost:5432/mydb`          |
| MySQL      | `jdbc:mysql://localhost:3306/mydb`               |

Always close connections (use try-with-resources):

```java
try (Connection conn = DriverManager.getConnection(url, user, pass)) {
    // use connection
}
```

### Statements and Result Sets

A basic query with `Statement`:

```java
try (Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("SELECT name FROM users")) {
    while (rs.next()) {
        String name = rs.getString("name");
    }
}
```

`ResultSet` cursor methods:

| Method              | Description                        |
|---------------------|------------------------------------|
| `next()`            | Advance to the next row            |
| `getString(col)`    | Read a string column by name/index |
| `getInt(col)`       | Read an integer column             |
| `getDouble(col)`    | Read a double column               |
| `wasNull()`         | Check if the last read value was SQL NULL |

### Prepared Statements

Use `PreparedStatement` for any query with user input — it prevents **SQL injection**:

```java
String sql = "SELECT name FROM users WHERE id = ?";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setInt(1, userId);
    try (ResultSet rs = ps.executeQuery()) {
        // process results
    }
}
```

| Method            | Parameter type |
|-------------------|----------------|
| `setString(i, v)` | String         |
| `setInt(i, v)`    | int            |
| `setDouble(i, v)` | double         |
| `setObject(i, v)` | any Object     |
| `setNull(i, type)`| SQL NULL       |

Never concatenate user input into SQL strings:

```java
// DANGEROUS — SQL injection risk
"SELECT * FROM users WHERE name = '" + userInput + "'"

// SAFE — parameterized
"SELECT * FROM users WHERE name = ?"
```

### Inserting and Generated Keys

```java
String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
    ps.setString(1, name);
    ps.setString(2, email);
    ps.executeUpdate();
    try (ResultSet keys = ps.getGeneratedKeys()) {
        keys.next();
        int newId = keys.getInt(1);
    }
}
```

### Transactions

By default, JDBC connections use **auto-commit** (each statement is its own transaction). For multi-step operations, disable auto-commit:

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

Transaction properties (ACID):

| Property    | Meaning                                          |
|-------------|--------------------------------------------------|
| Atomicity   | All operations succeed or all are rolled back    |
| Consistency | Database moves from one valid state to another   |
| Isolation   | Concurrent transactions do not interfere         |
| Durability  | Committed changes survive crashes                |

### Connection Pooling

Creating a new connection is expensive. **Connection pools** (HikariCP, Tomcat Pool) maintain a set of reusable connections:

```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:postgresql://localhost/mydb");
HikariDataSource ds = new HikariDataSource(config);

try (Connection conn = ds.getConnection()) {
    // use pooled connection
}
```

In production, always use a pool — never open a new `DriverManager` connection per request.

## Examples

| File                                                                                          | Demonstrates                                    |
|-----------------------------------------------------------------------------------------------|-------------------------------------------------|
| [`JdbcBasics.java`](src/main/java/course/ch26/examples/JdbcBasics.java)                       | `Statement`, `ResultSet`, row counting          |
| [`PreparedStatementDemo.java`](src/main/java/course/ch26/examples/PreparedStatementDemo.java)| Parameterized queries, inserts, generated keys  |
| [`TransactionDemo.java`](src/main/java/course/ch26/examples/TransactionDemo.java)             | Commit, rollback, fund transfer                 |

## Exercises

### Exercise 1: Student DAO (Guided)

**File:** [`StudentDao.java`](src/main/java/course/ch26/exercises/StudentDao.java)

Implement find and insert methods for a students table.

```bash
mvn test -pl 26-database-access-with-jdbc -Dtest="course.ch26.exercises.StudentDaoTest"
```

### Exercise 2: Transaction Manager (Practice)

**File:** [`TransactionManager.java`](src/main/java/course/ch26/exercises/TransactionManager.java)

Wrap arbitrary work in a commit/rollback transaction.

```bash
mvn test -pl 26-database-access-with-jdbc -Dtest="course.ch26.exercises.TransactionManagerTest"
```

### Exercise 3: Generic DAO (Challenge)

**File:** [`GenericDao.java`](src/main/java/course/ch26/exercises/GenericDao.java)

Build a reusable query helper with a `RowMapper` functional interface.

```bash
mvn test -pl 26-database-access-with-jdbc -Dtest="course.ch26.exercises.GenericDaoTest"
```

## Key Takeaways

- Always use **try-with-resources** for `Connection`, `Statement`, and `ResultSet`.
- Prefer **`PreparedStatement`** over string concatenation to prevent SQL injection.
- Use **transactions** for multi-step operations that must succeed or fail together.
- In production, use a **connection pool** — not `DriverManager` per request.
- Tests can use an in-memory **H2 database** without external infrastructure.

## Further Reading

- [JDBC Tutorial — Oracle](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)
- [java.sql.Connection — API docs](https://docs.oracle.com/en/java/javase/25/docs/api/java.sql/java/sql/Connection.html)
- [H2 Database](https://www.h2database.com/html/main.html)
- [HikariCP — Connection Pool](https://github.com/brettwooldridge/HikariCP)
