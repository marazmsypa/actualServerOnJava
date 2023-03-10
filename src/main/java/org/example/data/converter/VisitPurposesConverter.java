package org.example.data.converter;

import org.example.data.model.Subdivisions;
import org.example.data.model.VisitPurposes;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VisitPurposesConverter extends ModelConverter<VisitPurposes>{

    @Override
    public VisitPurposes convert(ResultSet resultSet) {
        List<Field> fields = List.of(VisitPurposes.class.getDeclaredFields());
        VisitPurposes obj = new VisitPurposes();
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
