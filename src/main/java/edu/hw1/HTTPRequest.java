package edu.hw1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class HTTPRequest {
    private final static String BASE_URL = "https://httpbin.org/";

    //Разбил базовый URL и конкретные страницы в константу и параметр,
    // поэтому можно выполнить почти любое задание с помощью этого коннекта(клиента) (может чуть изменить),
    // а парсить каждую страницу мне лень :). Поэтому только мой вариант.
    // Также тут нужны WireMock тесты,
    // а меня вообще никто не просил писать тесты на все,
    // поэтому WireMock пока тоже не будет, но если сильно надо, напишу.
    private static String connectByHttpClient(String page) throws IOException, InterruptedException {
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("Accept", "Returns anything that is passed to request")
                    .uri(URI.create(BASE_URL + page))
                    .build();
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        }
        return response.body();
    }

    private static String deserializeResponse(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var node = mapper.readTree(json);
            var arrayHeaders = "headers";

            if (!node.isEmpty() && node.has(arrayHeaders)) {
                node = node.get(arrayHeaders);

                return node.get("Accept").asText();
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
