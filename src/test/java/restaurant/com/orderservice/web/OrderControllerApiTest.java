package restaurant.com.orderservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import restaurant.com.orderservice.TestBuilder;
import restaurant.com.orderservice.exception.OrderNotFoundException;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.orderInfo.service.OrderInfoService;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        MockHttpServletRequestBuilder request = get("/api/v1/orders");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").isNotEmpty())
                .andExpect(jsonPath("$[0].orderDate").isNotEmpty())
                .andExpect(jsonPath("$[0].orderStatus").isNotEmpty())
                .andExpect(jsonPath("$[0].waiterId").isNotEmpty())
                .andExpect(jsonPath("$[0].restaurantId").isNotEmpty())
                .andExpect(jsonPath("$[0].clientId").isNotEmpty());

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void getRequestWithExistingOrderId_returnOrder() throws Exception {
        Order order = TestBuilder.createRandomOrder();
        Long orderId = order.getId();
        when(orderService.getOrderById(orderId)).thenReturn(order);

        MockHttpServletRequestBuilder request = get("/api/v1/orders/{orderId}", orderId);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderId").isNotEmpty())
        ;
        verify(orderService, times(1)).getOrderById(orderId);
    }

    @Test
    void getRequestWithNonExistingOrderId_throwsExceptionAndReturnStatusCode500() throws Exception {
        when(orderService.getOrderById(anyLong())).thenThrow(OrderNotFoundException.class);

        MockHttpServletRequestBuilder request = get("/api/v1/orders/{orderId}", 2L);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").isNotEmpty())
                .andExpect(jsonPath("statusCode").isNotEmpty())
                .andExpect(jsonPath("time").isNotEmpty());
        verify(orderService, times(1)).getOrderById(anyLong());
    }

    @Test
    void postRequestWithMissingRequiredBodyField_throwsExceptionAndReturnStatusCode400() throws Exception {
        CreateOrderRequest invalidOrderRequest = TestBuilder.createInvalidOrderRequest();

        MockHttpServletRequestBuilder post = post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(invalidOrderRequest));

        mockMvc.perform(post)
                .andExpect(status().is4xxClientError());
    }
}