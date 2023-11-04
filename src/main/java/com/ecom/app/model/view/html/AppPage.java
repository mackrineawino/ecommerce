package com.ecom.app.model.view.html;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ecom.app.model.view.css.AppCss;
import com.ecom.app.model.view.navbar.NavBar;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class AppPage implements Serializable {

        public void renderHtml(HttpServletRequest req, HttpServletResponse resp,
                        int activeMenu, String content) throws IOException {

                HttpSession session = req.getSession();
                PrintWriter print = resp.getWriter();

                print.write("<!DOCTYPE html>" +
                                "<html>" +

                                "<head>" +
                                new AppCss().getStyle() +
                                "<style>" +
                                ".navbar { display: flex; align-items: center; }" + // Align items horizontally
                                ".navbar .logout-link { text-decoration: none; color: #49A3C8; font-size: 20px; cursor: pointer; margin-left: 20px; }"
                                + // Style the "Logout" link
                                "body { margin: 0; padding: 0;}"+
                                "</style>" +
                                "</head>" +

                                "<body>" +
                                "<div style=\" display: flex; justify-content: space-between;\">" +
                                "<div>" +
                                "<h2><a href=\"./home\" style=\"margin: 0px 20px; text-decoration: none; font-size: 30px; cursor: pointer;\"><span style=\"color: #E0588E;\"><span style=\"font-size: 50px\">C</span>ool</span></span><span style=\"color: #49A3C8;\"><span style=\"font-size: 50px\">S</span>tuff</span></a></h2>" +

                                "</div>" +
                                "<div class=\"navbar\">"
                                + new NavBar().menu(activeMenu)
                                + "<a href=\"./logout\" class=\"logout-link\">LOGOUT</a>"
                                + "<a href=\"./cart\"><img style=\"width: 25px; cursor: pointer; margin: 40px 20px;\" src=\"https://sun1-97.userapi.com/s/v1/if1/0noPLWMBCqPv3zilDYqZ8ZuBQ4vugB8GNMnTXPAl_FV6mpzTDECSCkNLZnep-Y0dU_1KYXp-.jpg?size=201x201&quality=96&crop=25,22,201,201&ava=1\"></a>"
                                + "</div>" +
                                "</div>" +
                                "<h3 style=\" font-size: 20px; margin: 10px 0;\">Hello " + session.getAttribute("username") + "</h3>" +
                                "<h3 style=\" margin-top: 50px; margin-left: 20px; font-size: 40px;\">Shop Now</h3>");

                print.write(content);
                print.write(
                                "</body>" +
                                "</html>");

        }
}