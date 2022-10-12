package lab2.com.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WordService {
    public static List<String> checkWords(String word, int size) {
        ArrayList<String> words = new ArrayList<>();

        try {
            var lines = WordService.readWords();
            lines.sort(Comparator.comparingInt(String::length));
            if (size > word.length()) {
                size = word.length();
            }
            for (String line : lines) {
                if (line.length() < size) {
                    continue;
                }
                if (line.length() > size) {
                    break;
                }
                if (hasSameLetters(word, line)) {
                    words.add(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static boolean hasSameLetters(String word, String line) {
        char[] characters = line.toCharArray();
        for (char c : characters) {
            if (!word.contains(Character.toString(c))) {
                return false;
            }
        }
        return true;
    }

    private static List<String> readWords() throws IOException {
        return new ArrayList<>(Files.readAllLines(Paths.get("D:\\MyRepos\\Java-Technologies-Labs\\Lab2\\src\\main\\resources\\words.txt"), StandardCharsets.UTF_8));
    }
}
