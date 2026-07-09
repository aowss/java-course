package course.ch26.examples;

import course.ch26.JdbcTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionDemoTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = JdbcTestSupport.newConnection();
        JdbcTestSupport.createAccountsTable(connection);
    }

    @Test
    void transferMovesFundsBetweenAccounts() throws Exception {
        TransactionDemo.transfer(connection, 1, 2, 200.0);
        assertEquals(800.0, TransactionDemo.getBalance(connection, 1), 0.001);
        assertEquals(700.0, TransactionDemo.getBalance(connection, 2), 0.001);
    }

    @Test
    void transferRollsBackOnInsufficientFunds() throws Exception {
        assertThrows(SQLException.class, () -> TransactionDemo.transfer(connection, 1, 2, 5000.0));
        assertEquals(1000.0, TransactionDemo.getBalance(connection, 1), 0.001);
        assertEquals(500.0, TransactionDemo.getBalance(connection, 2), 0.001);
    }
}
