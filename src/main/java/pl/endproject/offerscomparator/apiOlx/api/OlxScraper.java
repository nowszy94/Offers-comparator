package pl.endproject.offerscomparator.apiOlx.api;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OlxScraper {


    public static List<String> searchFor(String searchItem, Integer numberOfItems) {
        List<String> foundItems = new ArrayList<>();
        String url = "https://www.olx.pl/oferty/q- " + searchItem + "/?search[order]=filter_float_price%3Aasc&view=galleryWide";

        try {
            Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
            Elements foundElementsTitles = getTitles(page);
            Elements foundElementsPrices = getPrices(page);
            foundItems = connectElements(foundElementsTitles, foundElementsPrices, numberOfItems);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Wrong url");
        }
        return foundItems;
    }

    private static List<String> connectElements(Elements foundElementsTitles, Elements foundElementsPrices, Integer size) {
        List<String> foundElements = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String element = foundElementsTitles.get(i).text() + "::" + foundElementsPrices.get(i).text();
            foundElements.add(element);
        }
        return foundElements;
    }

    private static Elements getTitles(Document page) {
        return page.select("div.rel >#gallerywide >.wrap >.inner>.normal>.link");
    }

    private static Elements getPrices(Document page) {
        return page.select("div.rel> #gallerywide >.wrap >.price");
    }


}
