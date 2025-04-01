package restaurant.com.orderservice.web.mapper;

import restaurant.com.orderservice.order.model.Order;
import restaurant.com.orderservice.orderInfo.OrderInfo;
import restaurant.com.orderservice.web.dto.CreateOrderRequest;
import restaurant.com.orderservice.web.dto.OrderInfoRequest;
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
        orderResponse.setOrderId(order.getId());
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

    public static OrderInfoRequest mapCreateOrderRequestToOrderInfo(CreateOrderRequest createOrderRequest) {
        OrderInfoRequest orderInfo = new OrderInfoRequest();
        orderInfo.setMenuItemId(createOrderRequest.getMenuItemId());
        orderInfo.setPrice(createOrderRequest.getPrice());
        orderInfo.setQuantity(createOrderRequest.getQuantity());

        return orderInfo;
    }

    public static List<OrderResponse> mapListOrderToListOrderResponse(List<Order> orders) {
        List<OrderResponse> orderResponses = new ArrayList<>();
        orders.forEach(order -> {
            OrderResponse orderResponse = DtoMapper.mapOrderListToOrderResponseList(order, order.getOrderInfo());
            orderResponses.add(orderResponse);

        });
        return orderResponses;
    }

    public static OrderInfoRequest mapCreateOrderRequestToOrderInfoRequest(CreateOrderRequest createOrderRequest) {
        OrderInfoRequest orderInfoRequest = new OrderInfoRequest();
        orderInfoRequest.setMenuItemId(createOrderRequest.getMenuItemId());
        orderInfoRequest.setPrice(createOrderRequest.getPrice());
        orderInfoRequest.setQuantity(createOrderRequest.getQuantity());

        return orderInfoRequest;

    }
}