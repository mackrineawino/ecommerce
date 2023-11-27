package com.ecom.app.bean;

import java.io.Serializable;
import java.util.List;

import com.ecom.app.model.entity.User;
import com.ecom.database.PostGresDatabase;
import com.ecom.utils.TextHash;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Remote
public class AuthBeanImpl implements AuthBeanI, Serializable {

    @Inject
    private TextHash hashText;

    public User authenticate(User loginUser) {

        try {
            loginUser.setPassword(hashText.hash(loginUser.getPassword()));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        List<User> users = PostGresDatabase.select(User.class);

        for (User user : users) {
            if (user.getUsername().equals(loginUser.getUsername())
                    && user.getPassword().equals(loginUser.getPassword())) {
                return user;
            }
        }

        return null;
    }
}
