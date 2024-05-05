<%@ page import="by.javaguru.je.jdbc.entity.Subjects" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список школьных предметов</title>
</head>
<body>
<h1>Список школьных предметов:</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Предмет</th>
    </tr>
    <%
        // Получаем список предметов из атрибута запроса "subjects"
        List<Subjects> subjects = (List<Subjects>) request.getAttribute("subjects");
        if (subjects != null) {
            for (Subjects subject : subjects) {
    %>
    <tr>
        <td><%= subject.getId() %>
        </td>
        <td><%= subject.getSubject() %>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2">Нет доступных предметов</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
