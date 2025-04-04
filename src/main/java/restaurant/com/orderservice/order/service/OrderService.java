package restaurant.com.orderservice.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.com.orderservice.exception.OrderNotFoundException;
import restaurant.com.orderservice.exception.RestaurantNotFoundException;
import restaurant.com.orderservice.exception.WaiterNotFoundException;
import restaurant.com.orderservice.factory.OrderFactory;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.order.repository.OrderRepository;
import restaurant.com.orderservice.web.dto.ChangeOrderStatusRequest;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private OrderFactory orderFactory;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderFactory orderFactory) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(CreateOrderRequest createOrderRequest) {
        Order order = orderFactory.createOrder(createOrderRequest);

        return orderRepository.save(order);
    }

    public void changeOrderStatus(Long orderId, ChangeOrderStatusRequest changeOrderStatusRequest) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));

        OrderStatus orderStatus = changeOrderStatusRequest.getOrderStatus();
        if (orderStatus == OrderStatus.COMPLETED || orderStatus == OrderStatus.CANCELLED) {
            order.setFinishDate(LocalDateTime.now());
        }
        order.setOrderStatus(orderStatus);

        orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
    }

    public List<Order> getOrdersByRestaurantId(Long restaurantId) {
        Optional<List<Order>> ordersOpt = orderRepository.findByRestaurantId(restaurantId);
        if (ordersOpt.isEmpty() || ordersOpt.get().size() == 0) {
            throw new RestaurantNotFoundException("Restaurant with id " + restaurantId + " not found");
        }
        return ordersOpt.get();
    }

    public List<Order> getOrdersByWaiterId(UUID waiterId) {
        Optional<List<Order>> orders = orderRepository.findByWaiterId(waiterId);
        if (orders.isEmpty() || orders.get().size() == 0) {
            throw new WaiterNotFoundException("Waiter with id " + waiterId + " not found");
        }
        return orders.get();
    }
}