package course.ch14.exercises;

import java.util.List;
import java.util.Map;

/**
 * Exercise 3 (Challenge): Transaction reporting with streams and collectors.
 */
public class TransactionReport {

    /**
     * A financial transaction.
     *
     * @param id       the transaction id
     * @param category the spending category
     * @param amount   the amount (positive = income, negative = expense)
     */
    public record Transaction(String id, String category, double amount) {
    }

    /**
     * Returns the total amount across all transactions.
     *
     * @param transactions the transactions
     * @return the sum of all amounts
     */
    public static double totalAmount(List<Transaction> transactions) {
        // TODO: implement
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns total spending (sum of negative amounts as positive values).
     *
     * @param transactions the transactions
     * @return total expenses as a positive number
     */
    public static double totalExpenses(List<Transaction> transactions) {
        // TODO: implement — filter negative amounts, sum absolute values
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Groups transactions by category.
     *
     * @param transactions the transactions
     * @return a map from category to list of transactions
     */
    public static Map<String, List<Transaction>> groupByCategory(List<Transaction> transactions) {
        // TODO: implement — Collectors.groupingBy
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns total amount per category.
     *
     * @param transactions the transactions
     * @return a map from category to total amount
     */
    public static Map<String, Double> totalByCategory(List<Transaction> transactions) {
        // TODO: implement — groupingBy with summingDouble
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the top N categories by total spending (expenses only).
     *
     * @param transactions the transactions
     * @param n            the number of categories to return
     * @return the top category names sorted by expense amount descending
     */
    public static List<String> topExpenseCategories(List<Transaction> transactions, int n) {
        // TODO: implement — filter expenses, group, sort, limit
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static void main(String[] args) {
        var txns = List.of(
                new Transaction("t1", "food", -25.50),
                new Transaction("t2", "salary", 3000.00),
                new Transaction("t3", "food", -12.00),
                new Transaction("t4", "transport", -5.00)
        );
        System.out.println("Total:     " + totalAmount(txns));
        System.out.println("Expenses:  " + totalExpenses(txns));
        System.out.println("By cat:    " + totalByCategory(txns));
        System.out.println("Top food:  " + topExpenseCategories(txns, 2));
    }
}
