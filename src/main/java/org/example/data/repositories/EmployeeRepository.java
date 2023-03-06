package org.example.data.repositories;

import org.example.data.converter.ModelConverter;
import org.example.data.model.Employees;
import org.example.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private final Connection connection;

    private final ModelConverter<Employees> converter;

    public EmployeeRepository(ModelConverter<Employees> converter) throws SQLException {
        this.converter = converter;
        this.connection = Database.getInstance().getConnection();
    }

    public List<Employees> findALl() {
        List<Employees> empList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user1.employees");
            resultSet.first();
            do {
                Employees employees = converter.convert(resultSet);
                empList.add(employees);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return empList;
    }

    public Employees findByCode(Integer code) {
        Employees emp = new Employees();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery( String.format("select * from user1.employees where code = %s", code));
            if (!resultSet.first()) return null;


            do {
               emp = converter.convert(resultSet);

            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return emp;
    }


}
