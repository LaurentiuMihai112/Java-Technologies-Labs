package com.lab2.servlets;

import com.lab2.models.WordModel;
import com.lab2.services.WordService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "modifiedWordServlet", value = "/word-servlet")
public class WordServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        var word = request.getParameter("word");
        var size = Integer.parseInt(request.getParameter("size"));
        var results = WordService.checkWords(new WordModel(word, size));
        if (!results.isEmpty()) {
            forwardToResultPage(request, response, results);
        }
    }

    private void forwardToResultPage(HttpServletRequest request, HttpServletResponse response, ArrayList<WordModel> results) throws ServletException, IOException {
        request.setAttribute("results", results);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    public void destroy() {
    }
}