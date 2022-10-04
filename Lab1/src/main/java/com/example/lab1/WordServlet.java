package com.example.lab1;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "wordServlet", value = "/word-servlet")
public class WordServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        var word = request.getParameter("word");
        System.out.println(word);
        if (word.length() != 0) {
            var characters = word.toCharArray();
            Arrays.sort(characters);
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Ordered letters" + "</h1>");
            out.println("<ul>");
            for (var c : characters) {
                out.println("<li>" + c + "</li>");
            }
            out.println("</ul>");
            out.println("</body></html>");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Please fill parameter word" + "</h1>");
            out.println("<div> http://localhost:8080/Lab1_war/word-servlet?word=YourWord </div>");
            out.println("</body></html>");
        }
    }


    public void destroy() {
    }
}