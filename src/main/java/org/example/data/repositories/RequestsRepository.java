package org.example.data.repositories;

import org.example.data.converter.ExtendedRequestsConverter;
import org.example.data.converter.ModelConverter;
import org.example.data.extendedModel.ExtendedRequests;
import org.example.data.model.Employees;
import org.example.data.model.Requests;
import org.example.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RequestsRepository {
    private static final String bigQuery = """
            SELECT employees.code AS "employees.code", employees.division_id AS "employees.division_id", employees.id AS "employees.id", employees.name AS "employees.name", employees.patronymic AS "employees.patronymic", employees.subdivision_id AS "employees.subdivision_id", employees.surname AS "employees.surname", request_statuses.id AS "requeststatuses.id", request_statuses.name AS "requeststatuses.name", request_types.id AS "requesttypes.id", request_types.name AS "requesttypes.name", requests.date_end AS "requests.date_end", requests.date_start AS "requests.date_start", requests.id AS "requests.id", requests.group_id AS "requests.group_id", requests.message AS "requests.message", requests.is_group AS "requests.is_group", visit_purposes.id AS "visitpurposes.id", visit_purposes.name AS "visitpurposes.name", visitors.birth_date AS "visitors.birth_date", visitors.email AS "visitors.email", visitors.id AS "visitors.id", visitors.image_path AS "visitors.image_path", visitors.is_in_black_list AS "visitors.is_in_black_list", visitors.black_list_reason AS "visitors.black_list_reason", visitors.login AS "visitors.login", visitors.name AS "visitors.name", visitors.note AS "visitors.note", visitors.organization AS "visitors.organization", visitors.passport_number AS "visitors.passport_number", visitors.passport_scan_path AS "visitors.passport_scan_path", visitors.passport_series AS "visitors.passport_series", visitors.password AS "visitors.password", visitors.patronymic AS "visitors.patronymic", visitors.phone AS "visitors.phone", visitors.surname AS "visitors.surname"
            FROM user1.requests
            inner join request_types on requests.request_type_id = request_types.id
            inner join request_statuses on requests.request_status_id = request_statuses.id
            inner join visit_purposes on requests.visit_purpose_id = visit_purposes.id
            inner join employees on requests.employee_id  = employees.id
            inner join visitors on requests.visitor_id  = visitors.id
            """;

    private final Connection connection;

    private final ModelConverter<Requests> converter;

    public RequestsRepository(ModelConverter<Requests> converter) throws SQLException {
        this.converter = converter;
        this.connection = Database.getInstance().getConnection();
    }

    public List<Requests> findALl() {
        List<Requests> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user1.requests");
            resultSet.first();
            do {
                Requests requests = converter.convert(resultSet);
                list.add(requests);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public List<ExtendedRequests> findALlWithPK() {
        ExtendedRequestsConverter exconverter = new ExtendedRequestsConverter() ;
        List<ExtendedRequests> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(bigQuery);
            resultSet.first();
            do {
                ExtendedRequests exRequests = exconverter.convert(resultSet);
                list.add(exRequests);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public List<ExtendedRequests> findALlWithPKAndUserInfo(Integer id) {
        List<ExtendedRequests> list = findALlWithPK();
        List<ExtendedRequests> sortedList = new ArrayList<>();
        for(ExtendedRequests ext : list){
            if (Objects.equals(ext.getVisitor().getId(), id)){
                sortedList.add(ext);
            }
        }
        return sortedList;
    }

    public void udate(Requests request) {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("UPDATE user1.requests " +
                    "SET request_type_id=" + request.getRequest_type_id() +
                    ", request_status_id=" + request.getRequest_status_id()+
                    ", date_start=" + dateConverter(request.getDate_start()) + ""+
                    ", date_end=" + dateConverter(request.getDate_end())  + "" +
                    ", visit_purpose_id=" + request.getVisit_purpose_id() +
                    ", employee_id=" + request.getEmployee_id() +
                    ", group_id=" + request.getGroup_id() +
                    ", visitor_id=" + request.getVisitor_id() +
                    ", is_group=" + request.isIs_group() +
                    ", message='" + request.getMessage() + "'"+
                    " WHERE id=" + request.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void postOne(Requests request){
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "INSERT INTO user1.requests" +
                            "(request_type_id, request_status_id, date_start, date_end, visit_purpose_id, employee_id, group_id, visitor_id, is_group, message)" +
                            "VALUES(" + request.getRequest_type_id() +
                            ", " + request.getRequest_status_id() +
                            ", " + dateConverter(request.getDate_start()) +
                            ", " + dateConverter(request.getDate_end()) +
                            ", " + request.getVisit_purpose_id() +
                            ", " + request.getEmployee_id() +
                            ", " +  request.getGroup_id() +
                            ", " + request.getVisitor_id() +
                            ", " +  request.isIs_group() +
                            ", '" + request.getMessage() +
                            "')"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String dateConverter(Date date){
        if (date == null) return null;
        StringBuilder builder = new StringBuilder();
        builder.append("'");
        Integer year = date.getYear() + 1900;
        builder.append(year.toString());
        builder.append("-");
        Integer month = date.getMonth() + 1;
        builder.append(month.toString().length() == 1 ? "0" + month.toString() : month.toString() );
        builder.append("-");
        Integer day = date.getDate();
        builder.append(day.toString().length() == 1 ? "0" + day.toString() : day.toString() );
        builder.append("'");
        return builder.toString();
    }
}
