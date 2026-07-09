package course.ch12.exercises;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionReport {

    public record Transaction(String id, String category, double amount) {
    }

    public static double totalAmount(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::amount)
                .sum();
    }

    public static double totalExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.amount() < 0)
                .mapToDouble(t -> -t.amount())
                .sum();
    }

    public static Map<String, List<Transaction>> groupByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::category));
    }

    public static Map<String, Double> totalByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.summingDouble(Transaction::amount)));
    }

    public static List<String> topExpenseCategories(List<Transaction> transactions, int n) {
        return transactions.stream()
                .filter(t -> t.amount() < 0)
                .collect(Collectors.groupingBy(
                        Transaction::category,
                        Collectors.summingDouble(t -> -t.amount())))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
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
