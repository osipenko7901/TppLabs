package ua.city;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private GenreDAO genreDAO = new GenreDAO();
    private MusicGroupDAO musicGroupDAO = new MusicGroupDAO();
    private SongDAO songDAO = new SongDAO();

    public void executeCommand(String command) {
        if (command.startsWith("insert genre")) {
            // Команда для добавления нового жанра
            Pattern pattern = Pattern.compile("name='(.+?)'");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                String name = matcher.group(1);
                genreDAO.insertGenre(name);
                System.out.println("Inserted genre successfully.");
            } else {
                System.out.println("Invalid insert genre command format.");
            }
        } else if (command.startsWith("update genre")) {
            // Команда для обновления названия жанра
            Pattern pattern = Pattern.compile("id=(\\d+), name='(.+?)'");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                int id = Integer.parseInt(matcher.group(1));
                String newName = matcher.group(2);
                genreDAO.updateGenre(id, newName);
                System.out.println("Updated genre successfully.");
            } else {
                System.out.println("Invalid update genre command format.");
            }
        } else if (command.startsWith("delete genre")) {
            // Команда для удаления жанра
            Pattern pattern = Pattern.compile("id=(\\d+)");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                int id = Integer.parseInt(matcher.group(1));
                genreDAO.deleteGenre(id);
                System.out.println("Deleted genre successfully.");
            } else {
                System.out.println("Invalid delete genre command format.");
            }
        } else if (command.startsWith("read genre")) {
            // Команда для чтения всех жанров
            genreDAO.getAllGenres();

        } else if (command.startsWith("insert group")) {
            // Команда для добавления новой музыкальной группы
            Pattern pattern = Pattern.compile("name='(.+?)'");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                String name = matcher.group(1);
                musicGroupDAO.insertMusicGroup(name);
                System.out.println("Inserted music group successfully.");
            } else {
                System.out.println("Invalid insert group command format.");
            }
        } else if (command.startsWith("update group")) {
            // Команда для обновления названия музыкальной группы
            Pattern pattern = Pattern.compile("id=(\\d+), name='(.+?)'");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                int id = Integer.parseInt(matcher.group(1));
                String newName = matcher.group(2);
                musicGroupDAO.updateMusicGroup(id, newName);
                System.out.println("Updated music group successfully.");
            } else {
                System.out.println("Invalid update group command format.");
            }
        } else if (command.startsWith("delete group")) {
            // Команда для удаления музыкальной группы
            Pattern pattern = Pattern.compile("id=(\\d+)");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                int id = Integer.parseInt(matcher.group(1));
                musicGroupDAO.deleteMusicGroup(id);
                System.out.println("Deleted music group successfully.");
            } else {
                System.out.println("Invalid delete group command format.");
            }
        } else if (command.startsWith("read group")) {
            // Команда для чтения всех музыкальных групп
            musicGroupDAO.getAllMusicGroups();

       } else if (command.startsWith("insert song")) {
            // Команда для добавления новой песни
            Pattern pattern = Pattern.compile("name='(.+?)', duration='(\\d{2}:\\d{2}:\\d{2})'");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                String name = matcher.group(1);
                 String duration = matcher.group(2);
                songDAO.insertSong(name, duration);
                System.out.println("Inserted song successfully.");
            } else {
                System.out.println("Invalid insert song command format.");
            }
        } else if (command.startsWith("update song")) {
            // Команда для обновления названия и/или длительности песни
            Pattern pattern = Pattern.compile("id=(\\d+), name='(.+?)'(?:, duration='(\\d{2}:\\d{2}:\\d{2})')?");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                int id = Integer.parseInt(matcher.group(1));
                String newName = matcher.group(2);
                String newDuration = matcher.group(3); // Может быть null
                if (newDuration != null) {
                    songDAO.updateSong(id, newName, newDuration);
                } else {
                    songDAO.updateSongName(id, newName);
                }
                System.out.println("Updated song successfully.");
            } else {
                System.out.println("Invalid update song command format.");
            }
        } else if (command.startsWith("delete song")) {
            // Команда для удаления песни
            Pattern pattern = Pattern.compile("id=(\\d+)");
            Matcher matcher = pattern.matcher(command);
            if (matcher.find()) {
                int id = Integer.parseInt(matcher.group(1));
                songDAO.deleteSong(id);
                System.out.println("Deleted song successfully.");
            } else {
                System.out.println("Invalid delete song command format.");
            }
        } else if (command.startsWith("read song")) {
            // Команда для чтения всех песен
            songDAO.getAllSongs();

        } else {
            System.out.println("Unknown command. Please try again.");
        }
    }
}
