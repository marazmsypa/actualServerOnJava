package org.example.data.repositories;

import org.example.data.converter.ModelConverter;
import org.example.data.model.Subdivisions;
import org.example.data.model.VisitPurposes;
import org.example.database.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VisitPurposesRepository {
    private final Connection connection;

    private final ModelConverter<VisitPurposes> converter;

    public VisitPurposesRepository(ModelConverter<VisitPurposes> converter) throws SQLException {
        this.converter = converter;
        this.connection = Database.getInstance().getConnection();
    }

    public List<VisitPurposes> findALl() {
        List<VisitPurposes> empList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user1.visit_purposes");
            resultSet.first();
            do {
                VisitPurposes employees = converter.convert(resultSet);
                empList.add(employees);
            } while (resultSet.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return empList;
    }

}
