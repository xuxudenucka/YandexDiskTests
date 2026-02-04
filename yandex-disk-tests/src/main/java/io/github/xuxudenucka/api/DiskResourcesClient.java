package io.github.xuxudenucka.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpResponse;

public class DiskResourcesClient extends BaseClient {

    private static final String RESOURCES_PATH = "/disk/resources";
    private static final String COPY_PATH = "/disk/resources/copy";

    public HttpResponse<String> createFolder(String path) {
        return put(RESOURCES_PATH + "?path=" + encode(path));
    }

    public HttpResponse<String> getResource(String path) {
        return get(RESOURCES_PATH + "?path=" + encode(path));
    }

    public HttpResponse<String> deleteResource(String path) {
        return delete(RESOURCES_PATH + "?path=" + encode(path));
    }

    public HttpResponse<String> copyResource(String from, String to) {
        return post(
                COPY_PATH +
                        "?from=" + encode(from) +
                        "&path=" + encode(to)
        );
    }

    private String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}