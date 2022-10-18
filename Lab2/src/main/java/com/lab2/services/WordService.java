package com.lab2.services;

import com.lab2.models.WordModel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class WordService {
    public static ArrayList<WordModel> checkWords(WordModel request) {
        var word = request.getWord();
        var size = request.getSize();
        var words = new ArrayList<WordModel>();
        try {
            var lines = Files.readAllLines(Paths.get("D:\\Master\\Java-Technologies-Labs\\Lab2\\src\\main\\resources\\words.txt"), StandardCharsets.UTF_8);
            lines.sort(Comparator.comparingInt(String::length));
            if (size > word.length()) {
                size = word.length();
            }
            for (var line : lines) {
                if (line.length() < size) {
                    continue;
                }
                if (line.length() > size) {
                    break;
                }
                if (hasSameLetters(word, line)) {
                    words.add(new WordModel(line, size));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private static boolean hasSameLetters(String word, String line) {
        var characters = line.toCharArray();
        for (var c : characters) {
            if (!word.contains(Character.toString(c))) {
                return false;
            }
        }
        return true;
    }

}
