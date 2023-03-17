package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.RequestTypes;
import org.example.data.model.Subdivisions;
import org.example.data.repositories.RequestTypesRepository;
import org.example.data.repositories.SubdivisionsRepository;

import java.util.List;

public class RequestTypesController extends BaseController{


    private final RequestTypesRepository repository;


    public RequestTypesController(RequestTypesRepository repository) {
        this.repository = repository;
    }


    public List<RequestTypes> getAll() {
        return repository.findALl();
    }


    @Override
    public void handle(HttpExchange exchange) {
        try {
            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/request_types") &&
                    exchange.getRequestMethod().equals("GET")) {
                res = getAll();
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
