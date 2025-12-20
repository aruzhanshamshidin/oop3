package oopass.edu;
public class Film {
    private String title;
    private String genre;
    private int duration; // в минутах

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

    public void displayInfo() {
        System.out.println("Film: " + title + ", Genre: " + genre + ", Duration: " + duration + " mins");
    }
}

