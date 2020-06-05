package pl.endproject.offerscomparator.infrastructure.autocompleteFeature;

import pl.endproject.offerscomparator.infrastructure.autocompleteFeature.Phrase;

import java.util.List;

public class SuggestionsWrapper {

    List<Phrase> suggestions;

    List<String> suggestion;

    public List<String> getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(List<String> suggestion) {
        this.suggestion = suggestion;
    }

    public List<Phrase> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Phrase> suggestions) {
        this.suggestions = suggestions;
    }
}

