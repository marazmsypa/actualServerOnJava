package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.Employees;
import org.example.data.model.Subdivisions;
import org.example.data.repositories.EmployeeRepository;
import org.example.data.repositories.SubdivisionsRepository;

import java.util.List;

public class SubdivisionsController extends BaseController {

    private final SubdivisionsRepository repository;


    public SubdivisionsController(SubdivisionsRepository repository) {
        this.repository = repository;
    }


    public List<Subdivisions> getAll() {
        return repository.findALl();
    }


    @Override
    public void handle(HttpExchange exchange) {
        try {
            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/subdivisions") &&
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
