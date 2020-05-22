package pl.endproject.offerscomparator.apiOlx.adapters;

import pl.endproject.offerscomparator.apiOlx.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsFromOlxAdapter {

    public List<Item> adaptItems(List<String> itemsFromScraper) {
        List<Item> createdItems = new ArrayList<>();
        for (String itemFromScraper : itemsFromScraper) {
            Item newItem = new Item();
            String[] splitElements = itemFromScraper.trim().split("::");
            newItem.setTitle(splitElements[0]);
            newItem.setPrice(splitElements[1]);
            createdItems.add(newItem);
        }
        return createdItems;
    }


}
