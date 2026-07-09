package course.ch21.exercises;

import course.ch21.examples.ShoppingCart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Shopping Cart Tests")
class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        cart = new ShoppingCart();
    }

    @Nested
    @DisplayName("when empty")
    class WhenEmpty {

        @Test
        @DisplayName("isEmpty() returns true")
        void isEmpty() {
            assertTrue(cart.isEmpty());
        }

        @Test
        @DisplayName("itemCount() returns 0")
        void itemCountIsZero() {
            assertEquals(0, cart.itemCount());
        }

        @Test
        @DisplayName("subtotal() returns 0")
        void subtotalIsZero() {
            assertEquals(0, cart.subtotal());
        }

        @Test
        @DisplayName("total() returns 0")
        void totalIsZero() {
            assertEquals(0, cart.total());
        }
    }

    @Nested
    @DisplayName("when has items")
    class WhenHasItems {

        @BeforeEach
        void addItems() {
            cart.addItem(new ShoppingCart.Item("Widget", 1000, 2));
            cart.addItem(new ShoppingCart.Item("Gadget", 2500, 1));
        }

        @Test
        @DisplayName("isEmpty() returns false")
        void isNotEmpty() {
            assertFalse(cart.isEmpty());
        }

        @Test
        @DisplayName("itemCount() reflects added items")
        void itemCountReflectsItems() {
            assertEquals(2, cart.itemCount());
        }

        @Test
        @DisplayName("subtotal() computes correctly")
        void subtotalIsCorrect() {
            assertEquals(4500, cart.subtotal());
        }

        @Test
        @DisplayName("totalQuantity() sums quantities")
        void totalQuantityIsCorrect() {
            assertEquals(3, cart.totalQuantity());
        }

        @Test
        @DisplayName("getItems() returns unmodifiable list")
        void getItemsIsUnmodifiable() {
            assertThrows(UnsupportedOperationException.class,
                    () -> cart.getItems().add(new ShoppingCart.Item("Hack", 1, 1)));
        }

        @Test
        @DisplayName("clear() empties the cart")
        void clearEmptiesCart() {
            cart.clear();
            assertTrue(cart.isEmpty());
            assertEquals(0, cart.subtotal());
        }

        @Nested
        @DisplayName("with discount applied")
        class WithDiscount {

            @BeforeEach
            void applyDiscount() {
                cart.applyDiscount("SAVE10");
            }

            @Test
            @DisplayName("getDiscountPercent() reflects the discount")
            void discountPercentIsSet() {
                assertEquals(10, cart.getDiscountPercent());
            }

            @Test
            @DisplayName("total() is subtotal minus discount")
            void totalReflectsDiscount() {
                assertEquals(4050, cart.total());
            }

            @Test
            @DisplayName("invalid discount code throws")
            void invalidCodeThrows() {
                assertThrows(IllegalArgumentException.class,
                        () -> cart.applyDiscount("INVALID"));
            }
        }
    }
}
