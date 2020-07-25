package pl.endproject.offerscomparator.infrastructure.apiAllegro.repository;

import pl.endproject.offerscomparator.infrastructure.apiAllegro.model.Item;

import java.util.List;

public interface ItemRepository {
    public List<Item> findByPhrase(String phrase);
}
