package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class BaseController implements HttpHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    public void writeResultToExchange(Object res, HttpExchange exchange) throws IOException {
        writeResultToExchange(res, exchange, 200);
    }

    public void writeResultToExchange(Object res, HttpExchange exchange, int code) throws IOException {
         String json = mapper
                .writeValueAsString(res);

        byte[] testResBytes = json
                .getBytes(StandardCharsets.UTF_8);

        OutputStream os = exchange.getResponseBody();

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers","x-prototype-version,x-requested-with");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods","GET,POST,PUT");

        exchange.sendResponseHeaders(code, testResBytes.length);

        os.write(testResBytes);

        os.flush();
        os.close();
        exchange.close();
    }
}

