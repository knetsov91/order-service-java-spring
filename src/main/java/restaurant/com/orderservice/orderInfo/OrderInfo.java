package restaurant.com.orderservice.orderInfo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restaurant.com.orderservice.order.model.Order;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "`order-info`")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal price;

    private UUID menuItemId;

    private int quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name="order_id", nullable=false)
    private Order order;
}