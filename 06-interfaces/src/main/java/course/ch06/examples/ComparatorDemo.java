package course.ch06.examples;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Demonstrates the {@link Comparator} functional interface and common factory methods.
 */
public class ComparatorDemo {

    /**
     * A simple product with a name and price.
     *
     * @param name  the product name
     * @param price the product price
     */
    public record Product(String name, double price) {
    }

    /**
     * Sorts products by price in ascending order.
     *
     * @param products the products to sort
     * @return a new list sorted by price
     */
    public static List<Product> sortByPrice(List<Product> products) {
        List<Product> copy = new ArrayList<>(products);
        copy.sort(Comparator.comparingDouble(Product::price));
        return copy;
    }

    /**
     * Sorts products by name length, then alphabetically by name.
     *
     * @param products the products to sort
     * @return a new list sorted by name length then name
     */
    public static List<Product> sortByNameLengthThenName(List<Product> products) {
        List<Product> copy = new ArrayList<>(products);
        Comparator<Product> byLength = Comparator.comparingInt(p -> p.name().length());
        copy.sort(byLength.thenComparing(Product::name));
        return copy;
    }

    /**
     * Returns the most expensive product in the list.
     *
     * @param products the products to search
     * @return the product with the highest price
     */
    public static Product mostExpensive(List<Product> products) {
        return products.stream()
                .max(Comparator.comparingDouble(Product::price))
                .orElseThrow();
    }

    /**
     * Reverses the natural order of strings by length.
     *
     * @param words the words to sort
     * @return a new list sorted by descending length
     */
    public static List<String> sortByDescendingLength(List<String> words) {
        List<String> copy = new ArrayList<>(words);
        copy.sort(Comparator.comparingInt(String::length).reversed());
        return copy;
    }

    public static void main(String[] args) {
        var products = List.of(
                new Product("Keyboard", 79.99),
                new Product("Mouse", 29.99),
                new Product("Monitor", 349.99)
        );

        System.out.println("By price: " + sortByPrice(products));
        System.out.println("Most expensive: " + mostExpensive(products));

        var words = List.of("java", "streams", "api", "optional");
        System.out.println("By descending length: " + sortByDescendingLength(words));
    }
}
