package com.ecom.app.bean;

import java.util.List;

import com.ecom.app.dao.GenericDaoI;
import com.ecom.app.dao.GenericDaoImpl;

public  abstract class GenericBeanImpl<T> implements GenericBeanI<T>{

    private final GenericDaoI<T> genericDao = new GenericDaoImpl<>();

    @SuppressWarnings({"unchecked","rawtypes"})
    @Override
    public List<T> list(Class<?> entity) {
        return genericDao.list(entity);

    }

    @Override
    public void addOrUpdate(T entity) {

        genericDao.addOrUpdate(entity);

    }

    @Override
    public void delete(Long productId) {
    
    }
    public GenericDaoImpl<T> getDao(){
        return (GenericDaoImpl<T>) genericDao;
    }

}