package org.example.data.repositories;

import org.example.data.converter.ModelConverter;
import org.example.data.model.Employees;
import org.example.data.model.Requests;
import org.example.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequestsRepository {

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
}
