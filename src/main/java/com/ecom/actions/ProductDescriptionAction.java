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

@WebServlet(urlPatterns = { "/viewMore/*" })
public class ProductDescriptionAction extends BaseAction {
    @EJB
    ProductBeanI productBean;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println(req.getParameter("id"));
        Long productId = Long.parseLong(req.getParameter("id"));

        Product product = getProductById(productId);
        renderPage(req, resp, 4, product.displayProductDescription());

    }

    private Product getProductById(Long productId) {
        List<Product> products = productBean.list(Product.class);

        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }

        return null;
    }

}
