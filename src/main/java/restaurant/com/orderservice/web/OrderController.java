package restaurant.com.orderservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.orderInfo.service.OrderInfoService;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderInfoRequest;
import restaurant.com.orderservice.web.dto.OrderResponse;
import restaurant.com.orderservice.web.mapper.DtoMapper;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderInfoService orderInfoService;

    public OrderController(OrderService orderService, OrderInfoService orderInfoService) {
        this.orderService = orderService;
        this.orderInfoService = orderInfoService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam(name="type", required = false) OrderStatus orderStatus) {
        List<Order> allOrders = orderService.getAllOrders();
        List<OrderResponse> orderResponses = DtoMapper.mapListOrderToListOrderResponse(allOrders);

        return ResponseEntity.ok(orderResponses);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.ok(DtoMapper.mapOrderWithOrderInfoToOrderResponse(order, order.getOrderInfo()));
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody CreateOrderRequest createOrderRequest) {

        Order order = orderService.createOrder(createOrderRequest);
        OrderInfoRequest orderInfoRequest = DtoMapper.mapCreateOrderRequestToOrderInfo(createOrderRequest);
        orderInfoService.addOrderInfoToOrder(orderInfoRequest, order.getId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity completeOrder(@PathVariable Long orderId) {
        orderService.completeOrder(orderId);

        return ResponseEntity.ok().build();
    }
}