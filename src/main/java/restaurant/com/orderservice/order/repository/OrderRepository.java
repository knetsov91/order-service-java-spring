package restaurant.com.orderservice.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.com.orderservice.order.model.Order;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<List<Order>> findByRestaurantId(Long restaurantId);
    Optional<List<Order>> findByWaiterId(UUID waiterId);
}
