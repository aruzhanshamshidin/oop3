package oopass.edu;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n🎬 MOVIE TICKET RESERVATION SYSTEM");
            System.out.println("1 - Show all films");
            System.out.println("2 - Add film");
            System.out.println("3 - Update film title");
            System.out.println("4 - Delete film");
            System.out.println("5 - Show all viewers");
            System.out.println("6 - Add viewer");
            System.out.println("0 - Exit");

            int command = sc.nextInt();
            sc.nextLine();

            switch (command) {

                case 1 -> FilmDAO.showAllFilms();

                case 2 -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Duration: ");
                    int duration = sc.nextInt();
                    FilmDAO.addFilm(title, genre, duration);
                }

                case 3 -> {
                    System.out.print("Film ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New title: ");
                    String newTitle = sc.nextLine();
                    FilmDAO.updateFilm(id, newTitle);
                }

                case 4 -> {
                    System.out.print("Film ID to delete: ");
                    int id = sc.nextInt();
                    FilmDAO.deleteFilm(id);
                }

                case 5 -> ViewerDAO.showAllViewers();

                case 6 -> {
                    System.out.print("Viewer name: ");
                    String name = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    System.out.print("Film ID: ");
                    int filmId = sc.nextInt();
                    ViewerDAO.addViewer(name, age, filmId);
                }

                case 0 -> {
                    System.out.println("Bye 👋");
                    System.exit(0);
                }

                default -> System.err.println("Unknown command");
            }
        }
    }
}
