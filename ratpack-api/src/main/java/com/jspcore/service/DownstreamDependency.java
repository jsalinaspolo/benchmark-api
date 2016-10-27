package com.jspcore.service;

import ratpack.exec.Promise;
import ratpack.http.client.HttpClient;
import ratpack.http.client.ReceivedResponse;

import java.net.URI;
import java.net.URISyntaxException;

public class DownstreamDependency {

    private HttpClient httpClient;
    private URI uri;

    public DownstreamDependency(HttpClient httpClient) {
        this.httpClient = httpClient;
        try {
            this.uri = new URI("http://localhost:8089/slow-one-seconds");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public Promise<ReceivedResponse> retrieve() {
        return httpClient.get(uri);
    }


}
