package com.ecom.actions.admin;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.view.html.HtmlView;

@WebServlet("/viewAll")
public class ViewAllItemsAction extends HttpServlet {
    @EJB
    ProductBeanI productBean;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productBean.list(Product.class);
        String productList = HtmlView.generateAdminTable(products);
        String addButton = HtmlView.addButton();
        String tableContent = addButton + productList;
        req.setAttribute("content", tableContent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("./app/adminPage.jsp");
        dispatcher.forward(req, resp);
    }

}
