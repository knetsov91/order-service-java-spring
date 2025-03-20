package restaurant.com.orderservice.web;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.com.orderservice.exception.OrderNotFoundException;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderResponse;
import restaurant.com.orderservice.web.mapper.DtoMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam(name="type", required = false) OrderStatus orderStatus) {
        List<OrderResponse> allOrders = null;
        if (orderStatus != null) {
            allOrders = orderService.getOrdersByStatus(orderStatus).stream().map(DtoMapper::mapOrderToOrderResponse).toList();
        } else {
            allOrders = orderService.getAllOrders();
        }

        return ResponseEntity.ok(allOrders);
    }

    @PostMapping
    public ResponseEntity<UUID> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {

        UUID orderId = orderService.createOrder(createOrderRequest);
        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/{waiterId}")
    public ResponseEntity<List<OrderResponse>> getWaiterOrders(@PathVariable UUID waiterId) {

        List<OrderResponse> orders = orderService.getWaiterOrders(waiterId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/{orderStatus}")
    public ResponseEntity<OrderResponse> changeOrderStatus(@PathVariable UUID orderId, @PathVariable OrderStatus orderStatus) {

        orderService.changeOrderStatus(orderId, orderStatus);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .build();

    }

    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<List<OrderResponse>> getOrdersByStatus(@PathVariable OrderStatus orderStatus) {
        List<Order> orders = orderService.getOrdersByStatus(orderStatus);

        return ResponseEntity.ok(orders
                .stream()
                .map(DtoMapper::mapOrderToOrderResponse)
                .toList());
    }
}
