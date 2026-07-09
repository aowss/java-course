package course.ch26.examples;

import course.ch26.JdbcTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JdbcBasicsTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = JdbcTestSupport.newConnection();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS books (
                        id INT PRIMARY KEY,
                        title VARCHAR(200) NOT NULL
                    )""");
            stmt.execute("DELETE FROM books");
            stmt.execute("INSERT INTO books (id, title) VALUES (1, 'Java Basics')");
            stmt.execute("INSERT INTO books (id, title) VALUES (2, 'Streams Guide')");
        }
    }

    @Test
    void countRowsReturnsTableSize() throws Exception {
        assertEquals(2, JdbcBasics.countRows(connection, "books"));
    }

    @Test
    void queryStringColumnReturnsAllValues() throws Exception {
        List<String> titles = JdbcBasics.queryStringColumn(connection, "SELECT title FROM books ORDER BY id");
        assertEquals(List.of("Java Basics", "Streams Guide"), titles);
    }

    @Test
    void tableExistsDetectsExistingTable() throws Exception {
        assertTrue(JdbcBasics.tableExists(connection, "books"));
    }
}
