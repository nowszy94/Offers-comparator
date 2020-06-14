package pl.endproject.offerscomparator.infrastructure.printPdfFeature;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.endproject.offerscomparator.api.ProductController;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.domain.ProductService;
import pl.endproject.offerscomparator.domain.port.ProductRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    ProductRepository repo;
    ProductService service;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductController productController;



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