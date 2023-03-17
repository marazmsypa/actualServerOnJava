package org.example.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.Visits;
import org.example.data.repositories.VisitsRepository;

import java.io.InputStream;
import java.util.List;

public class VisitsController extends BaseController{
    private final VisitsRepository repository;


    public VisitsController(VisitsRepository repository) {
        this.repository = repository;
    }


    public List<Visits> getAll() {
        return repository.findALl();
    }

    public Visits getOne(String id) {
        return repository.findOneByRequestId(id);
    }


    private final ObjectMapper mapper = new ObjectMapper();

    {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/visits") &&
                    exchange.getRequestMethod().equals("GET") && exchange.getRequestURI().getQuery() == null) {
                res = getAll();
                requestHandled = true;
            }

            if (exchange.getRequestURI().getPath().equals("/visits") &&
                    exchange.getRequestMethod().equals("GET") && exchange.getRequestURI().getQuery() != null) {
                if (exchange.getRequestURI().getQuery().contains("id")){
                    String[] strings = exchange.getRequestURI().getQuery().split("=");
                    if (strings.length != 2) return;
                    res = getOne(strings[1]);
                    requestHandled = true;
                }

            }

            if (exchange.getRequestURI().getPath().equals("/visits") &&
                    exchange.getRequestMethod().equals("POST")) {
                InputStream in = exchange.getRequestBody();

                repository.postOne(mapper.readValue(in, Visits.class));
                res = "Query success";
                requestHandled = true;
            }

            if (exchange.getRequestURI().getPath().equals("/visits") &&
                    exchange.getRequestMethod().equals("PUT")) {
                repository.update(mapper.readValue(exchange.getRequestBody(), Visits.class));
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
