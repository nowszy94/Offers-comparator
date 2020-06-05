package pl.endproject.offerscomparator.infrastructure.autocompleteFeature;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Reader {
    private static String path = "src/main/resources/nazwy.txt";


    public static List<String> getWords(String searchStr) {
        List<String> foundWords = new ArrayList<>();
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, StandardCharsets.UTF_8);

            while (sc.hasNextLine()) {
//                String line = sc.nextLine().replace(" ", ",").replace("?", "").replace(".", "").toLowerCase();
                String line = sc.nextLine().toLowerCase();
                String[] lineArray = line.split(",");

                if (line.startsWith(searchStr)) {
                    foundWords.add(lineArray[0]);
                }
            }

            if (!foundWords.contains(searchStr)) {
                foundWords.add(searchStr);

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

}
