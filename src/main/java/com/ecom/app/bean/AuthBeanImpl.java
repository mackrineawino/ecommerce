package com.ecom.app.bean;

import java.io.Serializable;

import com.ecom.app.model.entity.User;
import com.ecom.database.Database;

public class AuthBeanImpl  implements AuthBeanI, Serializable {

    Database database = Database.getDbInstance();

    public User authenticate(User loginUser) {

        User userDetails = null;

        for (User user : database.getUsers()) {
            if (loginUser.getUsername().equals(user.getUsername())
                    && loginUser.getPassword().equals(user.getPassword())) {
                userDetails = user;
                

                break;

            }
        }

        return userDetails;
    }
}
