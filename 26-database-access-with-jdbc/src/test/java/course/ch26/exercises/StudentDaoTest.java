package course.ch26.exercises;

import course.ch26.JdbcTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentDaoTest {

    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        connection = JdbcTestSupport.newConnection();
        JdbcTestSupport.createStudentsTable(connection);
    }

    @Test
    void findAllReturnsAllStudents() throws Exception {
        var students = StudentDao.findAll(connection);
        assertEquals(2, students.size());
        assertEquals("Alice", students.get(0).name());
    }

    @Test
    void findByIdReturnsStudent() throws Exception {
        var student = StudentDao.findById(connection, 1);
        assertTrue(student.isPresent());
        assertEquals("alice@school.edu", student.orElseThrow().email());
    }

    @Test
    void findByIdReturnsEmptyForMissing() throws Exception {
        assertTrue(StudentDao.findById(connection, 999).isEmpty());
    }

    @Test
    void insertAddsNewStudent() throws Exception {
        int id = StudentDao.insert(connection, "Carol", "carol@school.edu");
        assertTrue(id > 0);
        assertEquals(3, StudentDao.findAll(connection).size());
        assertEquals("Carol", StudentDao.findById(connection, id).orElseThrow().name());
    }
}
