package com.ecom.app.bean;

import java.io.Serializable;
import java.util.List;

import com.ecom.app.model.entity.User;
import com.ecom.utils.TextHash;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote
public class AuthBeanImpl implements AuthBeanI, Serializable {
  @PersistenceContext
    EntityManager em;
    @Inject
    private TextHash hashText;

    public User authenticate(User loginUser) {

        try {
            loginUser.setPassword(hashText.hash(loginUser.getPassword()));
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        List<User> users = em.createQuery("FROM User u WHERE u.password=:password AND u.username=:username", User.class)
        .setParameter("password", loginUser.getPassword())
        .setParameter("username", loginUser.getUsername())
        .getResultList();

        for (User user : users) {
            if (user.getUsername().equals(loginUser.getUsername())
                    && user.getPassword().equals(loginUser.getPassword())) {
                return user;
            }
        }

        return null;
    }
}
