package org.example.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.Requests;
import org.example.data.model.VisitPurposes;
import org.example.data.model.Visitors;
import org.example.data.repositories.VisitPurposesRepository;
import org.example.data.repositories.VisitorRepository;

import java.io.InputStream;
import java.util.List;

public class VisitorsController extends BaseController {
    private final VisitorRepository repository;


    public VisitorsController(VisitorRepository repository) {
        this.repository = repository;
    }


    public List<Visitors> getAll() {
        return repository.findALl();
    }

    public Visitors getOne(String passData) {
        return repository.findByPassportData(passData);
    }

    public Visitors getOneByLoginData(String loginData) {
        return repository.findByLoginData(loginData);
    }

    private final ObjectMapper mapper = new ObjectMapper();

    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().set("Access-Control-Allow-Methods","GET,POST,PUT");
                exchange.getResponseHeaders().set("Access-Control-Allow-Headers","Content-Type");
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/visitors") &&
                    exchange.getRequestMethod().equals("GET") && exchange.getRequestURI().getQuery() == null) {
                res = getAll();
                requestHandled = true;
            }

            if (exchange.getRequestURI().getPath().equals("/visitors") &&
                    exchange.getRequestMethod().equals("GET") && exchange.getRequestURI().getQuery() != null) {
                if (exchange.getRequestURI().getQuery().contains("passport_data")){
                    String[] strings = exchange.getRequestURI().getQuery().split("=");
                    if (strings.length != 2) return;
                    res = getOne(strings[1]);
                    requestHandled = true;
                }

            }

            if (exchange.getRequestURI().getPath().equals("/visitors") &&
                    exchange.getRequestMethod().equals("GET") && exchange.getRequestURI().getQuery() != null) {
                if (exchange.getRequestURI().getQuery().contains("login_data")){
                    String[] strings = exchange.getRequestURI().getQuery().split("=");
                    if (strings.length != 2) return;
                    res = getOneByLoginData(strings[1]);
                    requestHandled = true;
                }

            }

            if (exchange.getRequestURI().getPath().equals("/visitors") &&
                    exchange.getRequestMethod().equals("POST")) {
                InputStream in = exchange.getRequestBody();

                repository.postOne(mapper.readValue(in, Visitors.class));
                res = "Query success";
                requestHandled = true;
            }

            if (exchange.getRequestURI().getPath().equals("/visitors") &&
                    exchange.getRequestMethod().equals("PUT")) {
                InputStream in = exchange.getRequestBody();

                repository.update(mapper.readValue(in, Visitors.class));
                res = "Query success";
                requestHandled = true;
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
