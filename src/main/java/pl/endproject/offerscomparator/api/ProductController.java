package pl.endproject.offerscomparator.api;

import org.springframework.stereotype.Controller;
import pl.endproject.offerscomparator.domain.ProductService;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

}
