package org.example.data.converter;

import org.example.data.model.Employees;
import org.example.data.model.RequestTypes;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestTypesConverter extends ModelConverter<RequestTypes>{
    @Override
    public RequestTypes convert(ResultSet resultSet) {
        List<Field> fields = List.of(RequestTypes.class.getDeclaredFields());
        RequestTypes obj = new RequestTypes();
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
