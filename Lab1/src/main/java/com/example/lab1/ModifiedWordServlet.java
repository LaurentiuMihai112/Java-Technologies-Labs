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
import java.util.List;

@WebServlet(name = "modifiedWordServlet", value = "/modified-word-servlet")
public class ModifiedWordServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        var word = request.getParameter("word");
        var size = Integer.parseInt(request.getParameter("size"));
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        var results = new ArrayList<String>();
        if (size != 0) {
            results.addAll(Permutation.getAllKLength(word.toCharArray(), size));
        } else {
            for (int i = 1; i <= word.length(); i++) {
                results.addAll(Permutation.getAllKLength(word.toCharArray(), i));
            }
        }
        if (word.length() != 0) {
            var filtered = checkWords(results);
            assert filtered != null;
            filtered=filtered.stream().distinct().toList();
            out.println("<ul>");
            for (var w : filtered) {
                out.println("<li>" + w + "</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<h1>" + "Please fill parameter word" + "</h1>");
            out.println("<div> http://localhost:8080/Lab1_war/word-servlet?word=YourWord </div>");
        }
        out.println("</body></html>");
    }

    private List<String> checkWords(List<String> words) {
        try {
            var filteredWords = new ArrayList<String>();
            var lines = Files.readAllLines(Paths.get("D:\\MyRepos\\Java-Technologies-Labs\\Lab1\\src\\main\\resources\\words.txt"), StandardCharsets.UTF_8);
            for (var word : words) {
                if (lines.contains(word) || lines.contains(word.substring(0, 1).toUpperCase() + word.substring(1))) {
                    filteredWords.add(word);
                    System.out.println(word);
                }
            }
            return filteredWords;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void destroy() {
    }
}