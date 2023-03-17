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

        VisitPurposesConverter visitConverter = new VisitPurposesConverter();
        VisitPurposesRepository visitRepository = new VisitPurposesRepository(visitConverter);
        VisitPurposesController visitController = new VisitPurposesController(visitRepository);

        VisitorConverter visitorConverter = new VisitorConverter();
        VisitorRepository visitorRepository = new VisitorRepository(visitorConverter);
        VisitorsController visitorController = new VisitorsController(visitorRepository);

        VisitsConverter visisConverter = new VisitsConverter();
        VisitsRepository visitsRepository = new VisitsRepository(visisConverter);
        VisitsController visitsController = new VisitsController(visitsRepository);

        ImagesController imgController = new ImagesController();

        server.createContext("/employees", employeeController);
        server.createContext("/requests", requestsController);
        server.createContext("/request_types", requestsTypeController);
        server.createContext("/request_statuses", requestsStatController);
        server.createContext("/subdivisions", subdController);
        server.createContext("/visit_purposes", visitController);
        server.createContext("/visitors", visitorController);
        server.createContext("/visitor_images", imgController);
        server.createContext("/visits", visitsController);
        server.setExecutor(null);
        server.start();
    }


}