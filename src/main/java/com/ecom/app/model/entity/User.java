package com.ecom.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.ecom.app.model.view.html.HtmlTableColHeader;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @HtmlTableColHeader(headerLabel = "Username")
    @Column(name = "username")
    private String username;

    @HtmlTableColHeader(headerLabel = "Email")
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "userType")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Transient
    private String confirmPassword;

    public User() {
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User(Long id, String username, String email, String password, UserType userType) {
        setId(id);
        this.username = username;
        this.email = email;
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
