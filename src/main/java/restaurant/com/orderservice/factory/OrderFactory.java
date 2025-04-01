package restaurant.com.orderservice.factory;

import org.springframework.stereotype.Component;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import java.time.LocalDateTime;

@Component
public class OrderFactory {

    public Order createOrder(CreateOrderRequest createOrderRequest) {
        Order order = new Order();
        order.setClientId(createOrderRequest.getClientId());
        order.setOrderStatus(OrderStatus.PLACED);
        order.setOrderDate(LocalDateTime.now());
        order.setClientId(createOrderRequest.getClientId());
        order.setRestaurantId(createOrderRequest.getRestaurantId());
        order.setWaiterId(createOrderRequest.getWaiterId());

        return order;
    }
}