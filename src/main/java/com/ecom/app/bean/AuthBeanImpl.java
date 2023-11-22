package com.ecom.app.bean;

import java.io.Serializable;
import java.util.List;

import com.ecom.app.model.entity.User;
import com.ecom.database.PostGresDatabase;


import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class AuthBeanImpl implements AuthBeanI, Serializable {

    public User authenticate(User loginUser) {
        try {
            List<User> users = PostGresDatabase.select(User.class);

            for (User user : users) {
                if (user.getUsername().equals(loginUser.getUsername()) && user.getPassword().equals(loginUser.getPassword())) {
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
