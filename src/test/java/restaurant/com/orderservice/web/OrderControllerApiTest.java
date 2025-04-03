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
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.orderInfo.model.OrderInfo;
import restaurant.com.orderservice.orderInfo.service.OrderInfoService;
import restaurant.com.orderservice.web.dto.ChangeOrderStatusRequest;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderInfoRequest;
import restaurant.com.orderservice.web.dto.OrderResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static restaurant.com.orderservice.web.mapper.DtoMapper.mapCreateOrderRequestToOrderInfo;
import static restaurant.com.orderservice.web.mapper.DtoMapper.mapListOrderToListOrderResponse;

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

    @Test
    void postRequestToCreateOrderWithValidBody_returns201Status() throws Exception {
        CreateOrderRequest validOrderRequest = TestBuilder.createValidOrderRequest();
        OrderInfoRequest orderInfoRequest = mapCreateOrderRequestToOrderInfo(validOrderRequest);
        Order order = new Order();
        order.setId(1L);

        MockHttpServletRequestBuilder content = post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(validOrderRequest));

        when(orderService.createOrder(validOrderRequest)).thenReturn(order);
        when(orderInfoService.addOrderInfoToOrder(orderInfoRequest, order.getId())).thenReturn(any());

        mockMvc.perform(content)
                .andExpect(status().isCreated());

        verify(orderService, times(1)).createOrder(validOrderRequest);
        verify(orderInfoService, times(1)).addOrderInfoToOrder(orderInfoRequest, order.getId());
    }

    @Test
    void putRequestToUpdateOrderStatus_returns200Status() throws Exception {
        ChangeOrderStatusRequest changeOrderStatusRequest = new ChangeOrderStatusRequest();
        changeOrderStatusRequest.setOrderStatus(OrderStatus.PLACED);

        MockHttpServletRequestBuilder req = put("/api/v1/orders/{orderId}/status", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(changeOrderStatusRequest));

        mockMvc.perform(req)
                .andExpect(status().is2xxSuccessful());

        verify(orderService, times(1)).changeOrderStatus(1L, changeOrderStatusRequest);
    }

    @Test
    void getRequestToFetchOrdersForRestaurant_returns200StatusAndBodyWithDto() throws Exception {
        MockHttpServletRequestBuilder req = get("/api/v1/orders/restaurants/{restaurantId}", 1L);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setQuantity(1);
        orderInfo.setPrice(BigDecimal.valueOf(11));
        orderInfo.setMenuItemId(UUID.randomUUID());

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setRestaurantId(1L);
        order.setOrderStatus(OrderStatus.PLACED);
        order.getOrderInfo().add(orderInfo);

        List<Order> orders = List.of(order);
        List<OrderResponse> orderResponses = mapListOrderToListOrderResponse(orders);

        when(orderService.getOrdersByRestaurantId(1L))
                .thenReturn(orders);

        mockMvc.perform(req)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].orderDate").isNotEmpty())
                .andExpect(jsonPath("$[0].restaurantId").isNotEmpty())
                .andExpect(jsonPath("$[0].orderStatus").isNotEmpty())
                .andExpect(jsonPath("$[0].orderInfoResponses").isNotEmpty());
    }
}