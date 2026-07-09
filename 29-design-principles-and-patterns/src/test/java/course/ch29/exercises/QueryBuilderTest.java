package course.ch29.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QueryBuilderTest {

    @Test
    void simpleSelect() {
        String sql = new QueryBuilder()
                .select("id", "name")
                .from("users")
                .build();
        assertEquals("SELECT id, name FROM users", sql);
    }

    @Test
    void selectWithWhere() {
        String sql = new QueryBuilder()
                .select("name")
                .from("users")
                .where("active = true")
                .build();
        assertEquals("SELECT name FROM users WHERE active = true", sql);
    }

    @Test
    void selectWithOrderByAndLimit() {
        String sql = new QueryBuilder()
                .select("name", "email")
                .from("users")
                .orderBy("name")
                .limit(10)
                .build();
        assertEquals("SELECT name, email FROM users ORDER BY name LIMIT 10", sql);
    }

    @Test
    void fullQuery() {
        String sql = new QueryBuilder()
                .select("name")
                .from("users")
                .where("role = 'admin'")
                .orderBy("name")
                .limit(5)
                .build();
        assertEquals("SELECT name FROM users WHERE role = 'admin' ORDER BY name LIMIT 5", sql);
    }

    @Test
    void buildWithoutTableThrows() {
        assertThrows(IllegalStateException.class, () ->
                new QueryBuilder().select("id").build());
    }

    @Test
    void buildWithoutColumnsThrows() {
        assertThrows(IllegalStateException.class, () ->
                new QueryBuilder().from("users").build());
    }
}
