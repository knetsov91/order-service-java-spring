package restaurant.com.orderservice.demo;

import org.springframework.stereotype.Service;
import restaurant.com.orderservice.demo.web.dto.NewProductRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
    }

    public List<Product> getAllProducts() {
        return List.of(new Product(UUID.randomUUID(),"da",22));
    }

    public Product createNewProduct(NewProductRequest newProductRequest) {

        Product build = Product.builder().id(UUID.randomUUID())
                .name(newProductRequest.getName())
                .quantity(newProductRequest.getQuantity())
                .build();
        products.add(build);

        return build;
    }
}
