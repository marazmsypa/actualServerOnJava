package org.example.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.Visitors;
import org.example.data.repositories.VisitorRepository;

import java.io.InputStream;
import java.util.List;

public class ImagesController extends BaseController {
    public ImagesController() {
    }
    private final ObjectMapper mapper = new ObjectMapper();

    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            InputStream res = null;
            boolean requestHandled = false;
            if (exchange.getRequestURI().getPath().equals("/visitor_images") && exchange.getRequestMethod().equals("POST")) {
                res = exchange.getRequestBody();

                StringBuilder builder = new StringBuilder();

                int c;
                while ((c = res.read()) != -1) {
                    builder.append((char) c);
                }

                System.out.println(builder.toString());
            }

            if (requestHandled) {
                writeResultToExchange(res, exchange);
            } else {
                writeResultToExchange("404 not found", exchange, 404);
            }
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }

}
