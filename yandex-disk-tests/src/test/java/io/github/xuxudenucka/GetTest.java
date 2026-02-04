package io.github.xuxudenucka;

import io.github.xuxudenucka.api.DiskResourcesClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

public class GetTest {
    private final DiskResourcesClient client = new DiskResourcesClient();
    private static final String TEST_FOLDER = "/autotest-get-folder";


    @BeforeEach
    void prepareData() {
        client.createFolder(TEST_FOLDER);
    }


    @Test
    void shouldReturnFolderMetadata() {
        HttpResponse<String> response = client.getResource(TEST_FOLDER);


        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        assertTrue(response.body().contains("autotest-get-folder"));
        assertTrue(response.body().contains("dir"));
    }


    @AfterEach
    void cleanup() {
        client.deleteResource(TEST_FOLDER);
    }
}
