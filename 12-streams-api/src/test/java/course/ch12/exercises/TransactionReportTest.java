package course.ch12.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionReportTest {

    private final List<TransactionReport.Transaction> sample = List.of(
            new TransactionReport.Transaction("t1", "food", -25.50),
            new TransactionReport.Transaction("t2", "salary", 3000.00),
            new TransactionReport.Transaction("t3", "food", -12.00),
            new TransactionReport.Transaction("t4", "transport", -5.00)
    );

    @Test
    void totalAmount() {
        assertEquals(2957.50, TransactionReport.totalAmount(sample), 0.01);
    }

    @Test
    void totalExpenses() {
        assertEquals(42.50, TransactionReport.totalExpenses(sample), 0.01);
    }

    @Test
    void groupByCategory() {
        Map<String, List<TransactionReport.Transaction>> grouped = TransactionReport.groupByCategory(sample);
        assertEquals(2, grouped.get("food").size());
        assertEquals(1, grouped.get("salary").size());
    }

    @Test
    void totalByCategory() {
        Map<String, Double> totals = TransactionReport.totalByCategory(sample);
        assertEquals(-37.50, totals.get("food"), 0.01);
        assertEquals(3000.00, totals.get("salary"), 0.01);
    }

    @Test
    void topExpenseCategories() {
        assertEquals(List.of("food", "transport"),
                TransactionReport.topExpenseCategories(sample, 2));
    }

    @Test
    void topExpenseCategoriesLimitsN() {
        assertEquals(List.of("food"), TransactionReport.topExpenseCategories(sample, 1));
    }
}
