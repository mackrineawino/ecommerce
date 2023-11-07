package com.ecom.utils;

import org.apache.commons.beanutils.ConvertUtils;

import com.ecom.app.model.entity.ProductCategory;

public class EnumConverter {
    public static void registerEnumConverters() {
        ConvertUtils.register(new ProductCategoryConverter(), ProductCategory.class);
    }
}
