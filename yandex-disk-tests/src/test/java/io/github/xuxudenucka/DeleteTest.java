package io.github.xuxudenucka;

import io.github.xuxudenucka.api.DiskResourcesClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteTest {
    private final DiskResourcesClient client = new DiskResourcesClient();
    private static final String TEST_FOLDER = "/autotest-delete-folder";


    @BeforeEach
    void prepareData() {
        client.createFolder(TEST_FOLDER);
    }

    @AfterEach
    void cleanup() {
        client.deleteResource(TEST_FOLDER);
    }

    @Test
    void shouldDeleteFolder() {
        HttpResponse<String> response = client.deleteResource(TEST_FOLDER);


        assertTrue(response.statusCode() == 202 || response.statusCode() == 204,
                "Expected 202 or 204 on delete");


        HttpResponse<String> getAfterDelete = client.getResource(TEST_FOLDER);
        assertEquals(404, getAfterDelete.statusCode());
    }
}
