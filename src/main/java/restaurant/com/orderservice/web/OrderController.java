package restaurant.com.orderservice.web;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.orderInfo.service.OrderInfoService;
import restaurant.com.orderservice.web.dto.*;
import restaurant.com.orderservice.web.mapper.DtoMapper;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {

        Order order = orderService.createOrder(createOrderRequest);
        OrderInfoRequest orderInfoRequest = DtoMapper.mapCreateOrderRequestToOrderInfo(createOrderRequest);
        orderInfoService.addOrderInfoToOrder(orderInfoRequest, order.getId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity changeOrderStatus(@PathVariable(name = "orderId") Long orderId, @RequestBody ChangeOrderStatusRequest changeOrderStatusRequest) {
        orderService.changeOrderStatus(orderId, changeOrderStatusRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<List<OrderResponse>> getRestaurantOrders(@PathVariable Long restaurantId) {
        List<Order> orders = orderService.getOrdersByRestaurantId(restaurantId);
        List<OrderResponse> orderResponses = DtoMapper.mapListOrderToListOrderResponse(orders);

        return ResponseEntity.ok(orderResponses);
    }

    @PostMapping("{orderId}/order-infos")
    public ResponseEntity addOrderItemToOrder(@PathVariable Long orderId, @RequestBody OrderInfoRequest request) {
        orderInfoService.addOrderInfoToOrder(request, orderId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/waiters/{waiterId}")
    public ResponseEntity<List<OrderResponse>> getWaiterOrders(@PathVariable UUID waiterId) {
        List<Order> orders = orderService.getOrdersByWaiterId(waiterId);
        List<OrderResponse> orderResponses = DtoMapper.mapListOrderToListOrderResponse(orders);

        return ResponseEntity.ok(orderResponses);
    }
}