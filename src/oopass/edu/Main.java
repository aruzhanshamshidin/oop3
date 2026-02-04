package oopass.edu;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        Repository<Film> filmRepo = new FilmDAO();
        Repository<Viewer> viewerRepo = new ViewerDAO();

        server.createContext("/api/films", exchange -> handle(exchange, filmRepo, gson, Film.class));
        server.createContext("/api/viewers", exchange -> handle(exchange, viewerRepo, gson, Viewer.class));

        System.out.println("🚀 Сервер запущен!");
        System.out.println("🎥 Фильмы: http://localhost:8080/");
        server.createContext("/", exchange -> {
            try {
                byte[] response = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("src/web/index.html"));
                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(200, response.length);

                try (java.io.OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
            } catch (java.io.IOException e) {
                String error = "HTML file not found in src/resources/index.html";
                exchange.sendResponseHeaders(404, error.length());
                exchange.getResponseBody().write(error.getBytes());
                exchange.getResponseBody().close();
            }
        });
        server.start();
    }

    private static <T> void handle(HttpExchange exchange, Repository<T> repo, Gson gson, Class<T> type) {
        try {
            Repository.logAction("Request received for " + type.getSimpleName()); // Static method call
            String method = exchange.getRequestMethod();
            String query = exchange.getRequestURI().getQuery();
            String response = "";

            if ("GET".equals(method)) {
                List<T> data = repo.getAll();

                // --- ТРЕБОВАНИЕ №3: Обработка данных в памяти (Filter/Search) ---
                if (query != null && query.contains("filter=long") && type == Film.class) {
                    // Используем Stream API и Lambdas (Требование №8)
                    data = (List<T>) ((List<Film>) data).stream()
                            .filter(f -> f.getDuration() > 120)
                            .collect(Collectors.toList());
                }
                response = gson.toJson(data);
            }
            else if ("POST".equals(method)) {
                String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                T item = gson.fromJson(body, type);

                // Валидация (Требование №4)
                if (item == null) throw new ValidationException("JSON body is empty!");

                repo.add(item);
                response = "{\"status\":\"created\"}";
            }
            // ... (тут код для DELETE как раньше)

            sendResponse(exchange, response, 200);
        } catch (Exception e) {
            try { sendResponse(exchange, "{\"error\":\"" + e.getMessage() + "\"}", 500); } catch (Exception ignored) {}
        }
    }

    private static void sendResponse(HttpExchange exchange, String response, int status) throws java.io.IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
    }

}