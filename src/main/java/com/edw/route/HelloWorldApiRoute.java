package com.edw.route;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

import java.util.HashMap;

/**
 * <pre>
 *     com.edw.route.HelloWorldApiRoute
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 20 Nov 2023 09:34
 */
@ApplicationScoped
public class HelloWorldApiRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().port(8080);

        rest("/api")
                .get("/hello/{name}")
                    .produces("application/json")
                    .routeId("hello-world-api")
                    .to("direct:hello-world")
                .get("/call/{url}")
                    .produces("text/plain")
                    .routeId("calling-third-party-api")
                    .to("direct:external-api-call");

        from("direct:hello-world")
                .log("start saying hello world to ${header.name}")
                .process(exchange -> {
                    exchange.getMessage().setBody(new HashMap(){{
                        put("hello", exchange.getIn().getHeader("name"));
                    }});
                    exchange.getMessage().getHeaders().clear();

                }).marshal().json();

        from("direct:external-api-call")
                .log("calling ${header.url}")
                .process(exchange -> {
                    exchange.getOut().setHeader("url", exchange.getIn().getHeader("url"));
                    exchange.getIn().getHeaders().clear();
                })
                .toD("${header.url}")
                .log("result is ${body}")
                .log("finish calling ${header.url}")
                .setHeader("whatever", constant("header"));
    }
}
