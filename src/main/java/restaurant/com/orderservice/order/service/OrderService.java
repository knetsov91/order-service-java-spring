package restaurant.com.orderservice.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.com.orderservice.factory.OrderFactory;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.order.repository.OrderRepository;
import restaurant.com.orderservice.orderInfo.service.OrderInfoService;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private OrderFactory orderFactory;
    private OrderInfoService orderInfoService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderFactory orderFactory, OrderInfoService orderInfoService) {
        this.orderRepository = orderRepository;
        this.orderFactory = orderFactory;
        this.orderInfoService = orderInfoService;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with id " + orderId + " not found"));
        order.setFinishDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.COMPLETED);

        orderRepository.save(order);
    }
}