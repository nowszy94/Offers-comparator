package pl.endproject.offerscomparator.infrastructure.printPdfFeature;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.endproject.offerscomparator.api.ProductController;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.domain.ProductService;
import pl.endproject.offerscomparator.domain.port.ProductRepository;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.service.SearchItemService;
import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.ReaderConfig;
import pl.endproject.offerscomparator.infrastructure.printPDFeature.PdfService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    ProductRepository repo;
    ProductService service;



    @Before
    public void setUp() {
        //given
        repo = Mockito.mock(ProductRepository.class);
        Mockito.when(repo.findByPhrase("lustro")).thenReturn(mockData());
        service = new ProductService(Collections.singletonList(repo));

    }

    @Test
    public void should_create_list_of_products() {
        //given
        List<Product> productsList = service.findForPhrase("lustro");

        //then
        Assert.assertFalse(productsList.isEmpty());



    }

    private List<Product> mockData() {
        return Arrays.asList(new Product(1L, "Lustra do powieszenia w sypialni", null, null, null, null),
                new Product(2L, "Lustro typu boho", null, null, null, null),
                new Product(3L, "Lustra z LED", null, null, null, null));
    }
}