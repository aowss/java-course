package course.ch29.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 3 (Challenge): Build SQL queries fluently using the Builder pattern.
 *
 * <p>Implement a {@link QueryBuilder} that constructs SQL {@code SELECT} statements
 * with optional {@code WHERE}, {@code ORDER BY}, and {@code LIMIT} clauses.
 *
 * <pre>{@code
 * String sql = new QueryBuilder()
 *     .select("name", "email")
 *     .from("users")
 *     .where("active = true")
 *     .orderBy("name")
 *     .limit(10)
 *     .build();
 * }</pre>
 */
public final class QueryBuilder {

    private final List<String> columns = new ArrayList<>();
    private String table;
    private String whereClause;
    private String orderByColumn;
    private Integer limit;

    /**
     * Adds columns to the SELECT clause.
     *
     * @param cols the column names
     * @return this builder
     */
    public QueryBuilder select(String... cols) {
        // TODO: add columns to the list
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Sets the FROM table.
     *
     * @param tableName the table name
     * @return this builder
     */
    public QueryBuilder from(String tableName) {
        // TODO: set the table field
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Adds a WHERE clause.
     *
     * @param condition the WHERE condition (without the "WHERE" keyword)
     * @return this builder
     */
    public QueryBuilder where(String condition) {
        // TODO: set the whereClause field
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Adds an ORDER BY clause.
     *
     * @param column the column to sort by
     * @return this builder
     */
    public QueryBuilder orderBy(String column) {
        // TODO: set the orderByColumn field
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Adds a LIMIT clause.
     *
     * @param count the maximum number of rows
     * @return this builder
     */
    public QueryBuilder limit(int count) {
        // TODO: set the limit field
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Builds the SQL query string.
     *
     * <p>Format: {@code SELECT col1, col2 FROM table [WHERE ...] [ORDER BY ...] [LIMIT ...]}
     *
     * @return the SQL query
     * @throws IllegalStateException if no columns or table are set
     */
    public String build() {
        // TODO: assemble the SQL string from parts
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
