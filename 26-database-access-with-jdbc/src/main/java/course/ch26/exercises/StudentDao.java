package course.ch26.exercises;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Exercise 1 (Guided): Data access object for students.
 *
 * <p>Implement CRUD-style queries using {@link java.sql.PreparedStatement}.
 */
public class StudentDao {

    /**
     * A student record.
     *
     * @param id    primary key
     * @param name  student name
     * @param email student email
     */
    public record Student(int id, String name, String email) {
    }

    /**
     * Returns all students ordered by id.
     *
     * @param connection the database connection
     * @return all students
     * @throws SQLException if a database error occurs
     */
    public static List<Student> findAll(Connection connection) throws SQLException {
        // TODO: implement — SELECT id, name, email FROM students ORDER BY id
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Finds a student by id.
     *
     * @param connection the database connection
     * @param id         the student id
     * @return the student, or empty if not found
     * @throws SQLException if a database error occurs
     */
    public static Optional<Student> findById(Connection connection, int id) throws SQLException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Inserts a new student and returns the generated id.
     *
     * @param connection the database connection
     * @param name       the student name
     * @param email      the student email
     * @return the generated primary key
     * @throws SQLException if a database error occurs
     */
    public static int insert(Connection connection, String name, String email) throws SQLException {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        System.out.println("StudentDao — run tests with an in-memory H2 database.");
    }
}
