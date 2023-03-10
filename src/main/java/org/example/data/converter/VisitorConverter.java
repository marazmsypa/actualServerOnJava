package org.example.data.converter;

import org.example.data.model.VisitPurposes;
import org.example.data.model.Visitors;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VisitorConverter extends ModelConverter<Visitors>{

    @Override
    public Visitors convert(ResultSet resultSet) {
        List<Field> fields = List.of(Visitors.class.getDeclaredFields());
        Visitors obj = new Visitors();
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
