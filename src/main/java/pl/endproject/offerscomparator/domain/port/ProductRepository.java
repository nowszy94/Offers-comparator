package pl.endproject.offerscomparator.domain.port;

import pl.endproject.offerscomparator.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findByPhrase(String phrase);

}
