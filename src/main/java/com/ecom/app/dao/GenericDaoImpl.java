package com.ecom.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class GenericDaoImpl<T> implements GenericDaoI<T> {

    private EntityManager em;

    @SuppressWarnings({ "unchecked" })
    @Override
    public List<T> list(Class<?> entity) {
        String jpql = "FROM " + entity.getSimpleName() + " e";
    
        List<T> results = em.createQuery(jpql, (Class<T>) entity).getResultList();
    
        return results;
    }
    

    @Override
    public void addOrUpdate(T entity) {
        em.merge(entity);

    }

    @Override
    public void delete(Class<?> entityClass, Long id) {

        T entity = em.find((Class<T>) entityClass, id);

        if (entity != null) {
            em.remove(entity);
        } else {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
