package com.ecom.app.bean;

import java.util.List;

import javax.inject.Inject;

import com.ecom.app.dao.GenericDaoI;
import com.ecom.app.dao.GenericDaoImpl;

public  abstract class GenericBeanImpl<T> implements GenericBeanI<T>{

    @Inject
    private GenericDaoI<T> genericDao;

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
    public void delete(Class<?> entityClass, Long id) {
        genericDao.delete(entityClass, id);
    }
    public GenericDaoImpl<T> getDao(){
        return (GenericDaoImpl<T>) genericDao;
    }

}