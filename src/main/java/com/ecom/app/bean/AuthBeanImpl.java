package com.ecom.app.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.database.PostGresDatabase;

public class AuthBeanImpl implements AuthBeanI, Serializable {

    public User authenticate(User loginUser) {
        try (Connection connection = PostGresDatabase.getInstance().getConnection()) {
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT id, username, userType FROM users WHERE username=? AND password=? LIMIT 1")) {
                    preparedStatement.setString(1, loginUser.getUsername());
                    preparedStatement.setString(2, loginUser.getPassword());

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            User user = new User();
                            user.setId(resultSet.getLong("id"));
                            user.setUsername(resultSet.getString("username"));
                            user.setUserType(UserType.valueOf(resultSet.getString("userType")));
                            return user;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            // Handle the exception (log it, throw a custom exception, etc.)
            e.printStackTrace();
        }

        return null; // No matching user found or an exception occurred
    }
}
