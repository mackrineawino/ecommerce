package com.ecom.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.bean.ProductBeanImpl;

@WebServlet("/home")
public class HomeAction extends BaseAction {

    ProductBeanI productBean = new ProductBeanImpl();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         renderPage(req, resp, 0, productBean.productList());
    }

}