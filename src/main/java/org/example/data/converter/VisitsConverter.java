package org.example.data.converter;

import org.example.data.model.VisitPurposes;
import org.example.data.model.Visits;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VisitsConverter extends ModelConverter<Visits>{
    @Override
    public Visits convert(ResultSet resultSet) {
        List<Field> fields = List.of(Visits.class.getDeclaredFields());
        Visits obj = new Visits();
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
