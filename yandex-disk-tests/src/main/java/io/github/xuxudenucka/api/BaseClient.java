package io.github.xuxudenucka.api;


import io.github.xuxudenucka.config.ApiConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BaseClient {

    protected final HttpClient httpClient;

    public BaseClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    protected HttpResponse<String> get(String path) {
        return sendRequest("GET", path, null);
    }

    protected HttpResponse<String> put(String path) {
        return sendRequest("PUT", path, null);
    }

    protected HttpResponse<String> post(String path) {
        return sendRequest("POST", path, null);
    }

    protected HttpResponse<String> delete(String path) {
        return sendRequest("DELETE", path, null);
    }

    private HttpResponse<String> sendRequest(String method, String path, String body) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(ApiConfig.API_URL + path));

            ApiConfig.DEFAULT_HEADERS.forEach(requestBuilder::header);
            if (body != null) {
                requestBuilder.method(method, HttpRequest.BodyPublishers.ofString(body));
            } else {
                requestBuilder.method(method, HttpRequest.BodyPublishers.noBody());
            }
            HttpRequest request = requestBuilder.build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 400) {
                System.err.println("Error Response Status: " + response.statusCode());
                System.err.println("Error Body: " + response.body());
            }
            return response;

        } catch (Exception e) {
            throw new RuntimeException("HTTP request failed: " + method + " " + path, e);
        }
    }
}