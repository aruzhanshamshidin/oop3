package oopass.edu;

import java.util.Objects;

public class Film {
    private int id;
    private String title;
    private String genre;
    private int duration;

    public Film() {}

    private Film(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.genre = builder.genre;
        this.duration = builder.duration;
    }

   public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }

    @Override
    public String toString() {
        return "Film{id=" + id + ", title='" + title + "', genre='" + genre + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }


    public static class Builder {
        private int id;
        private String title;
        private String genre;
        private int duration;

        public Builder setId(int id) { this.id = id; return this; }
        public Builder setTitle(String title) { this.title = title; return this; }
        public Builder setGenre(String genre) { this.genre = genre; return this; }
        public Builder setDuration(int duration) { this.duration = duration; return this; }

        public Film build() {
            return new Film(this);
        }
    }
}