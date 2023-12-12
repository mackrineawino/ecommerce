package com.ecom.app.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

public interface GenericDaoI<T> extends Serializable {

    List<T> list(Class<?> entity);

    List<T> listWithFilters(Class<?> entity, Map<String, Object> filters);

    void addOrUpdate(T entity);

    void delete(Class<?> entityClass, Long id);

    EntityManager getEm();

    void setEm(EntityManager em);

}
