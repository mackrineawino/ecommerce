package com.ecom.app.bean;

import com.ecom.app.model.entity.User;

public interface UserBeanI {
    boolean register(User user);
    boolean deleteUser(User user);
}
