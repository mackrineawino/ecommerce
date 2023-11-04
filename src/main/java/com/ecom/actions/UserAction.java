package com.ecom.actions;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.app.model.view.html.SignUpPage;
import com.ecom.database.Database;

@WebServlet("/user")
public class UserAction extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         new SignUpPage().renderSignup(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Database database = Database.getDbInstance();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String userTypeString=req.getParameter("userType");

        UserType userType = null;
         Random random = new Random();

        if (password.equals(confirmPassword))

        userType = UserType.valueOf(userTypeString);
            database.getUsers().add(new User(random.nextLong(), username, password, userType));

        resp.sendRedirect("./");


    }
}
