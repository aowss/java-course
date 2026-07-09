package course.ch22.exercises;

/**
 * Inventory management interface used in Exercise 2.
 */
public interface InventoryService {

    /**
     * Checks if a product is in stock.
     *
     * @param productId the product identifier
     * @param quantity  the requested quantity
     * @return {@code true} if sufficient stock is available
     */
    boolean isInStock(String productId, int quantity);

    /**
     * Reserves stock for an order.
     *
     * @param productId the product identifier
     * @param quantity  the quantity to reserve
     */
    void reserve(String productId, int quantity);
}
