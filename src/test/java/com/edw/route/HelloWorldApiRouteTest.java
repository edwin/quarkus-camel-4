package com.edw.route;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


/**
 * <pre>
 *     com.edw.route.HelloWorldApiRouteTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 20 Nov 2023 12:09
 */
@QuarkusTest
@QuarkusTestResource(WireMockTestResource.class)
public class HelloWorldApiRouteTest {

    @Test
    public void testHelloCallExternalAPI() {
        given()
            .when()
                .get("/api/call/http%3A%2F%2Flocalhost%3A8082%2Fmock")
            .then()
                .statusCode(200)
                .body("hello", isA(String.class))
                .body("hello", equalTo("mock"))
            .log().all();
    }

    @Test
    public void testHelloAPI() {
        given()
            .when()
                .get("/api/hello/edwin")
            .then()
                .statusCode(200)
                .body("hello", isA(String.class))
                .body("hello", equalTo("edwin"))
            .log().all();
    }
}
