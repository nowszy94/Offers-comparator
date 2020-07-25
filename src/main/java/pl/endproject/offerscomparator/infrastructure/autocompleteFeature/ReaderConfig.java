package pl.endproject.offerscomparator.infrastructure.autocompleteFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Configuration
public class ReaderConfig {

    private final String PATH = "src/main/resources/odm.txt";

    @Bean
    public Reader readerFromFile() {
        List<String> wordsFromTextFile = readWordsFromFile();
        System.out.println("REading from file");
        return new Reader(wordsFromTextFile);
    }

    private List<String> readWordsFromFile() {
        return new ArrayList<>(getWords());
    }

    public List<String> getWords() {

        List<String> foundWords = new ArrayList<>();
        FileInputStream inputStream = null;
        Scanner sc = null;

        try {
            inputStream = new FileInputStream(PATH);
            sc = new Scanner(inputStream, StandardCharsets.UTF_8);

            while (sc.hasNextLine()) {
                String line = clearString(sc.nextLine());
                String[] lineArray = line.split(",");

                foundWords.addAll(Arrays.asList(lineArray));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return foundWords;
    }


    private String clearString(String s) {
        return s.replace(" ", ",").replace("?", "").replace(".", "").toLowerCase();
    }

}
