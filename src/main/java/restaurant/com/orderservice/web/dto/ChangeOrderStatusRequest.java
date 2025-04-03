package restaurant.com.orderservice.web.dto;

import lombok.Data;
import restaurant.com.orderservice.order.model.OrderStatus;

@Data
public class ChangeOrderStatusRequest {
    private OrderStatus orderStatus;
}
