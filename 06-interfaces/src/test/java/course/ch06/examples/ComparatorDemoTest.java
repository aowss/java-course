package course.ch06.examples;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComparatorDemoTest {

    @Test
    void sortByPriceAscending() {
        var products = List.of(
                new ComparatorDemo.Product("Keyboard", 79.99),
                new ComparatorDemo.Product("Mouse", 29.99),
                new ComparatorDemo.Product("Monitor", 349.99)
        );
        var sorted = ComparatorDemo.sortByPrice(products);
        assertEquals(List.of("Mouse", "Keyboard", "Monitor"),
                sorted.stream().map(ComparatorDemo.Product::name).toList());
    }

    @Test
    void sortByNameLengthThenName() {
        var products = List.of(
                new ComparatorDemo.Product("Monitor", 100),
                new ComparatorDemo.Product("Pen", 5),
                new ComparatorDemo.Product("Pad", 10)
        );
        var sorted = ComparatorDemo.sortByNameLengthThenName(products);
        assertEquals(List.of("Pad", "Pen", "Monitor"),
                sorted.stream().map(ComparatorDemo.Product::name).toList());
    }

    @Test
    void mostExpensiveReturnsHighestPrice() {
        var products = List.of(
                new ComparatorDemo.Product("A", 10),
                new ComparatorDemo.Product("B", 50),
                new ComparatorDemo.Product("C", 30)
        );
        assertEquals("B", ComparatorDemo.mostExpensive(products).name());
    }

    @Test
    void sortByDescendingLength() {
        var words = List.of("java", "streams", "api", "optional");
        var sorted = ComparatorDemo.sortByDescendingLength(words);
        assertEquals(List.of("optional", "streams", "java", "api"), sorted);
    }
}
