package com.ecom.app.model.view.html;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.model.view.css.AppCss;
import com.ecom.app.model.view.navbar.NavBar;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class AppPage implements Serializable {

        public void renderHtml(HttpServletRequest req, HttpServletResponse resp,
                        int activeMenu, String content) throws IOException {

                PrintWriter print = resp.getWriter();

                print.write("<!DOCTYPE html>" +
                                "<html>" +

                                "<head>" +
                                new AppCss().getStyle() +
                                " <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">" +
                                "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>" +
                                "<link href=\"https://fonts.googleapis.com/css2?family=Fira+Sans:wght@500&display=swap\" rel=\"stylesheet\">" +
                                "<style>" +
                                ".navbar { display: flex; align-items: center; }" +
                                ".navbar .logout-link { text-decoration: none; color: #49A3C8; font-size: 20px; cursor: pointer; margin-left: 20px; }"
                                +
                                "body { margin: 0; padding: 0px 50px; font-family: 'Fira Sans', sans-serif; background: black; }"+
                                "</style>" +
                                "</head>" +

                                "<body>" +
                                "<div style=\" display: flex; justify-content: space-between; background-color: #f1f1f1; border-radius: 5px;\n" + //
                                                "  z-index: 100; position: sticky; top: 0;\">" +
                                "<div style=\"margin-top: 12px; margin-left: 20px;\">" +
                                "<h2><a href=\"./home\" style=\"  text-decoration: none; font-size: 30px; cursor: pointer;\"><span style=\"color: #E0588E;\"><span style=\"font-size: 50px\">C</span>ool</span></span><span style=\"color: #49A3C8;\"><span style=\"font-size: 50px\">S</span>tuff</span></a></h2>" +

                                "</div>" +
                                "<div class=\"navbar\">"
                                + new NavBar().menu(activeMenu)
                                + "<a href=\"./logout\" class=\"logout-link\">LOGOUT</a>"
                                + "<a href=\"./addToCart\"><img style=\"width: 50px; color: #49A3C8; cursor: pointer; margin: 40px 0px; margin-left: 20px;\" src=\"https://github.com/mackrineawino/images/blob/main/image-removebg-preview%20(3).png?raw=true\"></a>"
                                + "</div>" +
                                "</div>"
                                //  "<h3 style=\" text-align: center; color: white; font-size: 30px; margin: 10px 0;\">Hello " + session.getAttribute("username") + "</h3>" +
                                //  "<h3 style=\"text-align: center; margin-top: 20px; margin-left: 20px; font-size: 25px; color: white;\">Shop Now</h3>"
                                 );

                print.write(content);
                print.write(
                        "<script src=\"js/AddToCart.js\"></script>\n" +
                        "<script src=\"js/DeleteFromCart.js\"></script>\n" +
                                "</body>" +
                                "</html>");

        }
}