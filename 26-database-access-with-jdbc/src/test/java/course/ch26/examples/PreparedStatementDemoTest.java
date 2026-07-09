package course.ch26.examples;

import course.ch26.JdbcTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreparedStatementDemoTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = JdbcTestSupport.newConnection();
        JdbcTestSupport.createUsersTable(connection);
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM users");
            stmt.execute("INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com')");
            stmt.execute("INSERT INTO users (name, email) VALUES ('Bob', 'bob@test.org')");
        }
    }

    @Test
    void findUserNameByIdReturnsName() throws Exception {
        assertEquals("Alice", PreparedStatementDemo.findUserNameById(connection, 1).orElseThrow());
    }

    @Test
    void findUserNameByIdReturnsEmptyForMissing() throws Exception {
        assertTrue(PreparedStatementDemo.findUserNameById(connection, 999).isEmpty());
    }

    @Test
    void insertUserReturnsGeneratedId() throws Exception {
        int id = PreparedStatementDemo.insertUser(connection, "Carol", "carol@example.com");
        assertTrue(id > 0);
        assertEquals("Carol", PreparedStatementDemo.findUserNameById(connection, id).orElseThrow());
    }

    @Test
    void countByEmailPatternMatchesDomain() throws Exception {
        assertEquals(1, PreparedStatementDemo.countByEmailPattern(connection, "%@example.com"));
        assertEquals(2, PreparedStatementDemo.countByEmailPattern(connection, "%@%.%"));
    }
}
