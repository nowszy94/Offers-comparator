package pl.endproject.offerscomparator.apiOlx.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OlxScraperTest {


    @Test
    public void shouldReturnFiveBookItemsWhenSearchForItem() {
        //given
        String searchingItem = "Book";
        Integer size = 5;

        //when
        List<String> result = OlxScraper.searchFor(searchingItem, size);

        //then
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    public void shouldDisplayCertainStringPatternAfterSearchingForItem() {
        //given
        String searchingItem = "Potop";
        String patternString = ".*::.*";

        //when
        List<String> result = OlxScraper.searchFor(searchingItem, 5);
        Pattern pattern = Pattern.compile(patternString);

        //then
        assertThat(pattern.matcher(result.get(0)).matches()).isEqualTo(true);
    }

    @Test
    public void displayResultOfScraperItems(){
        String searchingItem = "Potop";

        List<String> result = OlxScraper.searchFor(searchingItem, 5);

        System.out.println(result);
    }

}
