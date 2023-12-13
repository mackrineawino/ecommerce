package com.ecom.app.bean;

import com.ecom.app.model.entity.User;
import com.ecom.utils.TextHash;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class UserBeanImpl extends GenericBeanImpl<User> implements UserBeanI {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private TextHash hashText;


    @Override
    public void addOrUpdate(User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new RuntimeException("Password & confirm password do not match");
        }
    
        List<User> existingUsers = list(User.class);
        for (User existingUser : existingUsers) {
            System.out.println("Checking user: " + existingUser);
            if (existingUser.getUsername().equals(user.getUsername())
                    || existingUser.getEmail().equals(user.getEmail())) {
                throw new RuntimeException("User with the same username or email already exists!");
            }
        }
    
        try {
            user.setPassword(hashText.hash(user.getPassword()));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    
        // Call the addOrUpdate method from the superclass
        super.addOrUpdate(user);
    }



}