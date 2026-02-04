package io.github.xuxudenucka.config;

import java.util.Map;

public class ApiConfig {

    public static final String BASE_URL = "https://cloud-api.yandex.net";
    public static final String API_VERSION = "/v1";

    public static final String API_URL = BASE_URL + API_VERSION;

    public static final Map<String, String> DEFAULT_HEADERS = Map.of(
            "Authorization", "OAuth " + AuthConfig.TOKEN,
            "Accept", "application/json",
            "Content-Type", "application/json"
    );

    private ApiConfig() {
        // utility class
    }
}