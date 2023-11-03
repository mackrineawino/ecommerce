package com.ecom.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ecom.app.model.entity.User;
import com.ecom.app.model.entity.UserType;
import com.ecom.database.Database;

@WebServlet("/user")
public class UserAction extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
         PrintWriter print = resp.getWriter();
         print.write("<!DOCTYPE html>\n" + //
                 "<html>\n" + //
                 "    <head>\n" + //
                 "        <title>CoolStuff</title>\n" + //
                 "        <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" + //
                 "        <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" + //
                 "        <link\n" + //
                 "            href=\"https://fonts.googleapis.com/css2?family=Fira+Sans:wght@500&display=swap\"\n" + //
                 "            rel=\"stylesheet\">\n" + //
                 "        <style>\n" + //
                 "       \n" + //
                 "        body {\n" + //
                 "            font-family: 'Fira Sans', sans-serif;\n" + //
                 "            display: flex;\n" + //
                 "            justify-content: center;\n" + //
                 "            align-items: center;\n" + //
                 "            height: 100vh;\n" + //
                 "            margin: 0;\n" + //
                 "            background-position: center;\n" + //
                 "            background-size: cover;\n" + //
                 "            backdrop-filter: blur(2px);\n" + //
                 "            background-image: url(http://www.centreequestre-lugere.fr/lugere/fille/jordan-spizike-femme-pas-cher---1.jpg);\n" + //
                 "            \n" + //
                 "        }\n" + //
                 "\n" + //
                 "        .container {\n" + //
                 "            width: 400px;\n" + //
                 "            padding: 20px;\n" + //
                 "            border: 1px solid #ccc;\n" + //
                 "            background-color: white;\n" + //
                 "            text-align: center;\n" + //
                 "           margin: 0 auto;\n" + //
                 "           border-radius: 5px;\n" + //
                 "            \n" + //
                 "        }.container label {\n" + //
                 "            text-align: left; /* Align labels to the left */\n" + //
                 "            display: block; /* Make labels display as block elements for proper spacing */\n" + //
                 "        }\n" + //
                 ".container input{\n" + //
                 "    border-radius: 5px;\n" + //
                 "    border-color: #9FA6AE;\n" + //
                 "    \n" + //
                 "\n" + //
                 "}\n" + //
                 "\n" + //
                 "        input[type=\"text\"], input[type=\"password\"] {\n" + //
                 "            width: 100%;\n" + //
                 "            padding: 12px 20px;\n" + //
                 "            margin: 8px 0;\n" + //
                 "            box-sizing: border-box;\n" + //
                 "           \n" + //
                 "            \n" + //
                 "        }\n" + //
                 "       \n" + //
                 "\n" + //
                 "        button {\n" + //
                 "            background-color: #49A3C8;\n" + //
                 "            color: white;\n" + //
                 "            padding: 14px 20px;\n" + //
                 "            margin: 8px 0;\n" + //
                 "            border: none;\n" + //
                 "            cursor: pointer;\n" + //
                 "            width: 100%;\n" + //
                 "            margin-bottom: 5px;\n" + //
                 "            border-radius: 5px;\n" + //
                 "            font-size: 15px;\n" + //
                 "        }\n" + //
                 "         .container input::placeholder{\n" + //
                 "    color: #CECFD4;\n" + //
                 "}\n" + //
                 "        button:hover {\n" + //
                 "            background-color: #9FA6AE;\n" + //
                 "        }\n" + //
                 "        .container h2{\n" + //
                 "        \n" + //
                 "            \n" + //
                 "        }\n" + //
                 "    </style>\n" + //
                 "    </head>\n" + //
                 "    <body>\n" + //
                 "        <div class=\"container\">\n" + //
                 "            <h2 style=\"color: #49A3C8; font-size: 30px\">Welcome to <span\n" + //
                 "                    style=\"color: #E0588E;\"><span\n" + //
                 "                        style=\"font-size: 50px\">C</span>ool</span><span\n" + //
                 "                    style=\"font-size: 50px\">S</span>tuff</h2>\n" + //
                 "            <h3 style=\"color: #9FA6AE;\">Please Please SignUp</h3>\n" + //
                 "            <form action=\"./user\" method=\"POST\">\n" + //
                 "                <label for=\"username\" style=\"font-size: 14px\">Username</label>\n" + //
                 "                <input type=\"text\" id=\"username\" name=\"username\"\n" + //
                 "                    placeholder=\"kwach\" required>\n" + //
                 "\n" + //
                 "                <label for=\"password\" style=\"font-size: 14px\">Password</label>\n" + //
                 "                <input type=\"password\" id=\"password\" name=\"password\"\n" + //
                 "                    placeholder=\"  *****\" required>\n" + //
                 "                <label for=\"confirmPassword\" style=\"font-size: 14px\">Confirm\n" + //
                 "                    Password</label>\n" + //
                 "                <input type=\"password\" id=\"confirmPassword\"\n" + //
                 "                    name=\"confirmPassword\"\n" + //
                 "                    placeholder=\"  *****\" required>\n" + //
                 "\n" + //
                 "<div style=\"text-align: left;\">" +
                 "              <span  style=\"font-size: 14px;\">UserType:</span> <select name=\"userType\" id=\"userType\">\n" + //
                 "                    <option value=\"ADMIN\">ADMIN</option>\n" + //
                 "                    <option value=\"NORMAL_USER\">NORMAL USER</option>\n" + //
                 "                </select>\n" + //
                 "</div><br>" +
                 "\n" + //
                 "                <button type=\"submit\">Signup</button>\n" + //
                 "                <h5>Already have an account?<a href=\"./login\">Login</a></h5>\n" + //
                 "            </form>\n" + //
                 "        </div>\n" + //
                 "    </body>\n" + //
                 "</html>\n" + //
                 "");
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
