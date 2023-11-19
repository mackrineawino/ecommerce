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
        PreparedStatement sqlStmt = PostGresDatabase.getInstance().getConnection()
                .prepareStatement("insert into users(id, username, userType, password) values(?,?,?,?)");

            sqlStmt.setInt(1, ThreadLocalRandom.current().nextInt(1, 1000));
            sqlStmt.setString(2, user.getUsername());
            sqlStmt.setObject(3, user.getUserType(), java.sql.Types.OTHER);
            sqlStmt.setString(4, user.getPassword());

            sqlStmt.executeUpdate();

            return true;
        }
        return false;
    }




    @Override
    public boolean deleteUser(User user) {

        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }
}