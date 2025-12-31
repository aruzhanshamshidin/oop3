package oopass.edu;

import java.util.Objects;

public class Viewer extends Person {
    private Film chosenFilm;

    public Viewer(String name, int age) {
        super(name, age);
    }

    public Film getChosenFilm() {
        return chosenFilm;
    }

    public void chooseFilm(Film film) {
        this.chosenFilm = film;
    }

    @Override
    public String getRole() {
        return "Viewer";
    }

    @Override
    public String toString() {
        return "Viewer{name='" + getName() +
                "', age=" + getAge() +
                ", chosenFilm=" + (chosenFilm != null ? chosenFilm.getTitle() : "none") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viewer)) return false;
        Viewer viewer = (Viewer) o;
        return Objects.equals(getName(), viewer.getName()) &&
                Objects.equals(chosenFilm, viewer.chosenFilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), chosenFilm);
    }
}

