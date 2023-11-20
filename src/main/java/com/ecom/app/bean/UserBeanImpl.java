package com.ecom.app.bean;

import java.io.Serializable;
import com.ecom.app.model.entity.User;
import com.ecom.database.PostGresDatabase;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.concurrent.ThreadLocalRandom;

public class UserBeanImpl implements UserBeanI, Serializable {

    @Override
    public boolean register(User user) throws SQLException {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            PostGresDatabase.insert(user);

            return true;
        }
        return false;
    }




    @Override
    public boolean deleteUser(User user) {

        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }
}