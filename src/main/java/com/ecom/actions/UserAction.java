package com.ecom.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.UserBeanI;
import com.ecom.app.bean.UserBeanImpl;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.view.html.SignUpPage;

@WebServlet("/user")
public class UserAction extends BaseAction {
    UserBeanI userBean = new UserBeanImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new SignUpPage().renderSignup(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User registerUser = new User();
        serializeForm(registerUser, req.getParameterMap());
        userBean.register(registerUser);

        resp.sendRedirect("./");

    }
}
