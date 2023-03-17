package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.RequestStatuses;
import org.example.data.model.RequestTypes;
import org.example.data.repositories.RequestStatusesReposiory;
import org.example.data.repositories.RequestTypesRepository;

import java.util.List;

public class RequestStatusesController extends BaseController{


    private final RequestStatusesReposiory repository;


    public RequestStatusesController(RequestStatusesReposiory repository) {
        this.repository = repository;
    }


    public List<RequestStatuses> getAll() {
        return repository.findALl();
    }



    @Override
    public void handle(HttpExchange exchange) {
        try {
            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/request_statuses") &&
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
