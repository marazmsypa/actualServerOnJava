package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.Subdivisions;
import org.example.data.model.VisitPurposes;
import org.example.data.repositories.SubdivisionsRepository;
import org.example.data.repositories.VisitPurposesRepository;

import java.util.List;

public class VisitPurposesController extends BaseController  {

    private final VisitPurposesRepository repository;


    public VisitPurposesController(VisitPurposesRepository repository) {
        this.repository = repository;
    }


    public List<VisitPurposes> getAll() {
        return repository.findALl();
    }


    @Override
    public void handle(HttpExchange exchange) {
        try {
            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/visit_purposes") &&
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
