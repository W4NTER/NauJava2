package edu.hw1.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class HTTPRequest {
    private final static String BASE_URL = "https://httpbin.org/";
    private final static String DISCLAIMER =
            "Не удалось обработать запрос, возможно введеные данные, не сортветствуют ожидаемым";

    public String getAnswer(String page) {
        String[] arrOfLink = page.split("/");
        return responseHandler(arrOfLink[arrOfLink.length - 1]);
    }

    private String connectByHttpClient(String page) throws IOException, InterruptedException {
        HttpResponse<String> response;
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Content-Type", "application/json")
                    .header("Accept", "Returns anything that is passed to request")
                    .uri(URI.create(String.format("%s/%s", BASE_URL, page)))
                    .build();
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        }
        return response.body();
    }


    private String responseHandler(String page) {
        String[] parseLink = page.split("/");
        String searchingPage = parseLink[parseLink.length - 1];
        try {
            switch (searchingPage) {
                case "ip" -> {
                    return deserializeResponse(connectByHttpClient(searchingPage), Ip.class);
                }
                case "user-agent" -> {
                    return deserializeResponse(connectByHttpClient(searchingPage), UserAgent.class);
                }
                case "headers" -> {
                    return deserializeResponse(connectByHttpClient(searchingPage), Headers.class);
                }
                case "anything" -> {
                    return deserializeResponse(Anything.class, connectByHttpClient(searchingPage));
                }
                case "get" -> {
                    return deserializeResponse(Get.class, connectByHttpClient(searchingPage));
                }
                default -> {
                    return DISCLAIMER;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> String deserializeResponse(String json, Class<T> deserializeClass) {
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            return mapper.readValue(json, deserializeClass).toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> String deserializeResponse(Class<T> deserializeClass, String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var node = mapper.readTree(json);
            var arrayHeaders = "headers";

            if (!node.isEmpty() && node.has(arrayHeaders)) {
                node = node.get(arrayHeaders);

                return mapper.treeToValue(node, deserializeClass).toString();
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
