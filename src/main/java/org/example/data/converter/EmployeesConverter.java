package org.example.data.converter;

import org.example.data.model.Employees;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeesConverter extends ModelConverter<Employees> {
    @Override
    public Employees convert(ResultSet resultSet) {
        List<Field> fields = List.of(Employees.class.getDeclaredFields());
        Employees obj = new Employees();
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
