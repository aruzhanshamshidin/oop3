package oopass.edu;

import java.util.Objects;

public class Film {
    private String title;
    private String genre;
    private int duration;

    public Film(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }

    public void setTitle(String title) { this.title = title; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setDuration(int duration) { this.duration = duration; }

    @Override
    public String toString() {
        return "Film{title='" + title + "', genre='" + genre + "', duration=" + duration + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return duration == film.duration &&
                Objects.equals(title, film.title) &&
                Objects.equals(genre, film.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, duration);
    }
}
