package restaurant.com.orderservice.order.model;

import jakarta.persistence.*;
import lombok.Data;
import restaurant.com.orderservice.orderInfo.OrderInfo;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@Table(name="`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    private LocalDateTime finishDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private UUID waiterId;

    @Column(nullable = false)
    private Long restaurantId;

    private UUID clientId;

    @OneToMany(mappedBy = "order")
    private List<OrderInfo> orderInfo = new ArrayList<>();
}
