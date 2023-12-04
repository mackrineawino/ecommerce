package com.ecom.app.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDaoI<T> extends Serializable {

    List<T> list(Class<?> entity);

    void addOrUpdate(T entity);

    void delete(Class<?> entityClass, Long id);

    EntityManager getEm();

    void setEm(EntityManager em);

}
