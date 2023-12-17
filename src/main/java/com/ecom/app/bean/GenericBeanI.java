package com.ecom.app.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericBeanI<T> extends Serializable {

    List<T> list(Class<?> entity);
    List<T> list(Class<?> entity, Map<String, Object> filters);
    T findById(Class<T> entityClass, Long id);
    void addOrUpdate(T entity);

    public void delete(Class<?> entityClass, Long id);

}
