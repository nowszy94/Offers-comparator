package pl.endproject.offerscomparator.infrastructure.apiAllegro;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import pl.endproject.offerscomparator.domain.Product;
import pl.endproject.offerscomparator.domain.Source;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.model.Item;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.service.SearchItemService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchItemServiceAdapterTest {
    private static SearchItemServiceAdapter actualItem;
    private static String title = "product-title";

    @BeforeClass
    public static void setUp() {
        SearchItemService searchItemServiceMock = Mockito.mock(SearchItemService.class);

        Mockito.when(searchItemServiceMock.findByPhrase(title))
                .then(invocationOnMock -> Arrays.asList(
                        new Item("product-1", 8.00, 16.00, "https://item-1", "https://item-1/img", null, "Mint"),
                        new Item("product-2", 16.00, 24.00, "https://item-2", "https://item-2/img", null, "Near Mint"),
                        new Item("product-3", 24.00, 32.00, "https://item-3", "https://item-3/img", null, "Excellent"),
                        new Item("product-4", 32.00, 40.00, "https://item-4", "https://item-4/img", null, "Good")
                ));

         actualItem = new SearchItemServiceAdapter(searchItemServiceMock);
    }


    @Test
    public void shouldGetListOfFourProducts() {

        //when
        List<Product> productList = actualItem.findByPhrase(title);

        //then
        Assert.assertEquals(productList.size(), 4);
    }

    @Test
    public void shouldGetProductsWithAllOfPropertiesWhenPhraseIsGiven() {

        //when
        List<Product> productList = actualItem.findByPhrase(title);

        //then
        assertThat(productList.get(0).getId()).isNull();
        assertThat(productList.get(0).getName()).isEqualTo("product-1");
        assertThat(productList.get(0).getPrice()).isEqualTo(8.00);
        assertThat(productList.get(0).getImageUrl()).isEqualTo("https://item-1/img");
        assertThat(productList.get(0).getUrl()).isEqualTo("https://item-1");
        assertThat(productList.get(0).getSource()).isEqualTo(Source.ALLEGRO);
    }
}
