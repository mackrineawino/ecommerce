package com.ecom.app.bean;

import java.util.List;

import com.ecom.app.model.entity.Product;
import com.ecom.database.Database;

public class GenericBeanImpl<T> implements GenericBeanI<T>{

    @SuppressWarnings({"unchecked","rawtypes"})
    @Override
    public List<T> list(Class<?> entity) {
        return (List<T>) Database.getDbInstance().getData(entity);

    }

    @Override
    public void addOrUpdateProduct(T entity) {

        Database database = Database.getDbInstance();

        database.getData().add(entity);

    }

    @Override
    public void deleteProduct(Long productId) {
        // Database database = Database.getDbInstance();
        // List<T> dataList = database.getData();

    
        // dataList.removeIf(product -> ((Product) product).getId().equals(productId));
    }

}