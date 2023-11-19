package com.ecom.app.bean;

import java.io.Serializable;

import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.database.PostGresDatabase;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthBeanImpl implements AuthBeanI, Serializable {

    public User authenticate(User loginUser) throws SQLException {

        PreparedStatement sqlStmt = PostGresDatabase.getInstance().getConnection()
                .prepareStatement("select id, username, userType from users where username=? and password=? limit 1");
        sqlStmt.setString(1, loginUser.getUsername());
        sqlStmt.setString(2, loginUser.getPassword());

        ResultSet result = sqlStmt.executeQuery();

        User user = new User();

        while (result.next()) {
            user.setId(result.getLong("id"));
            user.setUsername(result.getString("username"));
            // Add the UserType column to the User object
            user.setUserType(UserType.valueOf(result.getString("userType")));
        }

        return user;
    }
}
