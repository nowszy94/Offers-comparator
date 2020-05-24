package pl.endproject.offerscomparator.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.domain.ProductService;

import java.util.List;

@Controller
public class TestProductController {
    private ProductService productService;

    public TestProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/byPhrase")
    public String findByPhrase(Model model){
        String phrase = "potop";
        List<Product> forPhrase = productService.findForPhrase(phrase);
        System.out.println(forPhrase);
        model.addAttribute("results", forPhrase);

        return "getAll";
    }
}
