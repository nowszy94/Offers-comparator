package pl.endproject.offerscomparator.infrastructure.autocompleteFeature;

import java.util.ArrayList;
import java.util.List;



public class Reader {
    private List<String> words;


    public Reader(List<String> words) {
        this.words = words;
    }

    public List<String> getWords() {
        return words;
    }

    /* Wyszukuje tylko te slowa z pliku, ktore zaczynaja sie od searchStr*/

    public  List<String> getWords(String searchStr) {

        List<String> wordsFromReader = this.getWords();
        List<String> suggestions = new ArrayList<>();

        for (String s: wordsFromReader){
            if (s.startsWith(searchStr)){
                suggestions.add(s);
            }

            if (suggestions.size()>4){
                break;
            }
        }

        return suggestions;
    }

}
