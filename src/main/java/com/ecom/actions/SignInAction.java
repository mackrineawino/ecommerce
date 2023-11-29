package com.ecom.actions;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import com.ecom.app.bean.AuthBeanI;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class SignInAction extends BaseAction {
    private static final Logger LOGGER = Logger.getLogger(SignInAction.class.getName());
   @EJB
    AuthBeanI authBean;
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

        try {
            User userDetails = authBean.authenticate(loginUser);

            if (userDetails != null) {
                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute("loggedInId", String.valueOf(new Date().getTime()));
                httpSession.setAttribute("username", userDetails.getUsername());
                httpSession.setAttribute("userType", userDetails.getUserType().toString());
                httpSession.setAttribute("email", userDetails.getEmail());

                if (userDetails.getUserType().equals(UserType.ADMIN)) {
                    resp.sendRedirect("./addProduct");
                } else {
                    resp.sendRedirect("./home");
                }
            } else {
                req.setAttribute("loginError", "Incorrect Username or Password");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error during login authentication", e);
            req.setAttribute("loginError", "An unexpected error occurred. Please try again.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
