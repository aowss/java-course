package course.ch26.exercises;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDao {

    public record Student(int id, String name, String email) {
    }

    public static List<Student> findAll(Connection connection) throws SQLException {
        String sql = "SELECT id, name, email FROM students ORDER BY id";
        List<Student> students = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                students.add(mapRow(rs));
            }
        }
        return students;
    }

    public static Optional<Student> findById(Connection connection, int id) throws SQLException {
        String sql = "SELECT id, name, email FROM students WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }
                return Optional.of(mapRow(rs));
            }
        }
    }

    public static int insert(Connection connection, String name, String email) throws SQLException {
        String sql = "INSERT INTO students (name, email) VALUES (?, ?)";
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

    private static Student mapRow(ResultSet rs) throws SQLException {
        return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"));
    }

    public static void main(String[] args) {
        System.out.println("StudentDao — run tests with an in-memory H2 database.");
    }
}
