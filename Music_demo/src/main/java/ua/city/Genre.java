package ua.city;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Genre {
    private int genreId;
    private String genreName;

    // Конструктор за замовченням
    public Genre() {
    }

    // Конструктор з параметрами
    public Genre(int genreId, String genreName) {
        this.genreId = genreId;
        this.genreName = genreName;
    }

    // Геттери та сеттери
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    // Метод для отримання всіх жанрів з БД
    public static List<Genre> getAllGenres(Connection connection) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        String query = "SELECT * FROM genre";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("genre_id");
                String name = resultSet.getString("genreName");
                genres.add(new Genre(id, name));
            }
        }
        return genres;
    }

    // Метод для додавання нового жанру
    public static void addGenre(Connection connection, String genreName) throws SQLException {
        String query = "INSERT INTO genre (genreName) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, genreName);
            preparedStatement.executeUpdate();
        }
    }

    // Метод для видалення жанру за ID
    public static void deleteGenreById(Connection connection, int genreId) throws SQLException {
        String query = "DELETE FROM genre WHERE genre_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, genreId);
            preparedStatement.executeUpdate();
        }
    }

    // Метод для оновлення назви жанру
    public static void updateGenreName(Connection connection, int genreId, String newGenreName) throws SQLException {
        String query = "UPDATE genre SET genreName = ? WHERE genre_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newGenreName);
            preparedStatement.setInt(2, genreId);
            preparedStatement.executeUpdate();
        }
    }
}



