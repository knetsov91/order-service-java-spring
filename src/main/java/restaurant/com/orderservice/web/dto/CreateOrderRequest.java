package restaurant.com.orderservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    private UUID waiterId;

    private Long restaurantId;

    private UUID clientId;

    private BigDecimal price;

    private UUID menuItemId;

    private int quantity;
}