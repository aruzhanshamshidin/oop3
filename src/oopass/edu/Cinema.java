package oopass.edu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {
    private String name;
    private List<Film> films;

    public Cinema(String name) {
        this.name = name;
        this.films = new ArrayList<>();
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public List<Film> getAllFilms() {
        return films;
    }

    // 🔍 Search
    public Film findFilmByTitle(String title) {
        for (Film film : films) {
            if (film.getTitle().equalsIgnoreCase(title)) {
                return film;
            }
        }
        return null;
    }

    // 🎯 Filter
    public List<Film> filterByGenre(String genre) {
        return films.stream()
                .filter(f -> f.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    // ⏱ Filter
    public List<Film> filterByDuration(int maxDuration) {
        return films.stream()
                .filter(f -> f.getDuration() <= maxDuration)
                .collect(Collectors.toList());
    }

    // 🔃 Sort
    public void sortByTitle() {
        films.sort(Comparator.comparing(Film::getTitle));
    }

    public void sortByDuration() {
        films.sort(Comparator.comparingInt(Film::getDuration));
    }

    @Override
    public String toString() {
        return "Cinema{name='" + name + "', films=" + films + "}";
    }
}
