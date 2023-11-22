package com.ecom.app.bean;
import com.ecom.app.model.entity.User;
import java.sql.SQLException;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote
public class UserBeanImpl extends GenericBeanImpl<User> implements UserBeanI {

    @Override
    public boolean register(User user) throws SQLException {
        if (!user.getPassword().equals(user.getConfirmPassword()))
        throw new RuntimeException("Password & confirm password do not match");

    //1. check if username already exist
    //2. hash password
    //3. initiate event to send email ...Observer design pattern

    getDao().addOrUpdate(user);

    return false;
    }




    @Override
    public boolean deleteUser(User user) {

        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }
    
}