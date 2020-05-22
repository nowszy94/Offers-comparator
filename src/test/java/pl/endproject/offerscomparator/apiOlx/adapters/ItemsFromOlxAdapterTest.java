package pl.endproject.offerscomparator.apiOlx.adapters;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import pl.endproject.offerscomparator.apiOlx.api.OlxScraper;
import pl.endproject.offerscomparator.apiOlx.model.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class ItemsFromOlxAdapterTest {

    private ItemsFromOlxAdapter itemsFromOlxAdapter;

    @Before
    public void setUp() {
        itemsFromOlxAdapter = new ItemsFromOlxAdapter();
    }

    @Test
    public void shouldCreateNewItemsWhenWePassDataFromOlxSite() {
        //given
        List<String> potopBooks = OlxScraper.searchFor("Potop", 5);

        //when
        List<Item> resultItems = itemsFromOlxAdapter.adaptItems(potopBooks);

        //then
        assertThat(resultItems.size()).isEqualTo(5);
        assertThat(resultItems.get(0).getTitle()).isNotNull();
        assertThat(resultItems.get(0).getPrice()).isNotNull();
    }

    @Test
    public void shouldCreateNewItemsWhenWeMockData() {
        //given
        String testResult1 = "H.Sienkiewicz Ogniem i Mieczem i Potop::Za darmo";
        String testResult2 = "Książka \"POTOP\" Henryk Sienkiewicz::1 zł";
        String testResult3 = "Lektury opracowania Zemsta Romeo i Julia Mały Książę Pan Tadeusz Potop::1 zł";
        String testResult4 = "Książka Potop Henryk Sienkiewicz, opracowanie lektury, powieści, filmu::2 zł";
        List<String> testList = new LinkedList<>();
        testList.add(testResult1);
        testList.add(testResult2);
        testList.add(testResult3);
        testList.add(testResult4);

        //when
        List<Item> resultItems = itemsFromOlxAdapter.adaptItems(testList);
        //then
        assertThat(resultItems.get(0).getTitle()).isEqualTo("H.Sienkiewicz Ogniem i Mieczem i Potop");
        assertThat(resultItems.get(1).getTitle()).isEqualTo("Książka \"POTOP\" Henryk Sienkiewicz");
        assertThat(resultItems.get(3).getPrice()).isEqualTo("2 zł");
    }
}
