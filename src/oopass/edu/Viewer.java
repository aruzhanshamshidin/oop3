package oopass.edu;
public class Viewer {
    private String name;
    private int age;
    private Film chosenFilm;

    public Viewer(String name, int age) {
        this.name = name;
        this.age = age;
        this.chosenFilm = null;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    public Film getChosenFilm() { return chosenFilm; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    public void chooseFilm(Film film) {
        this.chosenFilm = film;
    }
    public void displayInfo() {
        System.out.print("Viewer: " + name + ", Age: " + age);
        if (chosenFilm != null) {
            System.out.println(", Chosen Film: " + chosenFilm.getTitle());
        } else {
            System.out.println(", No film chosen yet.");
        }
    }
}
