package course.ch26.examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates basic JDBC operations: querying with {@link Statement}
 * and reading a {@link ResultSet}.
 */
public class JdbcBasics {

    /**
     * Counts all rows in the given table.
     *
     * @param connection the database connection
     * @param table      the table name
     * @return the row count
     * @throws SQLException if a database error occurs
     */
    public static int countRows(Connection connection, String table) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + table;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        }
    }

    /**
     * Queries a single string column and returns all values.
     *
     * @param connection the database connection
     * @param sql        the SQL query (must return one string column)
     * @return a list of string values in result order
     * @throws SQLException if a database error occurs
     */
    public static List<String> queryStringColumn(Connection connection, String sql) throws SQLException {
        List<String> results = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                results.add(rs.getString(1));
            }
        }
        return results;
    }

    /**
     * Checks whether a table exists in the current schema.
     *
     * @param connection the database connection
     * @param table      the table name
     * @return {@code true} if the table exists
     * @throws SQLException if a database error occurs
     */
    public static boolean tableExists(Connection connection, String table) throws SQLException {
        try (ResultSet rs = connection.getMetaData().getTables(null, null, table.toUpperCase(), null)) {
            return rs.next();
        }
    }

    public static void main(String[] args) {
        System.out.println("JdbcBasics — run tests with an in-memory H2 database.");
    }
}
