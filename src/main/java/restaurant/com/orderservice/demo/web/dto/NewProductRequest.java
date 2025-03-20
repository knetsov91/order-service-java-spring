package restaurant.com.orderservice.demo.web.dto;

import lombok.Data;

@Data
public class NewProductRequest {

    private String name;
    private int quantity;
}
