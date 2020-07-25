package pl.endproject.offerscomparator.infrastructure.autocompleteFeature;



import java.util.List;

public class SuggestionsWrapper {

    List<Phrase> suggestions;

    public List<Phrase> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Phrase> suggestions) {
        this.suggestions = suggestions;
    }
}

