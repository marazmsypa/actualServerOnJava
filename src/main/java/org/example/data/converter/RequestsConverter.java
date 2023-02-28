package org.example.data.converter;

import org.example.data.model.Employees;
import org.example.data.model.Requests;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestsConverter extends ModelConverter {
    @Override
    public Requests convert(ResultSet resultSet) {
        List<Field> fields = List.of(Requests.class.getDeclaredFields());
        Requests obj = new Requests();
        for (Field currentField : fields){
            currentField.setAccessible(true);
            try {
                currentField.set(obj, resultSet.getObject(currentField.getName()));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return obj;
    }
}
