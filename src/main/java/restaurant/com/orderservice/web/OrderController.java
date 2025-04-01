package restaurant.com.orderservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderResponse;
import restaurant.com.orderservice.web.mapper.DtoMapper;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam(name="type", required = false) OrderStatus orderStatus) {
        List<Order> allOrders = orderService.getAllOrders();
        List<OrderResponse> orderResponses = DtoMapper.mapListOrderToListOrderResponse(allOrders);

        return ResponseEntity.ok(orderResponses);
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody CreateOrderRequest createOrderRequest) {

        orderService.createOrder(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{orderId}/complete")
    public ResponseEntity completeOrder(@PathVariable Long orderId) {
        orderService.completeOrder(orderId);

        return ResponseEntity.ok().build();
    }
}