package pl.endproject.offerscomparator.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.domain.ProductService;
import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.Phrase;
import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.Reader;
import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.SuggestionsWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/offers")
    public String getOffers(Model model, @RequestParam(value = "userSearch", required = false, defaultValue = "") String userSearch) {
        if (!userSearch.isBlank()) {
            List<Product> products = productService.findForPhrase(userSearch);
            model.addAttribute("products", products);
        }
        return "getAll";
    }

    @PostMapping("/findProducts")
    public String findOffers(@RequestParam String userSearch) {

        return "redirect:/offers?userSearch=" + userSearch;
    }



    /* Dziala, ale przy odczytywaniu slow z odm.txt gdzie jest ich ponad parę tys. działa wolno */
    /* Dziala ok, ale przy odczytywaniu slow z nazwy.txt gdzie slow jest mniej dziala ok*/

    @RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public SuggestionsWrapper autocompleteSuggestions(@RequestParam("userSearch") String userSearch) throws IOException {
        System.out.println("userSearch: " + userSearch);
        ArrayList<Phrase> suggestions = new ArrayList<>();
        SuggestionsWrapper sw = new SuggestionsWrapper();

        List<String> dictionary = Reader.getWords(userSearch);

        for (String productName : dictionary) {
            suggestions.add(new Phrase(productName));

            if (suggestions.size() > 4) {

                break;
            }
        }

        sw.setSuggestions(suggestions);

        return sw;
    }


}
