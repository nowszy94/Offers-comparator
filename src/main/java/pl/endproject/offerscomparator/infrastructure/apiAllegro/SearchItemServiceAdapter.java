package pl.endproject.offerscomparator.infrastructure.apiAllegro;

import org.springframework.stereotype.Component;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.domain.Source;
import pl.endproject.offerscomparator.domain.port.ProductRepository;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.model.Item;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.service.SearchItemService;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchItemServiceAdapter implements ProductRepository {
//    private SearchItemService searchItemService=SearchItemService.getInstance();

    private SearchItemService searchItemService;

    public SearchItemServiceAdapter(SearchItemService searchItemService) {
        this.searchItemService = searchItemService;
    }

    @Override
    public List<Product> findByPhrase(String phrase) {
        if (phrase == null || phrase.trim().isEmpty()) {
            throw new RuntimeException("Phrase cannot be empty");
        }
        /*long a = System.currentTimeMillis();*/
        List<Product> productList = mapRepoToDomain(searchItemService.findByPhrase(phrase));
        /*System.out.println("ALLEGRO: " + (System.currentTimeMillis() - a));*/
        //list<Item> -> domain
        return productList;
    }

    private List<Product> mapRepoToDomain(List<Item> items) {
        List<Product> productList = new ArrayList<>();

        for (Item item : items) {
            Product product = Product.builder()
                    .name(item.getTitle())
                    .price(item.getPrice())
                    .url(item.getUrl())
                    .imageUrl(item.getImageUrl())
                    .source(Source.ALLEGRO)
                    .build();
            productList.add(product);
        }
        return productList;
    }

}
