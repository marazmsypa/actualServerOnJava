package org.example.data.repositories;

import org.example.data.converter.ModelConverter;
import org.example.data.model.Visits;
import org.example.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitsRepository {
    private final Connection connection;

    private final ModelConverter<Visits> converter;

    public VisitsRepository(ModelConverter<Visits> converter) throws SQLException {
        this.converter = converter;
        this.connection = Database.getInstance().getConnection();
    }

    public List<Visits> findALl() {
        List<Visits> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user1.visits");
            resultSet.first();
            do {
                Visits visit = converter.convert(resultSet);
                list.add(visit);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public Visits findOneByRequestId(String id) {
        Visits visit = new Visits();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user1.visits where request_id = " + id + " limit 1");

            if (!resultSet.first()){
                return null;
            }

            visit = converter.convert(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return visit;
    }

    public void update(Visits visit){
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("UPDATE user1.visits " +
                    "SET request_id=" + visit.getId()+
                    ", visit_date=" +dateConverter(visit.getVisit_date()) +
                    ", visit_start_time=" +timeConverter(visit.getVisit_start_time()) +
                    ", visit_end_time=" + timeConverter(visit.getVisit_end_time())+
                    ", actual_visit_time=" + timeConverter(visit.getActual_visit_time()) +
                    ", subdivision_visit_end_time=" + timeConverter(visit.getSubdivision_visit_end_time()) +
                    ", subdivision_visit_start_time=" + timeConverter(visit.getSubdivision_visit_start_time()) +
                    " " +
                    "WHERE id=" + visit.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void postOne(Visits visit){
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "INSERT INTO user1.visits" +
                            "(request_id, visit_date, visit_start_time, visit_end_time, actual_visit_time, subdivision_visit_end_time, subdivision_visit_start_time)" +
                            "VALUES(" + visit.getRequest_id() +
                            ", " + dateConverter(visit.getVisit_date()) +
                            ", " + timeConverter(visit.getVisit_start_time())+
                            ", " + timeConverter(visit.getVisit_end_time()) +
                            ", " + timeConverter(visit.getActual_visit_time()) +
                            ", " + timeConverter(visit.getSubdivision_visit_end_time()) +
                            ", " + timeConverter(visit.getSubdivision_visit_start_time()) +
                            ")"
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

    private String timeConverter(Time time){
        if(time == null) return null;
        StringBuilder builder = new StringBuilder();
        builder.append("'");
        builder.append(time);
        builder.append("'");
        return builder.toString();
    }
}
