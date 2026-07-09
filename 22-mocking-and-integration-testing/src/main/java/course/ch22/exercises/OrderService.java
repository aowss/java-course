package course.ch22.exercises;

/**
 * Service for processing orders. Depends on {@link PaymentGateway}
 * and {@link InventoryService}.
 *
 * <p>Used in Exercise 2: students test this class by mocking both dependencies.
 */
public class OrderService {

    private final PaymentGateway paymentGateway;
    private final InventoryService inventoryService;

    public OrderService(PaymentGateway paymentGateway, InventoryService inventoryService) {
        this.paymentGateway = paymentGateway;
        this.inventoryService = inventoryService;
    }

    /**
     * Result of placing an order.
     *
     * @param success whether the order was successful
     * @param message a description of the result
     */
    public record OrderResult(boolean success, String message) {}

    /**
     * Places an order for a product.
     *
     * <p>Steps:
     * <ol>
     *   <li>Check inventory</li>
     *   <li>Charge payment</li>
     *   <li>Reserve inventory</li>
     * </ol>
     *
     * @param productId the product to order
     * @param quantity  the quantity
     * @param amount    the total amount in cents
     * @param cardToken the payment card token
     * @return the order result
     */
    public OrderResult placeOrder(String productId, int quantity, int amount, String cardToken) {
        if (!inventoryService.isInStock(productId, quantity)) {
            return new OrderResult(false, "Out of stock");
        }

        if (!paymentGateway.charge(amount, cardToken)) {
            return new OrderResult(false, "Payment failed");
        }

        inventoryService.reserve(productId, quantity);
        return new OrderResult(true, "Order placed successfully");
    }
}
