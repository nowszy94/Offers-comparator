package pl.endproject.offerscomparator.domain.port;

import org.springframework.stereotype.Repository;
import pl.endproject.offerscomparator.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository {

    List<Product> findByPhrase(String phrase);

}
