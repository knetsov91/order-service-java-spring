package restaurant.com.orderservice.web.mapper;

import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.orderInfo.OrderInfo;
import restaurant.com.orderservice.web.dto.OrderInfoResponse;
import restaurant.com.orderservice.web.dto.OrderResponse;

import java.util.ArrayList;
import java.util.List;

public class DtoMapper {

    public static OrderInfoResponse mapOrderInfoToOrderInfoResponse(OrderInfo order) {
        OrderInfoResponse orderInfoResponse = new OrderInfoResponse();
        orderInfoResponse.setPrice(order.getPrice());
        orderInfoResponse.setQuantity(order.getQuantity());
        orderInfoResponse.setMenuItemId(order.getMenuItemId());

        return orderInfoResponse;
    }

    public static OrderResponse mapOrderToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setOrderStatus(order.getOrderStatus());
        orderResponse.setFinishDate(order.getFinishDate());
        orderResponse.setWaiterId(order.getWaiterId());
        orderResponse.setClientId(order.getClientId());
        orderResponse.setRestaurantId(order.getRestaurantId());

        return orderResponse;
    }

    public static OrderResponse mapOrderListToOrderResponseList(Order order, List<OrderInfo> orderInfoList) {
        List<OrderInfoResponse> orderResponseList = new ArrayList<>();
        for (OrderInfo orderInfo : orderInfoList) {
            OrderInfoResponse orderInfoResponse = mapOrderInfoToOrderInfoResponse(orderInfo);
            orderResponseList.add(orderInfoResponse);
        }
        OrderResponse orderResponse = mapOrderToOrderResponse(order);

        orderResponse.setOrderInfoResponses(orderResponseList);
        return orderResponse;
    }
}
