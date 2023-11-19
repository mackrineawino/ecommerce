package com.ecom.app.bean;

import com.ecom.app.model.entity.User;
import java.sql.SQLException;

public interface AuthBeanI {
    User authenticate(User loginUser) throws SQLException;
}
