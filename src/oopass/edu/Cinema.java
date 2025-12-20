package oopass.edu;

public class Cinema {
    private String name;
    private Film[] films;
    private int filmCount;

    public Cinema(String name, int maxFilms) {
        this.name = name;
        this.films = new Film[maxFilms];
        this.filmCount = 0;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void addFilm(Film film) {
        if (filmCount < films.length) {
            films[filmCount] = film;
            filmCount++;
        } else {
            System.out.println("Cannot add more films, cinema is full!");
        }
    }
    public void displayFilms() {
        System.out.println("Cinema: " + name + " has the following films:");
        for (int i = 0; i < filmCount; i++) {
            films[i].displayInfo();
        }
    }
}
