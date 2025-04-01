package restaurant.com.orderservice.web.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import restaurant.com.orderservice.order.model.OrderStatus;

@Data
public class OrderResponse {

    private Long id;

    private LocalDateTime orderDate;

    private LocalDateTime finishDate;

    private OrderStatus orderStatus;

    private UUID waiterId;

    private Long restaurantId;

    private UUID clientId;

    private List<OrderInfoResponse> orderInfoResponses;
}