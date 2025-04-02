package restaurant.com.orderservice.order.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import restaurant.com.orderservice.TestBuilder;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

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
}