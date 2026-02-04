package oopass.edu;

import java.util.Objects;

public class Viewer extends Person {
    private int id;
    private Film chosenFilm;


    public Viewer(String name, int age) {
        super(name, age);
    }

    public Viewer(int id, String name, int age) {
        super(name, age);
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Viewer{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", chosenFilm=" + (chosenFilm != null ? chosenFilm.getTitle() : "none") +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Viewer)) return false;
        Viewer viewer = (Viewer) o;
        return id == viewer.id &&
                getAge() == viewer.getAge() &&
                Objects.equals(getName(), viewer.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getAge());
    }
}
