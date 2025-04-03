package restaurant.com.orderservice.orderInfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.com.orderservice.orderInfo.model.OrderInfo;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
}