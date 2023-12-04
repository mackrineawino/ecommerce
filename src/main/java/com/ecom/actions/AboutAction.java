package com.ecom.actions;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.ecom.app.bean.ProductBeanI;

@WebServlet("/about")
public class AboutAction extends BaseAction {
   @EJB
    ProductBeanI productBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 2, aboutInfo());

    }

    private String aboutInfo() {
        StringBuilder about = new StringBuilder();
        about.append("<h1 style=\"color: white;\">").append("Coming soon").append("<h1>>");

        return about.toString();
    }
}