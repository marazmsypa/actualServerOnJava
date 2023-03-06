package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.example.data.model.Employees;
import org.example.data.repositories.EmployeeRepository;

import java.util.List;

public class EmployeeController extends BaseController {
    private final EmployeeRepository repository;


    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }


    public List<Employees> getAll() {
        return repository.findALl();
    }

    public Employees findByCode(Integer code) {
        return repository.findByCode(code);
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/employees") &&
                    exchange.getRequestMethod().equals("GET") && exchange.getRequestURI().getQuery().length() == 0) {
                res = getAll();
                requestHandled = true;
            }

            if (exchange.getRequestURI().getPath().equals("/employees") &&
                    exchange.getRequestMethod().equals("GET") && exchange.getRequestURI().getQuery().contains("code")) {
                String[] strings = exchange.getRequestURI().getQuery().split("=");
                if (strings.length != 2) return;
                res = findByCode(Integer.parseInt(strings[1]));
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
