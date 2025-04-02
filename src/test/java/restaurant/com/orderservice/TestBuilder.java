package restaurant.com.orderservice;

import lombok.experimental.UtilityClass;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.model.OrderStatus;
import restaurant.com.orderservice.orderInfo.OrderInfo;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderInfoRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class TestBuilder {

    public static Order createRandomOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setOrderDate(LocalDateTime.now());
        order.setWaiterId(UUID.randomUUID());
        order.setClientId(UUID.randomUUID());
        order.setRestaurantId(1L);
        order.setFinishDate(LocalDateTime.now());

        return order;
    }

    public static OrderInfo createOrderInfoFromCreateOrderRequest(CreateOrderRequest createOrderRequest) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setQuantity(1);
        orderInfo.setPrice(createOrderRequest.getPrice());
        orderInfo.setMenuItemId(createOrderRequest.getMenuItemId());
        return orderInfo;
    }

    public static Order createOrderFromCreateOrderRequest(CreateOrderRequest createOrderRequest) {

        Order order = new Order();
        order.setId(1L);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setOrderDate(LocalDateTime.now());
        order.setWaiterId(createOrderRequest.getWaiterId());
        order.setClientId(createOrderRequest.getClientId());
        order.setRestaurantId(createOrderRequest.getRestaurantId());
        OrderInfo orderInfoFromCreateOrderRequest = createOrderInfoFromCreateOrderRequest(createOrderRequest);
        order.getOrderInfo().add(orderInfoFromCreateOrderRequest);

        return order;
    }

    public static CreateOrderRequest createOrderRequest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setClientId(UUID.randomUUID());
        createOrderRequest.setRestaurantId(1L);
        createOrderRequest.setClientId(UUID.randomUUID());
        createOrderRequest.setWaiterId(UUID.randomUUID());
        createOrderRequest.setMenuItemId(UUID.randomUUID());
        createOrderRequest.setPrice(BigDecimal.valueOf(1L));
        createOrderRequest.setQuantity(1);

        return createOrderRequest;
    }
    public static CreateOrderRequest createInvalidOrderRequest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setClientId(UUID.randomUUID());
        createOrderRequest.setClientId(UUID.randomUUID());
        createOrderRequest.setWaiterId(UUID.randomUUID());
        createOrderRequest.setMenuItemId(UUID.randomUUID());
        createOrderRequest.setPrice(BigDecimal.valueOf(1L));
        createOrderRequest.setQuantity(1);

        return createOrderRequest;
    }
}