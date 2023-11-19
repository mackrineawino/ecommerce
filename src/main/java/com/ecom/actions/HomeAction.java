package com.ecom.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecom.app.bean.ProductBeanI;
import com.ecom.app.bean.ProductBeanImpl;
import com.ecom.app.model.entity.Product;
import com.ecom.app.model.view.html.HtmlCards;

@WebServlet("/home")
public class HomeAction extends BaseAction {

    ProductBeanI productBean = new ProductBeanImpl();
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> product = productBean.list(Product.class);
    String productItems = HtmlCards.generateCards(product);

    renderPage(req, resp, 3, productItems);

    }

}