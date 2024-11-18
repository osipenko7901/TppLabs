package ua.city;

public class Song {
    private int songId;
    private String songName;
    private String songDuration; // Час у форматі HH:mm:ss

    // Конструктор за замовченням
    public Song() {}

    // Конструктор із параметрами
    public Song(int songId, String songName, String songDuration) {
        this.songId = songId;
        this.songName = songName;
        this.songDuration = songDuration;
    }

    // Геттери та сеттери
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", songDuration='" + songDuration + '\'' +
                '}';
    }
}

