package pl.endproject.offerscomparator.apiOlx.service;

import org.springframework.stereotype.Service;
import pl.endproject.offerscomparator.apiOlx.adapters.ItemsFromOlxAdapter;
import pl.endproject.offerscomparator.apiOlx.api.OlxScraper;
import pl.endproject.offerscomparator.apiOlx.model.Item;

import java.util.List;

@Service
public class OlxService {

    private final ItemsFromOlxAdapter itemsFromOlxAdapter = new ItemsFromOlxAdapter();

    public List<Item> getAllItems(String itemName, Integer numberOfItems) {
        List<String> foundItems = OlxScraper.searchFor(itemName, numberOfItems);
        return itemsFromOlxAdapter.adaptItems(foundItems);
    }
}
