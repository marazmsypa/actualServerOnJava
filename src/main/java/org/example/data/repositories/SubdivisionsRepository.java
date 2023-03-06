package org.example.data.repositories;

import org.example.data.converter.ModelConverter;
import org.example.data.model.Employees;
import org.example.data.model.Subdivisions;
import org.example.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubdivisionsRepository {
    private final Connection connection;

    private final ModelConverter<Subdivisions> converter;

    public SubdivisionsRepository(ModelConverter<Subdivisions> converter) throws SQLException {
        this.converter = converter;
        this.connection = Database.getInstance().getConnection();
    }

    public List<Subdivisions> findALl() {
        List<Subdivisions> empList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user1.subdivisions");
            resultSet.first();
            do {
                Subdivisions employees = converter.convert(resultSet);
                empList.add(employees);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return empList;
    }
}
