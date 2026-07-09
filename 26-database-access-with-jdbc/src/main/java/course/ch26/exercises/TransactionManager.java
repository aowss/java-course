package course.ch26.exercises;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;

/**
 * Exercise 2 (Practice): Execute work inside a JDBC transaction.
 *
 * <p>Disable auto-commit, run the supplier, then commit or rollback.
 */
public class TransactionManager {

    /**
     * Executes the given work inside a transaction.
     *
     * <p>On success, commits the transaction. On {@link SQLException},
     * rolls back and rethrows. Restores the original auto-commit setting.
     *
     * @param connection the database connection
     * @param work       the work to execute
     * @param <T>        the result type
     * @return the result of the work
     * @throws SQLException if a database error occurs
     */
    public static <T> T executeInTransaction(Connection connection, Supplier<T> work) throws SQLException {
        // TODO: implement — setAutoCommit(false), try/commit, catch/rollback
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Executes the given runnable inside a transaction.
     *
     * @param connection the database connection
     * @param work       the work to execute
     * @throws SQLException if a database error occurs
     */
    public static void executeInTransaction(Connection connection, Runnable work) throws SQLException {
        // TODO: implement — delegate to the Supplier overload
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
