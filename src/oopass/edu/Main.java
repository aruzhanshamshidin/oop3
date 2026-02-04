package oopass.edu;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        // Создаем сервер на порту 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // --- РАБОТА С ФИЛЬМАМИ (/api/films) ---
        server.createContext("/api/films", exchange -> {
            String method = exchange.getRequestMethod();
            String response = "";
            int statusCode = 200;

            try {
                if ("GET".equals(method)) {
                    response = gson.toJson(FilmDAO.getAllFilms());
                }
                else if ("POST".equals(method)) {
                    String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    Film newFilm = gson.fromJson(body, Film.class);
                    FilmDAO.addFilm(newFilm);
                    response = "{\"message\":\"Film added!\"}";
                    statusCode = 201;
                }
                else if ("DELETE".equals(method)) {
                    // Удаление по ID: /api/films?id=1
                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.contains("id=")) {
                        int id = Integer.parseInt(query.split("id=")[1]);
                        FilmDAO.deleteFilm(id);
                        response = "{\"message\":\"Film deleted!\"}";
                    }
                }
                sendResponse(exchange, response, statusCode);
            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, "{\"error\":\"Internal Server Error\"}", 500);
            }
        });

        // --- РАБОТА СО ЗРИТЕЛЯМИ (/api/viewers) ---
        server.createContext("/api/viewers", exchange -> {
            String method = exchange.getRequestMethod();
            String response = "";
            int statusCode = 200;

            try {
                if ("GET".equals(method)) {
                    // Теперь ViewerDAO.getAllViewers() будет работать
                    response = gson.toJson(ViewerDAO.getAllViewers());
                }
                else if ("POST".equals(method)) {
                    String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    Viewer newViewer = gson.fromJson(body, Viewer.class);
                    ViewerDAO.addViewer(newViewer);
                    response = "{\"message\":\"Viewer added!\"}";
                    statusCode = 201;
                }
                else if ("DELETE".equals(method)) {
                    // Удаление по ID: /api/viewers?id=1
                    String query = exchange.getRequestURI().getQuery();
                    if (query != null && query.contains("id=")) {
                        int id = Integer.parseInt(query.split("id=")[1]);
                        ViewerDAO.deleteViewer(id);
                        response = "{\"message\":\"Viewer deleted!\"}";
                    }
                }
                sendResponse(exchange, response, statusCode);
            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, "{\"error\":\"Internal Server Error\"}", 500);
            }
        });

        System.out.println("🚀 Сервер запущен!");
        System.out.println("Фильмы: http://localhost:8080/api/films");
        System.out.println("Зрители: http://localhost:8080/api/viewers");
        server.start();
    }

    // Вспомогательный метод, чтобы не дублировать код отправки ответа
    private static void sendResponse(HttpExchange exchange, String response, int statusCode) throws java.io.IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}