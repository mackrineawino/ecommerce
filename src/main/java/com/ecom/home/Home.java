package com.ecom.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.app.bean.ProductBean;
import com.ecom.app.bean.ProductBeanImpl;


@WebServlet("/home")
public class Home extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductBean productBean = new ProductBeanImpl();

        PrintWriter print = resp.getWriter();

        print.write("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <style>\n" +
            // "div {\n" +
            // "  display: flex;\n" +
            // "  flex-basis: 31%;\n" +
            // "  background: #fff3f3;\n" +
            // "  border-radius: 10px;\n" +
            // "  margin-bottom: 5%;\n" +
            // "  padding: 20px 12px;\n" +
            // "  box-sizing: border-box;\n" +
            // "  height: 50px;\n" +
            // "}\n" +
            // "img {\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\n" +
            "<h2>Products</h2>\n");
        print.write(productBean.productList());
        print.write("\n" +
                "</body>\n" +
                "</html>");

    }
}