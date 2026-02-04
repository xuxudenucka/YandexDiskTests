package io.github.xuxudenucka;

import io.github.xuxudenucka.api.DiskResourcesClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostTest {
    private final DiskResourcesClient client = new DiskResourcesClient();
    private static final String SOURCE_FOLDER = "/autotest-post-source";
    private static final String COPY_FOLDER = "/autotest-post-copy";


    @BeforeEach
    void prepareData() {
        client.createFolder(SOURCE_FOLDER);
    }


    @Test
    void shouldCopyFolder() {
        HttpResponse<String> response = client.copyResource(SOURCE_FOLDER, COPY_FOLDER);

        assertTrue(response.statusCode() == 201 || response.statusCode() == 202,
                "Expected 201 or 202 for copy operation");
    }


    @AfterEach
    void cleanup() {
        client.deleteResource(SOURCE_FOLDER);
        client.deleteResource(COPY_FOLDER);
    }
}
