package restaurant.com.orderservice.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant.com.orderservice.demo.mapper.DtoMapper;
import restaurant.com.orderservice.demo.web.dto.NewProductRequest;
import restaurant.com.orderservice.demo.web.dto.ProductResponse;

import java.util.List;




@RestController
@RequestMapping("/api/v1" + "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllP() {
        List<ProductResponse> list = productService.getAllProducts()
                .stream()
                .map(DtoMapper::toProductResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ProductResponse> createNewProduct(@RequestBody NewProductRequest newProductRequest) {

        Product product = productService.createNewProduct(newProductRequest);

        ProductResponse productResponse = DtoMapper.toProductResponse(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productResponse);
    }


}
