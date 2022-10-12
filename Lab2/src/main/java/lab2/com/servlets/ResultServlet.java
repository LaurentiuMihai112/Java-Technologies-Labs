package lab2.com.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab2.com.services.WordService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "resultServlet", value = "/result-servlet")
public class ResultServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String word = request.getParameter("word");
        int size = Integer.parseInt(request.getParameter("size"));
        PrintWriter out = response.getWriter();
        out.println("<html>" + "<style> table { font-family: arial, sans-serif; border-collapse: collapse; width: 100%;} td, th {border: 1px solid #dddddd;text-align: left;padding: 8px;} tr:nth-child(even) {background-color: #dddddd;}</style>" +
                "<body>");
        if (word.length() != 0) {
            List<String> results = WordService.checkWords(word, size);
            out.println("<table>");
            for (String w : results) {
                out.println("<tr><th>" + w + "</th></tr>");
            }
            out.println("</table>");
        } else {
            out.println("<h1>" + "Please fill parameter word" + "</h1>");
            out.println("<div> http://localhost:8080/Lab1_war/word-servlet?word=YourWord </div>");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
