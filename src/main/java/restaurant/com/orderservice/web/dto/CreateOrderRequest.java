package restaurant.com.orderservice.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "Restaurant Id is required.")
    private Long restaurantId;

    private UUID clientId;

    @NotNull(message = "Price is required.")
    private BigDecimal price;

    @NotNull(message = "Menu item Id is required.")
    private UUID menuItemId;

    @NotNull
    @Positive(message = "Quantity must be greater then zero.")
    private int quantity;
}