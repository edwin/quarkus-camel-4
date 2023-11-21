package com.edw.route;

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
public class HelloWorldApiRouteTest {

    @Test
    public void testHelloCallExternalAPI() {
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
