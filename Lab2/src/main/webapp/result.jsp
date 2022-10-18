<%@ page import="com.lab2.models.WordModel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Laurentiu
  Date: 12-Oct-22
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>Word</th>
        <th>Size</th>
    </tr>
    <%
        List<WordModel> results = (List<WordModel>) request.getAttribute("results");
        for (WordModel result : results) {
            out.println("<tr style=\"border:1px solid gray>");
            out.println("<th>" + result.getWord() + "</th>");
            out.println("<th>" + result.getSize() + "</th>");
            out.println("</tr>");
        }
    %>
</table>
</body>
</html>
