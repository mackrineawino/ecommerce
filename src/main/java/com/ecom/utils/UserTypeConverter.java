package com.ecom.utils;

import org.apache.commons.beanutils.Converter;
import com.ecom.app.model.entity.UserType;

public class UserTypeConverter implements Converter{
     @Override
    public <T> T convert(Class<T> type, Object value) {
        if (type == UserType.class) {
            if (value instanceof String) {
                String userTypeString = (String) value;
                return type.cast(UserType.valueOf(userTypeString));
            }
        }
        return null;
    }
}
