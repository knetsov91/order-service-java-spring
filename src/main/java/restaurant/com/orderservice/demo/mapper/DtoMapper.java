package restaurant.com.orderservice.demo.mapper;


import lombok.experimental.UtilityClass;
import restaurant.com.orderservice.demo.Product;
import restaurant.com.orderservice.demo.web.dto.ProductResponse;

@UtilityClass
public class DtoMapper {

    public static ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .build();
    }
}
