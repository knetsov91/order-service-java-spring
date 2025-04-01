package restaurant.com.orderservice.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.com.orderservice.order.model.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

//    Optional<List<Order>> findByWaiter(UUID orderId);

//    @Query("FROM Order o WHERE o.orderStatus = :status")
//    List<Order> getOrderByStatus(OrderStatus status);
}
