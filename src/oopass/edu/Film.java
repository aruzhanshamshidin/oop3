package oopass.edu;

public class Film {
    private int id;
    private String title;
    private String genre;
    private int duration;

    // Приватный конструктор: объект создается только через Builder
    private Film(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.genre = builder.genre;
        this.duration = builder.duration;
    }

    // Геттеры
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }

    // Статический вложенный класс Builder
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