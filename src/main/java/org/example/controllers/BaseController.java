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
        byte[] testResBytes = mapper
                .writeValueAsString(res)
                .getBytes(StandardCharsets.UTF_8);

        OutputStream os = exchange.getResponseBody();

        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(code, testResBytes.length);

        os.write(testResBytes);

        os.flush();
        os.close();
        exchange.close();
    }
}

