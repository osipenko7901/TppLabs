package ua.city;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Создание объектов DAO
        GenreDAO genreDAO = new GenreDAO();
        MusicGroupDAO musicGroupDAO = new MusicGroupDAO();
        SongDAO songDAO = new SongDAO();

        // Приклад додавання жанру
        Genre genre = new Genre();
        genre.setGenreName("Blues");
        int genreId = genreDAO.addGenre(genre);
        genre.setGenreId(genreId);

        if (genreId > 0) {
            System.out.println("Жанр додано: " + genre);
        } else {
            System.out.println("Помилка: не вдалося додати жанр.");
        }

        // Приклад додавання музичної групи
        MusicGroup group = new MusicGroup();
        group.setGroupName("Pink Floyd");
        int groupId = musicGroupDAO.addMusicGroup(group);
        group.setGroupId(groupId);

        if (groupId > 0) {
            System.out.println("Музичну групу додано: " + group);
        } else {
            System.out.println("Помилка: не вдалося додати музичну групу.");
        }

        // Приклад додавання пісні
        Song song = new Song();
        song.setSongName("Comfortably Numb");
        song.setSongDuration("00:06:24");
        int songId = songDAO.addSong(song);
        song.setSongId(songId);

        if (songId > 0) {
            System.out.println("Пісню додано: " + song);
        } else {
            System.out.println("Помилка: не вдалося додати пісню.");
        }

        // Виведення всіх жанрів
        System.out.println("\nСписок жанрів:");
        List<Genre> genres = genreDAO.getAllGenres();
        for (Genre g : genres) {
            System.out.println("ID: " + g.getGenreId() + ", Назва: " + g.getGenreName());
        }

        // Виведення всіх музичних груп
        System.out.println("\nСписок музичних груп:");
        List<MusicGroup> groups = musicGroupDAO.getAllMusicGroups();
        for (MusicGroup g : groups) {
            System.out.println("ID: " + g.getGroupId() + ", Назва: " + g.getGroupName());
        }

        // Виведення всіх пісень
        System.out.println("\nСписок пісень:");
        List<Song> songs = songDAO.getAllSongs();
        for (Song s : songs) {
            System.out.println("ID: " + s.getSongId() + ", Назва: " + s.getSongName() + ", Тривалість: " + s.getSongDuration());
        }
    }
}
