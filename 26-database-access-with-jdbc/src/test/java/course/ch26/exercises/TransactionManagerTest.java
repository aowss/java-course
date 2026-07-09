package course.ch26.exercises;

import course.ch26.JdbcTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionManagerTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = JdbcTestSupport.newConnection();
        JdbcTestSupport.createAccountsTable(connection);
    }

    @Test
    void executeInTransactionCommitsOnSuccess() throws Exception {
        TransactionManager.executeInTransaction(connection, () -> {
            try (var ps = connection.prepareStatement("UPDATE accounts SET balance = balance - 100 WHERE id = 1")) {
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        assertEquals(900.0, queryBalance(1), 0.001);
    }

    @Test
    void executeInTransactionRollsBackOnFailure() throws Exception {
        assertThrows(RuntimeException.class, () ->
                TransactionManager.executeInTransaction(connection, () -> {
                    try (var ps = connection.prepareStatement(
                            "UPDATE accounts SET balance = balance - 100 WHERE id = 1")) {
                        ps.executeUpdate();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    throw new RuntimeException("simulated failure");
                }));
        assertEquals(1000.0, queryBalance(1), 0.001);
    }

    @Test
    void executeInTransactionSupplierReturnsValue() throws Exception {
        Double balance = TransactionManager.executeInTransaction(connection, () -> {
            try {
                return queryBalance(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        assertEquals(1000.0, balance, 0.001);
    }

    private double queryBalance(int id) throws SQLException {
        try (var stmt = connection.createStatement();
             var rs = stmt.executeQuery("SELECT balance FROM accounts WHERE id = " + id)) {
            rs.next();
            return rs.getDouble(1);
        }
    }
}
