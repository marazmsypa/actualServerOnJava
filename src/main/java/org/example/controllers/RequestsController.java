package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.example.data.extendedModel.ExtendedRequests;
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

    public List<ExtendedRequests> getAllExtended() {
        return repository.findALlWithPK();
    }
    public List<ExtendedRequests> getAllUserExtended(Integer id) {
        return repository.findALlWithPKAndUserInfo(id);
    }

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void handle(HttpExchange exchange) {
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().set("Access-Control-Allow-Methods","GET,POST,PUT");
                exchange.getResponseHeaders().set("Access-Control-Allow-Headers","Content-Type,Authorization");
            }

            Object res = null;
            boolean requestHandled = false;

            if (exchange.getRequestURI().getPath().equals("/requests") &&
                    exchange.getRequestMethod().equals("GET")) {
                res = getAll();
                requestHandled = true;
            }

            if (exchange.getRequestURI().getPath().equals("/requests") &&
                    exchange.getRequestMethod().equals("PUT")) {
                repository.udate(mapper.readValue(exchange.getRequestBody(), Requests.class));
                res = "Query success";
                requestHandled = true;
            }

            if (exchange.getRequestURI().getPath().equals("/requests") &&
                    exchange.getRequestMethod().equals("POST")) {
                repository.postOne(mapper.readValue(exchange.getRequestBody(), Requests.class));
                res = "Query success";
                requestHandled = true;
            }

            String ex = exchange.getRequestURI().getQuery();

            if (exchange.getRequestURI().getPath().equals("/requests") &&
                    exchange.getRequestMethod().equals("GET") && (exchange.getRequestURI().getQuery() != null)) {
                if (exchange.getRequestURI().getQuery().contains("extend=true") && !exchange.getRequestURI().getQuery().contains("&")) {
                    res = getAllExtended();
                    requestHandled = true;
                }
            }

            if (exchange.getRequestURI().getPath().equals("/requests") &&
                    exchange.getRequestMethod().equals("GET") && (exchange.getRequestURI().getQuery() != null)) {
                if (exchange.getRequestURI().getQuery().contains("extend=true") && exchange.getRequestURI().getQuery().contains("id")) {
                    String[] split = exchange.getRequestURI().getQuery().split("&");
                    res = getAllUserExtended(Integer.parseInt(split[1].split("=")[1]));
                    requestHandled = true;
                }
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
