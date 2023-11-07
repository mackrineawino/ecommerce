package com.ecom.utils;

import org.apache.commons.beanutils.ConvertUtils;

import com.ecom.app.model.entity.ProductCategory;
import com.ecom.app.model.entity.UserType;

public class EnumConverter {
    public static void registerEnumConverters() {
        ConvertUtils.register(new ProductCategoryConverter(), ProductCategory.class);
        ConvertUtils.register(new UserTypeConverter(), UserType.class);
    }
}
