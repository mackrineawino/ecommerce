package com.ecom.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.UserBeanI;
import com.ecom.app.bean.UserBeanImpl;
import com.ecom.app.model.entity.User;

@WebServlet("/user")
public class UserAction extends BaseAction {
    UserBeanI userBean = new UserBeanImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("./register.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User registerUser = new User();
        serializeForm(registerUser, req.getParameterMap());
    
        try {
            if (userBean.register(registerUser)) {
                req.setAttribute("registrationSuccess", "Registration successful! Proceed to login.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        RequestDispatcher dispatcher = req.getRequestDispatcher("./register.jsp");
        dispatcher.forward(req, resp);
    }
    
}
