package oopass.edu;
public class Main {
    public static void main(String[] args) {
        Film film1 = new Film("The maze runner", "Sci-Fi", 119);
        Film film2 = new Film("Avatar", "Action", 163);

        Viewer viewer1 = new Viewer("Aigera", 18);
        Viewer viewer2 = new Viewer("Baga", 22);

        Cinema cinema = new Cinema("Dream Cinema", 5);

        cinema.addFilm(film1);
        cinema.addFilm(film2);

        viewer1.chooseFilm(film1);
        viewer2.chooseFilm(film2);

        cinema.displayFilms();
        viewer1.displayInfo();
        viewer2.displayInfo();
    }
}
