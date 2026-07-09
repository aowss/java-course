package course.ch26.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Demonstrates {@link PreparedStatement} for parameterized queries.
 *
 * <p>Prepared statements prevent SQL injection and allow efficient
 * re-execution with different parameter values.
 */
public class PreparedStatementDemo {

    /**
     * Finds a user name by id using a parameterized query.
     *
     * @param connection the database connection
     * @param id         the user id
     * @return the user name, or empty if not found
     * @throws SQLException if a database error occurs
     */
    public static Optional<String> findUserNameById(Connection connection, int id) throws SQLException {
        String sql = "SELECT name FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getString("name"));
                }
                return Optional.empty();
            }
        }
    }

    /**
     * Inserts a new user and returns the generated id.
     *
     * @param connection the database connection
     * @param name       the user name
     * @param email      the user email
     * @return the generated primary key
     * @throws SQLException if a database error occurs
     */
    public static int insertUser(Connection connection, String name, String email) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                return keys.getInt(1);
            }
        }
    }

    /**
     * Counts users whose email matches a LIKE pattern.
     *
     * @param connection   the database connection
     * @param emailPattern the LIKE pattern (e.g. {@code "%@example.com"})
     * @return the number of matching users
     * @throws SQLException if a database error occurs
     */
    public static int countByEmailPattern(Connection connection, String emailPattern) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE email LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, emailPattern);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("PreparedStatementDemo — run tests with an in-memory H2 database.");
    }
}
