package com.ecom.app.bean;

import com.ecom.app.model.entity.User;


public interface UserBeanI extends GenericBeanI<User>{
    void addOrUpdate(User user) ;
}
