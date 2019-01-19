package com.main;
import com.reactive.handler.PersonHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import reactor.netty.http.server.HttpServer;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

public class WebFluxFunctionApp {

    public static void main(String[] args) throws IOException {
        HttpServer.create().port(8010).handle(new ReactorHttpHandlerAdapter(toHttpHandler(route(accept(APPLICATION_PROBLEM_JSON).and(GET("/person/{id}")),PersonHandler::list)))).bind().block();
        System.in.read();
    }


    }

