package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.Employees;
import org.example.data.model.Requests;
import org.example.data.repositories.EmployeeRepository;
import org.example.data.repositories.RequestsRepository;

import java.util.List;

public class RequestsController extends BaseController{
    private final RequestsRepository repository;

    public RequestsController(RequestsRepository repository) {
        this.repository = repository;
    }


    public List<Requests> getAll() {
        return repository.findALl();
    }


    @Override
    public void handle(HttpExchange exchange) {
        try {
            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/requests") &&
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
