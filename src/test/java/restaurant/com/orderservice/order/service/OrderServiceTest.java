package restaurant.com.orderservice.order.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import restaurant.com.orderservice.TestBuilder;
import restaurant.com.orderservice.exception.OrderNotFoundException;
import restaurant.com.orderservice.factory.OrderFactory;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.repository.OrderRepository;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderFactory orderFactory;

    @InjectMocks
    private OrderService orderService;

    @Test
    void whenGetAllOrders_thenReturnAllOrders() {
        List<Order> orders = new ArrayList();

        Order order = TestBuilder.createRandomOrder();
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> allOrders = orderService.getAllOrders();

        assertEquals(allOrders.size(), 1);
    }

    @Test
    void givenExistingOrderId_whenGetOrderById_thenReturnOrder() {
        Order orderExpected = TestBuilder.createRandomOrder();

        when(orderRepository.findById(orderExpected.getId())).thenReturn(Optional.of(orderExpected));

        Order orderActual = orderService.getOrderById(orderExpected.getId());

        assertEquals(orderExpected.getId(), orderActual.getId());
        verify(orderRepository).findById(orderExpected.getId());
    }

    @Test
    void givenNonExistingOrderId_whenGetOrderById_thenThrowsException() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        OrderNotFoundException orderNotFoundException = assertThrows(OrderNotFoundException.class,
                () -> orderService.getOrderById(orderId));

        assertEquals("Order with id " + orderId + " not found", orderNotFoundException.getMessage());
        verify(orderRepository).findById(orderId);
    }

    @Test
    void givenValidCreateOrderRequest_whenCreateOrder_thenReturnCreatedOrder() {
        CreateOrderRequest orderRequest = TestBuilder.createOrderRequest();
        Order expectedOrder = TestBuilder.createOrderFromCreateOrderRequest(orderRequest);

        when(orderFactory.createOrder(orderRequest)).thenReturn(expectedOrder);
        when(orderRepository.save(expectedOrder)).thenReturn(expectedOrder);

        Order orderActual = orderService.createOrder(orderRequest);

        assertEquals(expectedOrder.getId(), orderActual.getId());
        assertEquals(expectedOrder.getClientId(), orderActual.getClientId());
        assertEquals(expectedOrder.getOrderStatus(), orderActual.getOrderStatus());
        assertEquals(expectedOrder.getOrderDate(), orderActual.getOrderDate());
        assertEquals(expectedOrder.getRestaurantId(), orderActual.getRestaurantId());
        assertEquals(expectedOrder.getWaiterId(), orderActual.getWaiterId());
        assertEquals(expectedOrder.getFinishDate(), orderActual.getFinishDate());

        verify(orderRepository, times(1)).save(expectedOrder);
    }
}