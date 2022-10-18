package com.lab2.models;

public class WordModel {
    private String word;
    private int size;

    public WordModel(String word, int size) {
        this.word = word;
        this.size = size;
    }

    public String getWord() {
        return word;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "word='" + word + '\'' +
                ", size=" + size +
                '}';
    }
}
