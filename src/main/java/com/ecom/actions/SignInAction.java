package com.ecom.actions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import com.ecom.app.bean.AuthBeanI;
import com.ecom.app.bean.AuthBeanImpl;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/login")
public class SignInAction extends BaseAction {
    AuthBeanI authBean = new AuthBeanImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId")))
            resp.sendRedirect("./home");
        else
            resp.sendRedirect("./");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = new User();
        serializeForm(loginUser, req.getParameterMap());

        User userDetails;
        try {
            userDetails = authBean.authenticate(loginUser);
            if (userDetails != null) {
                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute("loggedInId", new Date().getTime() + "");
                httpSession.setAttribute("username", loginUser.getUsername());
                httpSession.setAttribute("userType", userDetails.getUserType().toString());
                if (userDetails.getUserType().equals(UserType.ADMIN)) {
                    resp.sendRedirect("./addProduct");
                } else {
                    resp.sendRedirect("./home");
                }
            } else {

                req.setAttribute("loginError", "Incorrect Username or Password");
            }

            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
