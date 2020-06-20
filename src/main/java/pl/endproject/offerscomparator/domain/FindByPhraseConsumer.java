package pl.endproject.offerscomparator.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


class FindByPhraseConsumer {

    private List<List<Product>> taskResults;

    public FindByPhraseConsumer() {
        this.taskResults = new ArrayList<>();
    }

    public int size() {
        return taskResults.size();
    }

    public synchronized void addResult(List<Product> products) {
        this.taskResults.add(products);
    }

    public List<List<Product>> getTaskResults() {
        return taskResults;
    }
}
