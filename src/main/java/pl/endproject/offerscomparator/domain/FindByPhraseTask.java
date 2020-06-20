package pl.endproject.offerscomparator.domain;

import pl.endproject.offerscomparator.domain.port.ProductRepository;

import java.util.List;

class FindByPhraseTask implements Runnable{

    private ProductRepository productRepository;
    private FindByPhraseConsumer findByPhraseConsumer;
    private String phrase;

    public FindByPhraseTask(ProductRepository productRepository, FindByPhraseConsumer findByPhraseConsumer, String phrase) {
        this.productRepository = productRepository;
        this.findByPhraseConsumer = findByPhraseConsumer;
        this.phrase = phrase;
    }

    @Override
    public void run() {
        List<Product> products = productRepository.findByPhrase(phrase);
        findByPhraseConsumer.addResult(products);
    }
}
