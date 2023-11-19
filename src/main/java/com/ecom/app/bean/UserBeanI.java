package com.ecom.app.bean;

import com.ecom.app.model.entity.User;
import java.sql.SQLException;

public interface UserBeanI {
    boolean register(User user) throws SQLException;
    boolean deleteUser(User user);
}
