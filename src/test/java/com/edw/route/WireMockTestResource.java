package com.edw.route;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;


/**
 * <pre>
 *     com.edw.route.WireMockTestResource
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 21 Nov 2023 14:24
 */
public class WireMockTestResource implements QuarkusTestResourceLifecycleManager {
    private WireMockServer server;

    @Override
    public Map<String, String> start() {
        // Setup & start the server
        server = new WireMockServer(8082);
        server.start();
        server.stubFor(
                get(urlEqualTo("/mock"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"hello\": \"mock\"}")));
        return new HashMap<>();
    }

    @Override
    public void stop() {
        if (server != null) {
            server.stop();
        }
    }
}
