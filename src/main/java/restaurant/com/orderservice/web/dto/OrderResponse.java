package restaurant.com.orderservice.web.dto;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restaurant.com.orderservice.order.model.OrderStatus;


public record OrderResponse (
    UUID orderId,
    OrderStatus orderStatus,
    LocalDateTime completedAt,
    BigDecimal price,
    LocalDateTime createdAt,
    UUID waiter){
}
