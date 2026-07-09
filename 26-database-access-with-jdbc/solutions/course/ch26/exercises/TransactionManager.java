package course.ch26.exercises;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Supplier;

public class TransactionManager {

    public static <T> T executeInTransaction(Connection connection, Supplier<T> work) throws SQLException {
        boolean originalAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try {
            T result = work.get();
            connection.commit();
            return result;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } catch (RuntimeException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(originalAutoCommit);
        }
    }

    public static void executeInTransaction(Connection connection, Runnable work) throws SQLException {
        executeInTransaction(connection, () -> {
            work.run();
            return null;
        });
    }
}
