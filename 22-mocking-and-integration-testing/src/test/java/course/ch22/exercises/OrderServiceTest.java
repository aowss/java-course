package course.ch22.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Exercise 2 (Practice): Test {@link OrderService} by mocking both dependencies.
 *
 * <p>Write tests for:
 * <ul>
 *   <li>Successful order when in stock and payment succeeds</li>
 *   <li>Failure when out of stock (payment should NOT be attempted)</li>
 *   <li>Failure when payment fails (inventory should NOT be reserved)</li>
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("OrderService Tests")
class OrderServiceTest {

    // TODO: @Mock PaymentGateway
    // TODO: @Mock InventoryService
    // TODO: @InjectMocks OrderService

    // TODO: test successful order placement
    // TODO: test out of stock returns failure without charging
    // TODO: test payment failure returns failure without reserving
}
