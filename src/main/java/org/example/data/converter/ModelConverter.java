package org.example.data.converter;

import java.sql.ResultSet;

public abstract class ModelConverter<T> {
    public abstract T convert(ResultSet resultSet);

}
