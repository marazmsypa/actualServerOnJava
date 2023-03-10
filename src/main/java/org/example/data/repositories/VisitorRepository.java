package org.example.data.repositories;

import org.example.data.converter.ModelConverter;
import org.example.data.model.Employees;
import org.example.data.model.Visitors;
import org.example.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitorRepository {
    private final Connection connection;

    private final ModelConverter<Visitors> converter;

    public VisitorRepository(ModelConverter<Visitors> converter) throws SQLException {
        this.converter = converter;
        this.connection = Database.getInstance().getConnection();
    }

    public List<Visitors> findALl() {
        List<Visitors> empList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user1.visitors");
            resultSet.first();
            do {
                Visitors employees = converter.convert(resultSet);
                empList.add(employees);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return empList;
    }

    public Visitors findByPassportData(String passData) {
        Visitors vis = new Visitors();
        try (Statement statement = connection.createStatement()) {
            String passSeria = passData.substring(0, 4);
            String passNumber = passData.substring(4) ;
            ResultSet resultSet = statement.executeQuery( String.format("select * from user1.visitors where passport_series = \"%s\" and passport_number = \"%s\" limit 1", passSeria , passNumber));
            if (!resultSet.first()) return null;
            do {
                vis = converter.convert(resultSet);

            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vis;
    }

    public Visitors findByLoginData(String loginData) {
        Visitors vis = new Visitors();
        try (Statement statement = connection.createStatement()) {
            String[] loginDataArray = loginData.split("_");
            String login = loginDataArray[0];
            String password = loginDataArray[1] ;
            ResultSet resultSet = statement.executeQuery( String.format("select * from user1.visitors where login = \"%s\" and password = \"%s\" limit 1", login , password));
            if (!resultSet.first()) return null;
            do {
                vis = converter.convert(resultSet);

            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vis;
    }

    public void postOne(Visitors visitor){
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("INSERT INTO user1.visitors" +
                    "(surname, name, patronymic, phone, email, organization, note, birth_date, passport_series, passport_number, " +
                    "image_path, passport_scan_path, login, password, is_in_black_list)" +
                    "VALUES('" + visitor.getSurname() +
                    "', '" + visitor.getName() +
                    "', '" + visitor.getPatronymic() +
                    "', '" + visitor.getPhone() +
                    "', '" + visitor.getEmail() +
                    "', '" + visitor.getOrganization() +
                    "', '" + visitor.getNote() +
                    "', " + dateConverter(visitor.getBirth_date()) +
                    ", '" + visitor.getPassport_series() +
                    "', '" + visitor.getPassport_number() +
                    "', '" + visitor.getImage_path() +
                    "', '" + visitor.get_passport_scan_path() +
                    "', '" + visitor.getLogin() +
                    "', '" + visitor.getPassword() +
                    "', 0)");
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
