package org.example.data.converter;

import org.example.data.extendedModel.ExtendedRequests;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConvertForeignClass{
    public static <T> T foreignObject(String typeName, ResultSet resultSet, Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, SQLException {
        T newObj = tClass.getDeclaredConstructor().newInstance();
        List<Field> fields = List.of(tClass.getDeclaredFields());
        String classTypeName = typeName.substring(23).toLowerCase();
        for (Field currentField : fields){
            currentField.setAccessible(true);
            currentField.set(newObj, resultSet.getObject(classTypeName + "." + currentField.getName()));
        }
        return newObj;


    }
}
