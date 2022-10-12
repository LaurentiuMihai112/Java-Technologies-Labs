<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    Lab2
</h1>
<br>
<form action="/lab2home/result-servlet" method="GET" name="servletForm"
      style="display:flex;flex-direction:column;gap: 20px;">
    <label>
        Word
        <input name="word" type="text">
    </label>
    <label>
        Size
        <input name="size" type="number" min="0" value="0" step="1">
    </label>
    <button style="width: 200px;" type="submit">Submit</button>
</form>
</body>
</html>