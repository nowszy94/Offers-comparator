package pl.endproject.offerscomparator.infrastructure.a;

import org.springframework.stereotype.Component;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.domain.Source;
import pl.endproject.offerscomparator.domain.port.ProductRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DummyProductRepositoryAdapter implements ProductRepository {

    @Override
    public List<Product> findByPhrase(String phrase) {
        return Arrays.asList(new Product(1L,"a",23.0,"http","image", Source.ALLEGRO),
                new Product(2L,"b",24.0,"https","image1", Source.OLX));
    }
}
