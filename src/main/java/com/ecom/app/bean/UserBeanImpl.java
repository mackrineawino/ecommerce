package com.ecom.app.bean;

import java.io.Serializable;
import java.util.Random;
import com.ecom.app.model.entity.User;
import com.ecom.database.Database;

public class UserBeanImpl implements UserBeanI, Serializable {
    Database database = Database.getDbInstance();
    Random random = new Random();

    @Override
    public boolean register(User user) {
        if (user.getPassword().equals(user.getConfirmPassword())) {
            database.getData()
                    .add(new User(random.nextLong(), user.getUsername(), user.getPassword(), user.getUserType()));

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return true;
    }

}
