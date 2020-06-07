package pl.endproject.offerscomparator.infrastructure.apiAllegroLokalnie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LokalnieScraper {
    private final static String URL = "https://allegrolokalnie.pl";



    public static List<String> finder(String phrase) {
        List<String> productsList = new ArrayList<>();
        String url = URL + "/oferty/?phrase=" + phrase.replace(" ","+") + "&city=&sort=price-asc&price_from=&price_to=&sort=price-asc&page=1&page=1";

        if (phrase.isEmpty()){
            throw new RuntimeException("Pole wyszukiwanie nie może zostać puste!");
        }

        try {
            Document page = Jsoup.connect(url).userAgent("Jsoup Scraper").get();
            productsList = connectElements(getTitles(page), getPrices(page), getImageUrl(page), getProductUrl(page));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productsList;
    }




    private static List<String> connectElements(Elements titles, Elements prices, Elements imageUrls, Elements productUrl) {

        List<String> foundElements = new ArrayList<>();
        Integer minValue = Collections.min(List.of(titles.size(), prices.size(), imageUrls.size(), productUrl.size()));

        for (int i = 0; i < minValue; i++) {
            foundElements.add(titles.get(i).text()
                    + "::" + prices.get(i).text()
                    + "::" + imageUrls.get(i).absUrl("src")
                    + "::" + " " +  URL +  productUrl.get(i).attr("href"));
        }
        return foundElements;
    }



    private static Elements getTitles(Document doc) {
        return doc.select("#app-root > form > main > div > div.layout__main > div > div.listing > div.listing__body > div > div > div > a > div.offer-card__body > div.offer-card__body-header > h3");
    }

    private static Elements getPrices(Document doc) {
        return doc.select("#app-root > form > main > div > div.layout__main > div > div.listing > div.listing__body > div > div > div > a > div.offer-card__body > div.offer-card__price > span");
    }

    private static Elements getImageUrl(Document doc) {
        return doc.select("#app-root > form > main > div > div.layout__main > div > div.listing > div.listing__body > div > div > div > a > div.offer-card__image > div > img");
    }

    private static Elements getProductUrl(Document doc) {
        return doc.select("#app-root > form > main > div > div.layout__main > div > div.listing > div.listing__body > div > div > div > a");
    }


}
