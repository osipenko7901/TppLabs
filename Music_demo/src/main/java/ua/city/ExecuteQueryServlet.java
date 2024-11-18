package ua.city;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/ExecuteQueryServlet")
public class ExecuteQueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query"); // Отримуємо SQL-запит з форми
        String table = request.getParameter("table"); // Отримуємо назву таблиці
        StringBuilder queryResult = new StringBuilder();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Виконуємо запит
            boolean hasResultSet = stmt.execute(query);

            if (hasResultSet) {
                ResultSet rs = stmt.getResultSet();
                int columns = rs.getMetaData().getColumnCount();
                
                // Формуємо HTML-таблицю з результатом
                queryResult.append("<table border='1'>");
                queryResult.append("<tr>");
                for (int i = 1; i <= columns; i++) {
                    queryResult.append("<th>").append(rs.getMetaData().getColumnName(i)).append("</th>");
                }
                queryResult.append("</tr>");
                
                while (rs.next()) {
                    queryResult.append("<tr>");
                    for (int i = 1; i <= columns; i++) {
                        queryResult.append("<td>").append(rs.getString(i)).append("</td>");
                    }
                    queryResult.append("</tr>");
                }
                queryResult.append("</table>");
            } else {
                queryResult.append("<p>Запит виконано, змінено рядків: ").append(stmt.getUpdateCount()).append("</p>");
            }
        } catch (Exception e) {
            queryResult.append("<p style='color: red;'>Помилка виконання запиту: ").append(e.getMessage()).append("</p>");
        }

        request.setAttribute("queryResult", queryResult.toString());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

