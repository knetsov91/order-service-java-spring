package restaurant.com.orderservice.web.mapper;

import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderResponse;

import java.time.LocalDateTime;

public class DtoMapper {

    public static OrderResponse mapOrderToOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderStatus(),
                order.getCompletedAt(),
                order.getPrice(),
                order.getCreatedAt(),
                order.getWaiter());
    }

    public static Order mapOrderToOrderResponse(CreateOrderRequest createOrderRequest) {
        return Order
                .builder()
                .price(createOrderRequest.getPrice())
                .orderStatus(OrderStatus.PLACED)
                .waiter(createOrderRequest.getWaiter())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
