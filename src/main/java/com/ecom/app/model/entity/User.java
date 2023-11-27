package com.ecom.app.model.entity;


import com.ecom.app.model.view.html.DbTableAnnotation;
import com.ecom.app.model.view.html.DbTableColAnnotation;

@DbTableAnnotation(name="users")
public class User extends BaseEntity{

    @DbTableColAnnotation(name="username")
    private String username;

    @DbTableColAnnotation(name="email")
    private String email;

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


    public User(Long id, String username, String email, String password, UserType userType) {
        setId(id);
        this.username = username;
        this.email=email;
        this.password = password;
        this.userType = userType;
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


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
