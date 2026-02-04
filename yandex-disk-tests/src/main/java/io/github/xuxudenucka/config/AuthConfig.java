package io.github.xuxudenucka.config;

public class AuthConfig {

    private static final String TOKEN_ENV = "YANDEX_DISK_TOKEN";

    public static final String TOKEN = initToken();

    private static String initToken() {
        String token = System.getenv(TOKEN_ENV);

        if (token == null || token.isBlank()) {
            throw new IllegalStateException(
                    "Environment variable '" + TOKEN_ENV + "' is missing. " +
                            "Please set it in your system or IDE settings to run tests."
            );
        }
        return token;
    }

    private AuthConfig() {
        // utility class
    }
}