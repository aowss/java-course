package course.ch21.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple shopping cart that supports adding items, computing totals,
 * and applying discount codes.
 *
 * <p>This class is used as the production code for Exercise 3:
 * students write nested tests for this class covering empty, non-empty,
 * and discounted cart states.
 */
public class ShoppingCart {

    /**
     * Represents an item in the cart.
     *
     * @param name     the item name
     * @param price    the unit price (in cents to avoid floating-point issues)
     * @param quantity the quantity
     */
    public record Item(String name, int price, int quantity) {
        public Item {
            if (price < 0) throw new IllegalArgumentException("Price must not be negative");
            if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least 1");
        }

        /**
         * @return the total price for this line item
         */
        public int lineTotal() {
            return price * quantity;
        }
    }

    private final List<Item> items = new ArrayList<>();
    private int discountPercent = 0;

    /**
     * Adds an item to the cart.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item must not be null");
        }
        items.add(item);
    }

    /**
     * Removes all items from the cart and resets the discount.
     */
    public void clear() {
        items.clear();
        discountPercent = 0;
    }

    /**
     * @return the number of distinct line items in the cart
     */
    public int itemCount() {
        return items.size();
    }

    /**
     * @return {@code true} if the cart has no items
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * @return the total quantity of all items
     */
    public int totalQuantity() {
        return items.stream().mapToInt(Item::quantity).sum();
    }

    /**
     * @return an unmodifiable view of the items in the cart
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Computes the subtotal before any discount.
     *
     * @return the subtotal in cents
     */
    public int subtotal() {
        return items.stream().mapToInt(Item::lineTotal).sum();
    }

    /**
     * Applies a discount code. Only one discount can be active at a time.
     *
     * @param code the discount code
     * @throws IllegalArgumentException if the code is not recognized
     */
    public void applyDiscount(String code) {
        discountPercent = switch (code) {
            case "SAVE10" -> 10;
            case "SAVE20" -> 20;
            case "HALF"   -> 50;
            default -> throw new IllegalArgumentException("Unknown discount code: " + code);
        };
    }

    /**
     * @return the current discount percentage (0 if none applied)
     */
    public int getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Computes the total after applying the discount.
     *
     * @return the discounted total in cents
     */
    public int total() {
        int sub = subtotal();
        return sub - (sub * discountPercent / 100);
    }
}
