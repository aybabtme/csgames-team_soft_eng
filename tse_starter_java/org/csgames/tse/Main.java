package org.csgames.tse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> sentences = loadSentences(args[0]);
        String query = args[1];

        for (String sentence : sentences) {
            if (SearchEngine.isMatch(sentence, query)) {
                System.out.println(sentence);
            }
        }
    }

    private static List<String> loadSentences(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        scanner.useDelimiter("\n");
        List<String> sentences = new ArrayList<String>();

        while (scanner.hasNext()) {
            sentences.add(scanner.next().trim());
        }
        scanner.close();

        return sentences;
    }

}
