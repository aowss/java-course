package course.ch21.exercises;

import course.ch21.examples.ShoppingCart;

import org.junit.jupiter.api.DisplayName;

/**
 * Exercise 3 (Challenge): Write nested tests for {@link ShoppingCart}.
 *
 * <p>Organize your tests into nested classes representing different cart states:
 *
 * <h3>Outer class: ShoppingCartTest</h3>
 * <ul>
 *   <li>Create a new ShoppingCart in a {@code @BeforeEach}</li>
 * </ul>
 *
 * <h3>@Nested: WhenEmpty</h3>
 * <ul>
 *   <li>isEmpty() returns true</li>
 *   <li>itemCount() returns 0</li>
 *   <li>subtotal() returns 0</li>
 *   <li>total() returns 0</li>
 * </ul>
 *
 * <h3>@Nested: WhenHasItems (add items in @BeforeEach)</h3>
 * <ul>
 *   <li>isEmpty() returns false</li>
 *   <li>itemCount() reflects added items</li>
 *   <li>subtotal() computes correctly</li>
 *   <li>totalQuantity() sums quantities</li>
 *   <li>getItems() returns unmodifiable list</li>
 *   <li>clear() empties the cart</li>
 * </ul>
 *
 * <h3>@Nested inside WhenHasItems: WithDiscount (apply discount in @BeforeEach)</h3>
 * <ul>
 *   <li>getDiscountPercent() reflects the discount</li>
 *   <li>total() is subtotal minus discount</li>
 *   <li>invalid discount code throws IllegalArgumentException</li>
 * </ul>
 */
@DisplayName("Shopping Cart Tests")
class ShoppingCartTest {

    // TODO: Create a ShoppingCart field and initialize it in @BeforeEach

    // TODO: @Nested class WhenEmpty — test empty cart behavior

    // TODO: @Nested class WhenHasItems — add items in @BeforeEach, test non-empty behavior
    //       Inside WhenHasItems:
    //       TODO: @Nested class WithDiscount — apply "SAVE10" in @BeforeEach, test discounted total
}
