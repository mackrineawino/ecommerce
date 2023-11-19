package com.ecom.app.model.entity;

import java.io.Serializable;

import com.ecom.app.model.view.html.DbTableAnnotation;
import com.ecom.app.model.view.html.DbTableColAnnotation;

@DbTableAnnotation(name="users")
public class User implements Serializable {

     @DbTableColAnnotation(name="id",  definition = "int")
    private Long id;

    @DbTableColAnnotation(name="username")
    private String username;

    @DbTableColAnnotation(name="password")
    private String password;

    @DbTableColAnnotation(name="userType")
    private  UserType userType;
    
    private String confirmPassword;


    public User(){}


    public String getConfirmPassword() {
        return confirmPassword;
    }


    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


    public User(Long id, String username, String password, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
