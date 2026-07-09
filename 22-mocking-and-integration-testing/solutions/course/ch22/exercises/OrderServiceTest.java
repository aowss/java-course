package course.ch22.exercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("OrderService Tests")
class OrderServiceTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void placeOrderSucceedsWhenInStockAndPaymentOk() {
        when(inventoryService.isInStock("widget", 2)).thenReturn(true);
        when(paymentGateway.charge(5000, "tok_abc")).thenReturn(true);

        OrderService.OrderResult result = orderService.placeOrder("widget", 2, 5000, "tok_abc");

        assertTrue(result.success());
        verify(inventoryService).reserve("widget", 2);
    }

    @Test
    void placeOrderFailsWhenOutOfStock() {
        when(inventoryService.isInStock("widget", 2)).thenReturn(false);

        OrderService.OrderResult result = orderService.placeOrder("widget", 2, 5000, "tok_abc");

        assertFalse(result.success());
        verify(paymentGateway, never()).charge(anyInt(), anyString());
        verify(inventoryService, never()).reserve(anyString(), anyInt());
    }

    @Test
    void placeOrderFailsWhenPaymentFails() {
        when(inventoryService.isInStock("widget", 2)).thenReturn(true);
        when(paymentGateway.charge(5000, "tok_abc")).thenReturn(false);

        OrderService.OrderResult result = orderService.placeOrder("widget", 2, 5000, "tok_abc");

        assertFalse(result.success());
        verify(inventoryService, never()).reserve(anyString(), anyInt());
    }
}
