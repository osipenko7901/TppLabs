package ua.city;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {

	// Метод для додавання нового жанру
	public int addGenre(Genre genre) {
		String sql = "INSERT INTO genre (genreName) VALUES (?)";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, genre.getGenreName());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	// Метод для отримання жанру за ID
	public Genre getGenreById(int genreId) throws SQLException {
		String sql = "SELECT * FROM genre WHERE genre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, genreId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Genre(rs.getInt("genre_id"), rs.getString("genreName"));
			}
		}
		return null;
	}

	// Метод для отримання всіх жанрів
	public List<Genre> getAllGenres() throws SQLException {
		List<Genre> genres = new ArrayList<>();
		String sql = "SELECT * FROM genre";
		try (Connection conn = DatabaseConnection.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				genres.add(new Genre(rs.getInt("genre_id"), rs.getString("genreName")));
			}
		}
		return genres;
	}

	// Метод для видалення жанру за ID
	public boolean deleteGenreById(int genreId) {
		String sql = "DELETE FROM genre WHERE genre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, genreId);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Метод для оновлення назви жанру
	public boolean updateGenreName(int genreId, String newGenreName) {
		String sql = "UPDATE genre SET genreName = ? WHERE genre_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, newGenreName);
			stmt.setInt(2, genreId);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}