package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.controllers.*;
import org.example.data.converter.*;
import org.example.data.repositories.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

        EmployeesConverter employeesConverter = new EmployeesConverter();
        EmployeeRepository employeeRepository = new EmployeeRepository(employeesConverter);
        EmployeeController employeeController = new EmployeeController(employeeRepository);

        RequestsConverter requestsConverter = new RequestsConverter();
        RequestsRepository requestsRepository = new RequestsRepository(requestsConverter);
        RequestsController requestsController = new RequestsController(requestsRepository);

        RequestsStatusesConverter requestsStatConverter = new RequestsStatusesConverter();
        RequestStatusesReposiory requestsStatRepository = new RequestStatusesReposiory(requestsStatConverter);
        RequestStatusesController requestsStatController = new RequestStatusesController(requestsStatRepository);

        RequestTypesConverter requestsTypesConverter = new RequestTypesConverter();
        RequestTypesRepository requestsTypeRepository = new RequestTypesRepository(requestsTypesConverter);
        RequestTypesController requestsTypeController = new RequestTypesController(requestsTypeRepository);

        SubdivisionsConverter subdConverter = new SubdivisionsConverter();
        SubdivisionsRepository subdRepository = new SubdivisionsRepository(subdConverter);
        SubdivisionsController subdController = new SubdivisionsController(subdRepository);

        server.createContext("/employees", employeeController);
        server.createContext("/requests", requestsController);
        server.createContext("/request_types", requestsTypeController);
        server.createContext("/request_statuses", requestsStatController);
        server.createContext("/subdivisions", subdController);

        server.createContext("/api/hello", (exchange -> {
            if ("GET".equals(exchange.getRequestMethod())) {
                String respText = "Hello!";
                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
            exchange.close();
        }));

        server.setExecutor(null);
        server.start();
    }


}