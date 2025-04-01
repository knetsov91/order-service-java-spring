package restaurant.com.orderservice.web.mapper;

import restaurant.com.orderservice.orderInfo.OrderInfo;
import restaurant.com.orderservice.web.dto.OrderInfoResponse;

public class DtoMapper {

    public static OrderInfoResponse mapOrderInfoToOrderInfoResponse(OrderInfo order) {
        OrderInfoResponse orderInfoResponse = new OrderInfoResponse();
        orderInfoResponse.setPrice(order.getPrice());
        orderInfoResponse.setQuantity(order.getQuantity());
        orderInfoResponse.setMenuItemId(order.getMenuItemId());

        return orderInfoResponse;
    }
}
