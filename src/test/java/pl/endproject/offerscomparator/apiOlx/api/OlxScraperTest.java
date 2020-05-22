package pl.endproject.offerscomparator.apiOlx.api;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

public class OlxScraperTest {


    @Test
    public void shouldReturnFiveBookItemsWhenSearchForItem() {
        //given
        String searchingItem = "Book";
        Integer size = 5;

        //when
        List<String> strings = OlxScraper.searchFor(searchingItem, size);

        //then
        assertThat(strings.size()).isEqualTo(5);
    }
    
}
