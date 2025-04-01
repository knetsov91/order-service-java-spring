package restaurant.com.orderservice.orderInfo.service;

import org.springframework.stereotype.Service;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.orderInfo.OrderInfo;
import restaurant.com.orderservice.orderInfo.repository.OrderInfoRepository;
import restaurant.com.orderservice.web.dto.OrderInfoRequest;

@Service
public class OrderInfoService {

    private OrderInfoRepository repository;
    private OrderService orderService;

    public OrderInfoService(OrderInfoRepository repository, OrderService orderService) {
        this.repository = repository;
        this.orderService = orderService;
    }

    public OrderInfo addOrderInfoToOrder(OrderInfoRequest orderInfoRequest, Long orderId) {
        Order order = orderService.getOrderById(orderId);

        OrderInfo createOrderInfoRequest = new OrderInfo();
        createOrderInfoRequest.setMenuItemId(orderInfoRequest.getMenuItemId());
        createOrderInfoRequest.setPrice(orderInfoRequest.getPrice());
        createOrderInfoRequest.setQuantity(orderInfoRequest.getQuantity());
        createOrderInfoRequest.setOrder(order);

        return repository.save(createOrderInfoRequest);
    }
}