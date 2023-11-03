package com.ecom.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ecom.app.bean.ProductBean;
import com.ecom.app.bean.ProductBeanImpl;

@WebServlet("/home")
public class Home extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        if (StringUtils.isNotBlank((String) httpSession.getAttribute("loggedInId"))) {
            ServletContext ctx = getServletContext();
            ProductBean productBean = new ProductBeanImpl();

            PrintWriter print = resp.getWriter();

            print.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <style>\n" +
                    "div {\n" +
                    "  display: flex;\n" +
                    "  flex-basis: 44%;\n" +
                    "  background: #fff3f3;\n" +
                    "  border-radius: 10px;\n" +
                    "  margin-bottom: 5%;\n" +
                    "  padding: 20px 12px;\n" +
                    "  box-sizing: border-box;\n" +
                    "  height: 50px;\n" +
                    "  padding: 25px;\n" +
                    "}\n" +
                    "img {\n" +
                    "  height: 100px;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "Welcome: " + ctx.getAttribute("username") + "<br/>" +
                    ctx.getInitParameter("AppName") + "<br/>" +
                    "\n" +
                    "<h2>Products</h2>\n");
            print.write(productBean.productList());
            print.write("\n" +
                    "Server Info: " + ctx.getServerInfo() + "<br/>" +
                    "Application Deployment Location" + ctx.getRealPath(ctx.getContextPath()) + "<br/>" +
                    "<a href=\"./logout\">Logout</a>" +
                    "</body>\n" +
                    "</html>");
        }

        else
            resp.sendRedirect("./");
    }

}