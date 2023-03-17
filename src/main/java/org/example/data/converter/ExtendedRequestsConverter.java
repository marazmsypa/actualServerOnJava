package org.example.data.converter;

import org.example.data.extendedModel.ExtendedRequests;
import org.example.data.model.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExtendedRequestsConverter extends ModelConverter<ExtendedRequests>{
    @Override
    public ExtendedRequests convert(ResultSet resultSet) {
        List<Field> fields = List.of(ExtendedRequests.class.getDeclaredFields());
        ExtendedRequests obj = new ExtendedRequests();
        for (Field currentField : fields){
            currentField.setAccessible(true);
            try {
                String typeName = currentField.getGenericType().getTypeName();

                switch (typeName){
                    case "org.example.data.model.RequestTypes" :
                    case "org.example.data.model.RequestStatuses" :
                    case "org.example.data.model.Employees" :
                    case "org.example.data.model.VisitPurposes" :
                    case "org.example.data.model.Visitors":
                        currentField.set(obj, ConvertForeignClass.foreignObject(typeName, resultSet, Class.forName(typeName)));
                        break;
                    default:
                        currentField.set(obj, resultSet.getObject("requests." + currentField.getName()));
                }



            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return obj;
    }
}
