<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>SQL Query Executor</title>
</head>
<body>
    <h2>SQL-запити для таблиць Genre, MusicGroup, Song</h2>
    
    <!-- Форма для таблиці Music -->
    <h3>Music</h3>
    <form action="ExecuteQueryServlet" method="POST">
        <input type="hidden" name="table" value="music">
        <textarea name="query" rows="4" cols="50" placeholder="Наприклад: SELECT * FROM music;"></textarea><br><br>
        <input type="submit" value="Виконати запит">
    </form>

    <!-- Вивід результатів -->
    <div id="results">
        <h3>Результат запиту:</h3>
        ${queryResult} <!-- Виведення результату виконання запиту -->
    </div>
</body>
</html>
