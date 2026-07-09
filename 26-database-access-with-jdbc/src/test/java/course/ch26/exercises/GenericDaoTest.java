package course.ch26.exercises;

import course.ch26.JdbcTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenericDaoTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = JdbcTestSupport.newConnection();
        JdbcTestSupport.createStudentsTable(connection);
    }

    @Test
    void queryMapsRowsToObjects() throws Exception {
        var students = GenericDao.query(
                connection,
                "SELECT id, name, email FROM students WHERE email LIKE ? ORDER BY id",
                rs -> new StudentDao.Student(rs.getInt("id"), rs.getString("name"), rs.getString("email")),
                "%@school.edu");
        assertEquals(2, students.size());
        assertEquals("Alice", students.get(0).name());
    }

    @Test
    void queryReturnsEmptyListWhenNoMatches() throws Exception {
        var students = GenericDao.query(
                connection,
                "SELECT id, name, email FROM students WHERE name = ?",
                rs -> new StudentDao.Student(rs.getInt("id"), rs.getString("name"), rs.getString("email")),
                "Nobody");
        assertTrue(students.isEmpty());
    }

    @Test
    void updateInsertsRow() throws Exception {
        int rows = GenericDao.update(
                connection,
                "INSERT INTO students (name, email) VALUES (?, ?)",
                "Dana", "dana@school.edu");
        assertEquals(1, rows);
        assertEquals(3, GenericDao.query(
                connection,
                "SELECT id, name, email FROM students",
                rs -> rs.getString("name")).size());
    }

    @Test
    void updateModifiesExistingRow() throws Exception {
        int rows = GenericDao.update(
                connection,
                "UPDATE students SET name = ? WHERE id = ?",
                "Alicia", 1);
        assertEquals(1, rows);
        var names = GenericDao.query(
                connection,
                "SELECT name FROM students WHERE id = ?",
                rs -> rs.getString("name"),
                1);
        assertEquals("Alicia", names.getFirst());
    }
}
