package com.ecom.app.model.view.html;

import javax.servlet.ServletContext;
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

                ServletContext ctx = req.getServletContext();

                PrintWriter print = resp.getWriter();

                print.write("<!DOCTYPE html>" +
                                "<html>" +

                                "<head>" +
                                new AppCss().getStyle() +
                                "</head>" +

                                "<body>" +
                                "<div style=\" display: flex; justify-content: space-between;\">" +
                                "<div>" +
                                "<h2><a href=\"./home\" style=\"margin: 0px 20px; text-decoration: none; font-size: 30px; cursor: pointer;\"><span style=\"color: #E0588E;\"><span\n"
                                + //
                                "                        style=\"font-size: 50px\">C</span>ool</span></span><span style=\"color: #49A3C8;\"><span\n"
                                + //
                                "                    style=\"font-size: 50px\">S</span>tuff</span></a><h2> " +
                                "</div>" +
                                "<div>" +
                                new NavBar().menu(activeMenu) +
                                "</div>" +
                                "<div>" +
                                "<a href=\"./cart\"><img style=\" width: 25px; cursor: pointer;  margin: 40px 20px;\" +\" src=\"https://sun1-97.userapi.com/s/v1/if1/0noPLWMBCqPv3zilDYqZ8ZuBQ4vugB8GNMnTXPAl_FV6mpzTDECSCkNLZnep-Y0dU_1KYXp-.jpg?size=201x201&quality=96&crop=25,22,201,201&ava=1\"><a/>"
                                +
                                "</div>" +
                                "</div>" +
                                "<br/>&nbsp;<br/>" +
                                "<h3>Welcome: " + session.getAttribute("username") + "</h3><br/>");

                print.write(content);
                print.write("<a href=\"./logout\">Logout</a>" +
                                "</body>" +
                                "</html>");

        }
}