package com.ecom.actions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.bean.ProductBeanImpl;

@WebServlet("/about")
public class AboutAction extends BaseAction {
    ProductBeanI productBean = new ProductBeanImpl();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        renderPage(req, resp, 2, aboutInfo());

    }

    private String aboutInfo() {
        StringBuilder about = new StringBuilder();
        about.append("<h1 style=\"color: white;\">").append("Coming soon").append("<h1>>");

        return about.toString();
    }
}