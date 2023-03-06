package org.example.data.repositories;

import org.example.data.converter.ModelConverter;
import org.example.data.model.RequestTypes;
import org.example.data.model.Subdivisions;
import org.example.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequestTypesRepository {

    private final Connection connection;

    private final ModelConverter<RequestTypes> converter;

    public RequestTypesRepository(ModelConverter<RequestTypes> converter) throws SQLException {
        this.converter = converter;
        this.connection = Database.getInstance().getConnection();
    }

    public List<RequestTypes> findALl() {
        List<RequestTypes> empList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user1.request_types");
            resultSet.first();
            do {
                RequestTypes employees = converter.convert(resultSet);
                empList.add(employees);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return empList;
    }
}
