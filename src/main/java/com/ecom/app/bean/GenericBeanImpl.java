package com.ecom.app.bean;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ecom.app.dao.GenericDaoI;
import com.ecom.app.dao.GenericDaoImpl;

public abstract class GenericBeanImpl<T> implements GenericBeanI<T> {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private GenericDaoI<T> genericDao;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<T> list(Class<?> entity) {
        genericDao.setEm(em);
        return genericDao.list(entity);

    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<T> list(Class<?> entity, Map<String, Object> filters) {
        genericDao.setEm(em);

        if (filters != null && !filters.isEmpty()) {
            return genericDao.listWithFilters(entity, filters);
        } else {
            return genericDao.list(entity);
        }
    }
    @Override
    public void addOrUpdate(T entity) {
        genericDao.setEm(em);
        genericDao.addOrUpdate(entity);

    }
    public T findById(Class<T> entityClass, Long id) {
        genericDao.setEm(em);
        return em.find(entityClass, id);
    }


    @Override
    public void delete(Class<?> entityClass, Long id) {
        genericDao.setEm(em);
        genericDao.delete(entityClass, id);
    }

    public GenericDaoImpl<T> getDao() {
        genericDao.setEm(em);
        return (GenericDaoImpl<T>) genericDao;
    }

}