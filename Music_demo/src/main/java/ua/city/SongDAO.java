package ua.city;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    // Метод для додавання нової пісні
    public int addSong(Song song) {
        String sql = "INSERT INTO song (songName, songDuration) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, song.getSongName());
            pstmt.setTime(2, Time.valueOf(song.getSongDuration()));
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

    // Метод для отримання всіх пісень
    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM song";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Song song = new Song();
                song.setSongId(rs.getInt("song_id"));
                song.setSongName(rs.getString("songName"));
                song.setSongDuration(rs.getString("songDuration"));
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }

    // Метод для оновлення пісні
    public void updateSong(Song song) {
        String sql = "UPDATE song SET songName = ?, songDuration = ? WHERE song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, song.getSongName());
            pstmt.setString(2, song.getSongDuration());
            pstmt.setInt(3, song.getSongId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для видалення пісні за ID
    public void deleteSong(int id) {
        String sql = "DELETE FROM song WHERE song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
