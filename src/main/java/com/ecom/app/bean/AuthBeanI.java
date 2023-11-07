package com.ecom.app.bean;

import com.ecom.app.model.entity.User;

public interface AuthBeanI {
    User authenticate(User loginUser);
}
