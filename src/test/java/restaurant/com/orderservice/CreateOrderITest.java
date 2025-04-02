package restaurant.com.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.order.repository.OrderRepository;
import restaurant.com.orderservice.order.service.OrderService;
import restaurant.com.orderservice.orderInfo.OrderInfo;
import restaurant.com.orderservice.orderInfo.repository.OrderInfoRepository;
import restaurant.com.orderservice.orderInfo.service.OrderInfoService;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderInfoRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static restaurant.com.orderservice.TestBuilder.createOrderInfoRequestFromCreateOrderRequest;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CreateOrderITest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Test
    void createOrder() {
        CreateOrderRequest createOrderRequest =  TestBuilder.createOrderRequest();
        OrderInfoRequest orderInfoRequest = createOrderInfoRequestFromCreateOrderRequest(createOrderRequest);

        OrderInfo orderInfo = TestBuilder.createOrderInfoFromCreateOrderRequest(createOrderRequest);

        Order orderActual = orderService.createOrder(createOrderRequest);
        OrderInfo orderInfoActual = orderInfoService.addOrderInfoToOrder(orderInfoRequest, orderActual.getId());

        assertEquals(1, orderRepository.count());
        assertEquals(1, orderInfoRepository.count());
    }
}