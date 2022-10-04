package com.example.lab1;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static List<String> getAllKLength(char[] set, int k) {
        int n = set.length;
        var generatedWords = new ArrayList<String>();
        getAllKLengthRec(set, "", n, k, generatedWords);
        return generatedWords;
    }

    public static void getAllKLengthRec(char[] set, String prefix, int n, int k, List<String> words) {

        if (k == 0) {
            words.add(prefix);
            return;
        }
        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            getAllKLengthRec(set, newPrefix, n, k - 1, words);
        }
    }
}
