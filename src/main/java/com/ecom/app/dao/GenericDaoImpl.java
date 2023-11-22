package com.ecom.app.dao;


import java.util.List;

import com.ecom.database.PostGresDatabase;

public class GenericDaoImpl<T> implements GenericDaoI<T> {

    @SuppressWarnings({"unchecked"})
    @Override
    public List<T> list(Class<?> entity) {
        return  (List<T>) PostGresDatabase.select(entity);

    }

    @Override
    public void addOrUpdate(T entity) {
        PostGresDatabase.insert(entity);

    }

    @Override
    public void delete(T entity) {

    }
}
