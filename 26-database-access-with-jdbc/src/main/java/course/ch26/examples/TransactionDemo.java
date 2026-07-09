package course.ch26.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Demonstrates JDBC transaction management with commit and rollback.
 *
 * <p>Always restore the original auto-commit setting in a {@code finally} block.
 */
public class TransactionDemo {

    /**
     * Transfers funds between two accounts within a single transaction.
     *
     * @param connection  the database connection
     * @param fromId      the source account id
     * @param toId        the destination account id
     * @param amount      the amount to transfer (must be positive)
     * @throws SQLException if a database error occurs or insufficient funds
     */
    public static void transfer(Connection connection, int fromId, int toId, double amount)
            throws SQLException {
        boolean originalAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try {
            debit(connection, fromId, amount);
            credit(connection, toId, amount);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(originalAutoCommit);
        }
    }

    /**
     * Returns the balance of an account.
     *
     * @param connection the database connection
     * @param accountId  the account id
     * @return the current balance
     * @throws SQLException if a database error occurs
     */
    public static double getBalance(Connection connection, int accountId) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("Account not found: " + accountId);
                }
                return rs.getDouble("balance");
            }
        }
    }

    private static void debit(Connection connection, int accountId, double amount) throws SQLException {
        double balance = getBalance(connection, accountId);
        if (balance < amount) {
            throw new SQLException("Insufficient funds in account " + accountId);
        }
        String sql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }

    private static void credit(Connection connection, int accountId, double amount) throws SQLException {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, accountId);
            ps.executeUpdate();
        }
    }

    public static void main(String[] args) {
        System.out.println("TransactionDemo — run tests with an in-memory H2 database.");
    }
}
