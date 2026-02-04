package oopass.edu;
import java.util.List;

public interface Repository<T> {
    List<T> getAll() throws Exception;
    void add(T item) throws Exception;
    void delete(int id) throws Exception;

    static void logAction(String action) {
        System.out.println("[LOG]: " + action);
    }
}