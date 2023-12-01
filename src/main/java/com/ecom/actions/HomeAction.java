package com.ecom.actions;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.view.html.HtmlCards;

@WebServlet("/home")
public class HomeAction extends BaseAction {
    @EJB
    ProductBeanI productBean;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productBean.list(Product.class);
        String productItems = HtmlCards.generateCards(products);
        String slider = HtmlCards.slider(products);

        String homeItems= slider+productItems;

        renderPage(req, resp, 0, homeItems);

    }

}