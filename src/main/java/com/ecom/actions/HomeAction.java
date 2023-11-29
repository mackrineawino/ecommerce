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
        for (Product product : products) {
            System.out.println(product.getId());
            System.out.println(product.getProductName());
        }
        String productItems = HtmlCards.generateCards(products);

        renderPage(req, resp, 3, productItems);

    }

}