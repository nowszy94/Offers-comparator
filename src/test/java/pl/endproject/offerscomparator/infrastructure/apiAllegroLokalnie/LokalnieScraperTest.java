package pl.endproject.offerscomparator.infrastructure.apiAllegroLokalnie;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LokalnieScraperTest {

    @Test
    public void shouldDisplayProductsInfoForGivenPhrase(){

        //given
        String phrase = " sienkiewicz potop";

        //when
        List<String> itemsFromScraper = LokalnieScraper.finder(phrase);

        //then
        Assert.assertFalse(itemsFromScraper.isEmpty());

        // Display items
        for (String s: itemsFromScraper
             ) {
            System.out.println(s);

        }


    }
}
