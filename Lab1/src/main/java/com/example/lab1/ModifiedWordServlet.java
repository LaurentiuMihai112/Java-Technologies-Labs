package com.example.lab1;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "modifiedWordServlet", value = "/modified-word-servlet")
public class ModifiedWordServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        var word = request.getParameter("word");
        var size = Integer.parseInt(request.getParameter("size"));
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (word.length() != 0) {
            var results = checkWords(word, size);
            out.println("<ul>");
            for (var w : results) {
                out.println("<li>" + w + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<h1>" + "Please fill parameter word" + "</h1>");
            out.println("<div> http://localhost:8080/Lab1_war/word-servlet?word=YourWord </div>");
        }
        out.println("</body></html>");
    }

    private List<String> checkWords(String word, int size) {
        var words = new ArrayList<String>();
        try {
            var lines = Files.readAllLines(Paths.get("D:\\MyRepos\\Java-Technologies-Labs\\Lab1\\src\\main\\resources\\words.txt"), StandardCharsets.UTF_8);
            lines.sort(Comparator.comparingInt(String::length));
            if (size > word.length()) {
                size = word.length();
            }
            if (size == 0) {
                for (var line : lines) {
                    if (hasSameLetters(word, line)) {
                        words.add(line);
                    }
                }
            } else {
                for (var line : lines) {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private boolean hasSameLetters(String word, String line) {
        var characters = line.toCharArray();
        for (var c : characters) {
            if (!word.contains(Character.toString(c))) {
                return false;
            }
        }
        return true;
    }

    public void destroy() {
    }
}