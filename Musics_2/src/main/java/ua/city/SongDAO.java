package ua.city;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongDAO {
    // Insert (Create)
    public void insertSong(String songName, String songDuration) {
        String sql = "INSERT INTO songg (songName, songDuration) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, songName);
            pstmt.setString(2, songDuration);
            pstmt.executeUpdate();
            System.out.println("Song inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Select all (Read)
    public void getAllSongs() {
        String sql = "SELECT * FROM songg";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("song_id") +
                        ", Name: " + rs.getString("songName") +
                        ", Duration: " + rs.getString("songDuration"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update
    public void updateSong(int songId, String newSongName, String newDuration) {
        String sql = "UPDATE songg SET songName = ?, songDuration = ? WHERE song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newSongName);
            pstmt.setString(2, newDuration);
            pstmt.setInt(3, songId);
            pstmt.executeUpdate();
            System.out.println("Song updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSongName(int songId, String newSongName) {
        String sql = "UPDATE songg SET songName = ? WHERE song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newSongName);
            pstmt.setInt(2, songId);
            pstmt.executeUpdate();
            System.out.println("Song name updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteSong(int songId) {
        String sql = "DELETE FROM songg WHERE song_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, songId);
            pstmt.executeUpdate();
            System.out.println("Song deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}