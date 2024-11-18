package ua.city;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // URL бази даних PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/Music";
    
    // Логін та пароль користувача бази даних
    private static final String USER = "postgres";
    private static final String PASSWORD = "osipenko7901";

    // Метод для встановлення з'єднання
    public static Connection getConnection() {
        Connection connection = null;
        try {
        	try {
        	    Class.forName("org.postgresql.Driver");
        	} catch (ClassNotFoundException e) {
        	    throw new SQLException("PostgreSQL JDBC Driver не знайден.", e);
        	}

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("З'єднання з базою даних встановлено.");
        } catch (SQLException e) {
            System.err.println("Помилка при підключенні до бази даних: " + e.getMessage());
        }
        return connection;
    }
}
