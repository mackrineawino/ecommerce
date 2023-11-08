package com.ecom.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.bean.ProductBeanImpl;
import com.ecom.app.model.view.html.AppPage;

@WebServlet("/viewMore")
public class ProductDescriptionAction extends HttpServlet {
   ProductBeanI productBean = new ProductBeanImpl();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        new AppPage().renderHtml(req, resp, 0, productBean.productList());

    }
}
