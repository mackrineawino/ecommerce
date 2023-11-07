package com.ecom.utils;

import org.apache.commons.beanutils.Converter;
import com.ecom.app.model.entity.ProductCategory;

public class ProductCategoryConverter implements Converter {
    @Override
    public <T> T convert(Class<T> type, Object value) {
        if (type == ProductCategory.class) {
            if (value instanceof String) {
                String categoryString = (String) value;
                return type.cast(ProductCategory.valueOf(categoryString));
            }
        }
        return null;
    }
}
