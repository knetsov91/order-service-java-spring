package restaurant.com.orderservice.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restaurant.com.orderservice.order.model.OrderStatus;

import java.math.BigDecimal;
import java.util.UUID;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    private BigDecimal price;

    private UUID waiter;

}
