package pl.endproject.offerscomparator.infrastructure.apiOlx;

import org.junit.Test;
import java.util.List;
import java.util.regex.Pattern;
import static org.assertj.core.api.Assertions.assertThat;

public class OlxScraperTest {

    @Test
    public void shouldDisplayCertainStringPatternAfterSearchingForProducts() {
        //given
        String searchingItem = "Potop";
        String patternString = ".*::.*::.*::.*";

        //when
        List<String> result = OlxScraper.searchFor(searchingItem, 5);
        Pattern pattern = Pattern.compile(patternString);

        //then
        assertThat(pattern.matcher(result.get(0)).matches()).isEqualTo(true);
    }

    @Test
    public void shouldReturnFiveElementsWhenWeSearchForProducts() {
        //given
        String searchingItem = "Potop";
        int numberOfItems = 5;

        //when
        List<String> result = OlxScraper.searchFor(searchingItem, 5);
        //then
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    public void displayResultOfScraperItems() {
        String searchingItem = "Potop";

        List<String> result = OlxScraper.searchFor(searchingItem, 5);

        for (String product:result) {
            System.out.println(product);
        }
    }
}
