package pl.endproject.offerscomparator.infrastructure.apiAllegro.service;

import org.springframework.stereotype.Component;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.model.Item;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.repository.ItemRepository;
import pl.endproject.offerscomparator.infrastructure.apiAllegro.repository.ItemRepositoryImpl;

import java.util.List;

@Component
public class SearchItemService {
    private final ItemRepository itemRepository = new ItemRepositoryImpl();

    public List<Item> findByPhrase(String phrase) {
        return itemRepository.findByPhrase(phrase);
    }
}
