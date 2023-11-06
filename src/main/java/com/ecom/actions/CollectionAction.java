package com.ecom.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ecom.app.bean.ProductBean;
import com.ecom.app.bean.ProductBeanImpl;
import com.ecom.app.model.view.html.AppPage;

@WebServlet("/collection")
public class CollectionAction extends HttpServlet{
    ProductBean productBean = new ProductBeanImpl();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        new AppPage().renderHtml(req, resp, 0, productBean.productList());

    }

}
