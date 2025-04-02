package restaurant.com.orderservice.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import restaurant.com.orderservice.TestBuilder;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.orderInfo.service.OrderInfoService;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @MockitoBean
    private OrderInfoService orderInfoService;

    @Test
    void getRequestOrders_returnAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        Order order = TestBuilder.createRandomOrder();
        orders.add(order);

        when(orderService.getAllOrders()).thenReturn(orders);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/orders");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").isNotEmpty())
                .andExpect(jsonPath("$[0].orderDate").isNotEmpty())
                .andExpect(jsonPath("$[0].orderStatus").isNotEmpty())
                .andExpect(jsonPath("$[0].waiterId").isNotEmpty())
                .andExpect(jsonPath("$[0].restaurantId").isNotEmpty())
                .andExpect(jsonPath("$[0].clientId").isNotEmpty())
        ;

        verify(orderService, times(1)).getAllOrders();
    }
}