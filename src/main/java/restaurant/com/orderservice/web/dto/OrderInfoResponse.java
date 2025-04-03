package restaurant.com.orderservice.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OrderInfoResponse {

    private BigDecimal price;

    private UUID menuItemId;

    private int quantity;
    private Long orderId;
}
