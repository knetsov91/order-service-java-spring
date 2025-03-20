package restaurant.com.orderservice.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.com.orderservice.exception.OrderNotFoundException;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.order.repository.OrderRepository;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderResponse;
import restaurant.com.orderservice.web.mapper.DtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(DtoMapper::mapOrderToOrderResponse)
                .toList();
    }

    public UUID createOrder(CreateOrderRequest createOrderRequest) {

        Order order = DtoMapper.mapOrderToOrderResponse(createOrderRequest);
        orderRepository.save(order);
        LOGGER.info("Order {} created: {}", order.getId(), order.getCreatedAt());
        return order.getId();
    }

    public List<OrderResponse> getWaiterOrders(UUID waiterId) {

        Optional<List<Order>> orders = orderRepository.findByWaiter(waiterId);
        if (orders.isEmpty()) {
            throw new RuntimeException("Orders for [%s] not found".formatted(waiterId));
        }
        return orders
                .get()
                .stream()
                .map(DtoMapper::mapOrderToOrderResponse)
                .toList();

    }

    public void changeOrderStatus(UUID orderId, OrderStatus orderStatus) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            LOGGER.error("Order with id [%s] not found".formatted(orderId));
            throw new OrderNotFoundException("Order with id [%s] not found".formatted(orderId));
        }

        Order order = orderOptional.get();
        order.setOrderStatus(orderStatus);
        LOGGER.info("Order with id [%s] updated to %s status".formatted(orderId, orderStatus));

        orderRepository.save(order);
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus) {
        List<Order> orderByStatus = orderRepository.getOrderByStatus(orderStatus);
        return orderByStatus;
    }
}
