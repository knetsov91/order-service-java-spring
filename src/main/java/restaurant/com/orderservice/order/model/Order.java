package restaurant.com.orderservice.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = true)
    private LocalDateTime completedAt;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    LocalDateTime createdAt;

    private UUID waiter;

    @Transient
    private List<UUID> menuItem;
}
