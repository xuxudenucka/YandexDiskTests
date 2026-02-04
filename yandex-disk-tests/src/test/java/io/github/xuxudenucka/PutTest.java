package io.github.xuxudenucka;

import io.github.xuxudenucka.api.DiskResourcesClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PutTest {
    private final DiskResourcesClient client = new DiskResourcesClient();
    private static final String TEST_FOLDER = "/autotest-put-folder";


    @Test
    void shouldCreateFolder() {
        HttpResponse<String> response = client.createFolder(TEST_FOLDER);


        assertTrue(response.statusCode() == 201 || response.statusCode() == 409,
                "Expected 201 (created) or 409 (already exists)");
    }


    @AfterEach
    void cleanup() {
        client.deleteResource(TEST_FOLDER);
    }
}
