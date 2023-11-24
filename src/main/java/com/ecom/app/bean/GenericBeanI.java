package com.ecom.app.bean;

import java.io.Serializable;
import java.util.List;

public interface GenericBeanI<T> extends Serializable {

    List<T> list(Class<?> entity);

    void addOrUpdate(T entity);

    public void delete(Class<?> entityClass, Long id);

}
