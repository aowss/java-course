package course.ch26.exercises;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Exercise 3 (Challenge): A generic query helper with a row mapper.
 *
 * <p>Parameterize result mapping so the same query logic works for any type.
 */
public class GenericDao {

    /**
     * Maps a single row of a {@link ResultSet} to a domain object.
     *
     * @param <T> the domain type
     */
    @FunctionalInterface
    public interface RowMapper<T> {

        /**
         * Maps the current row to an object.
         *
         * @param rs the result set positioned at a row
         * @return the mapped object
         * @throws SQLException if a database error occurs
         */
        T mapRow(ResultSet rs) throws SQLException;
    }

    /**
     * Executes a parameterized query and maps each row.
     *
     * @param connection the database connection
     * @param sql        the SQL query with {@code ?} placeholders
     * @param mapper     the row mapper
     * @param params     the parameter values in order
     * @param <T>        the result type
     * @return a list of mapped objects
     * @throws SQLException if a database error occurs
     */
    public static <T> List<T> query(
            Connection connection,
            String sql,
            RowMapper<T> mapper,
            Object... params) throws SQLException {
        // TODO: implement — PreparedStatement with setObject for each param
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Executes an update (INSERT, UPDATE, DELETE) with parameters.
     *
     * @param connection the database connection
     * @param sql        the SQL statement with {@code ?} placeholders
     * @param params     the parameter values in order
     * @return the number of rows affected
     * @throws SQLException if a database error occurs
     */
    public static int update(Connection connection, String sql, Object... params) throws SQLException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
