package com.jspcore;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class HttpServer {

    private final WireMockServer wireMockServer;

    public HttpServer() {
        this.wireMockServer = new WireMockServer(wireMockConfig().port(8089));
    }

    public void start() {
        wireMockServer.start();
        wireMockServer.stubFor(get(urlPathEqualTo("/slow-one-seconds")).willReturn(
                aResponse().withStatus(200).withBody("A bit slow").withFixedDelay(1000)));
        wireMockServer.stubFor(get(urlEqualTo("/slow-two-seconds")).willReturn(
                aResponse().withStatus(200).withBody("Slow").withFixedDelay(2000)));
        wireMockServer.stubFor(get(urlPathEqualTo("/slow-three-seconds")).willReturn(
                aResponse().withStatus(200).withBody("Very slow").withFixedDelay(3000)));
    }

    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
        httpServer.start();
    }
}
