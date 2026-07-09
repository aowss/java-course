package course.ch29.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class QueryBuilder {

    private final List<String> columns = new ArrayList<>();
    private String table;
    private String whereClause;
    private String orderByColumn;
    private Integer limit;

    public QueryBuilder select(String... cols) {
        for (String col : cols) {
            columns.add(col);
        }
        return this;
    }

    public QueryBuilder from(String tableName) {
        this.table = tableName;
        return this;
    }

    public QueryBuilder where(String condition) {
        this.whereClause = condition;
        return this;
    }

    public QueryBuilder orderBy(String column) {
        this.orderByColumn = column;
        return this;
    }

    public QueryBuilder limit(int count) {
        this.limit = count;
        return this;
    }

    public String build() {
        if (columns.isEmpty()) {
            throw new IllegalStateException("No columns specified");
        }
        if (table == null || table.isBlank()) {
            throw new IllegalStateException("No table specified");
        }

        StringJoiner joiner = new StringJoiner(", ");
        for (String col : columns) {
            joiner.add(col);
        }

        StringBuilder sql = new StringBuilder("SELECT ")
                .append(joiner)
                .append(" FROM ")
                .append(table);

        if (whereClause != null) {
            sql.append(" WHERE ").append(whereClause);
        }
        if (orderByColumn != null) {
            sql.append(" ORDER BY ").append(orderByColumn);
        }
        if (limit != null) {
            sql.append(" LIMIT ").append(limit);
        }

        return sql.toString();
    }
}
