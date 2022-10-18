
<%@ page import="com.lab2.models.WordModel" %><%--
  Created by IntelliJ IDEA.
  User: Laurentiu
  Date: 12-Oct-22
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Input</title>
</head>
<body>
<h1>Words</h1>
<form method="get" action="word-servlet"
      style="display:flex;flex-direction:column;justify-content:center;justify-items: center">
    <label style="padding: 1em">Word:
        <input type="text" id="word" name="word">
    </label>
    <label style="padding: 1em">Size:
        <input type="number" id="size" name="size" min="0" step="1">
    </label>
    <button style="padding: 1em; width: 200px" type="submit">
        Submit
    </button>
</form>

</body>
</html>
