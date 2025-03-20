package restaurant.com.orderservice.demo;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Product {

    private UUID  id;
    private String name;
    private int quantity;
}
