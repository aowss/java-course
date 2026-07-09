package course.ch26.exercises;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericDao {

    @FunctionalInterface
    public interface RowMapper<T> {

        T mapRow(ResultSet rs) throws SQLException;
    }

    public static <T> List<T> query(
            Connection connection,
            String sql,
            RowMapper<T> mapper,
            Object... params) throws SQLException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            bindParams(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(mapper.mapRow(rs));
                }
            }
        }
        return results;
    }

    public static int update(Connection connection, String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            bindParams(ps, params);
            return ps.executeUpdate();
        }
    }

    private static void bindParams(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }
}
