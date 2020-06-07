package pl.endproject.offerscomparator.infrastructure.apiAllegro.repository;

import org.junit.jupiter.api.Test;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.model.Item;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ItemRepositoryImplTest {
    private ItemRepository itemRepository = new ItemRepositoryImpl();
    private List<Item> listOfAllegroItems;

    @Test
    void shouldGet0ItemsWhenPhraseIsNotGiven() {

        //given
        String title = "";

        //when
        listOfAllegroItems = itemRepository.findByPhrase(title);

        //then
        assertThat(listOfAllegroItems, is(empty()));

    }


    @Test
    void shouldGet0ItemsWhenPhraseHasWhitespace() {

        //given
        String title = "          ";

        //when
        listOfAllegroItems = itemRepository.findByPhrase(title);

        //then
        assertThat(listOfAllegroItems, is(empty()));
    }

    @Test
    void shouldGetListOfAllegroItemsWhenPhraseIsGiven() {

        //given
        String title = "Allegro Allegro Allegro";
        //when
        listOfAllegroItems = itemRepository.findByPhrase(title);

        //then
        assertThat(listOfAllegroItems.size(), is(greaterThan(0)));
    }

    @Test
    void shouldGetListOfAllegroItemsWhenPhraseIsGivenWithoutCharsetEncodingBefore() {

        //given
        String title = "półka";
        //when
        listOfAllegroItems = itemRepository.findByPhrase(title);

        //then
        assertThat(listOfAllegroItems.size(), is(greaterThan(0)));
    }

    @Test
    void shouldGetItemTitleWhenPhraseIsGiven() {

        //given
        String title = "Allegro";

        //when
        listOfAllegroItems = itemRepository.findByPhrase(title);

        //then
        assertThat(listOfAllegroItems, hasItem(allOf(hasProperty("title"))));
    }

    @Test
    void shouldGetItemWithAllOfPropertiesWhenPhraseIsGiven() {

        //given
        String title = "Allegro";

        //when
        listOfAllegroItems = itemRepository.findByPhrase(title);

        //then
        assertThat(listOfAllegroItems, hasItem(allOf(
                hasProperty("title", not(""))
                , hasProperty("price",not(""))
                , hasProperty("priceWithShipping",not(""))
                , hasProperty("url",not(""))
                , hasProperty("imageUrl", not(""))
                , hasProperty("address", is(nullValue()))
                , hasProperty("condition",not("Brak danych")))));
    }
}
