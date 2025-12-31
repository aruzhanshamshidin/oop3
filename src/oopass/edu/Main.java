package oopass.edu;

public class Main {
    public static void main(String[] args) {

        Cinema cinema = new Cinema("Dream Cinema");

        Film f1 = new Film("Avatar", "Action", 163);
        Film f2 = new Film("The Maze Runner", "Sci-Fi", 119);
        Film f3 = new Film("Interstellar", "Sci-Fi", 169);

        cinema.addFilm(f1);
        cinema.addFilm(f2);
        cinema.addFilm(f3);

        Viewer v1 = new Viewer("Aigera", 18);
        Viewer v2 = new Viewer("Baga", 22);

        v1.chooseFilm(f2);
        v2.chooseFilm(f1);

        System.out.println("🎬 All films:");
        cinema.getAllFilms().forEach(System.out::println);

        System.out.println("\n🔍 Sci-Fi films:");
        cinema.filterByGenre("Sci-Fi").forEach(System.out::println);

        System.out.println("\n⏱ Films under 150 minutes:");
        cinema.filterByDuration(150).forEach(System.out::println);

        System.out.println("\n🔃 Sorted by title:");
        cinema.sortByTitle();
        cinema.getAllFilms().forEach(System.out::println);

        System.out.println("\n👥 Viewers:");
        System.out.println(v1);
        System.out.println(v2);
    }
}
